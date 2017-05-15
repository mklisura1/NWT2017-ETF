import { Injectable } from '@angular/core';
import {Headers, Http, RequestOptions, URLSearchParams} from '@angular/http';

import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Injectable()
export class PaymentsService {

  apiUrl: string;
  headers: Headers;
  options: RequestOptions;
  constructor(private http: Http) {
    this.apiUrl = 'http://localhost:1102/api/payments';
    this.headers = new Headers({ 'Content-Type': 'application/json' });
    this.options = new RequestOptions({ headers: this.headers });
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json() || 'Server error');
  }

  getPayments(status: string): Observable<any>{
    let params = new URLSearchParams();
    params.set("status", status);
    return this.http.get(this.apiUrl, { search: params})
      .map( response => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  insertPayment(payment): Observable<any>{
    let body = JSON.stringify(payment);

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.apiUrl, body, options)
      .map( response => response.json())
      .catch(this.handleError);
  }

  updatePayment(id, payment): Observable<any>{
    let body = JSON.stringify({payment: payment});
    return this.http.put(this.apiUrl, body, this.options)
      .map(response => response.json())
      .catch(this.handleError)
  }

  deletePayment(id): Observable<any>{
    let url = this.apiUrl + "/{id}";
    return this.http.delete(url.replace('{id}', id), {})
      .map(response => response.json())
      .catch(this.handleError)
  }

  signPayment(id: any) {
    let url = this.apiUrl + "/{id}/sign";
    return this.http.put(url.replace('{id}', id), {})
      .map(response => response.json())
      .catch(this.handleError)
  }
}
