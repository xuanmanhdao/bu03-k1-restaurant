import React, { useState } from "react";
import loginService from "../../service/login/loginService";
import { useHistory, Link } from "react-router-dom";
import { FaLock } from "react-icons/fa";

export default function Login() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [isError, setIsError] = useState(false);
  const [messageError, setMessageError] = useState("");
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const login = await loginService.getLogin({
      email,
      password,
    });
    console.log(login);
    const loginFail = "errorCodes" in login;
    if (!loginFail) {
      history.push("/");
      window.location.reload();

    } else {
      console.log(login.errorCodes[0].message);
      setIsError(true);
      setMessageError(login.errorCodes[0].message);
    }
  };

  return (
    <div className="bg-light d-flex align-items-center">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-6 col-lg-4">
            <div className="card shadow">
              <div className="card-body">
                <h3 className="card-title mb-4">Login</h3>
                <div className="alert-danger text-center">
                  {isError ? messageError : ""}
                </div>
                <form onSubmit={handleSubmit}>
                  <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input
                      type="email"
                      className="form-control"
                      id="username"
                      placeholder="Enter email"
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <div className="input-group">
                      <input
                        type="password"
                        className="form-control"
                        id="password"
                        placeholder="Password"
                        onChange={(e) => setPassword(e.target.value)}
                        required
                      />
                      <div className="input-group-append">
                        <button
                          className="btn btn-outline-secondary reveal"
                          type="button"
                        >
                          <FaLock
                            fontSize="20px"
                            style={{ cursor: "pointer" }}
                          />
                        </button>
                      </div>
                    </div>
                  </div>
                  <button type="submit" className="btn btn-primary btn-block">
                    Login
                  </button>
                </form>
                <hr />
                <p className="text-center">
                  Don't have an account?
                  <Link
                    to="/register"
                    style={{
                      color: "blue",
                      textDecoration: "underline",
                      backgroundColor: "yellow",
                      fontWeight: "bold",
                    }}
                  >
                    {" "}
                    Sign up
                  </Link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
