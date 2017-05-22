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

  public getUser(id): Observable<any> {
    return this.http.get(this.apiUrl + "/{id}/account".replace('{id}', id))
      .map( response => response.json())
      .catch((error: any) => Observable.throw('Server error'));
  }

  public getAllUsers(){
    return this.http.get(this.apiUrl)
      .map( response => response.json())
      .catch((error: any) => Observable.throw('Server error'));
  }



}
