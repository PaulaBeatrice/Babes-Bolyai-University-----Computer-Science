function sortBy(n){
    // tabelul
    var tabel = $("#tabel");
    var tipSort = "";
    var a, b, i ,j;
    // liniile tabelului (fara antete)
    var rows = tabel.find("tr").not("tr:first-child");

    a = rows[0].getElementsByTagName("td")[n-1].innerHTML.toLowerCase();
    b = rows[0+1].getElementsByTagName("td")[n-1].innerHTML.toLowerCase();
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

    for(i = 0; i < (rows.length - 1); i++)
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
        }
    }
}



function sortBy2(n) {
    // tabelul
    var tabel = $("#tabel2");
    var tipSort = "";
    var a, b, i, j, k;

    var row = tabel.find("tr").eq(n - 1);
    a = row.find("td").eq(0).text().toLowerCase();
    b = row.find("td").eq(1).text().toLowerCase();
    if (!isNaN(parseInt(a)) && !isNaN(parseInt(b))) {
        a = parseInt(a);
        b = parseInt(b);
    }

    // verificam cum sunt sortate elementele initial
    if (a > b) {
        // sunt sortate descrescator
        tipSort = "crescator"; // urmeaza sa sortam crescator
    } else {
        tipSort = "descrescator";
    }

    for (i = 0; i < row.find("td").length - 1; i++) {
        for (j = i + 1; j < row.find("td").length ; j++) {
            var ok = false;
            a = row.find("td").eq(i).text().toLowerCase();
            b = row.find("td").eq(j).text().toLowerCase();

            if (!isNaN(parseInt(a)) && !isNaN(parseInt(b))) {
                a = parseInt(a);
                b = parseInt(b);
            }

            if (tipSort == "crescator") {
                if (a > b) {
                    ok = true;
                }
            } else if (tipSort == "descrescator") {
                if (a < b) {
                    ok = true;
                }
            }
            // interschimbam coloanele si valorile
            if (ok) {
                tabel.find("tr").each(function() {
                    var tempCol = $(this).find("td").eq(i).html();
                    var tempVal = $(this).find("td").eq(j).html();
                    $(this).find("td").eq(i).html(tempVal);
                    $(this).find("td").eq(j).html(tempCol);
                });
            }
        }
    }
}
