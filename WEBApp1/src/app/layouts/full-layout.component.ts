import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './full-layout.component.html'
})
export class FullLayoutComponent implements OnInit {

  public disabled = false;
  public status: {isopen: boolean} = {isopen: false};
  loggedUser: any;
  public toggled(open: boolean): void {
    console.log('Dropdown is now: ', open);
  }

  constructor(private router: Router){
    this.loggedUser = UserService.getUser();
  }

  public toggleDropdown($event: MouseEvent): void {
    $event.preventDefault();
    $event.stopPropagation();
    this.status.isopen = !this.status.isopen;
  }

  ngOnInit(): void {}

  logout(){
    this.router.navigate(['/login']);
  }
}
