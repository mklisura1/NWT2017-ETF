import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';
import {DomesticPaymentComponent} from "./domestic-payment/domestic-payment.component";
import {InternationalPaymentComponent} from "./international-payment/international-payment.component";
import {PaymentsOverviewComponent} from "./payments-overview.component";
import {InternalPaymentComponent} from "./internal-payment/internal-payment.component";



const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Payment'
    },
    children: [
      {
        path: 'all',
        component: PaymentsOverviewComponent,
        data: {
          title: 'Overview'
        }
      },
      {
        path: 'domestic',
        component: DomesticPaymentComponent,
        data: {
          title: 'Domestic'
        }
      },
      {
        path: 'international',
        component: InternationalPaymentComponent,
        data: {
          title: 'International'
        }
      },
      {
        path: 'internal',
        component: InternalPaymentComponent,
        data: {
          title: 'Internal'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaymentsRoutingModule {}
