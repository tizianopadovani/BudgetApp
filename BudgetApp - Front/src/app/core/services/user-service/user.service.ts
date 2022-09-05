import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/User";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  postUser(user: User) : Observable<User> {

    return this.http.post<User>("http://localhost:8080/user", user);

  }

  getUserById(id: string) : Observable<User> {

    return this.http.get<User>("http://localhost:8080/user/" + id);

  }

  getUserByEmail(email: string) : Observable<User> {

    return this.http.get<User>("http://localhost:8080/user/email/" + email);

  }

  patchUser(id: string, user: User) : Observable<User> {

    return this.http.patch<User>("http://localhost:8080/user/" + id, user);

  }

  deleteUser(id: string) : Observable<User> {

    return this.http.delete<User>("http://localhost:8080/user/" + id);

  }

}
