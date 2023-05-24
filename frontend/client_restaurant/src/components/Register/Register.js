import React, { useState } from "react";
import registerService from "../../service/register/registerService";
import { useHistory, Link } from "react-router-dom";
import { FaLock } from "react-icons/fa";

export default function Register() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [name, setName] = useState();
  const [isError, setIsError] = useState(false);
  const [messageError, setMessageError] = useState("");
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const register = await registerService.getRegister({
      email,
      password,
      name,
    });
    console.log(register);
    const registerFail = "errorCodes" in register;
    if (!registerFail) {
      history.push("/");
    } else {
      console.log(register.errorCodes[0].message);
      setIsError(true);
      setMessageError(register.errorCodes[0].message);
    }
  };

  return (
    <div className="bg-light d-flex align-items-center">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-6 col-lg-4">
            <div className="card shadow">
              <div className="card-body">
                <h3 className="card-title mb-4">Register</h3>
                <div className="alert-danger text-center">
                  {isError ? messageError : ""}
                </div>
                <form onSubmit={handleSubmit}>
                  <div className="form-group">
                    <label htmlFor="name">Name</label>
                    <input
                      type="text"
                      className="form-control"
                      id="name"
                      placeholder="Enter name"
                      onChange={(e) => setName(e.target.value)}
                      required
                    />
                  </div>
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
                    Register
                  </button>
                </form>
                <hr />
                <p className="text-center">
                  Have an account?
                  <Link
                    to="/login"
                    style={{
                      color: "blue",
                      textDecoration: "underline",
                      backgroundColor: "yellow",
                      fontWeight: "bold",
                    }}
                  >
                    {" "}
                    Login
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
