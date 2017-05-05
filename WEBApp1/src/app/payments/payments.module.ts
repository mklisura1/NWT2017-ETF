import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PaymentsRoutingModule} from "./payments-routing.module";
import {DomesticPaymentComponent} from "./domestic-payment.component";
import { InternationalPaymentComponent } from './international-payment.component';
import {PaymentsOverviewComponent} from "./payments-overview.component";
import {InternalPaymentComponent} from "./internal-payment.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    PaymentsRoutingModule,
    FormsModule
  ],
  declarations: [DomesticPaymentComponent, InternationalPaymentComponent, PaymentsOverviewComponent, InternalPaymentComponent]
})
export class PaymentsModule { }
