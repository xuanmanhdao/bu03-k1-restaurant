import axios from "axios";
import { base_url } from "../base/baseUrl";

const getRegister = async (user) => {
  const response = await axios.post(`${base_url}auth/register`, user);
  const registerFail = "errorCodes" in response.data;
  if (!registerFail) {
    localStorage.setItem("user", JSON.stringify(response.data.data));
  }
  return response.data;
};
const registerService = {
    getRegister,
};

export default registerService;
