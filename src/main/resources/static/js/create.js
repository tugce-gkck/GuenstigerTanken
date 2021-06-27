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
                        }
                    } else {
                        console.log(xhttp.status, xhttp.statusText);
                        document.getElementById("loginButton").style.visibility = "visible";
                        document.getElementById("userInfo").style.visibility = "hidden";
                    }
                }
            };
            xhttp.open("GET", "/user?session=" + session, true);
            xhttp.send();
        },

    }
});
user.retrieveUsername();
var tanken = new Vue({
    el: '#contents',
    data: {
        tanken:{
            "id": "id",
            "date": "date",
            "name": "name",
            "city": "city",
            "reporter": "reporter",
            "price": "1,0",
            "distance": "1,0",
            "wc": true,
            "restaurant": true,
            "carwash": true
        },
        id: '0'
    },
    methods: {
        retrieveTanken: function () {
            var that = this;
            var session = get("session");
            var id = getPathId();
            var xhttp = new XMLHttpRequest();
            that.id = id;
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == XMLHttpRequest.DONE) {
                    if (xhttp.status == 200) {
                        if (xhttp.responseText) {
                            that.tanken = JSON.parse(xhttp.responseText);
                            var date = new Date(that.tanken.date);
                            const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
                            that.tanken.date = date.toLocaleDateString('de-DE', options);

                            var price = that.tanken.price.toString();
                            that.tanken.price = price.replace('.',',').replace(' ','')
                            var distance = that.tanken.distance.toString();
                            that.tanken.distance = distance.replace('.',',').replace(' ','')

                        }
                    } else {
                        console.log(xhttp.status, xhttp.statusText);
                    }
                }
            };
            xhttp.open("GET", "/tanken/" + id + "/?session=" + session, true);
            xhttp.send();
        },
        deleteTanken: function () {
            var that = this;
            var session = get("session");
            var id = getPathId();
            var xhttp = new XMLHttpRequest();
            that.id = id;
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == XMLHttpRequest.DONE) {
                    if (xhttp.status == 200) {
                        alert("Erfolgreich gelöscht!");
                        window.open("/?session=" + session, "_self");
                    } else {
                        alert("Löschen hat nicht funktioniert!");
                        console.log(xhttp.status, xhttp.statusText);
                    }
                }
            };
            xhttp.open("DELETE", "/tanken/" + id + "/?session=" + session, true);
            xhttp.send();
        }

    }
});
// URL Parameter auslesen
function get(name){
    if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
};
function getPathId(){
    return window.location.pathname.replaceAll("/","");
};

tanken.retrieveTanken();

