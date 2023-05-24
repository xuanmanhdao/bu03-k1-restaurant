import axios from "axios";
import { base_url } from "../../utils/baseUrl";
import { config } from "../../utils/axiosconfig";

const getStaffList = async () => {
  const response = await axios.get(`${base_url}user/owner/list`, config);
  return response.data.data;
};

const createStaff = async (data) => {
  const response = await axios.post(`${base_url}user/owner/add`, data, config);
  return response.data.data;
};

const updateStaff = async (data) => {
  const response = await axios.put(
    `${base_url}user/owner/update/${data.id}`,
    data,
    config
  );
  return response.data.data;
};

const removeStaff = async (id) => {
  const response = await axios.delete(
    `${base_url}user/owner/delete/${id}`,
    config
  );
  return response.data.data;
};

const getStaffById = async (id) => {
  const response = await axios.get(`${base_url}user/owner/get/${id}`, config);
  return response.data.data;
};
const StaffService = {
  getStaffList,
  createStaff,
  updateStaff,
  removeStaff,
  getStaffById,
};
export default StaffService;
