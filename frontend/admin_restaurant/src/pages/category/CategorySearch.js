import { useState } from "react"
import CategoryService from "./categoryService"
import { useNavigate } from "react-router-dom"

function CategorySearch(){
    const [type, setType] = useState('')
    const [description, setDescription] = useState('')

    const handleType = (type)=>setType(type)
    const handleDes = (des)=>setDescription(des)
    const navigate = useNavigate()
    const handleClick = async ()=>{
        const resultSearch = await CategoryService.searchCategory({type, des: description})
        console.log('result search: ',resultSearch)
        navigate(`/admin/list-category`,{state: {resultSearch}})
    }


    return (
        <form className="form-inline">
            <div className="form-row">
                <div className="col">
                    <input type="text" className="form-control" onChange={e=>handleType(e.target.value)}  placeholder="type..." />
                </div>
                <div className="col">
                    <input type="text" className="form-control" onChange={e=>handleDes(e.target.value)}  placeholder="description..." />
                </div>
                <button type="button" className="btn btn-primary mb-2 col" onClick={handleClick}>search</button>
            </div>
        </form>
    )
}

export default CategorySearch