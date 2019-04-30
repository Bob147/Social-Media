import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { UserService } from 'src/app/shared/UserService';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private us: UserService) { }
  @ViewChild('firstName') firstName: ElementRef;
  @ViewChild('lastName') lastName: ElementRef;
  @ViewChild('emailInput') email: ElementRef;
  @ViewChild('userName') userName: ElementRef;
  @ViewChild('passWord') psw: ElementRef;
  @ViewChild('userNameForReset') userNameForReset: ElementRef;
  signUp(): void {
    if (!this.userName.nativeElement.value || !this.psw.nativeElement.value) {
      alert('input can not be empty.'); return;
    }
    this.us.signUp(this.userName.nativeElement.value, this.psw.nativeElement.value,
      this.firstName.nativeElement.value, this.lastName.nativeElement.value, this.email.nativeElement.value)
      .subscribe(data => {
        console.log(data);
        if (!data) {
          alert('unsucessful sign up');
          } else {
            alert('sucessfully signed up, please log in');
         }
      });
  }

  resetPassword() {
    this.us.resetPassword(this.userNameForReset.nativeElement.value).subscribe();
  }
  ngOnInit() {
  }

}
