package com.travel.travel.models.dto;

public class DestinationDTO {
    private int id;
    private String title;
    private String location;
    private String description;
    private String urlImage;
    private UserDTO user;

    public DestinationDTO(int id, String title, String location, String description, String urlImage, UserDTO user) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.urlImage = urlImage;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
