import { Injectable, Inject } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserProfile } from './class/Profile';
import { Post } from './class/Post';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';

export class PostLikeCount {
  postId: number;
  likeCount: number;
  constructor(postId: number, likeCount: number) {
    this.postId = postId;
    this.likeCount = likeCount;
  }
}

@Injectable()
export class UserService {
    curUserProfile: UserProfile;
    curPageUserProfile: UserProfile = null;
    friendList: UserProfile[];
    allProfiles: UserProfile[];
// tslint:disable-next-line: variable-name
constructor(private httpCli: HttpClient, @Inject(LOCAL_STORAGE) public userStorage: WebStorageService) {
  if (this.userStorage.get('curUserProfile')) {
        this.curUserProfile = JSON.parse(this.userStorage.get('curUserProfile'));
        if (this.userStorage.get('friendList')) {this.friendList = JSON.parse(this.userStorage.get('friendList')); } else {
          this.retrieveFriendList().subscribe(
            data1 => {
             this.friendList = data1;
             this.userStorage.set('friendList', JSON.stringify(data1));
            }
          );
        }
      } else {
        this.friendList = null;
        this.curUserProfile = null;
      }

  if (!this.userStorage.get('allProfiles')) {
        this.getAllProfiles().subscribe(
          data2 => {
            console.log('get all profiles: ' + data2);
            this.allProfiles = data2;
            this.userStorage.set('allProfiles', JSON.stringify(data2));
          }
        );
      }
  this.allProfiles = JSON.parse(this.userStorage.get('allProfiles'));

  if (this.userStorage.get('curPageUserProfile')) {
    this.curPageUserProfile = JSON.parse(this.userStorage.get('curPageUserProfile')); }
}

// loginUser(user: string, password: string): Observable<UserProfile> {
//     let body = {
//         'username': user,
//         'password': password
//     }
//     return this.httpCli.post<UserProfile>(
//         'http://localhost:9010/project2_withSpring/login.do', body);
//     }

loginUser1(user: string, password: string): Observable<UserProfile> {
    return this.httpCli.get<UserProfile>(
        'http://localhost:9010/project2_withSpring/login.do?username=' + user + '&password=' + password);
}

signUp(user: string, password: string, firstName: string, lastName: string, email: string): Observable<boolean> {
    return this.httpCli.get<boolean>(
        'http://localhost:9010/project2_withSpring/signup.do?username=' + user + '&password=' + password
        + '&firstname=' + firstName + '&lastname=' + lastName + '&email=' + email);
}
changePassword(user: string, oldPass: string, newPass: string): Observable<boolean> {
// tslint:disable-next-line: max-line-length
  return this.httpCli.get<boolean>('http://localhost:9010/project2_withSpring/changePassword.do?username=' + user + '&oldPassword=' + oldPass + '&newPassword=' + newPass);
  }
resetPassword(userNameForReset: string) {
  return this.httpCli.get<boolean>('http://localhost:9010/project2_withSpring/resetPassword.do?username=' + userNameForReset);
  }
getProfileById(id: number): Observable<UserProfile> {
  return this.httpCli.get<UserProfile>(
    'http://localhost:9010/project2_withSpring/getProfileById.do?id' + id);
}
retrieveFriendList(): Observable<UserProfile[]> {
    console.log('this.curUserProfile.profileId is ' + this.curUserProfile.profileId);
    return this.httpCli.get<UserProfile[]>(
        'http://localhost:9010/project2_withSpring/getFriendsListById.do?id=' + this.curUserProfile.profileId);
}
// again.
retrieveAllPostList(): Observable<Post[]> {
    return this.httpCli.get<Post[]>('http://localhost:9010/project2_withSpring/getAllPosts.do');
}

retrieveUserPostList(id: number): Observable<Post[]> {
    return this.httpCli.get<Post[]>('http://localhost:9010/project2_withSpring/getUserPosts.do?id=' + id);
  }
  editProfile(body: UserProfile): Observable<UserProfile> {
    return this.httpCli.post<UserProfile>('http://localhost:9010/project2_withSpring/updateProfile.do', body);
  }
  addPost(body): Observable<boolean> {
   return this.httpCli.post<boolean>('http://localhost:9010/project2_withSpring/submitNewPost.do', body);
  }

  selectLikeCounts(): Observable<PostLikeCount[]> {
    return this.httpCli.get<PostLikeCount[]>('http://localhost:9010/project2_withSpring/getLikeCounts.do');
   }

  getUserLikedPosts(): Observable<Post[]> {
    return this.httpCli.get<Post[]>('http://localhost:9010/project2_withSpring/getUserLikedPosts.do?id=' + this.curUserProfile.profileId);
  }
  likeOrUnlikePost(toLike: boolean, postId: number): Observable<boolean> {
    if (toLike) {
    return this.httpCli.get<boolean>('http://localhost:9010/project2_withSpring/likePost.do?profileId='
    + this.curUserProfile.profileId + '&postId=' + postId);
    } else {
     return  this.httpCli.get<boolean>('http://localhost:9010/project2_withSpring/unlikePost.do?profileId='
    + this.curUserProfile.profileId + '&postId=' + postId);
    }
  }
  getAllProfiles(): Observable<UserProfile[]> {
    return this.httpCli.get<UserProfile[]>('http://localhost:9010/project2_withSpring/getAllProfiles.do');
  }
  addFriend(uId: number, fId: number): Observable<boolean> {
    return this.httpCli.get<boolean>('http://localhost:9010/project2_withSpring/addFriend.do?userId=' + uId + '&friendId=' + fId);
   }
  removeFriend(uId: number, fId: number): Observable<boolean> {
  return this.httpCli.get<boolean>('http://localhost:9010/project2_withSpring/removeFriend.do?userId=' + uId + '&friendId=' + fId);
  }
}

