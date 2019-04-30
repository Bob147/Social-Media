import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/shared/class/Post';
import { UserService, PostLikeCount } from 'src/app/shared/UserService';

@Component({
  selector: 'app-user-posts',
  templateUrl: './user-posts.component.html',
  styleUrls: ['./user-posts.component.css']
})
export class UserPostsComponent implements OnInit {
  posts: Post[];
  profilePicWhenNotSpecified: string;
   likeCount: PostLikeCount[] = [];
   isLiked: boolean[] = [];
   constructor(private us: UserService) {
     this.profilePicWhenNotSpecified
     = 'http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png';
     this.us.retrieveUserPostList(this.us.curUserProfile.profileId)
     .subscribe(
       data3 => {
         console.log(data3);
         this.posts = data3;
 // tslint:disable-next-line: prefer-for-of
         for (let i = 0; i < data3.length; i++) {
           this.isLiked.push(false); // initialized to be not liked
           this.likeCount.push(new PostLikeCount(data3[i].postId, 0));
           this.posts[i].timestamp = new Date(this.posts[i].timestamp);
         }
         this.us.selectLikeCounts()
     .subscribe(
       data2 => {
 // tslint:disable-next-line: forin
         for (const i in data3) {
           for (const temp of data2) {
             if (data3[i].postId === temp.postId) {
               this.likeCount[i].likeCount = temp.likeCount;
             }
         }

         }
         this.us.getUserLikedPosts()
         .subscribe(
           data1 => {
             for (const userliked of data1) {
               for (const i in data3) {
                 if (data3[i].postId === userliked.postId) {
                   this.isLiked[i] = true;
                 }
               }
             }
           }
         );
       }
     );
   }
   );
    }
    toLikeOrUnlikePost(i: number): void {
      console.log(i);
      if (this.isLiked[i]) {
       this.us.likeOrUnlikePost(false, this.posts[i].postId).subscribe();
       this.likeCount[i].likeCount--;
     } else {
       this.us.likeOrUnlikePost(true, this.posts[i].postId).subscribe();
       this.likeCount[i].likeCount++;
     }
      this.isLiked[i] = !this.isLiked[i];
   }
  ngOnInit() {
  }

}
