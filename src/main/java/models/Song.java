package models;

/**
 * Created by marcin on 27/05/17.
 */
public class Song {
    private int id;
    private String title, artist, album;
    private double length;

    public Song(int id, String title, String artist, String album, double length) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public double getLength() { return length; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setAlbum(String album) { this.album = album; }
    public void setLength(double length) { this.length = length; }
}
