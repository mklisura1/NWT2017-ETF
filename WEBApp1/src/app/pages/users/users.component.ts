import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: any[];
  public addUserModal;
  showDatePicker: boolean = false;
  user = {
    userId: '12345'
  };
  userTempObj = {
    date: new Date(),
    firstName: '',
    lastName: '',
    address: '',
    email: '',
    jmbg: '',
    phoneNumber: '',
    username: '',
    userId: null
  };
  formSubmitted: false;
  constructor(private userService: UserService) {
    this.getAllUsers();
  }

  ngOnInit() {

  }

  getAllUsers(){
    this.userService.getAllUsers()
      .subscribe(
        response => {
          this.users = response;
        }
      )
  }

  openAddUserModal(){

  }

  open(){
    this.showDatePicker = true;
  }

  onSelectionDone(event) {
    //this.internalTempObj.date = event;
    this.showDatePicker = false;
  }

}
