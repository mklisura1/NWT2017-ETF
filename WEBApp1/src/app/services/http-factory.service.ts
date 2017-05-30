import {XHRBackend, Http, RequestOptions} from "@angular/http";
import {HttpInterceptorService} from "./http-interceptor.service";
import {Router} from "@angular/router";
import {HelperService} from "./helper.service";


export function httpFactory(xhrBackend: XHRBackend, requestOptions: RequestOptions, router: Router, helperService: HelperService): Http {
    return new HttpInterceptorService(xhrBackend, requestOptions, router, helperService);
}
