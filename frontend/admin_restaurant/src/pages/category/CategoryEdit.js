import { useEffect, useRef } from "react";
import { useState } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import {default as categoryService} from "./categoryService";

function CategoryEdit(){
    const { id } = useParams();
    const [category, setCategory] = useState(null)
    const [list, setList] = useState([])
    const [listParent, setListParent] = useState([])
    const navigate=useNavigate();

    useEffect(() => {
        categoryService.getCategoryById(id).then(res => setCategory(res));
        categoryService.getAllCategoriesDto().then(res => setList(res));
      }, [id]);
      
      useEffect(() => {
        if(category !== null && category !== undefined){
            let listChild = [];
            function getListChild(c){
                listChild = [...listChild, c];
                if(c.listChild !== undefined){
                    c.listChild.map(cc=>{
                        getListChild(cc)
                    })
                }
            }
            getListChild(category)
            const parent = list.filter(item => !listChild.some(c => c.id === item.id));
            setListParent(parent);
        }
      }, [category, list]);


    const handlerChangeType = (value)=>{
        setCategory({...category, type: value})
    }
    const handlerChangeDesc = (value)=>{
        setCategory({...category, description: value})
    }
    const handleSelect = (parentId)=>{
        setCategory({...category, parentId})
      }
    const handleUpdate = async ()=>{
        await categoryService.updateCategory(category.id, category)
        navigate(`/admin/list-category`);
        console.log('da update')
    }

    return (
        (category != null) && (
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
                        <option value="" >root category</option>
                        {listParent.map((c,i)=>(
                        <option key={i} selected={c.id === category.parentId} value={c.id}>{c.type}</option>
                        // <option key={i} selected={c.id === category.parentId} value={c.id}>{c.type}</option>
                        ))}
                    </select>
                </div>
                <button type="button" onClick={handleUpdate} className="btn btn-success">Update Category</button>
            </div>
        )
    )
}

export default CategoryEdit