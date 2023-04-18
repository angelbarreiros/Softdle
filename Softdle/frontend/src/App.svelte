<script>
import Login from "./lib/Login.svelte"
import Register from "./lib/Register.svelte"
import Router ,{push} from 'svelte-spa-router'
import Error from "./lib/Error.svelte";
import UserHistory from "./lib/UserHistory.svelte";
import NotFound from "./lib/NotFound.svelte";
import {logged} from "./state.js";
import Forbidden from "./lib/Forbidden.svelte";
import Home from "./lib/Home.svelte";


const routes= {
    '/': Home ,
    '/login': Login,
    '/register': Register,
    '/userHistory': UserHistory,
    '/error':Error,
    '/forbidden':Forbidden,
    '*':NotFound
}
</script>
{#if $logged}
    <div class="cabecera">
        <div>
            <button on:click={()=>push('/')}> Home</button>
        </div>
        <div class="pana">
            <button on:click={()=>push('/userHistory')}> History</button>

        </div>
    </div>
{:else }
    <div class="cabecera">
        <div>
            <button on:click={()=>push('/')}> Home</button>
        </div>
        <div class="pana">
            <button on:click={()=>push('/login')}> Login</button>
            <button on:click={()=>push('/register')}> Register</button>
        </div>
    </div>
{/if}
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