
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
                                comp.TankenArray[i].date = new Date(comp.TankenArray[i].date);
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
function get(name){
    if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
};
app.retrieveAllTanken();

