import { Injectable } from '@angular/core';
import {Headers, Http, RequestOptions, URLSearchParams} from '@angular/http';

import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {

  apiUrl: string;

  constructor(private http: Http) {
    this.apiUrl = 'http://localhost:1105/api/user';
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json() || 'Server error');
  }

  public static getUser(){
    return {
      firstName: 'Haris',
      lastName: 'Spahic',
      userId: 12345,
      phoneNumber: '061578181',
      address: 'Ulica krhandziluk 21b',
      jmbg: '19812312312',
      email: 'spahaa@gmail.com',
      birthDate: new Date()
    }
  }

  public getAllUsers(){
    return this.http.get(this.apiUrl)
      .map( response => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }



}
