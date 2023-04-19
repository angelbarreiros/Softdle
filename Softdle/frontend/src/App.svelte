<script>
import Login from "./lib/Login.svelte"
import Register from "./lib/Register.svelte"
import Router ,{push} from 'svelte-spa-router'
import Error from "./lib/Error.svelte";
import UserHistory from "./lib/UserHistory.svelte";
import NotFound from "./lib/NotFound.svelte";
import { loggedFunction} from "./state.js";
import Forbidden from "./lib/Forbidden.svelte";
import Home from "./lib/Home.svelte";
import {Pulse} from "svelte-loading-spinners";


const routes= {
    '/': Home ,
    '/login': Login,
    '/register': Register,
    '/userHistory': UserHistory,
    '/error':Error,
    '/forbidden': Forbidden,
    '*': NotFound,
    '/:path*': NotFound

}
</script>

{#await loggedFunction}
    <h1>Comprobando sesion...</h1>
    <Pulse></Pulse>
{:then response}
    {#if response.data}
    <div class="cabecera">
        <div>
            <button on:click={()=>window.location.href = '/' }> Home</button>
        </div>
        <div class="pana">
            <button on:click={()=>push('/userHistory')}> History</button>
        </div>
    </div>
{:else }
    <div class="cabecera">
        <div>
            <button on:click={()=>window.location.href = '/' }> Home</button>
        </div>
        <div class="pana">
            <button on:click={()=>push('/login')}> Login</button>
            <button on:click={()=>push('/register')}> Register</button>
        </div>
    </div>
{/if}
    {:catch error}
    <h1>The database is down</h1>
{/await}
<Router {routes}/>
<style>
    .cabecera{
        margin: 10px;
        display: flex;
        justify-content: space-between;
    }
    .pana{
        display: flex;
        flex-direction: row;
        gap: 10px;
    }
</style>