import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
    selector: 'app-dashboard',
    templateUrl: './full-layout.component.html'
})
export class FullLayoutComponent implements OnInit {

    public disabled = false;
    public status: { isopen: boolean } = {isopen: false};
    loggedUser: any = {};

    public toggled(open: boolean): void {
        console.log('Dropdown is now: ', open);
    }

    constructor(private router: Router, private userService: UserService) {
        this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
    }

    public toggleDropdown($event: MouseEvent): void {
        $event.preventDefault();
        $event.stopPropagation();
        this.status.isopen = !this.status.isopen;
    }

    ngOnInit(): void {
    }

    logout() {
        localStorage.removeItem('loggedUser');
        localStorage.removeItem('tokenData');
        this.router.navigate(['/login']);
    }
}
