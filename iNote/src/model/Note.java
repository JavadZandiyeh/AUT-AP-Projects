package model;

import java.io.Serializable;

// TODO: Phase2: uncomment this code

/**
 * this is a serializable class for saving our notes to file
 * by object input streams
 */
public class Note implements Serializable {

    private String title;
    private String content;
    private String date;

    /**
     * constructor
     * @param title title of note
     * @param content content of note
     * @param date date that note has been saved
     */
    public Note(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    /**
     * @return all details of note
     */
    public String getContent_All() {
        return toString();
    }

    @Override
    public String toString() {
        return ", date='" + date + '\'' +
                ", content='" + content +
                 "title='" + title + '\'';
    }

}

