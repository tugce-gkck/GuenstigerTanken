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
                            document.getElementById("createButton").style.visibility = "visible";
                            document.getElementById("abmeldenButton").style.visibility = "visible";

                        }
                    } else {
                        console.log(xhttp.status, xhttp.statusText);
                        document.getElementById("loginButton").style.visibility = "visible";
                        document.getElementById("userInfo").style.visibility = "hidden";
                        document.getElementById("createButton").style.visibility = "hidden";
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
var tanken = new Vue({
    el: '#contents',
    data: {
        TankenArray:[]
    },
    methods: {
        retrieveAllTanken: function () {
            var comp = this;
            var session = get("session");
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == XMLHttpRequest.DONE) {
                    if (xhttp.status == 200) {
                        if (xhttp.responseText) {
                            comp.TankenArray = JSON.parse(xhttp.responseText);
                            for (var i = 0; i < comp.TankenArray.length; i++) {
                                var date = new Date(comp.TankenArray[i].date);
                                const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
                                comp.TankenArray[i].date = date.toLocaleDateString('de-DE', options);

                                var price = comp.TankenArray[i].price.toString();
                                comp.TankenArray[i].price = price.replace('.',',').replace(' ','')
                                var distance = comp.TankenArray[i].distance.toString();
                                comp.TankenArray[i].distance = distance.replace('.',',').replace(' ','')
                            }
                        }
                    } else {
                        console.log(xhttp.status, xhttp.statusText);
                    }
                }
            };
            xhttp.open("GET", "/tanken?session=" + session, true);
            xhttp.send();
        },
        onClickRow: function(index){
            var session = get("session");
            var id = this.TankenArray[index].id;
            window.open("/" + id + "/?session=" + session, "_self");
        }
    }
});
// URL Parameter auslesen
function get(name){
    if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
};
tanken.retrieveAllTanken();

