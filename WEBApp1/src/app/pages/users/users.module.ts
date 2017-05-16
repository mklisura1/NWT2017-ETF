import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import {UsersComponent} from "./users.component";
import {DatepickerModule, ModalModule} from "ngx-bootstrap";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    UsersRoutingModule,
    ModalModule.forRoot(),
    FormsModule,
    DatepickerModule.forRoot()
  ],
  declarations: [UsersComponent]
})
export class UsersModule { }
