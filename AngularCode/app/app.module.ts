import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { UserService } from './shared/UserService';
import { UserHomeComponent } from './user-home/user-home.component';
import { UploadImgService } from './shared/UploadingImgService';
import { HttpClientModule } from '@angular/common/http';
import { NavComponent } from './credential-page/loginNavBar/nav.component';
import { SignUpComponent } from './credential-page/sign-up/sign-up.component';
import { CredentialPageComponent } from './credential-page/credential-page.component';
import { FooterComponent } from './shared/footer/footer.component';
import { NewsfeedComponent } from './user-home/newsfeed/newsfeed.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FriendsListComponent } from './shared/friends-list/friends-list.component';
import { SearchNavComponent } from './shared/search-nav/search-nav.component';
import { SideProfileComponent } from './user-profile/side-profile/side-profile.component';
import { PostCreateComponent } from './shared/post-create/post-create.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import {StorageServiceModule} from 'angular-webstorage-service';
import { UserPostsComponent } from './user-profile/user-posts/user-posts.component';
import { CustomErrorPageComponent } from './custom-error-page/custom-error-page.component';
import { CoverImgComponent } from './user-profile/cover-img/cover-img.component';
import {FormsModule} from '@angular/forms';
import { OtherProfileComponent } from './other-profile/other-profile.component';
import { SendPostComponent } from './other-profile/send-post/send-post.component';
import { OtherPostComponent } from './other-profile/other-post/other-post.component';
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    FriendsListComponent,
    UserHomeComponent,
    SignUpComponent,
    CredentialPageComponent,
    FooterComponent,
    SearchNavComponent,
    SideProfileComponent,
    NewsfeedComponent,
    PostCreateComponent,
    UserProfileComponent,
    EditProfileComponent,
    UserPostsComponent,
    CustomErrorPageComponent,
    CoverImgComponent,
    OtherProfileComponent,
    SendPostComponent,
    OtherPostComponent

  ],
  imports: [
    StorageServiceModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot([
      {path: 'login', component: CredentialPageComponent},
      {path: 'userhome', component: UserHomeComponent},
      {path: 'profile', component: UserProfileComponent},
      {path: 'otherprofile', component: OtherProfileComponent},
      {path: 'editprofile', component: EditProfileComponent},
      {path: 'error', component: CustomErrorPageComponent},
      {path: '', component: CredentialPageComponent},
      {path: '**', component: CredentialPageComponent}
    ])
  ],
  providers: [UserService, UploadImgService],
  bootstrap: [AppComponent]
})
export class AppModule { }
