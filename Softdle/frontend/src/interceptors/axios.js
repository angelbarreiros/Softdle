import axios from "axios";
import {getJwtToken, logged} from "../state.js";
axios.defaults.baseURL='http://localhost:8080/'
axios.interceptors.response.use(resp => resp, error => {
    let loggedin;

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
            return Promise.reject(err);

        })

    }
    return Promise.reject(err);
})



