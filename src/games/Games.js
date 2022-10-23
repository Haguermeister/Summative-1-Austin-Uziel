import {useEffect, useState} from "react";
import Modal from "../utils/Modal";
import ModalPostRequest from "../utils/ModalPostRequest";
import axios from "axios";
import {BsTrashFill} from "react-icons/bs";

export const Games = (data) => {
    //gamesData is the main and original data and gets overwritten on search by category get requests
    const [gamesData, setGamesData] = useState(null);
    const [gamesDataBackUp, setGamesDataBackUp] = useState(null)
    const [searchByTitle, setSearchByTitle] = useState(null);
    const [searchByStudio, setSearchByStudio] = useState(null);
    const [searchByEsrb, setSearchByEsrb] = useState(null);
    const [searchById, setSearchById] = useState(null);

    useEffect(() => {
        if (data.games !== null) {
            setGamesData(data);
            // original data will be overwritten by search by specific category
            setGamesDataBackUp(data)
        }
    }, [data])

    const searchBySpecificCategory = () => {
        //search by title
        if (searchByTitle !== null && searchByStudio === null) {
            const response = axios.get(`http://localhost:8080/game/title/${searchByTitle}`)
                .then(response => {
                    if (response.status === 200 && response.data) {
                        //creates an object with the  response result data based on mapping requirements
                        const searchByTitleResults = {games: [response.data]};
                        //overwrite main data with results
                        setGamesData(searchByTitleResults)
                    } else {
                        alert("No results found")
                    }
                })
        }
        //search by studio
        if (searchByTitle === null && searchByStudio !== null) {
            const response = axios.get(`http://localhost:8080/game/studio/${searchByStudio}`)
                .then(response => {
                    if (response.status === 200 && response.data) {
                        //creates an object with the  response result data based on mapping requirements
                        const searchByStudioResults = {games: response.data};
                        //overwrite main data with results
                        setGamesData(searchByStudioResults)
                    } else {
                        alert("No results found")
                    }
                })
        }
        //search by esrb rating
        if (searchByTitle === null && searchByStudio === null && searchByEsrb !== null) {
            const response = axios.get(`http://localhost:8080/game/esrb/${searchByEsrb}`)
                .then(response => {
                    if (response.status === 200 && response.data.length > 0) {
                        //creates an object with the  response result data based on mapping requirements
                        const searchByEsrbResults = {games: response.data};
                        //overwrite main data with results
                        setGamesData(searchByEsrbResults)
                    } else {
                        alert("No results found")
                    }
                })
        }

    }


    //reset all search bar entries and state of the hooks
    const resetSearch = () => {
        document.getElementById("search-by-studio").value = null;
        document.getElementById("search-by-title").value = null;
        document.getElementById("search-by-esrb").value = null;

        //default state of hooks to meet searchByCategory statements
        setSearchByEsrb(null)
        setSearchByTitle(null)
        setSearchByStudio(null)

        //restore main data with backup data
        setGamesData(gamesDataBackUp)
    }

    const deleteById = (e) => {

        axios.delete(`http://localhost:8080/game/${e.currentTarget.id}`).then(res => {
                alert(res.status)
                window.location.reload();
            }
        )
    }

    const searchGameId = (e) => {
        //GET request includes ID of current game
        axios.get(`http://localhost:8080/game/${searchById}`).then(res => {
                alert(res.status)
                const getByIdResult = {games: [res.data]};
                //overwrite main data with result (must get only one result)
                setGamesData(getByIdResult)
            }
        )
    }

    //renders component when main data is not null
    //otherwise displays loading screen
    return gamesData ? (
        <div className="body-container">
            <div className="search-by-category-wrapper">
                <input id="search-by-title" type="text" onChange={(e) => setSearchByTitle(e.target.value)}
                       placeholder="search by title"/>

                <input id="search-by-studio" type="text" onChange={(e) => setSearchByStudio(e.target.value)}
                       placeholder="search by studio"/>

                <input id="search-by-esrb" type="text" onChange={(e) => setSearchByEsrb(e.target.value)}
                       placeholder="search by esrb Rating"/>
                <button onClick={searchBySpecificCategory}>search</button>
                <button onClick={resetSearch}>reset</button>
                <input id="search-by-id" type="text" onChange={(e) => setSearchById(e.target.value)}
                       placeholder="search by Id"/>
                <button onClick={searchGameId}>search by Id</button>
            </div>
            <div className="container scroll">
                {gamesData.games.map((item, i) => (
                    <div key={gamesData.games[i].gameId + 30} className="product-wrapper">
                        <BsTrashFill className="delete-by-id"  id={gamesData.games[i].gameId} onClick={deleteById}/>
                        <img
                            className="product-img"
                            src="//upload.wikimedia.org/wikipedia/commons/thumb/1/13/Replace_this_image_%28building%29.svg/100px-Replace_this_image_%28building%29.svg.png"
                            alt="placeholder"/>
                        <div className="product-properties">

                            <h1> Title: {gamesData.games[i].title}</h1>
                            <h1>Price: ${gamesData.games[i].price}</h1>
                            <h1>Esrb Rating: {gamesData.games[i].esrbRating}</h1>
                            <details><summary>Description:</summary><p>{gamesData.games[i].description}</p></details>
                            <h1> Studio: {gamesData.games[i].studio}</h1>
                            <h1>Quantity: {gamesData.games[i].quantity}</h1>
                            <h1>Id: {gamesData.games[i].gameId} </h1>
                        </div>

                        {/*Modal receives what type of product the user is currently editing for*/}
                        <Modal obj={null} game={{
                            id: gamesData.games[i].gameId,
                            price: gamesData.games[i].price,
                            quantity: gamesData.games[i].quantity,
                            esrbRating: gamesData.games[i].esrbRating,
                            description: gamesData.games[i].description,
                            studio: gamesData.games[i].studio,
                            title: gamesData.games[i].title,
                        }} tShirt={null}>
                        </Modal>
                    </div>
                ))}
            </div>

            <div className="wrapper-buttons">
                {/*modal for post request receives what type of product the user wants to add*/}
                <ModalPostRequest showTShirtPostRequestForm={false} showGamesPostRequestForm={true}
                                  showGameConsolesPostRequestForm={false}/>
                <button
                    onClick={() =>
                        console.log(window.location.assign('http://localhost:3000/tshirts'))}
                >
                    T-Shirts
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

    ) : <h1>Loading</h1>
}