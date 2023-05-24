import { useFormik } from "formik";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import * as yup from "yup";
import { getStaffById, updateStaff } from "../features/staff/staffSlice";
import { getRoles } from "../features/role/roleSlice";

const schema = yup.object().shape({
  name: yup.string().required("Name is Required"),
  phone: yup
    .string()
    .matches(/^(08|09|03|05)[0-9]{8}$/, "Invalid phone number")
    .required("Phone is Required"),
  email: yup
    .string()
    .email("Invalid email address")
    .required("Email is Required"),
  password: yup.string().required("Password is Required"),
  confirmPassword: yup
    .string()
    .oneOf([yup.ref("password"), null], "Passwords must match")
    .required("Confirm Password is Required"),
  roles: yup.array().min(1, "At least one role must be selected"),
});

const EditStaff = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { id } = useParams();

  const [roles, setRoles] = useState([]);

  useEffect(() => {
    dispatch(getStaffById(id));
    dispatch(getRoles()).then((result) => {
      if (result.payload) {
        setRoles(result.payload);
      }
    });
  }, [dispatch, id]);

  const staff = useSelector((state) => state.staff.getStaffById);

  const formik = useFormik({
    initialValues: {
      name: staff?.name || "",
      phone: staff?.phone || "",
      email: staff?.email || "",
      password: "",
      confirmPassword: "",
      roleName: roles?.name || "",
    },
    validationSchema: schema,
    onSubmit: async (values) => {
      console.log(values);
      await dispatch(updateStaff({ id, ...values }));
      formik.resetForm();
      navigate("/admin/staffList");
    },
  });

  return (
    <div>
      <h3 className="mb-4 title">Edit Staff</h3>
      <form onSubmit={formik.handleSubmit}>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Enter Name
          </label>
          <input
            type="text"
            className="form-control"
            name="name"
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.name}
          />
          {formik.touched.name && formik.errors.name && (
            <div className="error">{formik.errors.name}</div>
          )}
        </div>
        <div className="mb-3">
          <label htmlFor="phone" className="form-label">
            Enter Phone
          </label>
          <input
            type="text"
            className="form-control"
            name="phone"
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.phone}
          />
          {formik.touched.phone && formik.errors.phone && (
            <div className="error">{formik.errors.phone}</div>
          )}
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Enter Email
          </label>
          <input
            type="email"
            className="form-control"
            name="email"
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.email}
          />
          {formik.touched.email && formik.errors.email && (
            <div className="error">{formik.errors.email}</div>
          )}
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Enter Password
          </label>
          <input
            type="password"
            className="form-control"
            name="password"
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.password}
          />
          {formik.touched.password && formik.errors.password && (
            <div className="error">{formik.errors.password}</div>
          )}
        </div>
        <div className="mb-3">
          <label htmlFor="confirmPassword" className="form-label">
            Confirm Password
          </label>
          <input
            type="password"
            className="form-control"
            name="confirmPassword"
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.confirmPassword}
          />
          {formik.touched.confirmPassword && formik.errors.confirmPassword && (
            <div className="error">{formik.errors.confirmPassword}</div>
          )}
        </div>
        <div className="mb-3">
          <label htmlFor="role" className="form-label">
            Select Role:
          </label>
          <select
            className="form-select"
            name="roleName"
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            value={formik.values.roles}
            multiple
          >
            {roles
              .filter((role) => role.name !== "CUSTOMER")
              .map((role) => (
                <option key={role.name} value={role.name}>
                  {role.name}
                </option>
              ))}
          </select>
          {formik.touched.roles && formik.errors.roles && (
            <div className="error">{formik.errors.roles}</div>
          )}
        </div>
        <button type="submit" className="btn btn-primary">
          Update Staff
        </button>
      </form>
    </div>
  );
};

export default EditStaff;
