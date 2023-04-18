<script>
    export let numberOfAttempts;
    export let language;
    export let iswin;
    const createArray=(x)=>{
        return   Array.from({length: x}, (_, i) => i);
    }
</script>
<div class="colum" >
    {#if iswin}
        <h1>GG!</h1>
    {:else }
        <h1>Sorry :( , was fun to play , try again tomorrow</h1>
    {/if}

    <div class="outcome">
        {#each createArray(5-numberOfAttempts) as xd}
            <img src="src/assets/star.png" alt="">
        {/each}
        {#each createArray(numberOfAttempts) as xd}
            <img src="src/assets/empty-star.png" alt="">
        {/each}
    </div>
    <h1>Last try was: {language} </h1>

</div>
<style>
    .colum{
        display: flex;
        flex-direction: column;
    }
</style>