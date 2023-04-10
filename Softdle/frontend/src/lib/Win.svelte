<script>
export let win;
export let chances;
</script>
<div class="relative">
    <div class="prueba">dsaadasdasadsadsadsadsadsadsdas</div>
    <div class="card">
</div>

</div>
<style>
    .relative{
        display: flex;
        justify-content: center;
        align-content: center;
        position: relative;
        width: 100%;
        height: 100%;
        text-align: center;

    }
    .card {
        background-color: yellow;
        align-items: center;
        align-content: center;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 50%;
        height: 200vh;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        transition: 0.3s;
        z-index: 1;
    }
</style>