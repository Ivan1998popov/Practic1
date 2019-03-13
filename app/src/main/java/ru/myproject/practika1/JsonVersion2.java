package ru.myproject.practika1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class JsonVersion2 {


        @SerializedName("Author")
        @Expose
        private String author;
        @SerializedName("Genre")
        @Expose
        private String genre;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("PublicationDate")
        @Expose
        private Integer publicationDate;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public JsonVersion2 withAuthor(String author) {
            this.author = author;
            return this;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public JsonVersion2 withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public JsonVersion2 withName(String name) {
            this.name = name;
            return this;
        }

        public Integer getPublicationDate() {
            return publicationDate;
        }

        public void setPublicationDate(Integer publicationDate) {
            this.publicationDate = publicationDate;
        }

        public JsonVersion2 withPublicationDate(Integer publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }


    @Override
    public String toString() {

        return "Author : "+author+
                " Genre: "+genre+
                " Name: "+name+
                " PublicationDate: "+publicationDate+"\n";
    }
}

