import { useState } from "react"
import CategoryService from "./categoryService"
import { Link } from "react-router-dom"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faPenToSquare, faTrash } from "@fortawesome/free-solid-svg-icons"

function CategoryNode({category}){
    console.log("render Category Node")
    const [show, setShow] = useState(true)
    const handlerDelete = (id)=>{
        CategoryService.deleteCategory(id)
        setShow(false)
    }

    return ( show &&
        (<li className= "">
            <details>
                <summary>
                    <div className="content">
                        <span className="type btn">{category.type}</span>
                        <Link to={`/admin/category/edit/${category.id}`}>
                        <span className="edit">
                        <FontAwesomeIcon icon={faPenToSquare} />
                        </span>
                        </Link>
                        <span className="delete" onClick={()=>handlerDelete(category.id)}>
                        <FontAwesomeIcon icon={faTrash} />
                        </span>
                    </div>
                </summary>
                {/* <summary>{category.type}</summary> */}
                {/* <div id="button">
                    <Link to={`/admin/category/edit/${category.id}`}>
                    <span>Edit</span>
                    </Link>
                    <span onClick={()=>handlerDelete(category.id)}>Delete</span>
                </div> */}
                {category.listChild.length > 0  && (
                <ul className="nested">
                    {category.listChild.map(child => (
                        <CategoryNode key={child.id} category={child} />
                    ))}
                </ul>
            )}
            </details>
            
        </li>)
    )
}

export default CategoryNode