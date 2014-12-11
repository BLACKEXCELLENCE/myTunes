/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Christopher, Mark, Martin & Rasmus
 */
public class BESong {
    
    private int id;
    private String title;
    private String artist;
    private int length;
    private String categoryId;
    private String filename;
    private String searchWord;
    
    /**
     *
     * @param id
     * @param title
     * @param artist
     * @param categoryId
     * @param filename
     */
    public BESong(int id, 
            String title, 
            String artist, 
            String categoryId,
            String filename)
            
    {
        this.id=id; this.title = title; this.artist = artist;
        this.length = length; this.categoryId = categoryId;
        this.filename = filename;
    }

    /**
     *
     * @param id
     */
    public BESong(int id){
        this.id=id;
    }
    
    /**
     *
     * @param id
     * @param title
     * @param artist
     */
    public BESong(int id,
            String title,
            String artist)
    {
        this.id=id; this.title=title; this.artist=artist;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    /**
     *
     * @return
     */
    public String getSearchWord() {
        return searchWord;
    }
}