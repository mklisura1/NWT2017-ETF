import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PaymentsRoutingModule} from "./payments-routing.module";
import {DomesticPaymentComponent} from "./domestic-payment.component";
import { InternationalPaymentComponent } from './international-payment.component';
import {PaymentsOverviewComponent} from "./payments-overview.component";
import {InternalPaymentComponent} from "./internal-payment/internal-payment.component";
import {FormsModule} from "@angular/forms";
import {AccountsListComponent} from "../components/accounts-list/accounts-list.component";

@NgModule({
  imports: [
    CommonModule,
    PaymentsRoutingModule,
    FormsModule
  ],
  declarations: [DomesticPaymentComponent, InternationalPaymentComponent, PaymentsOverviewComponent, InternalPaymentComponent, AccountsListComponent]
})
export class PaymentsModule { }
