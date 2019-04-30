import { UserProfile } from './Profile';

export class Post {
    'postId': number;
    'sender': UserProfile;
    'receiver': UserProfile;
    'contents': string;
    'image_url': string;
    'timestamp': Date;

    constructor(postId: number, sender: UserProfile, receiver: UserProfile, contents: string, image_url: string, timestamp: Date){
        this.postId = postId;
        this.sender = sender;
        this.receiver = receiver;
        this.contents = contents;
        this.image_url = image_url;
        this.timestamp = timestamp;
        }
    // get getTimestamp(): Date {
    //     return new Date(this.timestamp);
    // }
}
