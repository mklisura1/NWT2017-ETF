import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PaymentsRoutingModule} from "./payments-routing.module";
import {DomesticPaymentComponent} from "./domestic-payment/domestic-payment.component";
import { InternationalPaymentComponent } from './international-payment/international-payment.component';
import {PaymentsOverviewComponent} from "./payments-overview.component";
import {InternalPaymentComponent} from "./internal-payment/internal-payment.component";
import {FormsModule} from "@angular/forms";
import {AccountsListComponent} from "../../components/accounts-list/accounts-list.component";
import { DatepickerModule } from 'ngx-bootstrap/datepicker';
import { PaymentsService } from './payments.service';
import {AccountsService} from "../accounts/accounts.service";
import {TabsModule} from "ngx-bootstrap";

@NgModule({
  imports: [
    CommonModule,
    PaymentsRoutingModule,
    FormsModule,
    TabsModule,
    DatepickerModule.forRoot()
  ],
  declarations: [DomesticPaymentComponent, InternationalPaymentComponent, PaymentsOverviewComponent, InternalPaymentComponent, AccountsListComponent],
  providers: [PaymentsService, AccountsService]
})
export class PaymentsModule { }
