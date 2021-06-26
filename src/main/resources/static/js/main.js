
var app = new Vue({
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

    }
});
// URL Parameter auslesen
function get(name){
    if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
};
app.retrieveAllTanken();

