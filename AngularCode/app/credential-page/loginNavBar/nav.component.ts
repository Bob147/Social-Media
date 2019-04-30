import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/UserService';
import { UserProfile } from 'src/app/shared/class/Profile';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  message: string;
  constructor(private us: UserService, private routerMod: Router) {
  }
  @ViewChild('nameInput') name: ElementRef;
  @ViewChild('pswInput') psw: ElementRef;
  login(): void {

    this.intialization();
    this.us.loginUser1(this.name.nativeElement.value, this.psw.nativeElement.value)
   .subscribe(
    data => {
      if (!data) {
        alert('uncessessful log in');
        } else {
          this.us.curUserProfile = new UserProfile(data.profileId, data.firstName, data.lastName,
            data.gender, data.birthdate, data.occupation, data.relationshipStatus, data.currentStatus,
            data.hobbies, data.aboutMe, data.profileImageUrl);
          this.us.userStorage.set('curUserProfile', JSON.stringify(this.us.curUserProfile));
          console.log(this.us.curUserProfile);
          this.routerMod.navigate(['/userhome']);
       }
    }
  );
}
intialization() {
  this.us.userStorage.remove('friendList');
  this.us.userStorage.remove('curUserProfile');
  this.us.userStorage.remove('allProfiles');
  this.us.userStorage.remove('curPageUserProfile');
  this.us.userStorage.remove('username');
  this.us.friendList = null;
  this.us.curUserProfile = null;
  this.us.allProfiles = null;
  this.us.curPageUserProfile = null;
  this.us.userStorage.set('username', JSON.stringify(this.name.nativeElement.value));
  console.log(JSON.parse(this.us.userStorage.get('username')));
}
  ngOnInit() {
  }

}
