import axios from "axios";

export const getServerData = url => async () => {
    const response = await axios.get(url);
    return response.data;
}