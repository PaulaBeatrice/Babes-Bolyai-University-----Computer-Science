function valideazaDatele()
{
	var nume = document.getElementById("nume").value;
    var dataNasterii = document.getElementById("data_nasterii").valueAsDate;
    var varsta = document.getElementById("varsta").value;
    var email = document.getElementById("email").value;

    var mesaj = "";
    // validare nume
    var ok = 1;
    if(nume.length == 0){
    	ok = 0;
    	mesaj += "Numele nu a fost completat!<br>";
    }
    for(let character of nume)
    	if(character >= '0' && character <= '9'){
    		ok = 0;
    		mesaj += "Numele nu a fost completat corect!<br>";
    	}
    if(ok == 0)
    	document.getElementById("nume").style.borderColor = "red";
    else
    	document.getElementById("nume").style.borderColor = "black";

    // validare data
    ok = 1;
    if(!dataNasterii){
    	ok = 0;
    	mesaj += "Data nasterii nu a fost completata!<br>";
    }
    if(dataNasterii > Date.now()){
    	ok = 0;
    	mesaj += "Data nasterii nu a fost completata corect!<br>";
    }
    if(ok == 0)
    	document.getElementById("data_nasterii").style.borderColor = "red";
    else
    	document.getElementById("data_nasterii").style.borderColor = "black";


    // validare varsta
    ok = 1;
    if(varsta.length == 0){
    	ok = 0;
    	mesaj += "Varsta nu a fost completata!<br>"
    }
   	for(let character of varsta)
   		if(character < '0' || character > '9'){
   			ok = 0;
   			mesaj += "Varsta nu a fost completata corect!<br>"
   		}
   	if(ok == 0)
   		document.getElementById("varsta").style.borderColor = "red";
   	else
   		document.getElementById("varsta").style.borderColor = "black";

   	// validare email
   	ok = 1;
   	if(email.length == 0){
   		ok = 0;
   		mesaj += "Email-ul nu a fost completat!<br>"
   	}
   	else if(email.match(/^.*@.*$/g) == null){
   		ok = 0;
   		mesaj += "Email-ul nu a fost completat corect<br>"
   	}
   	if(ok == 0)
   		document.getElementById("email").style.borderColor = "red";
   	else
   		document.getElementById("email").style.borderColor = "black";

    if(mesaj == "")
    	document.getElementById("mesaj").innerHTML = "Campurile sunt completate corect!";
    else 
    	document.getElementById("mesaj").innerHTML = mesaj;
}	