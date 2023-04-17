import { writable } from 'svelte/store';
import axios from "axios";
import {push} from "svelte-spa-router";
import {getJwtToken} from "./interceptors/axios.js";
export const languages = writable([]);
export const count = writable(0);
export const logged=writable(false)
export const hasNext=writable(true)
//comprobacionde si está logeado
let urilanguage = "http://localhost:8080/api/languages"

if (getJwtToken()!==null){
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

    axios.request(config).then(response=>{
        logged.set(response.data)
    })

}
else{
    push('/')
}

fetch(urilanguage).then((response) => response.json().then(response => {
    languages.set(response)
}))

