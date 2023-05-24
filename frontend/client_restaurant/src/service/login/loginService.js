import axios from "axios";
import { base_url } from "../base/baseUrl";

const getLogin = async (user) => {
  const response = await axios.post(`${base_url}auth/login`, user);
  const loginFail = "errorCodes" in response.data;
  if (!loginFail) {
    localStorage.setItem("user", JSON.stringify(response.data.data));
  }
  return response.data;
};
const loginService = {
  getLogin,
};

export default loginService;
