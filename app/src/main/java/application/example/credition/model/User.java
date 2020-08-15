package application.example.credition.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private int id;
    private String userName;
    private String userEmail;
    private int credits;
    private String userImage;

    public User(String userName, String userEmail, String userImage, int credits) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.credits = credits;
        this.userImage = userImage;
    }

    public User(int id, String userName, String userEmail, String userImage, int credits) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.credits = credits;
        this.userImage = userImage;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.userEmail);
        dest.writeInt(this.credits);
        dest.writeString(this.userImage);
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.userName = in.readString();
        this.userEmail = in.readString();
        this.credits = in.readInt();
        this.userImage = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
