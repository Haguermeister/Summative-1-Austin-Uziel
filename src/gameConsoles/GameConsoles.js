import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import Modal from "../utils/Modal";
import ModalPostRequest from "../utils/ModalPostRequest";
import axios from "axios";
import {BsTrashFill} from "react-icons/bs";

export const GameConsoles = (data) => {
    const navigate = useNavigate()
    const [consolesData, setConsolesData] = useState(null);
    const [consolesDataBackUp, setConsolesDataBackUp] = useState(null)
    const [searchByManufacturer, setSearchByManufacturer] = useState(null);
    const [searchById, setSearchById] = useState(null);

    useEffect(() => {
        if (data.consoles !== null) {
            setConsolesData(data);
            setConsolesDataBackUp(data)
        }
    }, [data])

    const search = () => {
        const response = axios.get(`http://localhost:8080/gameConsole/manufacturer/${searchByManufacturer}`)
            .then(response => {
                if (response.data.length === 0) {
                    alert("No results found")
                }
                const searchByManufacturerResults = {consoles: response.data};
                setConsolesData(searchByManufacturerResults)
            })
    }
    const resetSearch = () => {
        setConsolesData(consolesDataBackUp)
    }

    const deleteById = (e) => {

        axios.delete(`http://localhost:8080/gameConsole/${e.currentTarget.id}`).then(res => {
                alert(res.status)
                window.location.reload();
            }
        )
    }

    const searchGameByConsoleId = (e) => {
        axios.get(`http://localhost:8080/gameConsole/${searchById}`).then(res => {
                alert(res.status)
                const getByIdResult = {consoles: [res.data]};
                console.log(getByIdResult)
                setConsolesData(getByIdResult)
            }
        )
    }
    return consolesData ? (

        <div className="body-container">
            <div className="container scroll">
                {consolesData.consoles.map((item, i) => (
                    <div key={consolesData.consoles[i].consoleId + 10} className="product-wrapper">

                        <BsTrashFill id={consolesData.consoles[i].consoleId} onClick={deleteById}/>
                        <img
                            className="product-img"
                            src="//upload.wikimedia.org/wikipedia/commons/thumb/1/13/Replace_this_image_%28building%29.svg/100px-Replace_this_image_%28building%29.svg.png"
                            alt="placeholder"/>
                        <div className="product-properties">
                            <h1>Price: ${consolesData.consoles[i].price}</h1>
                            <h1>Quantity: {consolesData.consoles[i].quantity}</h1>
                            <h1>manufacturer: {consolesData.consoles[i].manufacturer}</h1>
                            <h1>memoryAmount: {consolesData.consoles[i].memoryAmount}</h1>
                            <h1>model: {consolesData.consoles[i].model}</h1>
                            <h1>processor: {consolesData.consoles[i].processor}</h1>
                            <h1>Id: {consolesData.consoles[i].consoleId}</h1>
                        </div>
                        <Modal obj={{
                            id: consolesData.consoles[i].consoleId,
                            price: consolesData.consoles[i].price,
                            quantity: consolesData.consoles[i].quantity,
                            manufacturer: consolesData.consoles[i].manufacturer,
                            memoryAmount: consolesData.consoles[i].memoryAmount,
                            model: consolesData.consoles[i].model,
                            processor: consolesData.consoles[i].processor
                        }} game={null} tShirt={null}>
                        </Modal>
                    </div>
                ))}
            </div>

            <div className="wrapper-buttons">
                <input id="search-by-manufacturer" type="text" onChange={(e) => setSearchByManufacturer(e.target.value)}
                       placeholder="search by manufacturer"/>
                <button onClick={search}>search</button>
                <button onClick={resetSearch}>reset</button>

                <input id="search-by-id" type="text" onChange={(e) => setSearchById(e.target.value)}
                       placeholder="search by Id"/>
                <button onClick={searchGameByConsoleId}>search by Id</button>

                <ModalPostRequest showTShirtPostRequestForm={false} showGamesPostRequestForm={false}
                                  showGameConsolesPostRequestForm={true}/>
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
                        console.log(window.location.assign('http://localhost:3000/tshirts'))}>
                    T-Shirts
                </button>
            </div>
        </div>
    ) : <p>loading</p>
}