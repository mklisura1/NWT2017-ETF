import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {HelperService} from "../../services/helper.service";

@Component({
    selector: 'app-add-user',
    templateUrl: './add-user.component.html',
    styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

    addUser = true;
    showBirthDatePicker = false;
    user = {
        first_name: '',
        last_name: '',
        birth_date: new Date(),
        username: '',
        password: '',
        email: '',
        address: '',
        jmbg: ''
    };
    formSubmitted = false;
    constructor(private userService: UserService, private helperService: HelperService) {
    }

    ngOnInit() {
    }

    clearForm(){
        this.user = {
            first_name: '',
            last_name: '',
            birth_date: new Date(),
            username: '',
            password: '',
            email: '',
            address: '',
            jmbg: ''
        };
    }

    submitUser(){
        this.formSubmitted = true;
        if(this.formValidation()){
            this.userService.insertUser(this.user)
                .subscribe(
                    response => {
                        console.log("insert user", response);
                        this.helperService.showSuccess('User is successfully added!')
                    }
                )
        }else{
            this.helperService.showInfo('Please fill up form !')
        }
    }

    private formValidation() {
        return (this.user.first_name.length > 0 && this.user.last_name.length > 0 && this.user.address.length > 0
        && this.user.email.length > 0 && this.user.birth_date && this.user.password.length > 0 &&
        this.user.jmbg.length > 0);
    }
}
