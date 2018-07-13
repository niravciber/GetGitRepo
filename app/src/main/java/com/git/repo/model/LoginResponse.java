package com.git.repo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable{

    @SerializedName("token_type")
    public String token_type;
    @SerializedName("scope")
    public String scope;
    @SerializedName("access_token")
    public String access_token;

    protected LoginResponse(Parcel in) {
        token_type = in.readString();
        scope = in.readString();
        access_token = in.readString();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token_type);
        dest.writeString(scope);
        dest.writeString(access_token);
    }
}
