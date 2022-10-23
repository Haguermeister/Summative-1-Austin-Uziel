import {useEffect, useState} from "react";
import Modal from "../utils/Modal";
import ModalPostRequest from "../utils/ModalPostRequest";
import axios from "axios";
import {BsTrashFill} from "react-icons/bs";

export const GameConsoles = (data) => {
    //tShirtsData is the main and original data and gets overwritten on search by category get requests
    const [consolesData, setConsolesData] = useState(null);
    const [consolesDataBackUp, setConsolesDataBackUp] = useState(null)
    const [searchByManufacturer, setSearchByManufacturer] = useState(null);
    const [searchById, setSearchById] = useState(null);

    useEffect(() => {
        if (data.consoles !== null) {
            setConsolesData(data);
            // original data will be overwritten by search by specific category
            setConsolesDataBackUp(data)
        }
    }, [data])

    const searchByCategory = () => {
        //search by manufacturer
        const response = axios.get(`http://localhost:8080/gameConsole/manufacturer/${searchByManufacturer}`)
            .then(response => {
                if (response.data.length === 0) {
                    alert("No results found")
                }
                //creates an object with the response result data based on mapping requirements
                const searchByManufacturerResults = {consoles: response.data};
                //overwrite main data with results
                setConsolesData(searchByManufacturerResults);
                alert("response status code: " + response.status);
            })
    }

    //reset all search bar entries and state of the hooks
    const resetSearch = () => {
        //restore main data with backup data
        setConsolesData(consolesDataBackUp)
    }

    const deleteById = (e) => {
        //DELETE request includes ID of current console
        axios.delete(`http://localhost:8080/gameConsole/${e.currentTarget.id}`).then(res => {
                alert(res.status)
                window.location.reload();
            }
        )
    }

    const searchGameByConsoleId = (e) => {
        //GET request includes ID of current console
        axios.get(`http://localhost:8080/gameConsole/${searchById}`).then(res => {
                alert(res.status)
                const getByIdResult = {consoles: [res.data]};
                //overwrite main data with result (must get only one result)
                setConsolesData(getByIdResult)
            }
        )
    }

    //renders component when main data is not null
    //otherwise displays loading screen
    return consolesData ? (

        <div className="body-container">

            <div className="search-by-category-wrapper">
                <input id="search-by-manufacturer" type="text" onChange={(e) => setSearchByManufacturer(e.target.value)}
                       placeholder="search by manufacturer"/>
                <button onClick={searchByCategory}>search</button>
                <button onClick={resetSearch}>reset</button>

                <input id="search-by-id" type="text" onChange={(e) => setSearchById(e.target.value)}
                       placeholder="search by Id"/>
                <button onClick={searchGameByConsoleId}>search by Id</button>
            </div>
            <div className="container scroll">
                {consolesData.consoles.map((item, i) => (
                    <div key={consolesData.consoles[i].consoleId + 10} className="product-wrapper">

                        <BsTrashFill className="delete-by-id"  id={consolesData.consoles[i].consoleId} onClick={deleteById}/>
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
                        {/*Modal receives what type of product the user is currently editing for*/}
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
                {/*modal for post request receives what type of product the user wants to add*/}
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