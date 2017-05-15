import { Component, OnInit } from '@angular/core';
import {PaymentsService} from "../payments.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-domestic-payment',
  templateUrl: './domestic-payment.component.html',
  styleUrls: ['./domestic-payment.component.scss']
})
export class DomesticPaymentComponent implements OnInit {

  domesticTempObj: any = {
    step: 'step1',
    accountCurrency: 'BAM',
    receiverBankAccNumber: '',
    receiverName: '',
    purpose: '',
    date: new Date(),
    accountTo: {},
    accountFrom: {},
    amount: '',
    userId: 1
  };

  submittedPayment: any;

  showError: boolean = false;


  showDatePicker: boolean = false;

  accountsList: any[];
  constructor(private paymentsService: PaymentsService, private router: Router) { }

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
    this.submittedPayment = true;
    if(!this.domesticTempObj.amount || !this.domesticTempObj.receiverBankAccNumber || !this.domesticTempObj.purpose || !this.domesticTempObj.receiverName){
      this.showError = true;
      return;
    }
    this.domesticTempObj.step = 'step2';
  }

  back() {
    this.domesticTempObj.step = 'step1';
  }

  newPayment() {
    this.domesticTempObj.step = 'step1';
    //clear inputs
  }

  createPayment(){
    console.log("CREATE PAYMENT", this.domesticTempObj);
    return {
      amount: this.domesticTempObj.amount,
      userId: this.domesticTempObj.userId,
      date: this.domesticTempObj.date,
      senderName: this.domesticTempObj.accountFrom.accountName,
      senderBankAccNumber: this.domesticTempObj.accountFrom.accountNumber,
      currency: this.domesticTempObj.accountCurrency,
      receiverName: this.domesticTempObj.receiverName,
      receiverBankAccNumber: this.domesticTempObj.receiverBankAccNumber,
      purpose: this.domesticTempObj.purpose,
      typeDescription: 'DomaciNalog'
    }
  }

  submitPayment() {
    let payment = this.createPayment();
    this.paymentsService.insertPayment(payment)
      .subscribe(
        response =>{
          if(response){
            this.domesticTempObj.step = 'step3';
            this.submittedPayment = response;
          }
        }
      )
  }
  confirmPayment() {
    //
  }

  open(){
    this.showDatePicker = true;
  }

  onNotifyAccFrom(account: any){
    this.domesticTempObj.accountFrom = account;
  }

  onSelectionDone(event) {
    //this.internalTempObj.date = event;
    this.showDatePicker = false;
  }

  showPayments(){
    this.router.navigate(['/payments/all']);
  }

  showSuccessButton: boolean = false;
  signPayment(){
    let id = this.submittedPayment.id;
    this.paymentsService.signPayment(id)
      .subscribe(
        response =>{
          if(response && response.status === 'Signed'){
            this.showSuccessButton = true;
          }
        }
      )
  }
}
