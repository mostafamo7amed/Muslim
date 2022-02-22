package com.mostafadesha.quranfm.Models;

import com.google.gson.annotations.SerializedName;

public class Surah {
    @SerializedName("number")
    int number;

    @SerializedName("name")
    String name;

    @SerializedName("englishName")
    String englishName;

    @SerializedName("numberOfAyahs")
    int numberOfAyahs;

    @SerializedName("revelationType")
    String revelationType;

    public Surah(int number, String name, String englishName, int numberOfAyahs, String revelationType) {
        this.number = number;
        this.name = name;
        this.englishName = englishName;
        this.numberOfAyahs = numberOfAyahs;
        this.revelationType = revelationType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getNumberOfAyahs() {
        return numberOfAyahs;
    }

    public void setNumberOfAyahs(int numberOfAyahs) {
        this.numberOfAyahs = numberOfAyahs;
    }

    public String getRevelationType() {
        return revelationType;
    }

    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }
}
