import { Injectable } from '@angular/core';
import {Headers, Http, URLSearchParams} from '@angular/http';

import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class PaymentsService {

  apiUrl: string;
  constructor(private http: Http) {
    this.apiUrl = 'http://localhost:1102/api/payments'
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
    let body = JSON.stringify({payment: payment});
    return this.http.post(this.apiUrl, body)
      .map( response => response.json())
      .catch(this.handleError);
  }

  updatePayment(id, payment): Observable<any>{
    let body = JSON.stringify({payment: payment});
    return this.http.put(this.apiUrl, body)
      .map(response => response.json())
      .catch(this.handleError)
  }

  deletePayment(id): Observable<any>{
    return this.http.delete(this.apiUrl, id)
      .map(response => response.json())
      .catch(this.handleError)
  }

}
