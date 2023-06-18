$(document).ready (function() {
    $("#btn").click(function() {
    console.log("AICI")
    var nume = $('#nume').val();
    var dataNasterii = new Date($('#data_nasterii').val());
    var varsta = $('#varsta').val();
    var email = $('#email').val();

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
        $('#nume').css('border-color', 'red');
    else
        $('#nume').css('border-color', 'black');

    // validare data
    ok = 1;
    if(!dataNasterii || isNaN(dataNasterii.getTime())){
        ok = 0;
        mesaj += "Data nasterii nu a fost completata!<br>";
    }
    if(dataNasterii > Date.now()){
        ok = 0;
        mesaj += "Data nasterii nu a fost completata corect!<br>";
    }
    if(ok == 0)
        $('#data_nasterii').css('border-color', 'red');
    else
        $('#data_nasterii').css('border-color', 'black');


    // validare varsta
    ok = 1;
    if(varsta.length == 0){
        ok = 0;
        mesaj += "Varsta nu a fost completata!<br>"
    }
    let gr = 1;
    for(let character of varsta)
        if(character < '0' || character > '9'){
            ok = 0;
            gr = 0;
        }
    if(gr == 0 && ok == 0)
        mesaj += "Varsta nu a fost completata corect!<br>"
    if(ok == 0)
        $('#varsta').css('border-color', 'red');
    else
        $('#varsta').css('border-color', 'black');

    // validare email
    ok = 1;
    if(email.length == 0){
        ok = 0;
        mesaj += "Email-ul nu a fost completat!<br>"
    }
    else if(!email.match(/^.*@.*$/g)){
        ok = 0;
        mesaj += "Email-ul nu a fost completat corect<br>"
    }
    if(ok == 0)
        $('#email').css('border-color', 'red');
    else
        $('#email').css('border-color', 'black');

    if(mesaj == "")
        $('#mesaj').html("Campurile sunt completate corect!");
    else 
        $('#mesaj').html(mesaj);
});

});

