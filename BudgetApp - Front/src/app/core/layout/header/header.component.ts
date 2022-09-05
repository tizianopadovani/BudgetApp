import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth-service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {

  isOpen: boolean = false;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
    this.debouncing();
  }

  openBurger() {
    this.isOpen = !this.isOpen;
  }

  closeBurger() {
    this.isOpen = false;
  }

  debouncing() {
    window.addEventListener('resize', () => {
      let timeout;
      clearTimeout(timeout);
      timeout = setTimeout(() => {
        if (window.innerWidth >= 768) this.closeBurger();
      }, 250);
    })
  }

  ngOnDestroy(): void {
    window.removeEventListener('resize', this.debouncing);
  }

}
