import { writable } from 'svelte/store';
import axios from "axios";
import {getJwtToken} from "./interceptors/axios.js";
export const languages = writable([]);
export const count = writable(0);
export const logged=writable(false)
export const hasNext=writable(true)


const data={
    "token":getJwtToken()
}

const config={
    method: 'post',
    maxBodyLength: Infinity,
    url: 'http://localhost:8080/auth/verify',
    headers: {
        'Content-Type': 'application/json'
    },
    data:data
};


export  const loggedFunction=axios.request(config).then(((response)=>{
    logged.set(response.data)
    return response;
}))


