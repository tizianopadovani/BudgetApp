import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../../core/services/user-service/user.service";
import {Subscription} from "rxjs";
import {User} from "../../../core/models/User";
import {Router} from "@angular/router";
import {AuthService} from "../../../core/services/auth-service/auth.service";
import {TokenStorageService} from "../../../core/services/token-service/token-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  loginForm : FormGroup = new FormGroup({});
  user ?: User;

  userSubscription ?: Subscription;
  authSubscription ?: Subscription;
  private isLoggedIn: boolean = false;
  private isLoginFailed: boolean = false;
  private errorMessage = '';

  constructor(private userService : UserService, private tokenStorage: TokenStorageService, private route : Router, private authService : AuthService) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(''),
      password: new FormControl('')
    })

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
  }

  submit() {

    this.authService.login(this.loginForm.get('email')?.value, this.loginForm.get('password')?.value).subscribe({
      next: data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });

  }

  ngOnDestroy(): void {
    this.userSubscription?.unsubscribe();
    this.authSubscription?.unsubscribe();
  }

}
