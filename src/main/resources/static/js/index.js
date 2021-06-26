Vue.component('login-new', {
        props: ['user'],
        template: `<div class="container center_div"><h3>Login</h3>
            <hr />
             <span style="visibility: hidden" id="errorLogin" class="label label-danger">Falsche Login-Daten!</span>
             <span style="visibility: hidden" id="successRegister" class="label label-success">User {{ user }} Erfolgreich registriert!</span>
             <span style="visibility: hidden" id="errorRegister" class="label label-danger">Registrierung User {{ user }} nicht möglich!</span>
            <br />
            <form id="loginForm">
                <div class="form-group">
                    <label for="inputUsername">Benutzername</label>
                    <input type="username" class="form-control" id="inputUsername" placeholder="Benutzername">
                </div>
                <div class="form-group">
                    <label for="inputPassword">Passwort</label>
                    <input type="password" class="form-control" id="inputPassword" placeholder="Passwort">
                </div>
               <button class="btn btn-primary" type="submit">Login</button>
               <button id="registerButton" class="btn btn-secondary" >Registrieren</button>

                <hr />
                <!--<button type="button" class="btn btn-link">Signup</button>
                <button type="button" class="btn btn-link">Reset Password</button>-->
            </form></div>`
    });



    var app = new Vue({
        el: '#contents',
        data: {
            user: 'none'
        },
        methods: {
            onRegisterClick: function(evt){
                evt.preventDefault();
                var username = document.getElementById("inputUsername").value;
                var password = document.getElementById("inputPassword").value;
                var body = {username: username, password: password };
                var bodyJson = JSON.stringify(body);
                var that = this;
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (xhttp.readyState == XMLHttpRequest.DONE) {
                        that.user = username;
                        if (xhttp.status == 200) {

                            document.getElementById("errorLogin").style.visibility = "hidden";
                            document.getElementById("successRegister").style.visibility = "visible";
                            document.getElementById("errorRegister").style.visibility = "hidden";
                        } else {
                            document.getElementById("errorLogin").style.visibility = "hidden";
                            document.getElementById("successRegister").style.visibility = "hidden";
                            document.getElementById("errorRegister").style.visibility = "visible";
                        }
                    }
                };
                xhttp.open("POST", "/register", true);
                xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
                xhttp.send(bodyJson);

            }
        }
    });



document.getElementById("loginForm").addEventListener('submit', function(evt){
    evt.preventDefault();
    var username = document.getElementById("inputUsername").value;
    var password = document.getElementById("inputPassword").value;
    var body = {username: username, password: password };
    var bodyJson = JSON.stringify(body);
    var that = this;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == XMLHttpRequest.DONE) {
            if (xhttp.status == 200) {
                var session = this.responseText;
                document.getElementById("errorLogin").style.visibility = "hidden";
                document.getElementById("successRegister").style.visibility = "hidden";
                document.getElementById("errorRegister").style.visibility = "hidden";
                window.open("/?session=" + session, "_self");

            } else {
                document.getElementById("errorLogin").style.visibility = "visible";
                document.getElementById("successRegister").style.visibility = "hidden";
                document.getElementById("errorRegister").style.visibility = "hidden";
            }
        }
    };
    xhttp.open("POST", "/login", true);
    xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhttp.send(bodyJson);

});

document.getElementById("registerButton").addEventListener('click', function(evt){
    evt.preventDefault();
    var username = document.getElementById("inputUsername").value;
    var password = document.getElementById("inputPassword").value;
    var body = {username: username, password: password };
    var bodyJson = JSON.stringify(body);
    var that = this;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == XMLHttpRequest.DONE) {
            if (xhttp.status == 200) {
                app.user = username;
                document.getElementById("errorLogin").style.visibility = "hidden";
                document.getElementById("successRegister").style.visibility = "hidden";
                document.getElementById("errorRegister").style.visibility = "hidden";
                window.open("/?session=" + session, "_self");

            } else {
                document.getElementById("errorLogin").style.visibility = "visible";
                document.getElementById("successRegister").style.visibility = "hidden";
                document.getElementById("errorRegister").style.visibility = "hidden";
            }
        }
    };
    xhttp.open("POST", "/login", true);
    xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhttp.send(bodyJson);

});