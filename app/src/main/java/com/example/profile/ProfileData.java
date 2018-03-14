package com.example.profile;

/**
 * Created by Rahul on 09-03-2018.
 */

public class ProfileData {

    private String imageUrl;
    private String name;
    private String skill;
    private String id;


    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {

        return changeCase(skill);
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String changeCase(String str){
        str=str.trim();
        str=str.toLowerCase();
        StringBuilder result = new StringBuilder(str.length());
        String words[] = str.split(" ");


        for (int i = 0; i < words.length; i++)
        {
            words[i]=words[i].trim();
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");

        }
        return result.toString();
    }


}
