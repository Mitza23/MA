package mihai.songserver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Song {
    private static int count = 0;
    private Integer id;
    private String title;
    private String artist;
    private String genre;
    private int year;
    private String link;

    public int getId() {
        if(id == null) {
            this.id = ++count;
        }
        return id;
    }

    public Song(String title, String artist, String genre, int year, String link) {
        this.id = ++count;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.link = link;
    }

    public Song(Song song) {
        this.id = ++count;
        this.title = song.title;
        this.artist = song.artist;
        this.genre = song.genre;
        this.year = song.year;
        this.link = song.link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return Objects.equals(id, song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void update(Song song) {
        title = song.title;
        artist = song.artist;
        genre = song.genre;
        year = song.year;
        link = song.link;
    }
}
