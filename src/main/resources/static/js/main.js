
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
                if (xhttp.readyState == XMLHttpRequest.DONE && xhttp.status == 200) {
                    console.log(xhttp.status, xhttp.statusText);
                    comp.TankenArray = xhttp.responseText;
                } else {
                    console.log(xhttp.status, xhttp.statusText);
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

