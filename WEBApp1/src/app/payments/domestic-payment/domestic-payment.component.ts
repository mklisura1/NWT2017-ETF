import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-domestic-payment',
  templateUrl: './domestic-payment.component.html',
  styleUrls: ['./domestic-payment.component.scss']
})
export class DomesticPaymentComponent implements OnInit {

  domesticTempObj: any = {
    step: 'step1',
    companyName: 'Probaaa name'
  };

  accountsList: any[];
  constructor() { }

  ngOnInit() {
    this.accountsList = [
      {
        accountNumber: "BA121234330923",
        accountBalance: 23.02,
        accountName: "Hamo Hamic",
        accountType: "Tekuci racun",
        accountCurrency: "BAM"
      },
      {
        accountNumber: "SI1231231230923",
        accountBalance: 333.02,
        accountName: "Hame u piketa",
        accountType: "Devizni racun",
        accountCurrency: "EUR"
      },
      {
        accountNumber: "IT1231231230923",
        accountBalance: 123.02,
        accountName: "Francesco Totti",
        accountType: "Tekuci racun",
        accountCurrency: "ITA"
      }
    ];
  }

  next() {
    this.domesticTempObj.step = 'step2';
  }

  back() {
    this.domesticTempObj.step = 'step1';
  }

  newPayment() {
    this.domesticTempObj.step = 'step1';
    //clear inputs
  }

  submitPayment() {
    this.domesticTempObj.step = 'step3';
  }
  confirmPayment() {
    //
  }
}
