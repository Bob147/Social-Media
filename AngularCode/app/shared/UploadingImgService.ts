import * as AWS from 'aws-sdk/global';
import * as S3 from 'aws-sdk/clients/s3';
import { Injectable } from '@angular/core';
import { stringify } from '@angular/compiler/src/util';

@Injectable()
export class UploadImgService {

  myImgUrl: string;
  constructor() { }
  uploadfile(file): string {
    const bucket = new S3(
      {
        accessKeyId: 'AKIA2YQ2WT2ID3PGJI6U',
        secretAccessKey: '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',
        region: 'us-west-2'
      }
    );

    const params = {
      Bucket: 'ellenfirstbucketforproject2',
      Key:  file.name,
      ACL: 'public-read',
      Body: file
    };

// tslint:disable-next-line: only-arrow-functions
    bucket.upload(params, function(err, data) {
      if (err) {
        alert('There was an error uploading your file, please try again later');
        return false;
      }
      alert('Successfully uploaded file. plaese refresh the page.');
      return true;
    });
    this.myImgUrl = 'https://ellenfirstbucketforproject2.s3.us-west-2.amazonaws.com/' + file.name;
    console.log(this.myImgUrl);
    return this.myImgUrl;
  }

}
