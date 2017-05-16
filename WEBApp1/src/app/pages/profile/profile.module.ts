import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';
import {FormsModule} from "@angular/forms";
import {BsDropdownModule, DatepickerModule} from "ngx-bootstrap";

@NgModule({
  imports: [
    CommonModule,
    ProfileRoutingModule,
    FormsModule,
    BsDropdownModule,
    DatepickerModule.forRoot()
  ],
  declarations: [ProfileComponent]
})
export class ProfileModule { }
