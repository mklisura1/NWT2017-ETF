import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionsComponent } from './transactions.component';
import { RouterModule } from '@angular/router';

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
  declarations: [TransactionsComponent]
})
export class TransactionsModule { }
