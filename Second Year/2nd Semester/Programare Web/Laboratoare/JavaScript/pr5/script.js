
document.addEventListener("DOMContentLoaded", function() {
	// punem in div prima imagine
	setCurrent(0);
  	
  	// la 5 secunde schimbam imaginea din div
	setInterval(function() {
	    var currentIndex=getCurrentIndex();
	    var elems = document.querySelectorAll("#lista li");
		var nr = elems.length;

		currentIndex++
		if(currentIndex === nr){
			currentIndex=0;
		}
		setCurrent(currentIndex);
    }, 5000); // timpul in milisecunde
});


function changeImg(n){ // schimbam imaginea in div, fct apelata de butoane
	var elems = document.querySelectorAll("#lista li");
 	var nr = elems.length;

  	var currentIndex=getCurrentIndex(); // preluam indexul imaginii curente din div
	// console.log("ind=",currentIndex)
	if(n === 1){ // daca am apasat next incrementam indexul
		 currentIndex++
		 if(currentIndex === nr){ // daca am ajuns la final revenim la prima imagine
		 	currentIndex=0;
		 }
	}
	else
	{ // daca am apasat previous decrementam indexul
		currentIndex--
	  	if(currentIndex === -1){ // daca am ajuns la inceput revenim la ultima imaginez
	  		currentIndex=nr-1
	  	}
	}	
	setCurrent(currentIndex);
}

function setCurrent(ind){ // setam la div informatia din elementul cu indexul ind al listei
  var valDiv = document.getElementById("imgCurenta");
  valDiv.innerHTML = ''; // sterge continutul vechi din div
  var element = document.querySelectorAll("#lista li")[ind];
  var imagine = element.firstElementChild.cloneNode(true);
  var link = document.createElement("a");
  link.href = element.lastElementChild.href;
  link.appendChild(imagine);
  valDiv.appendChild(link);
}

function getCurrentIndex(){ // aflam indexul elementului din lista care e afisat
	var elems = document.querySelectorAll("#lista li");
  	var valDiv = document.getElementById("imgCurenta");

 	var nr = elems.length;
  	var currentIndex = 0;

  	for(let i = 0; i < nr; i++){
  	 	if(valDiv.querySelector("img").src === elems[i].querySelector("img").src)
  	 	{
  	 		if(valDiv.querySelector("a").ahref === elems[i].querySelector("a").ahref)
  	 			currentIndex = i;
  	 	}
  	}
  	return currentIndex;
}