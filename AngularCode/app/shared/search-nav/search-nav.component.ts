import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../UserService';
import { UserProfile } from '../class/Profile';

@Component({
  selector: 'app-search-nav',
  templateUrl: './search-nav.component.html',
  styleUrls: ['./search-nav.component.css']
})
export class SearchNavComponent implements OnInit {
  name: string;
  imgUrl: string;
  ProfileList: UserProfile[];
  filteredProfileList: UserProfile[];
// tslint:disable-next-line: variable-name
  _listFilter = '';

  get listFilter() {
      return this._listFilter;
  }
  set listFilter(temp: string) {
      this._listFilter = temp;
      this.filteredProfileList =
          this.listFilter ?
          this.performFilter(this._listFilter) : this.ProfileList;
  }
  constructor(private us: UserService, private routerMod: Router) {
    this.name = this.us.curUserProfile.firstName + ' ' + this.us.curUserProfile.lastName;
    this.imgUrl = this.us.curUserProfile.profileImageUrl;
    if (!this.imgUrl) {
      this.imgUrl =
      'http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png';
    }

    if (this.us.allProfiles) {
      this.ProfileList = this.us.allProfiles;
      this.filteredProfileList = this.us.allProfiles;
    } else {
      this.us.getAllProfiles().subscribe(
        data2 => {
          console.log('get all profiles: ' + data2);
          this.us.allProfiles = data2;
          this.us.userStorage.set('allProfiles', JSON.stringify(data2));
          this.ProfileList = this.us.allProfiles;
          this.filteredProfileList = this.us.allProfiles;
        }
      );
    }
    console.log('in search bar : ' + this.ProfileList);
    }

  performFilter(filterBy: string): UserProfile[] {
      filterBy = filterBy.toLocaleLowerCase();
      return this.ProfileList.filter((ProfileList: UserProfile) =>
        (ProfileList.firstName + ' ' + ProfileList.lastName).toLocaleLowerCase().indexOf(filterBy)
              !== -1);
  }
  goToOthersProfile(profile: UserProfile) {
    this.us.curPageUserProfile = profile;
    this.us.userStorage.set('curPageUserProfile', JSON.stringify(profile));
    if (this.routerMod.url === '/otherprofile') {
      location.reload();
    } else {
      this.routerMod.navigate(['/otherprofile']);
    }
  }
  goToProfile() {
    this.routerMod.navigate(['/profile']);
  }
  goToEdit() {
    this.routerMod.navigate(['/editprofile']);
  }
  goHome() {
    this.routerMod.navigate(['/userhome']);
  }

  logOut() {
    this.us.userStorage.remove('friendList');
    this.us.userStorage.remove('curUserProfile');
    this.us.userStorage.remove('allProfiles');
    this.us.userStorage.remove('curPageUserProfile');
    this.us.friendList = null;
    this.us.curUserProfile = null;
    this.us.allProfiles = null;
    this.us.curPageUserProfile = null;
    this.routerMod.navigate(['/login']);
  }

  ngOnInit() {
  }

}
