/**
 * Created by sharis on 22.5.2017.
 */
import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

import { AuthenticationService } from './authentication.service';


@Injectable()
export class AuthenticationGuard implements CanActivate {

  constructor(private router: Router,
              private authenticationService: AuthenticationService) { }

  canActivate(): boolean {
    if (this.authenticationService.isAuthenticated()) {
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }

}
