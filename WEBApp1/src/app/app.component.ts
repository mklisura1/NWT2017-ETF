import { Component } from '@angular/core';
import { ToastrConfig } from 'ngx-toastr';

@Component({
  // tslint:disable-next-line
  selector: 'body',
  template: '<router-outlet></router-outlet>'
})
export class AppComponent {
    constructor(toastrConfig: ToastrConfig){
        toastrConfig.timeOut = 5000;
        toastrConfig.newestOnTop = true;
        toastrConfig.progressBar = true;
        toastrConfig.positionClass = 'toast-bottom-left'
    }
}
