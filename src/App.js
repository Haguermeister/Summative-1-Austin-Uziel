import './App.css';
import {Routes, Route} from "react-router-dom";
import {Tshirts} from "./tshirt/Tshirts";
import {Games} from "./games/Games";
import {GameConsoles} from "./gameConsoles/GameConsoles";
import {DataSource} from "./utils/DataSource";
import {getServerData} from "./utils/getServerData";
import {Home} from "./Home";

function App() {
    return (<>
            <Routes>
                <Route path="/" element={
                    <Home/>
                }/>
                <Route path="/tshirts" element={
                    <DataSource getDataFunc={getServerData('http://localhost:8080/tshirt')}
                                resourceName="tshirts">
                        <Tshirts/>
                    </DataSource>}/>
                }/>
                <Route path="/games" element={
                    <DataSource getDataFunc={getServerData('http://localhost:8080/game')}
                                resourceName="games">
                        <Games/>
                    </DataSource>}/>
                }/>
                <Route path="/consoles" element={
                    <DataSource getDataFunc={getServerData('http://localhost:8080/gameConsole')}
                                resourceName="consoles">
                        <GameConsoles/>
                    </DataSource>}/>
                <Route
                    path="*"
                    element={
                        <main style={{padding: "1rem"}}>
                            <p>There's nothing here! 404</p>
                        </main>
                    }
                />
            </Routes>
        </>
    );
}

export default App;
