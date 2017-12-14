function showAdd() {
    var y = document.getElementById("updateForm");
    if(y.style.display==="block"){
        y.style.display ="none";
    }
    var x = document.getElementById("addForm");
    if(x.style.display==="none"){
        x.style.display ="block";
    }else {
        x.style.display = "none";
    }
    setTime();
}
function showUpdate() {
    var x = document.getElementById("addForm");
    if(x.style.display==="block"){
        x.style.display ="none";
    }

    var y = document.getElementById("updateForm");
    if(y.style.display==="none"){
        y.style.display="block";
    }else {
        y.style.display="none";
    }
    setTime();
}
function setTime() {
    var date = new Date();

    var formatyed =  (date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2)
        + "T" + date.getHours() + ":" + ('0' + date.getMinutes()).slice(-2));
    var times =  document.getElementsByClassName("period");

    for(var i=0;i<2;i++){
        times[i].setAttribute("min",formatyed);
    }
}
function sendAudio() {
    var val = document.getElementsByTagName("span");
        var warn=0;
        var critical=0;
        for (var i=0;i<val.length;i++){
            if(val[i].innerHTML === "WARNING"){
                warn++;

            }else if(val[i].innerHTML === "CRITICAL"){
                critical++;
            }

        }
        if(warn>0&& critical===0){
            document.getElementById('audioW').play();
        }
        if(critical>0){
            document.getElementById('audioC').play();
        }

    }

function deleteUrl(idUrl) {
    var url = "/delete/"+idUrl;
    $(".table-block").load(url);
}

function getPlay(idUrl) {
    var url = "/pause/"+idUrl;
    $(".table-block").load(url);
}

setInterval(retrieveUrl,12000);

function retrieveUrl() {
    var url = '/refresh';
    $(".table-block").load(url);
    sendAudio();
}





