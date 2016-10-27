package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.physical;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.ConvertHandler;

/**
 * Created by Edwin on 11/10/2015
 */
public class Account implements Parcelable {
    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
    //region Fields
    private int id;
    private final String username;
    private String email;
    private final String password;
    private Date creation;
    //endregion
    private boolean blocked;

    //region Constructors
    public Account(int id, String username, String email, String password, Date creation, boolean blocked) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creation = creation;
        this.blocked = blocked;
    }

    private Account(Parcel in) {
        id = in.readInt();
        username = in.readString();
        email = in.readString();
        password = in.readString();
        creation = ConvertHandler.StringToDate(in.readString());
        blocked = in.readByte() != 0;
    }

    //region Properties
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    //endregion

    public String getPassword() {
        return password;
    }

    public Date getCreation() {
        return creation;
    }
    //endregion

    public boolean getBlocked() {
        return blocked;
    }

    //region Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(getId());
        out.writeString(getUsername());
        out.writeString(getEmail());
        out.writeString(getPassword());
        out.writeString(ConvertHandler.DateToString(getCreation()));
        out.writeByte((byte) (getBlocked() ? 1 : 0));
    }

    @Override
    public String toString() {
        return "User: " + username + ", email:" + email + ", pass: " + password + ", creation: " + ConvertHandler.DateToString(creation) + ", blocked; " + blocked;
    }
    //endregion
}
