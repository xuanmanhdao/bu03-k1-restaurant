import { Card, Button } from "react-bootstrap";
import { CartState } from "../service/product/productContext";
import { checkImage } from "../service/base/utils";
import React, { useEffect, useState } from "react";
import cartService from "../service/cart/cartService";
import { useHistory } from "react-router-dom";

const SingleProduct = ({ prod }) => {
  const [imageUrl, setImageUrl] = useState("");
  const history = useHistory();

  const {
    state: { cart },
    dispatch,
  } = CartState();

  useEffect(() => {
    checkImageUrl();
  }, [prod]);

  const checkImageUrl = async () => {
    const checkedUrl = await checkImage(prod.image);
    setImageUrl(checkedUrl);
  };

  useEffect(() => {
    const fetchData = async () => {
      const cart = await cartService.getItemCart();
      dispatch({ type: "SET_CART", payload: cart });
    };
    fetchData();
  }, [dispatch]);

  function addCart(data) {
    dispatch({
      type: "ADD_TO_CART",
      payload: data,
    });
    if (localStorage.getItem('user') === null)
      history.push("/login");
  }

  return (
    <div className="products">
      <Card>
        <Card.Img variant="top" src={imageUrl} alt={prod.name} />
        <Card.Body>
          <Card.Title>{prod.name}</Card.Title>
          <Card.Subtitle style={{ paddingBottom: 10 }}>
            <span> {prod.price} VND</span>
          </Card.Subtitle>
          {cart.some((p) => p.id === prod.id) ? (
            <Button
              variant="danger"
              onClick={() =>
                dispatch({
                  type: "REMOVE_FROM_CART",
                  payload: prod.id,
                })
              }
            >
              Remove from Cart
            </Button>
          ) : (
            <Button
              onClick={() =>
                addCart(prod)
              }
              disabled={!prod.inStock}
            >
              {!prod.inStock ? "Out of Stock" : "Add to Cart"}
            </Button>
          )}
        </Card.Body>
      </Card>
    </div >
  );
};

export default SingleProduct;
