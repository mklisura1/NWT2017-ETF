import {Component, OnInit} from '@angular/core';
import {AccountsService} from "../../../services/accounts.service";
import {PaymentsService} from "../payments.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TemplatesService} from "../../../services/templates.service";
import {HelperService} from "../../../services/helper.service";

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
        userId: JSON.parse(localStorage.getItem('loggedUser')).id
    };
    templateName = '';
    submittedPayment: any;

    showError: boolean = false;


    showDatePicker: boolean = false;

    accountsList: any[];

    constructor(private paymentsService: PaymentsService, private router: Router, private activatedRoute: ActivatedRoute,
                private helperService: HelperService, private accountsService: AccountsService,
                private templatesService: TemplatesService) {
    }

    ngOnInit() {
        this.getAccounts();
        this.activatedRoute.params.subscribe(
            params => {
                this.populatePayment(params);
            }
        );

    }

    getAccounts() {
        console.log("GET ACCOUNTS");
        this.accountsService.getAccountsByCurrency('foreign')
            .subscribe(
                response => {
                    console.log("GET ACCOUNTS - REsponse");

                    if (response)
                        this.accountsList = response;
                }
            )
    }

    populatePayment(params) {
        //console.log(params);
        this.internationalTempObj.purpose = params.payment_purpose;
        this.internationalTempObj.receiverName = params.receiver_name;
        this.internationalTempObj.receiverBankAccNumber = params.receiver_bank_acc_number;
        this.internationalTempObj.amount = params.amount;

    }

    next() {
        this.submittedPayment = true;
        if (!this.internationalTempObj.amount || !this.internationalTempObj.receiverBankAccNumber || !this.internationalTempObj.purpose || !this.internationalTempObj.receiverName) {
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

    createPayment() {
        console.log("CREATE PAYMENT", this.internationalTempObj);
        return {
            amount: this.internationalTempObj.amount,
            userId: JSON.parse(localStorage.getItem('loggedUser')).id,
            date: this.internationalTempObj.date,
            senderName: this.internationalTempObj.accountFrom.bank_account_name,
            senderBankAccNumber: this.internationalTempObj.accountFrom.bank_account_number,
            currency: this.internationalTempObj.accountCurrency,
            receiverName: this.internationalTempObj.receiverName,
            receiverBankAccNumber: this.internationalTempObj.receiverBankAccNumber,
            purpose: this.internationalTempObj.purpose,
            typeDescription: 'ForeignPayment'
        }
    }

    submitPayment() {
        let payment = this.createPayment();
        this.paymentsService.insertPayment(payment)
            .subscribe(
                response => {
                    if (response) {
                        this.internationalTempObj.step = 'step3';
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
        this.internationalTempObj.accountFrom = account;
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
        if(this.templateName.length <= 0) return;
        console.log("SUBMITTED",this.submittedPayment);


        let template = {
            "template_name": this.templateName,
            "payment_purpose": this.submittedPayment.purpose,
            "payment_type":"ForeignPayment",
            "amount": this.submittedPayment.amount,
            "receiver_bank_acc_number": this.submittedPayment.receiverBankAccNumber,
            "receiver_name":this.submittedPayment.receiverName,
            "sender_name":this.submittedPayment.senderName,
            "sender_bank_acc_number": this.submittedPayment.senderBankAccNumber,
            "userId": JSON.parse(localStorage.getItem('loggedUser')).id
        };

        this.templatesService.savePaymentToTemplate(template)
            .subscribe(
                data => {
                    this.helperService.showSuccess("Template successfully saved.")
                }
            )
    }

}
