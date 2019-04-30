import { Component, OnInit } from '@angular/core';
import { UploadImgService } from '../shared/UploadingImgService';

@Component({
  selector: 'app-test-s3',
  templateUrl: './test-s3.component.html',
  styleUrls: ['./test-s3.component.css']
})
export class TestS3Component implements OnInit {
  myImageUrl: string;
  selectedFiles: FileList;
  constructor(private uploadService: UploadImgService) {
    this.myImageUrl = 'https://ellenfirstbucketforproject2.s3.us-west-2.amazonaws.com/2.jpeg';
  }

  ngOnInit() {
  }

  upload() {
    const file = this.selectedFiles.item(0);
    this.myImageUrl = this.uploadService.uploadfile(file);
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

}
