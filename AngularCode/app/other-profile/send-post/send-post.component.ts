import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/shared/class/Post';
import { UploadImgService } from 'src/app/shared/UploadingImgService';
import { UserService } from 'src/app/shared/UserService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-send-post',
  templateUrl: './send-post.component.html',
  styleUrls: ['./send-post.component.css']
})
export class SendPostComponent implements OnInit {
  myPostImageUrl: string;
  selectedFiles: FileList;
  post: Post;
  show: boolean;
  constructor(private uploadService: UploadImgService, private us: UserService, private routerMod: Router) {
    this.show = false;
    for (const temp of this.us.friendList) {
      if (temp.profileId === this.us.curPageUserProfile.profileId) {
        this.show = true;
        break;
      }
    }
  }
  addPost(content: string) {
    if (this.selectedFiles) {
      const file = this.selectedFiles.item(0);
      this.myPostImageUrl = this.uploadService.uploadfile(file); // this take care of sending img to s3.
    }
    if (this.us.curUserProfile || this.us.curPageUserProfile) {
      alert('sorry, due to some technology issue, we cannot process your request. Please try again later');
      return;
    }
    this.post = new Post(0, this.us.curUserProfile, this.us.curPageUserProfile, content,
      this.myPostImageUrl, null);
    this.us.addPost(this.post).subscribe();
  }


  selectFile(event) {
    this.selectedFiles = event.target.files;
  }
  ngOnInit() {
  }

}
