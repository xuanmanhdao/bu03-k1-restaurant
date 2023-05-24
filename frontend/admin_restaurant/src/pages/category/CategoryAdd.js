import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import CategoryService from "./categoryService";
import { async } from "q";

function CategoryAdd(){
  const [category, setCategory] = useState({})
  const [list, setList] = useState([])
  const navigate=useNavigate();
  const handlerChangeType = (type)=>{
    const newCategory = {...category, type: type}
    setCategory(newCategory)
  }
  const handlerChangeDesc = (des)=>{
    const newCategory = {...category, description: des}
    setCategory(newCategory)
  }
  const handleSelect = (parentId)=>{
    const newCategory = {...category, parentId}
    setCategory(newCategory)
  }
  const addCategory = async ()=>{
    await CategoryService.createCategory(category)
    navigate(`/admin/list-category`);
  }

  useEffect(()=>{
    CategoryService.getAllCategoriesDto().then(res=>setList(res))
  }, [])

  return (
        <div id="form">
                <div className="form-row">
                    <div className="form-group col-md-6">
                        <label htmlFor="inputEmail4">Type</label>
                        <input type="text" className="form-control" id="inputEmail4" placeholder="Type"
                         value={category.type} onChange={e=>handlerChangeType(e.target.value)} />
                    </div>
                    <div className="form-group col-md-6">
                        <label htmlFor="inputPassword4">Description</label>
                        <input type="text" className="form-control" id="inputPassword4" placeholder="Description"
                         value={category.description} onChange={e=>handlerChangeDesc(e.target.value)} />
                    </div>
                </div>
                <div className="form-group col-md-6">
                    <label htmlFor="inputState">Parent category</label>
                    <select id="inputState" className="form-control" onChange={e=>handleSelect(e.target.value)}>
                        <option>root category</option>
                        {list.map((c,i)=>(
                        <option key={i}  value={c.id}>{c.type}</option>
                        ))}
                    </select>
                </div>
                    <button type="button" onClick={addCategory} className="btn btn-success">Add Category</button>
            </div>
  );
};

export default CategoryAdd;
