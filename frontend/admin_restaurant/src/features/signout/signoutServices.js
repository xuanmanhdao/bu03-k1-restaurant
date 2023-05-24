import axios from "axios";
import { base_url } from "../../utils/baseUrl";

export const signoutServices = async () => {
  const response = await axios.post(`${base_url}auth/logout`);
  localStorage.removeItem("user");
  return response.data;
};
