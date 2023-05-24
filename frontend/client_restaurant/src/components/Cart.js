import { useEffect, useState } from "react";
import { Button, Col, Form, Image, ListGroup, Row } from "react-bootstrap";
import { AiFillDelete } from "react-icons/ai";
import { CartState } from "../service/product/productContext";
import { checkImage } from "../service/base/utils";
import cartService from "../service/cart/cartService";
import ModalNoti from "./ModalNoti";

const Cart = () => {
  const [imageUrl, setImageUrl] = useState("");
  const [total, setTotal] = useState();
  const [selectedItems, setSelectedItems] = useState([]);
  const [showDeleteButton, setShowDeleteButton] = useState(false);
  const [orderId, setOrderId] = useState(undefined)

  const [message, setMessage] = useState('')
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [address, setAddress] = useState('')

  const {
    state: { cart },
    dispatch,
  } = CartState();

  useEffect(() => {
    const fetchImageUrls = async () => {
      const checkedUrls = await Promise.all(
        cart.map((prod) => checkImage(prod.image))
      );
      setImageUrl(checkedUrls);
    };
    fetchImageUrls();

    if (selectedItems.length > 0) {
      setTotal(
        cart.reduce((acc, curr) => {
          if (selectedItems.includes(curr.id)) {
            return acc + Number(curr.price) * curr.qty;
          }
          return acc;
        }, 0)
      );
    } else {
      setTotal(0);
    }
  }, [cart, selectedItems]);

  useEffect(() => {
    const fetchData = async () => {
      const cart = await cartService.getItemCart();
      dispatch({ type: "SET_CART", payload: cart });
    };
    fetchData();
  }, [dispatch]);

  const handleItemSelect = (productId, product) => {
    let updatedSelectedItems;

    if (selectedItems.includes(productId)) {
      updatedSelectedItems = selectedItems.filter((id) => id !== productId);
    } else {
      updatedSelectedItems = [...selectedItems, productId];
    }

    setSelectedItems(updatedSelectedItems);

    if (updatedSelectedItems.length >= 1) {
      setShowDeleteButton(true);
    } else {
      setShowDeleteButton(false);
    }
  };


  console.log(selectedItems);

  const handleOrder = async () => {
    const orderData = { address, items: [] }
    const selected = cart.filter(c => selectedItems.includes(c.id))
    selected.map(p => {
      orderData.items.push({
        productId: p.id,
        quantity: p.qty
      })
    })
    const response = await cartService.order(orderData)
    const mess = response.data.message
    setIsModalOpen(true);
    if (mess === "Success") {
      setMessage(mess)
      setOrderId(response.data.data.id)
      dispatch({
        type: "REMOVE_FROM_CART",
        payload: selectedItems.map((itemId) => itemId),
      })
      setShowDeleteButton(false);
    }
    else
      setMessage(response.data.errorCodes[0].message)
  }

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };
  const handleAddress = (add)=>setAddress(add)

  return (
    <div className="home">
      <div className="productContainer">
        <ListGroup>
          {cart.map((prod, index) => (
            <ListGroup.Item key={prod.id}>
              <Row>
                <Col md={1}>
                  <Form.Check
                    type="checkbox"
                    checked={selectedItems.includes(prod.id)}
                    onChange={() => handleItemSelect(prod.id)}
                  />
                </Col>
                <Col md={2}>
                  <Image style={{ width: '500px', height: '150px', objectFit: 'cover' }}
                    src={imageUrl[index]}
                    alt={prod.name} fluid rounded />
                </Col>
                <Col md={2}>
                  <span>{prod.name}</span>
                </Col>
                <Col md={2}>{prod.price} VND</Col>

                <Col md={2}>
                  <Form.Control
                    as="select"
                    value={prod.qty}
                    onChange={(e) =>
                      dispatch({
                        type: "CHANGE_CART_QTY",
                        payload: {
                          id: prod.id,
                          qty: e.target.value,
                        },
                      })
                    }
                  >
                    {[...Array(prod.inStock).keys()].map((x) => (
                      <option key={x + 1}>{x + 1}</option>
                    ))}
                  </Form.Control>
                </Col>
                <Col md={2}>
                  <Button
                    type="button"
                    variant="light"
                    onClick={() =>
                      dispatch({
                        type: "REMOVE_FROM_CART",
                        payload: prod.id,
                      })
                    }
                  >
                    <AiFillDelete fontSize="20px" />
                  </Button>
                </Col>
              </Row>
            </ListGroup.Item>
          ))}
        </ListGroup>
        <div className="form-group">
          <ModalNoti isOpen={isModalOpen} onRequestClose={handleCloseModal} message={message} />
          <div id="orderId" hidden>{orderId}</div>
        </div>
      </div>
      <div className="filters summary">
        <span className="title">Subtotal ({selectedItems.length}) items</span>
        <span style={{ fontWeight: 700, fontSize: 20 }}>Total: {total} VND</span>
        <Button
          type="button"
          style={{ padding: "10px" }}
          disabled={selectedItems.length === 0 || address.trim().length === 0}
          onClick={handleOrder}
        >
          Proceed to Checkout
        </Button>
        {showDeleteButton && (
          <Button
            variant="danger"
            className="mt-3 mx-auto"
            style={{ padding: "10px" }}
            onClick={() =>
              dispatch({
                type: "REMOVE_FROM_CART",
                payload: selectedItems.map((itemId) => itemId),
              })
            }
          >
            Delete selected item
          </Button>
        )}
        <div style={{marginTop: "100px"}}>
          <input class="form-control" style={{height:"100px"}} placeholder="Enter your address..." onChange={e=>handleAddress(e.target.value)} />
        </div>
      </div>
    </div>
  );
};

export default Cart;
