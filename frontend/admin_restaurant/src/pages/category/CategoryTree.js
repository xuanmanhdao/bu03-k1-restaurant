import CategoryNode from "./CategoryNode"

function CategoryTree({listCategory}){
    return (
        <ul className="tree">
            <details open>
            <summary>Category List</summary>
                {listCategory.map(category=>(
                    <CategoryNode key={category.id} category={category} />
                ))}
            </details>
        </ul>
    )
}

export default CategoryTree