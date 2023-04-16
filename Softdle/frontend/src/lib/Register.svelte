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
        <div class="form-floating">
            <input bind:value={firstname}>
            <label>Email address</label>
        </div>
        <div class="form-floating">
            <input bind:value={lastname}>
            <label>Email address</label>
        </div>

        <div class="form-floating">
            <input bind:value={username} >
            <label>Name</label>
        </div>


        <div class="form-floating">
            <input bind:value={password} >
            <label>Password</label>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
    </form>
</main>