import {useEffect, useState} from "react";
import Modal from "../utils/Modal";
import ModalPostRequest from "../utils/ModalPostRequest";
import axios from "axios";
import {BsTrashFill} from "react-icons/bs";

export const Tshirts = (data) => {
    //tShirtsData is the main and original data and gets overwritten on search by category get requests
    const [tShirtsData, setTShirtsData] = useState(null);
    const [tShirtsDataBackUp, setTShirtsDataBackUp] = useState(null);
    const [searchBySize, setSearchBySize] = useState(null);
    const [searchByColor, setSearchByColor] = useState(null);
    const [searchById, setSearchById] = useState(null);

    useEffect(() => {
        if (data.tshirts !== null) {
            setTShirtsData(data);
            // original data will be overwritten by search by specific category
            setTShirtsDataBackUp(data);
        }
    }, [data])

    const searchByCategory = () => {
        //search t-shirt by size
        if (searchBySize !== null && searchByColor === null) {
            const response = axios.get(`http://localhost:8080/tshirt/size/${searchBySize}`)
                .then(response => {
                    if (response.data.length === 0) {
                        alert("No results found")
                    }
                    //creates an object with the response result data based on mapping requirements
                    const searchByTShirtSizeResults = {tshirts: response.data};
                    //overwrite main data with results
                    setTShirtsData(searchByTShirtSizeResults);
                    alert("response status code: " + response.status);
                })
        }
        //search t-shirt by color
        if (searchBySize === null && searchByColor !== null) {
            const response = axios.get(`http://localhost:8080/tshirt/color/${searchByColor}`)
                .then(response => {
                    if (response.data.length === 0) {
                        alert("No results found")
                    }
                    //creates an object based on mapping requirements
                    const searchByTShirtSizeResults = {tshirts: response.data};
                    //overwrite main data with results
                    setTShirtsData(searchByTShirtSizeResults);
                    alert("response status code: " + response.status);
                })
        }
    }

    //reset all search bar entries and state of the hooks
    const resetSearch = () => {
        document.getElementById("search-by-size").value = null;
        document.getElementById("search-by-color").value = null;

        //default state of hooks to meet searchByCategory statements
        setSearchBySize(null)
        setSearchByColor(null)

        //restore main data with backup data
        setTShirtsData(tShirtsDataBackUp)
    }

    const deleteById = (e) => {
        //DELETE request includes ID of current t-shirt
        axios.delete(`http://localhost:8080/tshirt/${e.currentTarget.id}`).then(res => {
                alert(res.status)
                window.location.reload();
            }
        )
    }

    const searchTShirtById = (e) => {
        //GET request includes ID of current t-shirt
        axios.get(`http://localhost:8080/tshirt/${searchById}`).then(res => {
                alert(res.status)
                const getByIdResult = {tshirts: [res.data]};
                //overwrite main data with result (must get only one result)
                setTShirtsData(getByIdResult)
            }
        )
    }

    //renders component when main data is not null
    //otherwise displays loading screen
    return tShirtsData ? (
        <div className="body-container">
            <div className="search-by-category-wrapper">
                <input id="search-by-size" type="text" onChange={(e) => setSearchBySize(e.target.value)}
                       placeholder="search by size"/>

                <input id="search-by-color" type="text" onChange={(e) => setSearchByColor(e.target.value)}
                       placeholder="search by color"/>
                <button onClick={searchByCategory}>search</button>
                <button onClick={resetSearch}>reset</button>


                <input id="search-by-id" type="text" onChange={(e) => setSearchById(e.target.value)}
                       placeholder="search by Id"/>
                <button onClick={searchTShirtById}>search by Id</button>
            </div>
            <div className="container scroll">
                {tShirtsData.tshirts.map((item, i) => (
                    <div key={tShirtsData.tshirts[i].tshirtId + 20} className="product-wrapper">
                        <BsTrashFill className="delete-by-id" id={tShirtsData.tshirts[i].tshirtId} onClick={deleteById}/>
                        <img
                            className="product-img"
                            src="//upload.wikimedia.org/wikipedia/commons/thumb/1/13/Replace_this_image_%28building%29.svg/100px-Replace_this_image_%28building%29.svg.png"
                            alt="placeholder"/>
                        <div className="product-properties">
                            <h1>Price: ${tShirtsData.tshirts[i].price}</h1>
                            <h1>Quantity: {tShirtsData.tshirts[i].quantity}</h1>
                            <h1>Size: {tShirtsData.tshirts[i].size}</h1>
                            <h1>Color: {tShirtsData.tshirts[i].color}</h1>
                            <h1>Description: {tShirtsData.tshirts[i].description}</h1>
                            <h1>Id: {tShirtsData.tshirts[i].tshirtId}</h1>
                        </div>
                        {/*Modal receives what type of product the user is currently editing for*/}
                        <Modal obj={null} tShirt={{
                            id: tShirtsData.tshirts[i].tshirtId,
                            price: tShirtsData.tshirts[i].price,
                            size: tShirtsData.tshirts[i].size,
                            description: tShirtsData.tshirts[i].description,
                            color: tShirtsData.tshirts[i].color,
                            quantity: tShirtsData.tshirts[i].quantity
                        }} game={null}>
                        </Modal>
                    </div>
                ))}
            </div>

            <div className="wrapper-buttons">
                {/*modal for post request receives what type of product the user wants to add*/}
                <ModalPostRequest showTShirtPostRequestForm={true} showGamesPostRequestForm={false}
                                  showGameConsolesPostRequestForm={false}/>
                <button
                    onClick={() =>
                        console.log(window.location.assign('http://localhost:3000/games'))}

                >
                    Games
                </button>
                <button
                    onClick={() =>
                        console.log(window.location.assign('http://localhost:3000/'))}
                >
                    Home
                </button>
                <button
                    onClick={() =>
                        console.log(window.location.assign('http://localhost:3000/consoles'))}
                >
                    Game Consoles
                </button>
            </div>
        </div>
    ) : <p>loading</p>
}