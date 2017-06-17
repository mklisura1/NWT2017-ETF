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
    editableUser = false;
    showBirthDatePicker = false;
    user = {
        first_name: '',
        last_name: '',
        birth_date: new Date(),
        username: '',
        password: '',
        email: '',
        address: '',
        jmbg: '',
        mobile: ''
    };
    formSubmitted = false;
    users:any[] = [];
    constructor(private userService: UserService, private helperService: HelperService) {
    }

    ngOnInit() {
    }

    getAllUsers(){
        this.addUser = false;
        this.userService.getAllUsers()
            .subscribe(
                response => this.users = response
            )
    }
    removeUser(id){
        this.userService.deleteUser(id)
            .subscribe(
                response => {
                    this.getAllUsers();
                    this.helperService.showSuccess('User successfully deleted!');
                    this.editableUser = false;
                    this.addUser = false;
                    this.clearForm();
                    },
                (err) => this.helperService.showError('Error while deleting user!')
            )
    }

    cancel(){
        this.user = {
            first_name: '',
            last_name: '',
            birth_date: new Date(),
            username: '',
            password: '',
            email: '',
            address: '',
            jmbg: '',
            mobile: ''
        };
        this.editableUser = false;
        this.addUser = false;
    }
    editUser(user){
        this.user = user;
        this.user.password = '';
        this.editableUser = true;
        this.addUser = true;
    }

    updateUser(){
        this.formSubmitted = true;
        if(this.formValidation()){
            this.userService.updateUser(this.user)
                .subscribe(
                    response => {
                        console.log("update user", response);
                        this.helperService.showSuccess('User is successfully updated!');
                        this.clearForm();
                        this.getAllUsers();
                        this.addUser = false;
                        this.editableUser = false;
                    },
                    (err) => {
                        this.helperService.showError('Error while editing user!')

                    }
                )
        }else{
            this.helperService.showInfo('Please fill up form !')
        }
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
            jmbg: '',
            mobile: ''
        };
    }

    submitUser(){
        this.formSubmitted = true;
        if(this.formValidation()){
            this.userService.insertUser(this.user)
                .subscribe(
                    response => {
                        console.log("insert user", response);
                        this.helperService.showSuccess('User is successfully added!');
                        this.clearForm();
                    },
                    (err) => {
                        this.helperService.showError('Error while adding user!')

                    }
                )
        }else{
            this.helperService.showInfo('Please fill up form !')
        }
    }

    private formValidation() {
        return (this.user.first_name.length > 0 && this.user.last_name.length > 0 && this.user.address.length > 0
        && this.user.email.length > 0 && this.user.birth_date &&
        this.user.jmbg.length > 0);
    }
}
