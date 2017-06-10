import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AccountsService} from "../../../services/accounts.service";
import {PaymentsService} from "../payments.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TemplatesService} from "../../../services/templates.service";
import {HelperService} from "../../../services/helper.service";

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
    templateName: string = '';
    submittedPayment: any;

    showError: boolean = false;


    showDatePicker: boolean = false;

    accountsList: any[];

    constructor(private accountsService: AccountsService,
                private paymentsService: PaymentsService, private activatedRoute: ActivatedRoute,
                private router: Router, private helperService: HelperService,
                private templatesService: TemplatesService) {
        //this.getAccounts();
    }

    ngOnInit() {

        //this.getAccounts();
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
        this.activatedRoute.params.subscribe(
            params => {
                this.populatePayment(params);
            }
        )
    }

    populatePayment(params) {
        //console.log(params);
        this.internalTempObj.purpose = params.payment_purpose;
        this.internalTempObj.amount = params.amount;
    }

    getAccounts() {
        console.log("GET ACCOUNTS");
        this.accountsService.getAccounts()
            .subscribe(
                response => {
                    console.log("GET ACCOUNTS - REsponse");

                    if (response)
                        this.accountsList = response;
                }
            )
    }

    next() {

        if (!this.internalTempObj.amount) {
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

    createPayment() {
        console.log("CREATE PAYMENT", this.internalTempObj);
        return {
            amount: this.internalTempObj.amount,
            userId: this.internalTempObj.userId,
            date: this.internalTempObj.date,
            senderName: this.internalTempObj.accountFrom.accountName,
            senderBankAccNumber: this.internalTempObj.accountFrom.accountNumber,
            receiverName: this.internalTempObj.accountTo.accountName,
            receiverBankAccNumber: this.internalTempObj.accountTo.accountNumber,
            typeDescription: 'InternalPayment'
        }
    }

    submitPayment() {
        let payment = this.createPayment();
        this.paymentsService.insertPayment(payment)
            .subscribe(
                response => {
                    if (response) {
                        this.internalTempObj.step = 'step3';
                        this.submittedPayment = response;
                    }
                }
            )

    }

    confirmPayment() {
        //
    }

    showSuccessButton: boolean = false;

    signPayment() {
        let id = this.submittedPayment.id;
        this.paymentsService.signPayment(id)
            .subscribe(
                response => {
                    if (response && response.status === 'Signed') {
                        this.showSuccessButton = true;
                    }
                }
            )
    }

    open() {
        this.showDatePicker = true;
    }

    onNotifyAccFrom(account: any) {
        this.internalTempObj.accountFrom = account;
    }

    onNotifyAccTo(account: any) {
        this.internalTempObj.accountTo = account;
    }

    onSelectionDone(event) {
        //this.internalTempObj.date = event;
        this.showDatePicker = false;
    }

    showPayments() {
        this.router.navigate(['/payments/all']);
    }

    saveAsTemplate() {
        //this.submittedPayment
        if(this.templateName.length <= 0) return;
        console.log("SUBMITTED",this.submittedPayment);


        let template = {
            "template_name": this.templateName,
            "payment_purpose": this.submittedPayment.purpose,
            "payment_type":"InternalPayment",
            "amount": this.submittedPayment.amount,
            "receiver_bank_acc_number": this.submittedPayment.receiverBankAccNumber,
            "receiver_name":this.submittedPayment.receiverName,
            "sender_name":this.submittedPayment.senderName,
            "sender_bank_acc_number": this.submittedPayment.senderBankAccNumber,
            "user_id": JSON.parse(localStorage.getItem('loggedUser')).id
        };

        this.templatesService.savePaymentToTemplate(template)
            .subscribe(
                data => {
                    this.helperService.showSuccess("Template successfully saved.")
                }
            )
    }

}
