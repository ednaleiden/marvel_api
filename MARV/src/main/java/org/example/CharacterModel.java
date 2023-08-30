package org.example;

import java.util.List;

public class CharacterModel {
    private String name;
    private String description;
    private String thumbnailURL;
    private List<String> comicsTitle;

    public CharacterModel() {
        this.name = name;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.comicsTitle = comicsTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public List<String> getComicsTitle() {
        return comicsTitle;
    }

    public void setComicsTitle(List<String> comicsTitle) {
        this.comicsTitle = comicsTitle;
    }
}
