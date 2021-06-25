Vue.component('tanken-liste', {
    props: [],
    template: `<div class="container center_div"><h3>Login</h3>
            <hr />
             <span style="visibility: hidden" id="errorLogin" class="label label-danger">Falsche Login-Daten!</span>
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

                <hr />
                <!--<button type="button" class="btn btn-link">Signup</button>
                <button type="button" class="btn btn-link">Reset Password</button>-->
            </form></div>`
});



var app = new Vue({
    el: '#contents',
    data: {
    },
    methods: {

    }
});