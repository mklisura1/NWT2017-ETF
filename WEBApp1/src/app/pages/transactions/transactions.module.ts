import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TransactionsComponent} from './transactions.component';
import {RouterModule} from '@angular/router';
import {TransactionsService} from '../../services/transactions.service';

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild([{
                path: 'all',
                component: TransactionsComponent
            }
            ]
        )
    ],
    declarations: [TransactionsComponent],
    providers: [TransactionsService]
})
export class TransactionsModule {
}
