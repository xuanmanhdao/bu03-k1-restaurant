import { FaShoppingCart, FaUser } from "react-icons/fa";
import { AiFillDelete } from "react-icons/ai";
import {
  Badge,
  Button,
  Container,
  Dropdown,
  FormControl,
  Nav,
  Navbar,
} from "react-bootstrap";
import { Link, useLocation, useHistory } from "react-router-dom";
import { CartState } from "../service/product/productContext";
import "../styles.css";
import { checkImage } from "../service/base/utils";
import React, { useEffect, useState } from "react";
import { decodeToken } from "../service/base/decodeToken";
import signoutService from "../service/signout/signoutService";

const Header = () => {
  const [imageUrl, setImageUrl] = useState("");
  const {
    state: { cart },
    dispatch,
    productDispatch,
  } = CartState();

  useEffect(() => {
    const fetchImageUrls = async () => {
      const checkedUrls = await Promise.all(
        cart.map((prod) => checkImage(prod.image))
      );
      setImageUrl(checkedUrls);
    };
    fetchImageUrls();
  }, [cart]);

  const [statusButtonLogin, setStatusButtonLogin] = useState(true);
  const [showDropdown, setShowDropdown] = useState(false);
  const handleDropdownToggle = () => setShowDropdown(!showDropdown);
  const [decodedToken, setDecodedToken] = useState({});
  const history = useHistory();

  useEffect(() => {
    const decoded = decodeToken();
    if (decoded) {
      setStatusButtonLogin(false);
      setDecodedToken(decoded);
    } else {
      setDecodedToken('');
    }
  }, []);
  const handleSignOut = () => {
    // Xử lý đăng xuất tại đây
    console.log("Da dang xuat");
    setStatusButtonLogin(true);
    signoutService.getSignout();
    history.push("/");
    window.location.reload();

  };

  return (
    <Navbar bg="dark" variant="dark" style={{ height: 80 }}>
      <Container>
        <Navbar.Brand>
          <Link to="/">Shopping Cart</Link>
        </Navbar.Brand>
        {useLocation().pathname.split("/")[1] !== "cart" && (
          <Navbar.Text className="search">
            <FormControl
              style={{ width: 500 }}
              type="search"
              placeholder="Search a product..."
              className="m-auto"
              aria-label="Search"
              onChange={(e) => {
                productDispatch({
                  type: "FILTER_BY_SEARCH",
                  payload: e.target.value,
                });
              }}
            />
          </Navbar.Text>
        )}
        <Nav>
          <Dropdown alignRight>
            <Dropdown.Toggle variant="success">
              <FaShoppingCart color="white" fontSize="25px" />
              <Badge>{decodedToken ? (cart.length) : 0}
              </Badge>
            </Dropdown.Toggle>
            <Dropdown.Menu style={{ minWidth: 370 }}>
              {decodedToken ? (
                <>
                  {cart.map((prod, index) => (
                    <span className="cartitem" key={prod.id}>
                      <img
                        src={imageUrl[index]}
                        className="cartItemImg"
                        alt={prod.name}
                      />
                      <div className="cartItemDetail">
                        <span>{prod.name}</span>
                        <span>{prod.price} VND</span>
                      </div>
                      <AiFillDelete
                        fontSize="20px"
                        style={{ cursor: "pointer" }}
                        onClick={() =>
                          dispatch({
                            type: "REMOVE_FROM_CART",
                            payload: prod.id,
                          })
                        }
                      />
                    </span>
                  ))}
                  <Link to="/cart">
                    <Button style={{ width: "95%", margin: "0 10px" }}>
                      Go To Cart
                    </Button>
                  </Link>
                </>
              ) : (
                <span style={{ padding: 10 }}>Cart is Empty!</span>
              )}
            </Dropdown.Menu>
          </Dropdown>
          {statusButtonLogin ? (
            <Link to="/login" style={{ margin: "0 30px" }}>
              <Button>Login/Register</Button>
            </Link>
          ) : (
            <Dropdown
              alignRight
              show={showDropdown}
              onToggle={handleDropdownToggle}
              style={{ margin: "0 30px" }}
            >
              <Dropdown.Toggle
                variant="light"
                id="user-dropdown"
                className="d-flex align-items-center"
              >
                <FaUser size={20} />
                <span className="ml-2">{decodedToken.name}</span>
              </Dropdown.Toggle>
              <Dropdown.Menu>
                <Dropdown.Item onClick={handleSignOut}>Sign out</Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          )}
        </Nav>
      </Container>
    </Navbar>
  );
};

export default Header;
