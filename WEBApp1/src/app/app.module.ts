import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';

import {AppComponent} from './app.component';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {TabsModule} from 'ngx-bootstrap/tabs';
import {NAV_DROPDOWN_DIRECTIVES} from './shared/nav-dropdown.directive';

import {ChartsModule} from 'ng2-charts/ng2-charts';
import {SIDEBAR_TOGGLE_DIRECTIVES} from './shared/sidebar.directive';
import {AsideToggleDirective} from './shared/aside.directive';
import {BreadcrumbsComponent} from './shared/breadcrumb.component';
import {ToastrModule} from 'ngx-toastr';
// Routing Module
import {AppRoutingModule} from './app.routing';

// Layouts
import {FullLayoutComponent} from './layouts/full-layout.component';
import {SimpleLayoutComponent} from './layouts/simple-layout.component';
import {Http, HttpModule, RequestOptions, XHRBackend} from "@angular/http";
import {UserService} from "./services/user.service";
import {AuthenticationService} from "./services/authentication.service";
import {httpFactory} from "./services/http-factory.service";
import {AuthenticationGuard} from "./services/authentication.guard";
import {HelperService} from "./services/helper.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {Router} from "@angular/router";
import { AccountsService } from './services/accounts.service';

@NgModule({
    imports: [
        BrowserModule,
        AppRoutingModule,
        BsDropdownModule.forRoot(),
        TabsModule.forRoot(),
        ChartsModule,
        HttpModule,
        BrowserAnimationsModule,
        ToastrModule.forRoot(), // ToastrModule added
    ],
    declarations: [
        AppComponent,
        FullLayoutComponent,
        SimpleLayoutComponent,
        NAV_DROPDOWN_DIRECTIVES,
        BreadcrumbsComponent,
        SIDEBAR_TOGGLE_DIRECTIVES,
        AsideToggleDirective
    ],
    providers: [
        {
        provide: Http,
        useFactory: httpFactory,
        deps: [XHRBackend, RequestOptions, Router, HelperService]
    },
        {
        provide: LocationStrategy,
        useClass: HashLocationStrategy
    }, UserService, AuthenticationService, AuthenticationGuard, HelperService, AccountsService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
