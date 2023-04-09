<script>
  import {languages} from '/home/angel/Softdle/Softdle/frontend/src/lib/state.js'
  let input;
  let urilanguage="http://localhost:8080/api/languages"
  let urifind="http://localhost:8080/api/find?name="
  fetch(urilanguage).then((response)=>response.json().then(response => {
      response.map(item =>$languages=[...$languages,item])
  }))
  let answer=[];
  function find(){
      fetch(urifind+input.value,{method:"POST"}).then(response=> response.json().then((response)=>{
          let arr= Object.values(response)
          arr.shift()
          answer=arr
      }))

  }
  let button;
  function debug(){
    console.log(answer)
  }
  function onlyLanguages(){
      return $languages.map(item => item.name)
  }


</script>

<main>
<!--    <button on:click={()=>debug()}></button>-->
    <input  list="languages" type="text" bind:this={input}>
    <button bind:this={button} on:click={()=>find()}>TRY</button>
   <datalist id="languages">
       {#each $languages as item}
           <option value={item.name}></option>
       {/each}
   </datalist>

    <br>
    <div class="prueba">
        {#each answer as item}
            {#if (item===true)}
                <p>true</p>
            {/if}
            {#if (item===false)}
                <p>false</p>
            {/if}
            {#if (item==="More")}
                <p>More</p>
            {/if}
            {#if (item==="Less")}
                <p>Less</p>
            {/if}
            {#if (item==="Perfect")}
                <p>Perfect</p>
            {/if}
            {#if (item!=="algo")}
                <p>{item}</p>
            {/if}
        {/each}
    </div>

</main>

<style>
    .prueba{
        display: flex;
        flex-direction: row;
    }

  
</style>
