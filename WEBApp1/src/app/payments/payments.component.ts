import { Component, OnInit } from '@angular/core';
import {PaymentsService} from "./payments.service";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.scss']
})
export class PaymentsComponent implements OnInit {

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
