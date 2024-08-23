package com.manueljsanchezg.libray_backend.model;

import org.antlr.v4.runtime.misc.NotNull;

public class BookDTO {

    private String title;
    private String description;
    private Integer year;
    private String genre;
    private String authorName;

    public BookDTO(String title, String description, Integer year, String genre, String authorName) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.genre = genre;
        this.authorName = authorName;
    }

    public BookDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
