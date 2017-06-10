import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-accounts-list',
    templateUrl: './accounts-list.component.html',
    styleUrls: ['./accounts-list.component.scss']
})
export class AccountsListComponent implements OnInit {

    showList: boolean = false;
    selectedAccount: any;

    public scrollbarOptions = {axis: 'y', theme: 'minimal-dark'};

    @Input() accountsList: any[];
    @Output() selectedAccountEvent: EventEmitter<any> = new EventEmitter<any>();

    constructor() {
        this.selectedAccount = {};
        //this.selectedAccount = this.accountsList[0];
    }

    ngOnInit() {
        console.log("Accouns list", this.accountsList);
        this.selectedAccount = this.accountsList[0];
        this.selectedAccountEvent.emit(this.selectedAccount);

    }


    selectAccount(account) {
        this.selectedAccount = account;
        this.selectedAccountEvent.emit(this.selectedAccount);
        this.showList = false;
    }
}
