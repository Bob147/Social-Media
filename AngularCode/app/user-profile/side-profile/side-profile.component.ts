import { Component, OnInit, Input } from '@angular/core';
import { UserProfile } from 'src/app/shared/class/Profile';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/UserService';

@Component({
  selector: 'app-side-profile',
  templateUrl: './side-profile.component.html',
  styleUrls: ['./side-profile.component.css']
})
export class SideProfileComponent implements OnInit {
  @Input() curUserProfile: UserProfile;
  buttonValue: string;
  constructor(private routerMod: Router, private us: UserService) {
    this.us.friendList = JSON.parse(this.us.userStorage.get('friendList'));
    if (this.curUserProfile) {
      this.buttonValue = 'Add Friend';
      if (this.curUserProfile.profileId !== this.us.curUserProfile.profileId) {
      for (const friend of this.us.friendList) {
        if (this.curUserProfile.profileId === friend.profileId) {
          this.buttonValue = 'Remove Friend';
          break;
        }
      }
    } else {
    this.buttonValue = 'Edit Profile';
    }

    } else {
      this.curUserProfile = JSON.parse(this.us.userStorage.get('curPageUserProfile'));
    }
  }

  forward(): void {
    if (this.buttonValue === 'Add Friend') {
      this.us.addFriend(this.us.curUserProfile.profileId, this.us.curPageUserProfile.profileId).subscribe();
      this.us.friendList.push(this.us.curPageUserProfile);
      this.us.userStorage.set('friendList', JSON.stringify(this.us.friendList));
      location.reload();
    } else if (this.buttonValue === 'Remove Friend') {
      this.us.removeFriend(this.us.curUserProfile.profileId, this.us.curPageUserProfile.profileId).subscribe();
      for (let i = 0; i < this.us.friendList.length; i++) {
        if (this.us.friendList[i].profileId === this.us.curPageUserProfile.profileId) {
          this.us.friendList.splice(i, 1);
          break;
        }
      }
      this.us.userStorage.set('friendList', JSON.stringify(this.us.friendList));
      location.reload();
    } else if (this.buttonValue === 'Edit Profile') {
      this.routerMod.navigate(['/editprofile']);
    }
  }
  ngOnInit() {
    // if(current page user is not current User)
    this.buttonValue = 'Add Friend';
    if (this.curUserProfile.profileId !== this.us.curUserProfile.profileId) {
      for (const friend of this.us.friendList) {
        if (this.curUserProfile.profileId === friend.profileId) {
          this.buttonValue = 'Remove Friend';
          break;
        }
      }
    } else {
    this.buttonValue = 'Edit Profile';
    }
  }

}
