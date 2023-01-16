package mihai.songserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository repository;

    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    public int add(Song song) {
        return repository.add(new Song(song));
    }

    public boolean update(Song song) {
        return repository.update(song);
    }

    public List<Song> getAll() {
        return repository.getAll();

    }
}
