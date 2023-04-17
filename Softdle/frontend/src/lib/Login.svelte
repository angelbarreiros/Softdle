<script>

    import axios from 'axios';
    import {push} from 'svelte-spa-router'
    import {logged} from "../state.js";

    let username = '', password = ''
    $: submit = async () => {
        let data = JSON.stringify({
            "username": username,
            "password": password
        });
        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'auth/login',
            headers: {
                'Content-Type': 'application/json'
            },
            withCredentials:true,

            data: data
        };
        axios.request(config)
            .then((response) => {
               if (response.status===200){
                   document.cookie = `jwt=${response.data.token}; max-age=3600; path=/; secure; SameSite=Lax;`;
                   axios.defaults.headers.common['Authorization'] ='Bearer '+ response.data.token
                   $logged=true
                   push('/userHistory')
               }
            })
            .catch(() => {
                push('/error')
            });
    }

</script>
<main>
    <form on:submit|preventDefault={submit}>
        <h1>Plis sign in</h1>
        <div>
            <input bind:value={username} id="username" type="text">
            <label for="username">Usename</label>
        </div>
        <div>
            <input bind:value={password} id="password" type="text">
            <label for="password">Password</label>
        </div>
        <button type="submit">Submit</button>
    </form>
</main>
<style>
    form {
        max-width: 600px;
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    button {
        width: auto;
        max-width: 10%;
    }
</style>