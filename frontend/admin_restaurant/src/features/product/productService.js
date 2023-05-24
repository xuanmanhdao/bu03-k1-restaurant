import axios from "axios";
import { config } from "../../utils/axiosconfig";
import { base_url } from "../../utils/baseUrl";

const getProducts = async () => {
  const response = await axios.get(`${base_url}product/?page=1&size=15`, config);

  return response.data.data.content;
};
const getProductsId = async (id) => {
  const response = await axios.get(`${base_url}product/staff/get-product${id}`, config);

  return response.data.data;
};
const createProduct = async (product) => {
  const response = await axios.post(`${base_url}product/owner/save`, product, config);

  return response.data;
};

const updateProduct = async (id, product) => {
  const response = await axios.put(`${base_url}product/staff/${id}`, product, config);

  return response.data;
};

const deleteProduct = async (productId) => {
  const response = await axios.delete(`${base_url}product/owner?ids=${productId}`, config)
  return response;
}

const editProduct = async (productId) => {
  const response = await axios.put(`${base_url}product/staff/${productId}`, config)
  return response;
}

const productService = {
  getProducts,
  getProductsId,
  createProduct,
  deleteProduct,
  editProduct,
  updateProduct,
};

export default productService;
