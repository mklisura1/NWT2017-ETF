import {XHRBackend, Http, RequestOptions} from "@angular/http";
import {HttpInterceptorService} from "./http-interceptor.service";
import {Router} from "@angular/router";


export function httpFactory(xhrBackend: XHRBackend, requestOptions: RequestOptions, router: Router): Http {
    return new HttpInterceptorService(xhrBackend, requestOptions, router);
}
