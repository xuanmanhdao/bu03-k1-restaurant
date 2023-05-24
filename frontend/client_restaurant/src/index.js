import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import ProductContext from "./service/product/productContext";

import "bootstrap/dist/css/bootstrap.min.css";
ReactDOM.render(
  <React.StrictMode>
    <ProductContext>
      <App />
    </ProductContext>
  </React.StrictMode>,
  document.getElementById("root")
);
