import { Component, OnInit, Input, SimpleChanges, SimpleChange, OnChanges } from '@angular/core';
import { UserProfile } from 'src/app/shared/class/Profile';
import { UserService } from 'src/app/shared/UserService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cover-img',
  templateUrl: './cover-img.component.html',
  styleUrls: ['./cover-img.component.css']
})
export class CoverImgComponent implements  OnChanges, OnInit {

  @Input() curUserProfile: UserProfile = null;
  constructor() {}

  ngOnChanges(changes: SimpleChanges) {
    if (!this.curUserProfile.profileImageUrl) {
      this.curUserProfile.profileImageUrl =
      'http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png';
    }
    const curUserProfile: SimpleChange = changes.curUserProfile;
    console.log('prev value: ', curUserProfile.previousValue);
    console.log('got new: ', curUserProfile.currentValue);
    this.curUserProfile = curUserProfile.currentValue;
  }
  ngOnInit() {
if (!this.curUserProfile.profileImageUrl) {
      this.curUserProfile.profileImageUrl =
      'http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png';
    }
  }

}
