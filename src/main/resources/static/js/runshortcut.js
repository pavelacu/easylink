function getShortlinkRest(){
    fetch('http://localhost/checktool/dato.json', {
      method: "GET",
      headers: {"Content-type": "application/json;charset=UTF-8", 
      'Access-Control-Allow-Origin': '*' }
    })
    .then(response => response.json())
    .then(json => setLabelLink(json))
    .catch(err => console.log(err));	
}

function postShortlinkRest(){
	var serverUrl = 'cut/'	
	var myurl = document.getElementById("myurl");		
	var datos = {
	  url: myurl.value
	}
	
	myHeaders = new Headers({
		"Content-Type": "application/json",		
		"Access-Control-Allow-Origin": "*",
		"Access-Control-Allow-Methods": "POST",
		"Access-Control-Allow-Headers": "Authorization",
		"crossDomain": "true"
		
	});

	fetch(serverUrl, {
		method: 'POST',	
		mode: 'cors',
		headers: myHeaders,
		body: JSON.stringify(datos)		
	})
	.then(response => response.text()) 
	.then(text => setLabelLinkTxt(text))
	.catch(err => console.log(err));
	
}

function setLabelLinkTxt(data){	
	var shortlink = window.location.href +"fw/"+ data
	document.getElementById("labellink").innerHTML = shortlink
}


function setLabelLink(jsondata){	
	var shortlink = jsondata['name']
	document.getElementById("labellink").innerHTML = shortlink
}

function myCopy() {
   	var copyText = document.getElementById("labellink");	
	navigator.clipboard.writeText(copyText.innerHTML);
  	alert("Copied the text: " + copyText.value);
}