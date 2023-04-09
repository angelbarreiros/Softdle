<script>
  const formData= new FormData();
  let input;
  let urlimg="http://localhost:8080/users/download"
  fetch(urlimg).then((response)=>{
    response.text().then((yourByteArrayAsBase64)=>document.getElementById("ItemPreview").src = "data:image/png;base64," + yourByteArrayAsBase64)

  })


  function algo(){
    let url="http://localhost:8080/users/upload"
    for (const file of input.files) {
        formData.append("file", file);
        formData.append("id", 1)
        fetch(url, {
            method: "post",
            body: formData,
        }).then((response) => console.log(response)).catch((error) => console.log(error));
    }
  }
</script>

<main>

</main>

<style>
  
</style>
