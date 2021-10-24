package com.example.evernote.userentity;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable {

    private int id;
    private String mail;
    private String password;

    public Account(int id, String mail, String password) {
        this.id = id;
        this.mail = mail;
        this.password = password;
    }

    public Account() {}

    protected Account(Parcel in) {
        id = in.readInt();
        mail = in.readString();
        password = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    //Getters
    public int getId() { return id; }
    public String getMail() { return mail; }
    public String getPassword() { return password; }

    //Setters
    public void setId(int id) { this.id = id; }
    public void setMail(String mail) { this.mail = mail; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mail);
        dest.writeString(password);
    }
}
