import { Component, OnInit } from '@angular/core';
import { AccountsService } from '../../services/accounts.service';
import { TransactionsService } from '../../services/transactions.service';
import { UserService } from '../../services/user.service';
import { HelperService } from '../../services/helper.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {

  accounts: any[] = [];
  loggedUser: any = {};
  totalCredits: any = 0;
  transactions: any[] = [];
  users: any[] = [];

  constructor(private accountsService: AccountsService, private helperService: HelperService, private transactionsService: TransactionsService, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.showAccounts();
  }

  showAccounts() {
    this.accountsService.getAccounts()
      .subscribe(
      response => {
        console.log('Get User Bank Accounts: ', response);
        this.accounts = response.filter(element => {
          return element.user === JSON.parse(localStorage.getItem('loggedUser')).id;
        });
        for (let account of this.accounts) {
          this.totalCredits = this.totalCredits + account.credit_amount;
        }
        this.helperService.showSuccess('Accounts are successfully retrieved!');
        this.addTransactions();
      }
      );
  }

  addTransactions() {
    console.log(this.accounts.length);
    for (let account of this.accounts) {
      this.transactionsService.getTransactionsByBankAccountId(account.bank_account_id)
        .subscribe(
           response => {
              console.log('Get Transactions: ', response);
              this.transactions.push(response);
             console.log(this.transactions);
            });
    }
    this.userService.getAllUsers()
      .subscribe(
          response => {
            console.log('Users:', response);
            this.users = response;
          });
  }
}
