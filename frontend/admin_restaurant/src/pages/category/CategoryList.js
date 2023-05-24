import React, { useEffect, useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './category.css'
import CategoryTree from "./CategoryTree";
import CategoryService from "./categoryService";

function Categorylist(){
  const [list, setList] = useState([])

    useEffect(()=>{
        CategoryService.getAllCategories().then(res => setList(res))
    }, [])

    //xu ly search
    const [type, setType] = useState('')
    const [description, setDescription] = useState('')

    const handleType = (type)=>setType(type)
    const handleDes = (des)=>setDescription(des)
    const handleClick = async ()=>{
        const resultSearch = await CategoryService.searchCategory({type, des: description})
        setList(resultSearch)
    }
    //xu ly search

  
  return (
    <div id="category">
      {/* search */}
        <form id="search">
            <div className="form-row">
                <input type="text" className="form-control" onChange={e=>handleType(e.target.value)}  placeholder="type..." />
                <input type="text" className="form-control" onChange={e=>handleDes(e.target.value)}  placeholder="description..." />
                <button type="button" className="btn btn-primary mb-2 col" onClick={handleClick}>search</button>
            </div>
        </form>
      {/* search */}
      <h3 className="mb-4 title">Product Categories</h3>
      <div className="category">
          <CategoryTree listCategory={list}  />
      </div>
    </div>
  );
}
export default Categorylist;

