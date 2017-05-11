import { Component, OnInit } from '@angular/core';
import {PaymentsService} from "./payments.service";

@Component({
  selector: 'app-payments-overview',
  templateUrl: './payments-overview.component.html',
  styleUrls: ['./payments-overview.component.scss']
})
export class PaymentsOverviewComponent implements OnInit {

  payments: any[];

  constructor(private paymenstService: PaymentsService) { }

  ngOnInit() {
    this.getPayments();
  }

  getPayments(){
    this.paymenstService.getPayments("Signed")
      .subscribe(response=>{
        if(response.content){
          this.payments = response.content;
        }
      })
  }
}
