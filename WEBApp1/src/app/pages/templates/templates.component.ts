import {Component, OnInit} from '@angular/core';
import {TemplatesService} from "../../services/templates.service";
import {HelperService} from "../../services/helper.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-templates',
    templateUrl: './templates.component.html',
    styleUrls: ['./templates.component.scss']
})
export class TemplatesComponent implements OnInit {

    templates = [];

    constructor(private templateService: TemplatesService,
                private helperService: HelperService,
    private router: Router) {
    }

    ngOnInit() {
        this.loadTemplates();
    }


    loadTemplates() {
        this.templates = [];
        this.templateService.getAllTemplates()
            .subscribe(data => {
                this.templates = data;
            })
    }

    payFromTemplate(template) {
        switch (template.payment_type){
            case 'InternalPayment':
                this.router.navigate(['/payments/internal', template]);
                break;
            case 'ForeignPayment':
                this.router.navigate(['/payments/international', template]);
                break;
            default:
                this.router.navigate(['/payments/domestic',template]);

        }
    }

    deleteTemplate(id) {
        this.templateService.deleteTemplate(id)
            .subscribe(
                response => {
                    this.loadTemplates();
                },
                (err) => this.helperService.showError('Error while deleting user!')
            )
    }

}
