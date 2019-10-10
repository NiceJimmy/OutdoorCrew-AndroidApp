package org.techtown.myrecyclerview;

public class Dictionary2 {

    private String title;
    private String contents;
    private String image;
    private String posit;

    public Dictionary2(String title, String contents, String image, String posit) {
        this.title = title;
        this.contents = contents;
        this.image = image;
        this.posit = posit;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosit() {
        return posit;
    }

    public void setPosit(String posit) {
        this.posit = posit;
    }
}