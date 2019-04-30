import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/UserService';
import { Router } from '@angular/router';
import { UserProfile } from '../shared/class/Profile';

@Component({
  selector: 'app-other-profile',
  templateUrl: './other-profile.component.html',
  styleUrls: ['./other-profile.component.css']
})
export class OtherProfileComponent implements OnInit {

  curPageUser: UserProfile;
  constructor(private us: UserService, private routerMod: Router) {
    if (!this.us.userStorage.get('curUserProfile')) {this.routerMod.navigate(['/error']); }
    this.curPageUser = this.us.curPageUserProfile;
  }

  ngOnInit() {
  }

}
