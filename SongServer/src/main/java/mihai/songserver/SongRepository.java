package mihai.songserver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository {
    private List<Song> list;

    public SongRepository() {
        list = new ArrayList<>();
        list.add(new Song("Man With The Bag", "Jessie J", "Christmas", 2018,
                "https://youtu.be/_gNbBq0wtNg"));
        list.add(new Song("Undeva in Balkani", "Puya", "hip-hop", 2009,
                "https://youtu.be/rvhqsqmzm20"));
        list.add(new Song("Daca Pleci - Remix", "Dirty Nano", "dance", 2022,
                "https://youtu.be/Yo7fHE7GDoE"));
        list.add(new Song("Snowman", "Sia", "Christmas", 2018,
                "https://youtu.be/J_QGZspO4gg"));
    }

    public List<Song> getAll() {
        return list;
    }

    public boolean deleteById(int id) {
        for(int i = 0 ; i < list.size() ; i++){
            if(list.get(i).getId() == id){
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    public int add(Song song) {
        list.add(song);
        return song.getId();
    }

    public boolean update(Song song) {
        for(var litSong: list){
            if(litSong.equals(song)){
                litSong.update(song);
                return true;
            }
        }
        return false;
    }

}
