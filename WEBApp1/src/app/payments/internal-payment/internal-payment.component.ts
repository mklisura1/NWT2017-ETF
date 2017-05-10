import { Component, OnInit } from '@angular/core';

@Component({
  // selector: 'app-internal-payment',
  templateUrl: './internal-payment.component.html',
  styleUrls: ['./internal-payment.component.scss']
})
export class InternalPaymentComponent implements OnInit {

  internalTempObj: any = {
    step: 'step1',
    companyName: 'Probaaa name'
  };
  constructor() { }

  ngOnInit() {
  }

  next() {
    this.internalTempObj.step = 'step2';
  }

  back() {
    this.internalTempObj.step = 'step1';
  }

  newPayment() {
    this.internalTempObj.step = 'step1';
    //clear inputs
  }

  submitPayment() {
    this.internalTempObj.step = 'step3';
  }
  confirmPayment() {
    //
  }

}
