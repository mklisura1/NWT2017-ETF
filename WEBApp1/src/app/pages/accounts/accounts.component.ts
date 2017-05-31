import { Component, OnInit } from '@angular/core';
import { AccountsService } from '../../services/accounts.service';
import { HelperService } from '../../services/helper.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {

  items = [];

  user: any = {};

  constructor(private accountsService: AccountsService, private helperService: HelperService) {
    this.showAccounts();
  }

  ngOnInit() {
  }

  showAccounts() {
    this.accountsService.getAccounts()
      .subscribe(
        data => {
          console.log('Get User Bank Accounts:', data);
          /*this.items.push(data);*/
          this.helperService.showSuccess('Account is successfully retrieved!');
        }
      );
  }

}
