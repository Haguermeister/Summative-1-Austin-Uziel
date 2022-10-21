import {useEffect, useState} from "react";
import Modal from "../utils/Modal";
import ModalPostRequest from "../utils/ModalPostRequest";
import axios from "axios";
import {BsTrashFill} from "react-icons/bs";

export const Tshirts = (data) => {
    const [tShirtsData, setTShirtsData] = useState(null);
    const [tShirtsDataBackUp, setTShirtsDataBackUp] = useState(null);
    const [searchBySize, setSearchBySize] = useState(null);
    const [searchByColor, setSearchByColor] = useState(null);
    const [searchById, setSearchById] = useState(null);

    useEffect(() => {
        if (data.tshirts !== null) {
            setTShirtsData(data);
            setTShirtsDataBackUp(data)
        }
    }, [data])

    const searchByCategory = () => {
        if (searchBySize !== null && searchByColor === null) {
            const response = axios.get(`http://localhost:8080/tshirt/size/${searchBySize}`)
                .then(response => {
                    if (response.data.length === 0) {
                        alert("No results found")
                    }
                    const searchByTShirtSizeResults = {tshirts: response.data};
                    setTShirtsData(searchByTShirtSizeResults)
                })
        }
        if (searchBySize === null && searchByColor !== null) {
            const response = axios.get(`http://localhost:8080/tshirt/color/${searchByColor}`)
                .then(response => {
                    if (response.data.length === 0) {
                        alert("No results found")
                    }
                    const searchByTShirtSizeResults = {tshirts: response.data};
                    setTShirtsData(searchByTShirtSizeResults)
                })
        }
    }
    const resetSearch = () => {
        document.getElementById("search-by-size").value = null;
        setSearchBySize(null)
        document.getElementById("search-by-color").value = null;
        setSearchByColor(null)
        setTShirtsData(tShirtsDataBackUp)
    }

    const deleteById = (e) => {

        axios.delete(`http://localhost:8080/tshirt/${e.currentTarget.id}`).then(res => {
                alert(res.status)
                window.location.reload();
            }
        )
    }

    const searchTShirtById = (e) => {
        axios.get(`http://localhost:8080/tshirt/${searchById}`).then(res => {
                alert(res.status)
                const getByIdResult = {tshirts: [res.data]};
            console.log(getByIdResult)
                setTShirtsData(getByIdResult)
            }
        )
    }

    return tShirtsData ? (
        <div className="body-container">
            <div className="container scroll">
                <input id="search-by-size" type="text" onChange={(e) => setSearchBySize(e.target.value)}
                       placeholder="search by size"/>

                <input id="search-by-color" type="text" onChange={(e) => setSearchByColor(e.target.value)}
                       placeholder="search by color"/>
                <button onClick={searchByCategory}>search</button>
                <button onClick={resetSearch}>reset</button>


                <input id="search-by-id" type="text" onChange={(e) => setSearchById(e.target.value)}
                       placeholder="search by Id"/>
                <button onClick={searchTShirtById}>search by Id</button>


                {tShirtsData.tshirts.map((item, i) => (
                    <div key={tShirtsData.tshirts[i].tshirtId + 20} className="product-wrapper">
                        <BsTrashFill id={tShirtsData.tshirts[i].tshirtId} onClick={deleteById}/>
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
                        <Modal obj={null} tShirt={{
                            id: tShirtsData.tshirts[i].tshirtId,
                            price: tShirtsData.tshirts[i].price,
                            size: tShirtsData.tshirts[i].size,
                            description: tShirtsData.tshirts[i].description,
                            color: tShirtsData.tshirts[i].color,
                            quantity: tShirtsData.tshirts[i].quantity
                        }}

                               game={null}
                        >
                        </Modal>
                    </div>
                ))}
            </div>

            <div className="wrapper-buttons">
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