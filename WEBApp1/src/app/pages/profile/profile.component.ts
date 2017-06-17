import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  editableForm: boolean = false;
  user: any = {};
  constructor(private userService: UserService) {
    this.getUser();

  }

  showDatePicker:
  boolean = false;
  ngOnInit() {

  }

  getUser(){

    this.user = JSON.parse(localStorage.getItem('loggedUser'));

    // this.userService.getUser(JSON.parse(localStorage.getItem('tokenData')).userid)
    //   .subscribe( data=> {
    //     console.log("USER DATA", data);
    //     localStorage.setItem('loggedUser', JSON.stringify(data));
    //     this.user = data;
    //   })
  }

  changeForm(){
    console.log("change form", this.editableForm);
    if(!this.editableForm){
      let tempUser = {
        first_name: this.user.firstName,
        last_name: this.user.lastName,
        email: this.user.email,
        jmbg: this.user.jmbg,
        address: this.user.address,
        birth_date: this.user.birthDate,
          mobile: this.user.mobile
      }
    }
  }

  open(){
    this.showDatePicker = true;
  }

  onSelectionDone(event) {
    //this.internalTempObj.date = event;
    this.showDatePicker = false;
  }

}
