import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';

import {Observable} from 'rxjs/Rx';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class AccountsService {

  apiUrl: string;

  constructor(private http: Http) {
    this.apiUrl = 'http://localhost:1101/api/accounts';
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json() || 'Server error');
  }

  public getAccounts() {

    console.log('Anesssss: ', localStorage.getItem('tokenData'));
    return this.http.get(this.apiUrl)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw('Server error'));
  }

}
