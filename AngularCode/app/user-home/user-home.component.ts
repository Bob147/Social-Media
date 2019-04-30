import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/UserService';
import { UserProfile } from '../shared/class/Profile';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {
  myProfile: UserProfile;
  constructor(private us: UserService, private routerMod: Router) {
    if (!this.us.userStorage.get('curUserProfile')) {this.routerMod.navigate(['/error']); }
    this.myProfile = this.us.curUserProfile;
  }

  ngOnInit() {
  }

}
