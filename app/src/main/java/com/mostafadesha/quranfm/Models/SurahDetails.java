package com.mostafadesha.quranfm.Models;

import com.google.gson.annotations.SerializedName;

public class SurahDetails {

    @SerializedName("id")
    int id;

    @SerializedName("sura")
    int sura;

    @SerializedName("aya")
    int aya;

    @SerializedName("arabic_text")
    String arabic_text;

    @SerializedName("translation")
    String translation;

    @SerializedName("footnotes")
    String footnotes;

    public SurahDetails(int id, int sura, int aya, String arabic_text, String translation, String footnotes) {
        this.id = id;
        this.sura = sura;
        this.aya = aya;
        this.arabic_text = arabic_text;
        this.translation = translation;
        this.footnotes = footnotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSura() {
        return sura;
    }

    public void setSura(int sura) {
        this.sura = sura;
    }

    public int getAya() {
        return aya;
    }

    public void setAya(int aya) {
        this.aya = aya;
    }

    public String getArabic_text() {
        return arabic_text;
    }

    public void setArabic_text(String arabic_text) {
        this.arabic_text = arabic_text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }
}
