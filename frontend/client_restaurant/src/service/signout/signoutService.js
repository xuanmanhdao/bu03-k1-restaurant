import axios from "axios";
import { base_url } from "../base/baseUrl";

const getSignout = async (user) => {
  await axios.post(`${base_url}auth/logout`);
  localStorage.removeItem("user");
};
const signoutService = {
  getSignout,
};

export default signoutService;
