import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TemplatesRoutingModule } from './templates-routing.module';
import {TemplatesComponent} from "./templates.component";
import {TemplatesService} from "../../services/templates.service";

@NgModule({
  imports: [
    CommonModule,
    TemplatesRoutingModule
  ],
  declarations: [TemplatesComponent],
    providers: [TemplatesService]
})
export class TemplatesModule { }
