import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Http, Headers} from "@angular/http";

@Injectable()
export class AuthenticationService {

    apiUrl: string;
  constructor(private http: Http) {
      this.apiUrl = 'http://localhost:1105/api/auth/login';
  }

    getToken(user: { name: string; pass: string }): Observable<any> {
        let headers = new Headers();
        let data = JSON.stringify({username: user.name, password: user.pass});
        headers.append('X-Requested-With','XMLHttpRequest');
        headers.append('Content-Type','application/json');

        return this.http.post(this.apiUrl, data, {
            headers: headers
        })
            .map( response => response.json())
            .catch((error: any) => Observable.throw('Server error'));

    }

  isAuthenticated(): boolean {
    console.log("tokenData", localStorage.getItem('tokenData'));
    return !!localStorage.getItem('tokenData');
  }
}
