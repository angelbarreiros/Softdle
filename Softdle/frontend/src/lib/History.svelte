<script>
import axios from "axios";
import {hasNext} from "../state.js";
export let config={
    method: 'get',
    maxBodyLength: Infinity,
    url: 'users/history',
    headers: {
        'Content-Type': 'application/json'
    },
};
const createArray=(x)=>{
    return   Array.from({length: x}, (_, i) => i);
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

const thePromise=(config)=>{
    return new Promise(async (resolve,reject)=>{
        try {
            axios.request(config).then((response)=>{
                $hasNext=response.data.existMoreItems
                resolve(response)
            })
        }catch (error){
            reject("error")
        }
    })
}

</script>

<div class="column">
    <div class="centered">
        {#await thePromise(config)}
        {:then response}
            <ol>
                {#each response.data.items as item}
                    <div  class="fila">
                        <div  class="estrellas">
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
        {/await}
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
    .calendar{
        object-fit: scale-down;
    }
</style>