import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountsRoutingModule } from './accounts-routing.module';
import { AccountsComponent } from './accounts.component';
import {FormsModule} from '@angular/forms';
import { AccountsService } from '../../services/accounts.service';
import { TransactionsService } from '../../services/transactions.service';

@NgModule({
  imports: [
    CommonModule,
    AccountsRoutingModule,
    FormsModule
  ],
  declarations: [AccountsComponent],
  providers: [AccountsService, TransactionsService]
})
export class AccountsModule { }
