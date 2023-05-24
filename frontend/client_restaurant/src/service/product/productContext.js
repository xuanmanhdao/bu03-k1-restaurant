import { createContext, useContext, useReducer, useEffect, useState } from "react";
import { cartReducer, productReducer } from "../base/Reducers";
import { getProducts } from "./productService";
import cartService from "../cart/cartService";

const Cart = createContext();

const Context = ({ children }) => {
  const [isLoading, setIsLoading] = useState(true);

  async function fetchData() {

    const products = (await getProducts()).map((item) => ({
      id: item.id,
      name: item.name,
      price: item.price,
      image: item.image,
      inStock: item.quantity,
    }));
    dispatch({ type: 'SET_PRODUCTS', payload: products });
    setIsLoading(false);
  }

  useEffect(() => {
    fetchData();
  }, []);

  const [state, dispatch] = useReducer(cartReducer, {
    products: [],
    cart: [],
  });

  const [productState, productDispatch] = useReducer(productReducer, {
    byStock: false,
    searchQuery: "",
  });

  return (
    <Cart.Provider value={{ state, dispatch, productState, productDispatch }}>
      {isLoading ? ( // Render loading state while fetching data
        <div>Loading...</div>
      ) : (
        children // Render children when data has been fetched
      )}
    </Cart.Provider>
  );
};

export const CartState = () => {
  return useContext(Cart);
};

export default Context;
