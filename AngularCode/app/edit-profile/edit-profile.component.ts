import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { UserProfile } from '../shared/class/Profile';
import { UploadImgService } from '../shared/UploadingImgService';
import { UserService } from '../shared/UserService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  constructor(private uploadService: UploadImgService, private us: UserService, private routerMod: Router) {
    if (!this.us.userStorage.get('curUserProfile')) {this.routerMod.navigate(['/error']); }
    this.profile = this.us.curUserProfile;
    this.myImageUrl = this.us.curUserProfile.profileImageUrl;
    if (!this.myImageUrl) {
      this.myImageUrl =
      'http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png';
    }
  }
  myImageUrl: string;
  selectedFiles: FileList;
  profile: UserProfile;
  @ViewChild('firstname') fname: ElementRef;
  @ViewChild('lastname') lname: ElementRef;
  @ViewChild('gender') gender: ElementRef;
  @ViewChild('birthdate') birthdate: ElementRef;
  @ViewChild('occupation') occupation: ElementRef;
  @ViewChild('rstatus') relationshipStatus: ElementRef;
  @ViewChild('cstatus') currentStatus: ElementRef;
  @ViewChild('hobbies') hobbies: ElementRef;
  @ViewChild('aboutme') aboutMe: ElementRef;
  @ViewChild('oldPass') oldPass: ElementRef;
  @ViewChild('newPass') newPass: ElementRef;
  @ViewChild('newPass2') newPass2: ElementRef;
  editProfile() {
    if (this.selectedFiles) {
      const file = this.selectedFiles.item(0);
      this.myImageUrl = this.uploadService.uploadfile(file); // this take care of sending img to s3.
    }
    this.profile = new UserProfile(
      this.us.curUserProfile.profileId,
      this.fname.nativeElement.value, this.lname.nativeElement.value,
      this.gender.nativeElement.value, this.birthdate.nativeElement.value,
      this.occupation.nativeElement.value, this.relationshipStatus.nativeElement.value,
      this.currentStatus.nativeElement.value, this.hobbies.nativeElement.value,
      this.aboutMe.nativeElement.value, this.myImageUrl);
    this.us.editProfile(this.profile)
      .subscribe(
        data => {
          if (!data) {
            alert('update profile uncessessfully');
            } else {
              this.us.curUserProfile = data;
              this.us.userStorage.set('curUserProfile', JSON.stringify(this.us.curUserProfile));
              console.log(this.us.curUserProfile);
              this.routerMod.navigate(['/profile']);
           }
        }
      );
  }
  changePassword() {
    if (this.newPass.nativeElement.value !== this.newPass2.nativeElement.value) {
      alert('comfirmed password not match new password');
      return;
    } else {
      if (
      this.us.changePassword(JSON.parse(this.us.userStorage.get('username')),
        this.oldPass.nativeElement.value,
        this.newPass.nativeElement.value).subscribe()) {
          alert('password changed successful');
        } else {
          alert('password changed unsuccessful');
        }
      this.routerMod.navigate(['/userhome']);
    }
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  ngOnInit() {
  }

}
