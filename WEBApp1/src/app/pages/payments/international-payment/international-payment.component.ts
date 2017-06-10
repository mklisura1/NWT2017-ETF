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
        userId: 1
    };
    templateName = '';
    submittedPayment: any;

    showError: boolean = false;


    showDatePicker: boolean = false;

    accountsList: any[];

    constructor(private paymentsService: PaymentsService, private router: Router, private activatedRoute: ActivatedRoute,
                private helperService: HelperService,
                private templatesService: TemplatesService) {
    }

    ngOnInit() {
        this.activatedRoute.params.subscribe(
            params => {
                this.populatePayment(params);
            }
        );
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
            userId: this.internationalTempObj.userId,
            date: this.internationalTempObj.date,
            senderName: this.internationalTempObj.accountFrom.accountName,
            senderBankAccNumber: this.internationalTempObj.accountFrom.accountNumber,
            currency: this.internationalTempObj.accountCurrency,
            receiverName: this.internationalTempObj.receiverName,
            receiverBankAccNumber: this.internationalTempObj.receiverBankAccNumber,
            purpose: this.internationalTempObj.purpose,
            typeDescription: 'InternationalPayment'
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
