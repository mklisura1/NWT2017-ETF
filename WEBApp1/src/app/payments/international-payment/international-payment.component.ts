import { Component, OnInit } from '@angular/core';
import {AccountsService} from "../../accounts/accounts.service";
import {PaymentsService} from "../payments.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-international-payment',
  templateUrl: './international-payment.component.html',
  styleUrls: ['./international-payment.component.scss']
})
export class InternationalPaymentComponent implements OnInit {

  internationalTempObj: any = {
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
    if(!this.internationalTempObj.amount || !this.internationalTempObj.receiverBankAccNumber || !this.internationalTempObj.purpose || !this.internationalTempObj.receiverName){
      this.showError = true;
      return;
    }
    this.internationalTempObj.step = 'step2';
  }

  back() {
    this.internationalTempObj.step = 'step1';
  }

  newPayment() {
    this.internationalTempObj.step = 'step1';
    //clear inputs
  }

  createPayment(){
    console.log("CREATE PAYMENT", this.internationalTempObj);
    return {
      amount: this.internationalTempObj.amount,
      userId: this.internationalTempObj.userId,
      date: this.internationalTempObj.date,
      senderName: this.internationalTempObj.accountFrom.accountName,
      senderBankAccNumber: this.internationalTempObj.accountFrom.accountNumber,
      currency: this.internationalTempObj.accountCurrency,
      receiverName: this.internationalTempObj.receiverName,
      receiverBankAccNumber: this.internationalTempObj.receiverBankAccNumber,
      purpose: this.internationalTempObj.purpose,
      typeDescription: 'DomaciNalog'
    }
  }

  submitPayment() {
    let payment = this.createPayment();
    this.paymentsService.insertPayment(payment)
      .subscribe(
        response =>{
          if(response){
            this.internationalTempObj.step = 'step3';
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
    this.internationalTempObj.accountFrom = account;
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
