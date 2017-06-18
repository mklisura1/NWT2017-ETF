import { Injectable } from '@angular/core';
import {ToastrService} from "ngx-toastr";
@Injectable()
export class HelperService {

    constructor(private toastrService: ToastrService) {
    }


    showSuccess(message) {
        this.toastrService.success(message);
    }

    showError(error){
        this.toastrService.error(error);
    }

    showInfo(info){
        this.toastrService.info(info);
    }
}
