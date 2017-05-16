import { Component, OnInit } from '@angular/core';
import {PaymentsService} from "./payments.service";

@Component({
  selector: 'app-payments-overview',
  templateUrl: './payments-overview.component.html',
  styleUrls: ['./payments-overview.component.scss']
})
export class PaymentsOverviewComponent implements OnInit {

  payments: any[];
  selectedTab: string;

  constructor(private paymenstService: PaymentsService) { }

  ngOnInit() {
    this.getPayments("Waiting");
  }

  getPayments(status: string){
    this.selectedTab = status;
    this.paymenstService.getPayments(status)
      .subscribe(response=>{
        if(response.content){
          this.payments = response.content;
        }
      })
  }

  signPayment(id): void{
    this.paymenstService.signPayment(id)
      .subscribe(response=>{
        if(response.content){
          //TODO: show Popup message
          this.getPayments(this.selectedTab);
        }
      })
  }

  deletePayment(id): void{
    this.paymenstService.deletePayment(id)
      .subscribe(response=>{
        if(response.message){
          //TODO: show Popup message
          this.getPayments(this.selectedTab);
        }
      })
  }
}
