import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { deleteStaff, getStaffList } from "../features/staff/staffSlice";
import { Link } from "react-router-dom";
import { BiEdit, BiInfoCircle } from "react-icons/bi";
import { AiFillDelete } from "react-icons/ai";
import { Table } from "antd";

const columns = [
  {
    title: "Email",
    dataIndex: "email",
    sorter: (a, b) => a.name.localeCompare(b.name),
  },
  {
    title: "Name",
    dataIndex: "name",
    sorter: (a, b) => a.name.localeCompare(b.name),
  },
  {
    title: "Phone",
    dataIndex: "phone",
    sorter: (a, b) => a.phone.length - b.phone.length,
  },
  {
    title: "CreatedAt",
    dataIndex: "createdAt",
  },
  {
    title: "UpdatedAt",
    dataIndex: "updatedAt",
  },
  {
    title: "Action",
    dataIndex: "action",
  },
];

const Stafflist = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getStaffList());
  }, []);

  const staffState = useSelector((state) => state.staff.products);
  console.log(staffState);
  const [selectedStaff, setSelectedStaff] = useState(null); // Khai báo state selectedStaff
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);

  const handleEdit = (staff) => {
    setSelectedStaff(staff); // Lưu thông tin của nhân viên được chọn vào state selectedStaff
    setIsEditFormVisible(true);
  };
  const handleDelete = (staffId) => {
    const shouldDelete = window.confirm(
      `Bạn có chắc chắn muốn xóa nhân viên có ID ${staffId} không?`
    );
    if (shouldDelete) {
      // Gọi action deleteStaff và truyền vào staffId cần xóa
      dispatch(deleteStaff(staffId))
        .then(() => {
          // Nếu xóa thành công, cập nhật lại danh sách nhân viên
          dispatch(getStaffList());
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  let dataStaff = [];
  if (staffState && staffState.length > 0) {
    for (let i = 0; i < staffState.length; i++) {
      dataStaff.push({
        key: staffState[i].id,
        email: staffState[i].email,
        name: staffState[i].name,
        phone: staffState[i].phone,
        createdAt: staffState[i].createdAt,
        updatedAt: staffState[i].updatedAt,
        action: (
          <>
            <Link
              key={`detail-${i}`}
              className=" fs-3 text-danger"
              to={`/admin/detailStaff/${staffState[i].id}`}
            >
              <BiInfoCircle />
            </Link>
            <Link
              key={`edit-${i}`}
              className=" fs-3 text-danger"
              to={`/admin/editStaff/${staffState[i].id}`}
            >
              <BiEdit />
            </Link>
            <Link
              key={`delete-${i}`}
              className="ms-3 fs-3 text-danger"
              to="/admin/staffList"
              onClick={() => handleDelete(staffState[i].id)}
            >
              <AiFillDelete />
            </Link>
          </>
        ),
      });
    }
  } else {
    dataStaff = []; // or display an error message
  }
  return (
    <div>
      <h3 className="mb-4 title">Staffs</h3>
      <div>
        <Table
          striped
          bordered
          hover
          columns={columns}
          dataSource={dataStaff}
        />
      </div>
    </div>
  );
};
export default Stafflist;
