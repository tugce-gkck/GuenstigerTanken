var user = new Vue({
    el: '#user',
    data: {
        username: 'none'
    },
    methods: {
        retrieveUsername: function () {
            var that = this;
            var session = get("session");
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == XMLHttpRequest.DONE) {
                    if (xhttp.status == 200) {
                        if (xhttp.responseText) {
                           that.username = xhttp.responseText;
                            document.getElementById("loginButton").style.visibility = "hidden";
                            document.getElementById("userInfo").style.visibility = "visible";
                            document.getElementById("abmeldenButton").style.visibility = "visible";
                        }
                    } else {
                        console.log(xhttp.status, xhttp.statusText);
                        document.getElementById("loginButton").style.visibility = "visible";
                        document.getElementById("userInfo").style.visibility = "hidden";
                        document.getElementById("abmeldenButton").style.visibility = "hidden";
                    }
                }
            };
            xhttp.open("GET", "/user?session=" + session, true);
            xhttp.send();
        },

    }
});
user.retrieveUsername();
// URL Parameter auslesen
function get(name){
    if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
};

document.getElementById("createForm").addEventListener('submit', function(evt){
    evt.preventDefault();
    var name = document.getElementById("inputName").value;
    var city = document.getElementById("inputCity").value;
    var price = document.getElementById("inputPrice").value;
    var distance = document.getElementById("inputDistance").value;
    var wc = document.getElementById("checkWC").checked;
    var restaurant = document.getElementById("checkRestaurant").checked;
    var carwash = document.getElementById("checkCarwash").checked;

    var body = {name: name, city: city, price: price,distance: distance,wc: wc, restaurant: restaurant, carwash: carwash};
    var bodyJson = JSON.stringify(body);
    var session = get("session");
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == XMLHttpRequest.DONE) {
            if (xhttp.status == 200) {
                alert("Neuer Eintrag konnte erfolgreich erstellt werden!");
                window.open("/?session=" + session, "_self");
            } else {
                alert("Eintrag konnte nicht erstellt werden!");
                console.log(xhttp.status, xhttp.statusText);
            }
        }
    };
    xhttp.open("POST", "/tanken?session=" + session, true);
    xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhttp.send(bodyJson);
});

document.getElementById("cancelButton").addEventListener('click', function(evt){
    evt.preventDefault();
    var session = get("session");
    window.open("/?session=" + session, "_self");
});

