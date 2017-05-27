package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marcin on 27/05/17.
 */
public class Library {
    private List<Song> songs;

    public Library(){
        this.songs = new ArrayList<>();
    }

    public List<Song> getSongs() { return songs; }
    public void setSongs(List<Song> songs) { this.songs = songs; }

    public void addSong(Song song){ this.songs.add(song); }
    public void removeSong(Song song){
        Iterator<Song> it = this.songs.iterator();
        while(it.hasNext()){
            Song s = it.next();
            if(s == song)
                it.remove();
        }
    }
}
