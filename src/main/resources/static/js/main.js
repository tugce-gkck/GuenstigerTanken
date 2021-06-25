Vue.component('tanken-entry', {
    props: ['tanken'],
    template: `
        <tr>
            <td>{{ tanken.date }}</td>
            <td>{{ tanken.name }}</td>
            <td>{{ tanken.city }}</td>
            <td>{{ tanken.price }}</td>
            <td>{{ tanken.distance }}</td>
        </tr>`
});

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
                if (this.status == 200) {
                    comp.TankenArray = JSON.parse(this.responseText);
                } else {
                    console.log(this.status, this.statusText);
                }
            };
            xhttp.open("GET", "/tanken?session='" + session + "'", true);
            xhttp.send();
        },
        get: function(name){
            if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
                return decodeURIComponent(name[1]);
        }
    }
});

app.$refs.tabelle.retrieveAllTanken();

