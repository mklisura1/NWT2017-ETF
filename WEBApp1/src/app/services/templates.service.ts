import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions, URLSearchParams} from '@angular/http';
// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class TemplatesService {

    apiUrl: string;

    constructor(private http: Http) {
        this.apiUrl = 'http://localhost:1103/api/template';
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json() || 'Server error');
    }

    public getAllTemplates() {
        let params = new URLSearchParams();
        params.set("userId", JSON.parse(localStorage.getItem('loggedUser')).id);
        return this.http.get(this.apiUrl, {search: params})
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw('Server error'));
    }

    deleteTemplate(id): Observable<any> {
        return this.http.delete(this.apiUrl + '/' + id)
            .map((res: Response) => {
                console.log(res);
                res
            })
            .catch((error: any) => Observable.throw('Server error'));
    }

    savePaymentToTemplate(template) {
        let body = JSON.stringify(template);
        return this.http.post(this.apiUrl, body)
            .map(response => response ? response : {'Message': 'Success'})
            .catch(this.handleError);
    }

}
