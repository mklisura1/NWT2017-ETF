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

  private helperService: HelperService;
  _router: Router;
    constructor(backend: ConnectionBackend, defaultOptions: RequestOptions, private router: Router) {
        super(backend, defaultOptions);
        this._router = router;
    }

    request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
        return this.intercept(super.request(url, options));
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


    /**
     * This method as the name sugests intercepts the request and checks if there are any errors.
     * If an error is present it will be checked what error there is and if it is a general one then it will be handled here, otherwise, will be
     * thrown up in the service layers
     */
    intercept(observable: Observable<Response>): Observable<Response> {

        //  return observable;
        return observable.catch((err, source) => {
            console.log("INTEREPT", err, source);
            if (err.status == 401) {
                console.log("ROUTER", this._router);
                //return observable;
                return Observable.empty(err);
            } else {
                //return observable;
                return Observable.throw(err);
            }
        });
    }
}

