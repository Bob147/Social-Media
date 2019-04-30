import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-custom-error-page',
  templateUrl: './custom-error-page.component.html',
  styleUrls: ['./custom-error-page.component.css']
})
export class CustomErrorPageComponent implements OnInit {

  constructor( private routerMod: Router) { }

  backToLogin() {
    this.routerMod.navigate(['/login']);
  }
  ngOnInit() {
  }

}
