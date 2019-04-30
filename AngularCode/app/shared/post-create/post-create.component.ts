import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { UploadImgService } from '../UploadingImgService';
import { UserService } from '../UserService';
import { Router } from '@angular/router';
import { Post } from '../class/Post';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.css']
})
export class PostCreateComponent implements OnInit {
  myPostImageUrl: string;
  selectedFiles: FileList;
  post: Post;
  constructor(private uploadService: UploadImgService, private us: UserService, private routerMod: Router) {
    if (!this.us.curUserProfile) {this.routerMod.navigate(['/error']); }
  }
  addPost(content: string) {
    if (this.selectedFiles) {
      const file = this.selectedFiles.item(0);
      this.myPostImageUrl = this.uploadService.uploadfile(file); // this take care of sending img to s3.
    }
    this.post = new Post(0, this.us.curUserProfile, this.us.curUserProfile, content,
      this.myPostImageUrl, null);
    this.us.addPost(this.post).subscribe();
  }


  selectFile(event) {
    this.selectedFiles = event.target.files;
  }
  ngOnInit() {
  }

}
