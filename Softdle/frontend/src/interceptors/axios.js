import axios from "axios";
import { logged} from "../state.js";
import {push} from "svelte-spa-router";
axios.defaults.baseURL='http://localhost:8080/'
export function getJwtToken() {
    const cookies = document.cookie.split(';').map(cookie => cookie.trim());
    const jwtCookie = cookies.find(cookie => cookie.startsWith('jwt='));
    return jwtCookie ? jwtCookie.split('=')[1] : null;
}
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
if (getJwtToken()===null){
    push('/')
}
axios.interceptors.response.use(async resp => resp, async error => {
    let loggedin;
    await axios.request(config).then(response=>{
        logged.set(response.data)
    })
    logged.subscribe(value => loggedin=value)
    console.log(loggedin)
    if (error.response.status === 403 && loggedin) {
        const jwtoken=getJwtToken();
        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'auth/refresh',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtoken}`
            },
            withCredentials: true,
        };

        return axios.request(config).then(response => {
            if (response.status === 200) {
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token

                return axios(error.config)
            }

            return Promise.reject(error);

        })

    }

    return Promise.reject(error);
})


