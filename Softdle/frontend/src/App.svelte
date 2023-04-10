<script>
    import {count, languages} from "./state.js";
    let header=false
    let input;
    let urilanguage = "http://localhost:8080/api/languages"
    let urifind = "http://localhost:8080/api/find?name="
    fetch(urilanguage).then((response) => response.json().then(response => {
        response.map(item => $languages = [...$languages, item])
    }))
    let answer = [];

     function find() {
        fetch(urifind + input.value, {method: "POST"}).then(response => response.json().then(async (response) => {
            let arr = Object.values(response)
            let match = arr.shift()
            //TODO CAMBIAR LAS CARACTERISTICAS DE ESTA CALL EN STRING , TIENE QUE DEVOLVER UN OBJETO CON 2 ARRAYS
            let item = $languages.find(item => item.name.toLowerCase() === input.value.toLowerCase())
            answer = [{arr: arr, image: "data:image/png;base64, " + item.imagePath}, ...answer]
            header=true;
            await new Promise(resolve => setTimeout(resolve, 2000))
            if (match){
                $count=5;
            }else {
                $count++;
            }
        }))

    }

    let button;

    function debug() {
        console.log(Object.values(answer))
    }

    function onlyLanguages() {
        return $languages.map(item => item.name)
    }


</script>
{#if ($count !==5)}
    <main>
        <!--    <button on:click={()=>debug()}></button>-->
       <div class="inputbutton">
           <input list="languages" type="text" bind:this={input}>
           <button bind:this={button} on:click={()=>find()}>TRY</button>
           <datalist id="languages">
               {#each $languages as item}
                   <option value={item.name}></option>
               {/each}
           </datalist>
       </div>
          <div class="absolute">
              {#if (header === true)}
                <div class="header">
                    <div class="square">Language </div>
                    <div class="square">Typing</div>
                    <div class="square">Compiling </div>
                    <div class="square">Release</div>
                    <div class="square">Creator </div>
                    <div class="square">Paradigm </div>
                    <div class="square">Purpose </div>
                    <div class="square">Jobs</div>
                </div>
            {/if}
            <div class="vertical">
                {#each answer as obj }
                    <div class="horizontal">
                        <div class="square">
                            <img class="logo" src={obj.image} alt="true">
                        </div>
                        {#each obj.arr as item,i}
                            {#if (item === true)}
                                <div class="square">
                                    <img src="src/assets/true.jpg" alt="true">
                                </div>
                            {/if}
                            {#if (item === false)}
                                <div class="square">
                                    <img src="src/assets/false.jpg" alt="false">
                                </div>
                            {/if}
                            {#if (item === "More")}
                                <div class="square">
                                    <img src="src/assets/high.jpg" alt="higher">
                                </div>
                            {/if}
                            {#if (item === "Less")}
                                <div class="square">
                                    <img src="src/assets/low.jpg" alt="lower">
                                </div>
                            {/if}
                            {#if (item === "Perfect")}
                                <div class="square">
                                    <img src="src/assets/true.jpg" alt="perfect">
                                </div>
                            {/if}
                        {/each}
                    </div>
                {/each}
            </div>
          </div>
    </main>

{:else }
    Ganaste
{/if}

<style>
    .horizontal {
        display: flex;
        flex-direction: row;
        gap: 20px;
    }

    .horizontal .square img {

        width: 100%;
        height: 100%;
        text-shadow: 0 0 3px #000;
        box-shadow: inset 0 0 6px #000;
        margin-top: 10px;

    }

    .vertical {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .header {
        display: flex;
        flex-direction: row;
        gap: 25px;
    }

    .header .square {
        font-size: 18px;
        width: 100%;
        height: 100%;
        border-bottom: 2px solid;
        box-sizing: border-box;
        text-align: center;
        overflow: hidden;
    }
    .horizontal .square{
        font-size: 30px;
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        text-align: center;
        overflow: hidden;
    }


    input {
        width: 300px;
        height: 50px;
        font-size: 20px;
    }

    .logo {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    button {
        height: 50px;
    }
    .absolute{
        margin-left: 500px;
        margin-right: 500px;
        position: absolute;
        left: 0;
        right: 0;
        top: 20%;
        bottom: 0;


    }
    .inputbutton{
        position: absolute;
        left: 40%;
        right: 0;
        top: 12%;
        bottom: 0;
    }



</style>
