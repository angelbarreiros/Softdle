<script>
    import Juego from "./Juego.svelte";
    import {loggedFunction} from "../state.js";
    import axios from "axios";
    import {getJwtToken} from "../interceptors/axios.js";


    let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: 'users/isPlayed',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getJwtToken()}`
            },
        };
    const local=()=>{
        return localStorage.getItem("played") === "true";

    }


</script>
{#await loggedFunction}
    {:then response}
    {#if response.data}
        {#await axios.request(config)}
            {:then play}
            {#if play.data}
                <p>ya has jugado</p>
                {:else}
                <Juego></Juego>
            {/if}
            {/await}
    {:else }
                {#if local()}
                    <p>ya has jugado</p>
                    {:else }
                    <Juego></Juego>
                {/if}
    {/if}
{/await}
