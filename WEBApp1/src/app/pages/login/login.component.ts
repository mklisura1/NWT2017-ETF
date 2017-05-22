import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
    templateUrl: 'login.component.html'
})
export class LoginComponent {
    submitted = false;
    user = {
        name: 'svlada3@gmail.com',
        pass: 'test1234'
    };

    username: string;
    password: string;
    forgetPassword: boolean = false;
    usernameReset: '';

    constructor(private router: Router, private authService: AuthenticationService) {
    }

    logIn() {
        this.submitted = true;



        if (this.user.name && this.user.pass) {
            this.submitted = false;
            this.authService.getToken(this.user)
                .subscribe(
                    response => {
                        if(response.token){
                            localStorage.setItem('tokenData', JSON.stringify(response));
                            this.router.navigate(['/profile']);
                        }
                    }
                )
            this.user.name = '';
            this.user.pass = '';
        }
    }

    resetPassword(){
        if(this.usernameReset.length <= 0) return;
        //call the microservice
    }
}
