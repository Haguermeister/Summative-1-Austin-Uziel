import {useState} from "react";
import axios from "axios";

export const GameConsolesPostRequestForm = () => {
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [memoryAmount, setMemoryAmount] = useState("");
    const [processor, setProcessor] = useState("");
    const [model, setModel] = useState("");
    const [manufacturer, setManufacturer] = useState("");


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            axios.post("http://localhost:8080/gameConsole", {
                price: price,
                quantity: quantity,
                memoryAmount: memoryAmount,
                processor: processor,
                model: model,
                manufacturer: manufacturer
            }).then(res => console.log(res));
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <div className="post-request-form">
            <form onSubmit={handleSubmit}>
                <input
                    type="number"
                    placeholder="price"
                    onChange={(e) => setPrice(e.target.value)}
                />
                <input
                    type="number"
                    placeholder="quantity"
                    onChange={(e) => setQuantity(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="memoryAmount"
                    onChange={(e) => setMemoryAmount(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="processor"
                    onChange={(e) => setProcessor(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="model"
                    onChange={(e) => setModel(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="manufacturer"
                    onChange={(e) => setManufacturer(e.target.value)}
                />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}