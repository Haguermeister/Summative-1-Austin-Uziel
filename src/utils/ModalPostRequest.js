import styled from 'styled-components';
import {useEffect, useState} from "react";
import {TShirtPostRequest} from "../tshirt/TShirtPostRequest";
import {GamesPostRequest} from "../games/GamesPostRequest";
import {GameConsolesPostRequestForm} from "../gameConsoles/GameConsolesPostRequestForm";


const ModalBackground = styled.div`
position: fixed;
z-index: 4;
left: 0;
top: 0;
width: 100%;
height: 100%;
overflow: auto;
background-color: rgb(0,0,0,0.5);
`;

const ModalBody = styled.div`
background-color: rgba(220,220,220);
margin: 10% auto;
padding: 20px;
width: 90%;
height: auto;
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
`;

// Modal from: https://www.linkedin.com/learning/react-design-patterns/take-your-react-skills-to-the-next-level?autoplay=true

function ModalPostRequest(productPostRequest, showTShirtPostRequestForm, showGamesPostRequestForm, showGameConsolesPostRequestForm) {
    const [shouldShow, setShouldShow] = useState(false);

    return (
        <>
            <div className="flex justify-center">

                <button onClick={() => setShouldShow(true)}>

                    {productPostRequest.showTShirtPostRequestForm === true ? "Add New T-Shirt" : ""}

                    {productPostRequest.showGamesPostRequestForm === true ? "Add New Game" : ""}

                    {productPostRequest.showGameConsolesPostRequestForm === true ? "Add New Game Console" : ""}
                </button>
            </div>
            {shouldShow && (
                <ModalBackground>
                    <ModalBody onClick={(e) => e.stopPropagation()}>

                        {/*based on what type of product the user wants to create, it will show that form for that product*/}
                        {productPostRequest.showTShirtPostRequestForm === true ? <TShirtPostRequest/> : ""}

                        {productPostRequest.showGamesPostRequestForm === true ? <GamesPostRequest/> : ""}

                        {productPostRequest.showGameConsolesPostRequestForm === true ? <GameConsolesPostRequestForm/> : ""}

                        <div>
                            <button style={{width: 50}} className="close-modal-btn"
                                    onClick={() => setShouldShow(false)}>
                                Close
                            </button>
                        </div>
                    </ModalBody>
                </ModalBackground>
            )}
        </>
    );
}


export default ModalPostRequest;