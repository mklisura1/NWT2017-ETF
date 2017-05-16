import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  editableForm: boolean = false;
  user: any;
  constructor() {
    this.user = UserService.getUser();

  }

  showDatePicker: boolean = false;

  ngOnInit() {

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
        birth_date: this.user.birthDate
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
