import jwt_decode from "jwt-decode";

export const decodeToken = () => {
  const getTokenFromLocalStorage = localStorage.getItem("user")
    ? JSON.parse(localStorage.getItem("user"))
    : null;
  if (getTokenFromLocalStorage) {
    const decoded = jwt_decode(getTokenFromLocalStorage.token);
    return decoded;
  } else {
    return null;
  }
};