import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AccountsService} from "../../accounts/accounts.service";
import {PaymentsService} from "../payments.service";

@Component({
  // selector: 'app-internal-payment',
  templateUrl: './internal-payment.component.html',
  styleUrls: ['./internal-payment.component.scss']
})
export class InternalPaymentComponent implements OnInit {

  internalTempObj: any = {
    step: 'step1',
    companyName: 'Probaaa name',
    date: new Date(),
    accountTo: {},
    accountFrom: {},
    accountCurrency: 'BAM',
    amount: '',
    userId: 1
  };

  submittedPayment: any;

  showError: boolean = false;


  showDatePicker: boolean = false;

  accountsList: any[];
  constructor(private accountsService: AccountsService,
  private paymentsService: PaymentsService) {
    this.getAccounts();
  }

  ngOnInit() {

    this.getAccounts();
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

  getAccounts(){
    console.log("GET ACCOUNTS");
    this.accountsService.getAccounts()
      .subscribe(
        response => {
          console.log("GET ACCOUNTS - REsponse");

          if(response)
            this.accountsList = response;
        }
      )
  }

  next() {

    if(!this.internalTempObj.amount){
      this.showError = true;
      return;
    }
    this.internalTempObj.step = 'step2';
  }

  back() {
    this.internalTempObj.step = 'step1';
  }

  newPayment() {
    this.internalTempObj.step = 'step1';
    //clear inputs
  }

  createPayment(){
    console.log("CREATE PAYMENT", this.internalTempObj);
    return {
      amount: this.internalTempObj.amount,
      userId: this.internalTempObj.userId,
      date: this.internalTempObj.date,
      senderName: this.internalTempObj.accountFrom.accountName,
      senderBankAccNumber: this.internalTempObj.accountFrom.accountNumber,
      receiverName: this.internalTempObj.accountTo.accountName,
      receiverBankAccNumber: this.internalTempObj.accountTo.accountNumber,
      type: 'InternalTransfer'
    }
  }

  submitPayment() {
    let payment = this.createPayment();
    this.paymentsService.insertPayment(payment)
      .subscribe(
        response =>{
          if(response){
            this.internalTempObj.step = 'step3';
            this.submittedPayment = response;
          }
        }
      )

  }
  confirmPayment() {
    //
  }

  signPayment(){
    let id = this.submittedPayment.id;
    this.paymentsService.signPayment(id)
      .subscribe(
        response =>{
          if(response)
            this.internalTempObj.step = 'step3';
        }
      )
  }

  open(){
    this.showDatePicker = true;
  }

  onNotifyAccFrom(account: any){
    this.internalTempObj.accountFrom = account;
  }
  onNotifyAccTo(account: any){
    this.internalTempObj.accountTo = account;
  }
  onSelectionDone(event) {
    //this.internalTempObj.date = event;
    this.showDatePicker = false;
  }

}
