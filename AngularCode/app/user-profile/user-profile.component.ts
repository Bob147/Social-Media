import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/UserService';
import { UserProfile } from '../shared/class/Profile';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  curUserProfile: UserProfile;
  constructor(private us: UserService, private routerMod: Router) {
    if (!this.us.userStorage.get('curUserProfile')) {this.routerMod.navigate(['/error']); }
    this.curUserProfile = this.us.curUserProfile;
  }

  ngOnInit() {
  }

}
