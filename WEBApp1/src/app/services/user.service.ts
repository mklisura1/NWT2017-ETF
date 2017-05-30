import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';

import {Observable} from 'rxjs/Rx';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

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
        .map((res:Response) => res.json())
        .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  public getAllUsers(){
    return this.http.get(this.apiUrl)
        .map((res:Response) => res.json())
        .catch((error:any) => Observable.throw('Server error'));
  }

  insertUser(data): Observable<any>{
      return this.http.post(this.apiUrl, data)
          .map((res:Response) => {console.log(res); res})
          .catch((error:any) => Observable.throw('Server error'));
  }

    deleteUser(id): Observable<any>{
        return this.http.delete(this.apiUrl + '/' + id)
            .map((res:Response) => {console.log(res); res})
            .catch((error:any) => Observable.throw('Server error'));
    }



}
