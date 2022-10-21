import {useNavigate} from "react-router-dom";

export const Home = () => {

//let's you navigate to other pages programmatically
    const navigate = useNavigate()
    return (
        <div className="container">
            <div><h1>Home</h1></div>

            <div className="wrapper-buttons">
                <button
                    onClick={() => navigate("/tshirts")}
                >
                    T-Shirts
                </button>
                <button
                    onClick={() => navigate("/games")}
                >
                    Games
                </button>
                <button
                    onClick={() => navigate("/consoles")}
                >
                    Game Consoles
                </button>
            </div>
        </div>
    )
}