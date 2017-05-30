import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddUserComponent} from "./add-user.component";

const routes: Routes = [
    {
        path: '',
        component: AddUserComponent,
        data: {
            title: 'Add new eBanking user'
        }
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddUserRoutingModule { }
