import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';

import {Observable} from 'rxjs/Rx';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class AccountsService {

  apiUrl: string;
  headers: Headers;
  options: RequestOptions;

  constructor(private http: Http) {
    this.apiUrl = 'http://localhost:1101/api/accounts';
    this.headers = new Headers({ 'Content-Type': 'application/json' });
    this.options = new RequestOptions({ headers: this.headers });
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json() || 'Server error');
  }

  getAccounts(): Observable<any> {
    return this.http.get(this.apiUrl)
      .map( response => response.json())
      .catch((error: any) => Observable.throw(error || 'Server error'));
  }

  insertAccount(account): Observable<any>{
    let body = JSON.stringify(account);

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.apiUrl, body, options)
      .map( response => response.json())
      .catch(this.handleError);
  }

  /*updateAccount(id, account): Observable<any>{
    let body = JSON.stringify({account: account});
    return this.http.put(this.apiUrl, body, this.options)
      .map(response => response.json())
      .catch(this.handleError);
  }

  deletePayment(id): Observable<any>{
    let url = this.apiUrl + "/{id}";
    return this.http.delete(url.replace('{id}', id), {})
      .map(response => response.json())
      .catch(this.handleError);
  }*/

}
