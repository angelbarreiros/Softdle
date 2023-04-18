<script>
    import Juego from "./Juego.svelte";
    import {count, loggedFunction} from "../state.js";
    import axios from "axios";
    import {getJwtToken} from "../interceptors/axios.js";
    import WinMensage from "./WinMensage.svelte";


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
    const loose=()=>{
        return localStorage.getItem("loose?") === "true";
    }
    const lastLanguage=()=>{
        return localStorage.getItem("language")
    }
    const cont=()=>{
        return parseInt(localStorage.getItem("count"))
    }
    let iswin=true;
    let g =false
    const perder=()=>{
        iswin=false
        g=true
    }
    const jugar=()=>{
        g=true
    }
    let lastconfig = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'users/lastResult',
        headers: {
            'Content-Type': 'application/json',
        },
    };
    const datefromlastgame=()=>{
        let nplayed=localStorage.getItem("nextPlayed" );
        if(nplayed!= null){
            if (Date.now()>parseInt(nplayed)){
                localStorage.setItem("played","false")
                localStorage.setItem("loose?","false")
            }

        }
    }
    datefromlastgame();



</script>
{#await loggedFunction}
    {:then response}
    {#if response.data}
        {#await axios.request(config)}
            {:then play}
<!--            // si ya ha jugado -> g es que ya ha ganado-->
            {#if play.data || g}
                {#await axios.request(lastconfig)}
                    {:then resp}
                    <WinMensage iswin={resp.data.numberOfAttempts<5} language={resp.data.language} numberOfAttempts={resp.data.numberOfAttempts}></WinMensage>
                {/await}
                {:else}
<!--                // si no que juegue-->
                <Juego on:win={jugar} on:loose={perder}></Juego>
            {/if}
            {/await}
    {:else }
<!--        //  si no esta loggeado-->
                {#if local() || g }
                    <WinMensage iswin={!loose()} language={lastLanguage()} numberOfAttempts={cont()}></WinMensage>
                    {:else }
                    <Juego on:win={jugar} on:loose={perder} ></Juego>
                {/if}
    {/if}
{/await}
