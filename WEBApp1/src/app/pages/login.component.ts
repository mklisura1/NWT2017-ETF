import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  templateUrl: 'login.component.html'
})
export class LoginComponent {
  submitted = false;
  user = {
    name: '',
    pass: ''
  };
  constructor(private router: Router) { }

  logIn(){
    this.submitted = true;
    this.router.navigate(['/payments/internal']);
    if(this.user.name && this.user.pass){
     this.submitted = false;
     this.user.name = '';
     this.user.pass = '';
    }
  }
}
