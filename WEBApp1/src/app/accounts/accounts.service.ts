import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class AccountsService {

  apiUrl: string;
  constructor(private http: Http) {
    this.apiUrl = 'http://localhost:8761'
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json() || 'Server error');
  }

  getAccounts(): Observable<any>{
    return this.http.get(this.apiUrl)
      .map( response => response.json())
      .catch(this.handleError);
  }

}
