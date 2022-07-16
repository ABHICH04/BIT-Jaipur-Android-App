package com.example.bitjaipur.ui.faculty;

public class TeacherData  {
    private String name, email, post, image, uniqueKey,ph,category;
    public TeacherData()
    {

    }

    public TeacherData(String name, String email, String post, String image,String ph, String uniqueKey) {
        this.name = name;
        this.email = email;
        this.post = post;
        this.image = image;
        this.ph=ph;
        this.uniqueKey = uniqueKey;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }


}