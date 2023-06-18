const tabel = $("#tabel")[0];
tabel.innerHTML = "";
let values = [1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8];

// amestecarea valorilor 
for (let i = values.length - 1; i > 0; i--) {
   const j = Math.floor(Math.random() * (i + 1));
   [values[i], values[j]] = [values[j], values[i]];
}

const dim = Math.sqrt(values.length);
for(let i = 0; i < dim; i++){
	var row = $("<tr>");
	for(let j = 0; j < dim; j++){
		const item = $("<td>");
		item.text(values[i * dim + j]);
		item.css("background-color", "black");
		item.click(()=>checkItems(item));
		row.append(item);
	}
	$(tabel).append(row);
}

let firstItem = null;
let secondItem = null;

function checkItems(item){
	if($(item).css("background-color") === "rgb(255, 255, 255)"){
		return; //deja descoperita
	}

	$(item).css("background-color", "white");

	if(firstItem === null)
		firstItem = item;
	else if(secondItem === null){
		secondItem = item;

		//verificam daca se potrivesc
		if($(firstItem).text() === $(secondItem).text()){
			// se potrivesc
			firstItem = null;
			secondItem = null;
		}
		else{
			// nu se potrivesc
			setTimeout(() => {
				$(firstItem).css("background-color", "black");
				$(secondItem).css("background-color", "black");
				firstItem = null;
				secondItem = null;
			}, 2000);
		}
	}
}
