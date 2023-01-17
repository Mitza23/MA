package mihai.songserver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<Song>> getAll() {
        var list = songService.getAll();
        System.out.println("GET ALL");
        for( var s : list) {
            System.out.println("Get: " + s);
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @PostMapping
    public ResponseEntity<Integer> addSong(@RequestBody Song song) {
        System.out.println("Add: " + song);
        Integer id =  songService.add(song);
        return ResponseEntity.of(Optional.of(id));
    }

    @PutMapping
    public boolean updateSong(@RequestBody Song song) {
        System.out.println("Update: " + song);
        return songService.update(song);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSong(@PathVariable int id) {
        System.out.println("Delete: " + id);
        Boolean result = songService.delete(id);
        return ResponseEntity.of(Optional.of(result));
    }
}
