package mihai.songserver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping
    public List<Song> getAll() {
        return songService.getAll();
    }

    @PostMapping
    public int addSong(@RequestBody Song song) {
        return songService.add(song);
    }

    @PutMapping
    public boolean updateSong(@RequestBody Song song) {
        return songService.update(song);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSong(@PathVariable int id) {
        return songService.delete(id);
    }
}
