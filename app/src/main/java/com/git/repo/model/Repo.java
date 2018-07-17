package com.git.repo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;

public class Repo {

    public Repo(){}
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("full_name")
    String full_name;
    @SerializedName("description")
    String description;
    @SerializedName("stargazers_count")
    String starts;
    @SerializedName("owner")
    Permissons permissons;

    public Permissons getPermissons() {
        return permissons;
    }

    public void setPermissons(Permissons permissons) {
        this.permissons = permissons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }


    public static class Permissons{
        @SerializedName("avatar_url")
            String photo;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

}
