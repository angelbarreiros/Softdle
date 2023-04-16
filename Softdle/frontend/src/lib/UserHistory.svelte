<script>
    import axios from "axios";
    import {push} from "svelte-spa-router";
    import {logged} from "../state.js";


    let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'users/history',
        headers: {
            'Content-Type': 'application/json'
        },
    };
    if (!$logged){
         push('/forbidden')
    }
    const logout=()=>{
        axios.defaults.headers.common['Authorization']='';
        $logged=false
        document.cookie = "jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/; SameSite=Lax;";

        push('/')
    }
     const getDate=(mili)=>{
         const userLanguage = navigator.language;
         const date = new Date(mili);
         const formatter = new Intl.DateTimeFormat(userLanguage, {
             weekday: 'long',
             year: 'numeric',
             month: 'long',
             day: 'numeric'
         });


         return formatter.format(date);
     }
    const createArray=(x)=>{
        return   Array.from({length: x}, (_, i) => i);
    }



</script>

<div class="container">
    <div class="column">
        <div class="centered">
            {#await axios.request(config)}
            {:then response}
                <ol>
                    {#each response.data.items as item}

                        <div class="fila">
                            <div class="estrellas">
                                <div class="outcome">
                                    {#if (item.numberOfAttempts===5)}
                                        <div>
                                            <p>Loose:</p>
                                        </div>
                                    {:else }
                                        <div>
                                            <p>Win:</p>
                                        </div>
                                    {/if}
                                </div>
                                {#each createArray(5-item.numberOfAttempts) as xd}
                                    <img src="src/assets/star.png" alt="">
                                {/each}
                                {#each createArray(item.numberOfAttempts) as xd}
                                    <img src="src/assets/empty-star.png" alt="">
                                {/each}
                            </div>
                            <img class="calendar" src="src/assets/calendar.png" alt="">
                            <p> {getDate(item.date)}</p>

                        </div>

                    {/each}
                </ol>

            {:catch error}
                <p>{error.message}</p>
            {/await}
        </div>
        <button on:click={logout}>
            log out
        </button>
    </div>
</div>
<style>
    .column{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

    }

    .outcome{
        width: 100px;
    }


    .container{
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .estrellas {
        display: flex;

        align-items: center;
        flex-direction: row;
    }


    .fila{
        display: flex;
        flex-direction: row;
        width: 100%;
        gap: 10px;
    }
    li{
        list-style: none;
    }
    .calendar{
        object-fit: scale-down;
    }
</style>
