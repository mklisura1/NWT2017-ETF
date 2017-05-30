import { Injectable } from '@angular/core';
import { ConnectionBackend, RequestOptions, Request, RequestOptionsArgs, Response, Http, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/finally';
import {HelperService} from "./helper.service";

@Injectable()
export class HttpInterceptorService extends Http{

  private router: Router;
  private helperService: HelperService;
    constructor(backend: ConnectionBackend, defaultOptions: RequestOptions) {
        super(backend, defaultOptions);
    }

    request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
        return (super.request(url, options))
            .map((res: Response) => {
                console.log("RES");

                if (res) {
                    if (res.status === 201) {
                        return [{ status: res.status, json: res }]
                    }
                    else if (res.status === 200) {
                        return [{ status: res.status, json: res }]
                    }
                }
            }).catch((error: any) => {
                if (error.status === 500) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 400) {
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 409) {
                    this.helperService.showError('Status code 409 error occurred!');
                    return Observable.throw(new Error(error.status));
                }
                else if (error.status === 406) {
                    return Observable.throw(new Error(error.status));
                }
            });
    }

    get(url: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.get(url, this.getRequestOptionArgs(options));
    }

    post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.post(url, body, this.getRequestOptionArgs(options));
    }

    put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.put(url, body, this.getRequestOptionArgs(options));
    }

    delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.delete(url, this.getRequestOptionArgs(options));
    }

    private updateUrl(req: string) {
        return  req;
    }

    private getRequestOptionArgs(options?: RequestOptionsArgs) : RequestOptionsArgs {
        if (options == null) {
            options = new RequestOptions();
        }
        if (options.headers == null) {
            options.headers = new Headers();
        }
        options.headers.append('Content-Type', 'application/json');

        if(localStorage.getItem('tokenData')){
            let tokenData = JSON.parse(localStorage.getItem('tokenData'));
            options.headers.append('X-Authorization', 'Bearer ' + tokenData.token.toString())
        }

        return options;
    }


    intercept(observable: Observable<Response>): Observable<any> {
        console.log("In the intercept routine..");

        return observable
            .map((res: Response) => res)
            .catch((error: any) => {
                this.helperService.showError('Error neki');
                console.log("error neki");
                return Observable.throw(error.statusText);
            });
    }
}

