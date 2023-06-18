function sortBy(n){
    // tabelul
    var tabel = document.getElementById("tabel");
    var tipSort = "";
    var a, b, i ,j;
    // liniile tabelului
    var rows = tabel.rows;

    a = rows[1].getElementsByTagName("td")[n-1].innerHTML.toLowerCase();
    b = rows[1+1].getElementsByTagName("td")[n-1].innerHTML.toLowerCase();
    if(!isNaN(parseInt(a)) && !isNaN(parseInt(b))) {
		a = parseInt(a);
	    b = parseInt(b);
	}
    // verificam cum sunt sortate elementele initial
    if(a > b){
        // sunt sortate descrescator
        tipSort = "crescator"; // urmeaza sa sortam crescator
    }else{
        tipSort = "descrescator";
    }

    for(i = 1; i < (rows.length - 1); i++)
    {
        for(j = i+ 1; j < rows.length; j++)
        {
            var ok = false;
            //luam 2 valori din tabel
            a = rows[i].getElementsByTagName("td")[n-1].innerHTML.toLowerCase();
            b = rows[j].getElementsByTagName("td")[n-1].innerHTML.toLowerCase();

            if(!isNaN(parseInt(a)) && !isNaN(parseInt(b))) {
			    a = parseInt(a);
			    b = parseInt(b);
			}

            if(tipSort == "crescator"){
                if(a > b){
                    ok = true;
                }
            }
            else if(tipSort == "descrescator"){
                if(a < b){
                    ok = true;
                }
            }

            if(ok){
                // interschimba cele 2 randuri in tabel
                var temp = rows[i].innerHTML;
                rows[i].innerHTML = rows[j].innerHTML;
                rows[j].innerHTML = temp;
            }
            // console.log("i=",i,"j=",j, "a=",a,"b=",b);
            // console.log("ok=",ok);
        }
        console.log("i=",i);
    }
}

function sortBy2(n){
    // tabelul
    var tabel = document.getElementById("tabel2");
    var tipSort = "";
    var a, b, i, j, k;
    var rows = tabel.rows;
    var row = rows[n-1];

    a = row.getElementsByTagName("td")[0].innerHTML.toLowerCase();
    b = row.getElementsByTagName("td")[1].innerHTML.toLowerCase();
    if(!isNaN(parseInt(a)) && !isNaN(parseInt(b))) {
        a = parseInt(a);
        b = parseInt(b);
    }
    // verificam cum sunt sortate elementele initial
    if(a > b){
        // sunt sortate descrescator
        tipSort = "crescator"; // urmeaza sa sortam crescator
    }else{
        tipSort = "descrescator";
    }

    for(i = 0; i < (row.cells.length - 2); i++){
        for(j = i+1; j < (row.cells.length-1); j++){
            var ok = false;
            a = row.getElementsByTagName("td")[i].innerHTML.toLowerCase();
            b = row.getElementsByTagName("td")[j].innerHTML.toLowerCase();

            if(!isNaN(parseInt(a)) && !isNaN(parseInt(b))) {
                a = parseInt(a);
                b = parseInt(b);
            }

            if(tipSort == "crescator"){
                if(a > b){
                    ok = true;
                }
            }
            else if(tipSort == "descrescator"){
                if(a < b){
                    ok = true;
                }
            }
            // interschimbam coloanele si valorile
            if(ok){
                for (k = 0; k < rows.length; k++) {
                    var tempCol = rows[k].getElementsByTagName("td")[i].innerHTML;
                    var tempVal = rows[k].getElementsByTagName("td")[j].innerHTML;
                    rows[k].getElementsByTagName("td")[i].innerHTML = tempVal;
                    rows[k].getElementsByTagName("td")[j].innerHTML = tempCol;
                }
            }
        }
    }
}