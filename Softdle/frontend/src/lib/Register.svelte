<script>
    import axios from 'axios';
    import {push} from 'svelte-spa-router'
    let username = '', firstname = '', password = '', lastname='';
    $: submit = async () => {
        let data = JSON.stringify({
            "firstName": firstname,
            "lastName": lastname,
            "username": username,
            "password": password
        });
        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'auth/register',
            headers: {
                'Content-Type': 'application/json'
            },

            data : data
        };
        axios.request(config)
            .then(() => {
                push('/login');
            })
            .catch(() => {
                push('/error');
            });
    }
</script>

<main >
    <form on:submit|preventDefault={submit}>
        <h1 >Please register</h1>
        <div >
            <input id="register_firstname" bind:value={firstname}>
            <label for="register_firstname">Firstname</label>
        </div>
        <div >
            <input id="register_lastname" bind:value={lastname}>
            <label for="register_lastname">Lastname</label>
        </div>

        <div >
            <input id="register_username" bind:value={username} >
            <label for="register_username">Username</label>
        </div>

        <div>
            <input id="register_password" type="password" bind:value={password} >
            <label for="register_password">Password</label>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
    </form>
</main>
<style>
    form{
        display: flex;
        flex-direction: column;
        gap: 10px;
        justify-content: center;
        align-items: start;

    }
</style>