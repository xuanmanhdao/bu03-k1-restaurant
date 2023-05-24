import axios from "axios";
import { base_url } from "../../utils/baseUrl";
import { config } from "../../utils/axiosconfig";

const getAllCategories = async () => {
  const response = await axios.get(`${base_url}category/`, config);
  return response.data.data;
};
const getAllCategoriesDto = async () => {
  const response = await axios.get(`${base_url}category/all`, config);
  return response.data.data;
};
const createCategory = async (category) => {
  const response = await axios.post(`${base_url}category/owner`, category, config);

  return response.data;
};

const getCategoryById = async (id) => {
  const response = await axios.get(`${base_url}category/${id}`, config);

  return response.data.data;
};
const searchCategory = async ({type, des}) => {
  const response = await axios.get(`${base_url}category/search?type=${type}&des=${des}`, config);

  return response.data.data;
};


const deleteCategory = async (id) => {
  const response = await axios.delete(`${base_url}category/owner/${id}`, config);

  return response.data.data;
};
const updateCategory = async (id,category) => {
  console.log(category);
  const response = await axios.put(
    `${base_url}category/owner/${id}`, category,
    config
  );

  return response.data.data;
};
const CategoryService = {
  getAllCategories,
  getAllCategoriesDto,
  createCategory,
  getCategoryById,
  deleteCategory,
  searchCategory,
  updateCategory,
};

export default CategoryService;
