export class UserProfile {
    'profileId': number;
    'firstName': string;
    'lastName': string;
    'gender': string;
    'birthdate': Date;
    'occupation': string;
    'relationshipStatus': string;
    'currentStatus': string;
    'hobbies': string;
    'aboutMe': string;
    'profileImageUrl': string;


// tslint:disable-next-line: max-line-length
  constructor(id: number, firstName: string, lastName: string, gender: string, birthdate: Date, occupation: string, relationshipStatus: string, currentStatus: string, hobbies: string, aboutMe: string, profileImageUrl: string) {
    this.profileId = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.birthdate = birthdate;
    this.occupation = occupation;
    this.relationshipStatus = relationshipStatus;
    this.currentStatus = currentStatus;
    this.hobbies = hobbies;
    this.aboutMe = aboutMe;
    this.profileImageUrl = profileImageUrl;
  }
}
