import { Component, OnInit } from '@angular/core';
import { AccountsService } from '../../services/accounts.service';
import { HelperService } from '../../services/helper.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {

  accounts: any[] = [];

  user: any = {};

  constructor(private accountsService: AccountsService, private helperService: HelperService, private router: Router) {
  }

  ngOnInit() {
    this.showAccounts();
  }

  showAccounts() {
    this.accountsService.getAccounts()
      .subscribe(
        response => {
          console.log('Get User Bank Accounts: ', response);
          /*if (response.content) {
            this.accounts = response;
          }*/
          this.accounts = response;
          this.helperService.showSuccess('Account is successfully retrieved!');
        }
      );
  }

}
