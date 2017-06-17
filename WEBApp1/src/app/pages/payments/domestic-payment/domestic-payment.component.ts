import {Component, OnInit} from '@angular/core';
import {PaymentsService} from "../payments.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TemplatesService} from "../../../services/templates.service";
import {HelperService} from "../../../services/helper.service";
import {AccountsService} from "../../../services/accounts.service";

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
    templateName = '';

    showError: boolean = false;


    showDatePicker: boolean = false;

    accountsList: any[];

    constructor(private paymentsService: PaymentsService, private router: Router, private activatedRoute: ActivatedRoute,
                private helperService: HelperService,
                private templatesService: TemplatesService,
    private accountsService: AccountsService) {
    }

    ngOnInit() {
        this.getAccounts();
        this.activatedRoute.params.subscribe(
            params => {
                this.populatePayment(params);
            }
        )
    }

    getAccounts(){
        this.accountsService.getAccountsByCurrency('domestic')
            .subscribe(response =>{
                // this.accountsList = response.result;
                this.accountsList = response.filter(element => {
                    return element.user === JSON.parse(localStorage.getItem('loggedUser')).id;
                });
            })
    }

    populatePayment(params) {
        //console.log(params);
        this.domesticTempObj.purpose = params.payment_purpose;
        this.domesticTempObj.receiverName = params.receiver_name;
        this.domesticTempObj.receiverBankAccNumber = params.receiver_bank_acc_number;
        this.domesticTempObj.amount = params.amount;

    }

    next() {
        this.submittedPayment = true;
        if (!this.domesticTempObj.amount || !this.domesticTempObj.receiverBankAccNumber || !this.domesticTempObj.purpose || !this.domesticTempObj.receiverName) {
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

    createPayment() {
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
            typeDescription: 'DomesticPayment'
        }
    }

    submitPayment() {
        let payment = this.createPayment();
        this.paymentsService.insertPayment(payment)
            .subscribe(
                response => {
                    if (response) {
                        this.domesticTempObj.step = 'step3';
                        this.submittedPayment = response;
                    }
                }
            )
    }

    confirmPayment() {
        //
    }

    open() {
        this.showDatePicker = true;
    }

    onNotifyAccFrom(account: any) {
        this.domesticTempObj.accountFrom = account;
    }

    onSelectionDone(event) {
        //this.internalTempObj.date = event;
        this.showDatePicker = false;
    }

    showPayments() {
        this.router.navigate(['/payments/all']);
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

    saveAsTemplate() {
        //this.submittedPayment
        if (this.templateName.length <= 0) return;
        console.log("SUBMITTED", this.submittedPayment);


        let template = {
            "template_name": this.templateName,
            "payment_purpose": this.submittedPayment.purpose,
            "payment_type": "ForeignPayment",
            "amount": this.submittedPayment.amount,
            "receiver_bank_acc_number": this.submittedPayment.receiverBankAccNumber,
            "receiver_name": this.submittedPayment.receiverName,
            "sender_name": this.submittedPayment.senderName,
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
