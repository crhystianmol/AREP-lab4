var button = document.getElementById('button');
	button.addEventListener('click', function () {
		var url=window.location.href+'/mensajes';
		var mensaje=document.getElementById("mensaje").value;
		axios.post(url,mensaje)
			.then(res => {
					getMessages()
				}
			)
	});

function getMessages(){
    var url=window.location.href+'/mensajes';
    $("#table_messages > tbody").empty();
    axios.get(url).then(res=>{
        res.data.map(mensaje=>{
            $("#table_messages > tbody").append(
                "<tr>" +
                "<td>" + mensaje.mensaje + "</td>" +
                "</tr>"
            );
        })
    })

}