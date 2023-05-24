import { useFormik } from "formik";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import * as yup from "yup";
import { createStaff, resetState } from "../features/staff/staffSlice";
import CustomInput from "../components/CustomInput";
let schema = yup.object().shape({
  name: yup.string().required("Name is Required"),
  phone: yup
    .string()
    .matches(/^(08|09|03|05)[0-9]{8}$/, "Invalid phone number")
    .required("Phone is Required"),
  email: yup
    .string()
    .email("Invalid email address") // <-- Kiểm tra xem địa chỉ email có hợp lệ không
    .required("Email is Required"),
  password: yup.string().required("Password is Required"),
  confirmPassword: yup
    .string()
    .oneOf([yup.ref("password"), null], "Passwords must match")
    .required("Confirm Password is Required"),
});

const AddStaff = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      name: "",
      phone: "",
      email: "",
      password: "",
      confirmPassword: "",
    },
    validationSchema: schema,
    onSubmit: async (values) => {
      try {
        await dispatch(createStaff(values));
        formik.resetForm();
        navigate("/admin/staffList");
      } catch (error) {
        if (error.errorCodes && error.errorCodes[0].code === 502) {
          const errorMessage = error.errorCodes[0].message;
          toast.error(errorMessage);
        } else {
          toast.error("Something went wrong!");
        }
      }
    },
  });
  return (
    <div>
      <h3 className="mb-4 title">Add Staff</h3>
      <div>
        <form
          onSubmit={formik.handleSubmit}
          className="d-flex gap-3 flex-column"
        >
          <CustomInput
            type="text"
            label="Enter Name"
            name="name"
            onChng={formik.handleChange("name")}
            onBlr={formik.handleBlur("name")}
            val={formik.values.name}
          />
          <div className="error">
            {formik.touched.name && formik.errors.name}
          </div>
          <CustomInput
            type="text"
            label="Enter Phone"
            name="phone"
            onChng={formik.handleChange("phone")}
            onBlr={formik.handleBlur("phone")}
            val={formik.values.phone}
          />
          <div className="error">
            {formik.touched.phone && formik.errors.phone}
          </div>
          <CustomInput
            type="text"
            label="Enter Email "
            name="email"
            onChng={formik.handleChange("email")}
            onBlr={formik.handleBlur("email")}
            val={formik.values.email}
          />
          <div className="error">
            {formik.touched.email && formik.errors.email}
          </div>

          <CustomInput
            type="password"
            label="Enter Password "
            name="password"
            onChng={formik.handleChange("password")}
            onBlr={formik.handleBlur("password")}
            val={formik.values.password}
          />
          <div className="error">
            {formik.touched.password && formik.errors.password}
          </div>

          <CustomInput
            type="password"
            label="Enter Confirm Password "
            name="confirmPassword"
            onChng={formik.handleChange("confirmPassword")}
            onBlr={formik.handleBlur("confirmPassword")}
            val={formik.values.confirmPassword}
          />
          <div className="error">
            {formik.touched.confirmPassword && formik.errors.confirmPassword}
          </div>
          <button
            className="btn btn-success border-0 rounded-3 my-5"
            type="submit"
          >
            Add Staff
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddStaff;
