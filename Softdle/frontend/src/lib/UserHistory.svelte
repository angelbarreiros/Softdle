<script>
    import axios from "axios";

    import {hasNext, logged} from "../state.js";
    import History from "./History.svelte";
    const logout=()=>{
        axios.defaults.headers.common['Authorization']='';
        $logged=false
        document.cookie = "jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/; SameSite=Lax;";
        window.location.href = '/';

    }
     let config= {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'users/history?page=0',
        headers: {
            'Content-Type': 'application/json'
        },
    };
    let page =0
    let next;
    let prev;
    const  nextPage=()=>{
        prev.disabled=false
        page=page+1
        config.url='users/history?page='+page
    }

    const  prevPage=()=>{
        next.disabled=false
        page=page-1;
        if (page-1<0){
            prev.disabled=true
        }
        config.url='users/history?page='+page

    }
    const cambio=()=>{
        next.disabled=$hasNext
    }

</script>

<div  class="container">
    <History config={config} on:cambio={cambio}></History>
    <div>
        <button disabled="disabled" bind:this={prev}  on:click={prevPage} >Previous page</button>

        <button on:click={logout}>
            log out
        </button>
        <button   bind:this={next} on:click={nextPage} >Next page</button>

    </div>
</div>
<style>

    .container{
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }
</style>
