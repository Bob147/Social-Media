import { Component, OnInit, Input } from '@angular/core';
import { UserProfile } from 'src/app/shared/class/Profile';
import { UserService } from '../UserService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-friends-list',
  templateUrl: './friends-list.component.html',
  styleUrls: ['./friends-list.component.css']
})
export class FriendsListComponent implements OnInit {
  friends: UserProfile[] = [];
  constructor(private us: UserService, private routerMod: Router) {
    if (this.us.userStorage.get('friendList')) {
      this.friends = this.us.friendList;
    } else {
      this.us.retrieveFriendList()
      .subscribe(
        data2 => {
          console.log('get all profiles: ' + data2);
          this.us.friendList = data2;
          this.us.userStorage.set('friendList', JSON.stringify(data2));
          this.friends = this.us.friendList;
        }
      );
    }
   }

   goToThisProfile(profile: UserProfile) {
    this.us.curPageUserProfile = profile;
    this.us.userStorage.set('curPageUserProfile', JSON.stringify(profile));
    if (this.routerMod.url === '/otherprofile') {
      location.reload();
    } else {
      this.routerMod.navigate(['/otherprofile']);
    }
  }
  ngOnInit() {
  }

}
