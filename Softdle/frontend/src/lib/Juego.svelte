<script>
    import {fly} from 'svelte/transition';
    import {count,languages} from "../state.js";
    import Win from "./Win.svelte";
    let button; //bind del boton
    let input; // bind del input
    let header = false //cuando aparece la cabecera
    let answer = []; //datos que se usaran en cada cuadradito
    let choosed=[]; //lenguages elejidos por el usuario -> falta meterlo en el cache del navegador
    let urilanguage = "http://localhost:8080/api/languages"
    let urifind = "http://localhost:8080/api/find?name="
    let uriget = "http://localhost:8080/api/get?name="
    fetch(urilanguage).then((response) => response.json().then(response => {
        response.map(item => $languages = [...$languages, item])
    }))



    function find() {

        if (!choosed.includes(input.value.toUpperCase())){
            fetch(urifind + input.value, {method: "POST"}).then(response =>{
                if (response.ok){
                    response.json().then((response) => {
                        fetch(uriget + input.value, {method: "GET"}).then(r => {
                            if (r.ok){
                                r.json().then((r) => {
                                    $languages=$languages.filter(item=>item.name.toUpperCase()!==input.value.toUpperCase())
                                    choosed=[...choosed,input.value.toUpperCase()]
                                    input.value=''
                                    let arr = Object.values(response)
                                    let match = arr.shift()
                                    let charac = Object.values(r)
                                    let item = charac.shift()
                                    answer = [...answer, {arr: arr, charac: charac, image: "data:image/png;base64, " + item}]
                                    header = true;
                                    if (match) {
                                        $count = 5;
                                    } else {
                                        $count++;
                                    }

                                })
                            }
                        })
                    })
                }
                else{
                    error()
                }
            })
        }
        else{
            error()
        }

    }
    function error(){
        input.value=''
        button.disabled=true
        input.animate([
            { transform: 'translateX(-5px)', borderColor: 'red' },
            { transform: 'translateX(5px)', borderColor: 'red' },
            { transform: 'translateX(-5px)', borderColor: 'red' },
            { transform: 'translateX(5px)', borderColor: 'red' },
            { transform: 'translateX(-5px)', borderColor: 'red' },
            { transform: 'translateX(0px)', borderColor: '' }
        ], {
            duration: 100,
            iterations: 5
        });
        setTimeout(() => {
            button.disabled = false;
        }, 500);
    }

    function enter(event){
        if (event.code === 'Enter') {
            find();
        }
    }


</script>
<main>
    <div class="inputbutton">
        <input placeholder="Type language name" list="languages" type="text" bind:this={input} on:keydown={(event)=> enter(event)}>
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
                <div class="square">Language</div>
                <div class="square">Typing</div>
                <div class="square">Compiling</div>
                <div class="square">Release</div>
                <div class="square">Creator</div>
                <div class="square">Paradigm</div>
                <div class="square">Purpose</div>
                <div class="square">Jobs</div>
            </div>
        {/if}
        <div class="vertical">
            {#each answer as obj }
                <div class="horizontal">
                    <div class="square">
                        <img in:fly="{{ y: 200, duration: 2000 }}" class="logo" src={obj.image} alt="true">
                    </div>
                    {#each obj.arr as item,i}
                        {#if (item === true)}
                            <div in:fly="{{ y: 200, duration: 2000 }}" class="square">
                                <p>{obj.charac.at(i)}</p>
                                <img src="src/assets/true.jpg" alt="true">
                            </div>
                        {/if}
                        {#if (item === false)}
                            <div in:fly="{{ y: 200, duration: 2000 }}" class="square">
                                <p>{obj.charac.at(i)}</p>
                                <img src="src/assets/false.jpg" alt="false">
                            </div>
                        {/if}
                        {#if (item === "More")}
                            <div in:fly="{{ y: 200, duration: 2000 }}" class="square">
                                <p>{obj.charac.at(i)}</p>
                                <img src="src/assets/high.jpg" alt="higher">
                            </div>
                        {/if}
                        {#if (item === "Less")}
                            <div in:fly="{{ y: 200, duration: 2000 }}" class="square">
                                <p>{obj.charac.at(i)}</p>
                                <img src="src/assets/low.jpg" alt="lower">
                            </div>
                        {/if}
                        {#if (item === "Perfect")}
                            <div in:fly="{{ y: 200, duration: 2000 }}" class="square">
                                <p>{obj.charac.at(i)}</p>
                                <img src="src/assets/true.jpg" alt="perfect">
                            </div>
                        {/if}
                    {/each}
                </div>
            {/each}
        </div>
    </div>
    {#if ($count===5)}
        <div class="card">
            <Win></Win>
        </div>
    {/if}

</main>

<style>
    .card{
        position: relative;
        align-content: center;
        align-items: center;
        text-align: center;

    }
    p {
        position: absolute;
        top: 40%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-size: 16px;
        white-space: normal;

    }

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

    .horizontal .square {
        position: relative;
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        overflow-wrap: break-word;
        overflow: hidden;
        border: white 2px solid;
    }

    .vertical {
        display: flex;
        flex-direction: column-reverse;
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

    .absolute {
        position: absolute;
        left: 0;
        right: 0;
        top: 20%;
        bottom: 0;


    }

    .inputbutton {
        position: absolute;
        left: 40%;
        right: 0;
        top: 12%;
        bottom: 0;
    }


</style>
