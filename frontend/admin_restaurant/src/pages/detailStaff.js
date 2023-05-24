import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { getStaffById } from "../features/staff/staffSlice";
import { useFormik } from "formik";
import CustomInput from "../components/CustomInput";

const DetailStaff = () => {
  const dispatch = useDispatch();
  const { id } = useParams();

  useEffect(() => {
    dispatch(getStaffById(id));
  }, [dispatch, id]);

  const staff = useSelector((state) => state.staff.getStaffById);
  console.log(staff);
  const formik = useFormik({
    initialValues: {
      name: staff?.name || "",
      phone: staff?.phone || "",
      email: staff?.email || "",
      roles: staff?.roleName || [],
    },
  });
  return (
    <div>
      <h3 className="mb-4 title">Detail Staff</h3>
      <div>
        <form className="d-flex gap-3 flex-column">
          <CustomInput
            type="text"
            label="Name"
            name="name"
            val={formik.values.name}
            disabled="true"
          />
          <CustomInput
            type="text"
            label="Phone"
            name="phone"
            val={formik.values.phone}
            disabled="true"
          />
          <CustomInput
            type="text"
            label="Email "
            name="email"
            val={formik.values.email}
            disabled="true"
          />
          <p>ROLE:</p>
          <p>
            {formik.values.roles.length > 0
              ? formik.values.roles.map((role, index) => (
                  <li key={index}>{role}</li>
                ))
              : ""}
          </p>
        </form>
      </div>
    </div>
  );
};
export default DetailStaff;
