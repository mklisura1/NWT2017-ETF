import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {HelperService} from "app/services/helper.service";

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

    constructor(private router: Router,
                private authService: AuthenticationService,
                private userService: UserService,
                private helperService: HelperService) {
    }

    logIn() {
        this.submitted = true;

        if (this.user.name && this.user.pass) {

            this.authService.getToken(this.user)
                .subscribe(
                    response => {
                        console.log("GET TOKEN", response);
                        if (response.token) {
                            this.submitted = false;
                            localStorage.setItem('tokenData', JSON.stringify(response));
                            this.getUser();
                        }
                    },
                    err => {
                        this.submitted = false;
                    }
                );

        }
    }

    getUser() {
        if (!localStorage.getItem('tokenData'))
            return;

        this.userService.getUser(JSON.parse(localStorage.getItem('tokenData')).userid)
            .subscribe(data => {
                localStorage.setItem('loggedUser', JSON.stringify(data));
                this.router.navigate(['/profile']);
                this.user = data;
            })
    }

    resetPassword() {
        if (this.usernameReset.length <= 0) return;
        this.userService.resetPassword(this.usernameReset)
            .subscribe(data => {
                this.helperService.showSuccess('Check your email!')
            },
            err =>{
                this.helperService.showError('Unable to reset password. Try again!');
            })
    }
}
