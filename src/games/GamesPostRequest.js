import {useState} from "react";
import axios from "axios";

export const GamesPostRequest = () => {
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [description, setDescription] = useState("");
    const [esrbRating, setEsrbRating] = useState("");
    const [title, setTitle] = useState("");
    const [studio, setStudio] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            axios.post("http://localhost:8080/game", {
                price: price,
                quantity: quantity,
                description: description,
                esrbRating: esrbRating,
                title: title,
                studio: studio
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
                    placeholder="description"
                    onChange={(e) => setDescription(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="esrbRating"
                    onChange={(e) => setEsrbRating(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="title"
                    onChange={(e) => setTitle(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="studio"
                    onChange={(e) => setStudio(e.target.value)}
                />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}