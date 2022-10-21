import {useState} from "react";
import axios from "axios";

export const TShirtPostRequest = () => {
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [size, setSize] = useState("");
    const [color, setColor] = useState("");
    const [description, setDescription] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            axios.post("http://localhost:8080/tshirt", {
                price: price,
                quantity: quantity,
                size: size,
                color: color,
                description: description
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
                    placeholder="size"
                    onChange={(e) => setSize(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="color"
                    onChange={(e) => setColor(e.target.value)}
                />
                <input
                type="text"
                placeholder="description"
                onChange={(e) => setDescription(e.target.value)}
            />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}