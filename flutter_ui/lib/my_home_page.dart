import 'package:flutter/material.dart';
import 'package:flutter_ui/add_song.dart';
import 'package:flutter_ui/edit_song.dart';
import 'package:flutter_ui/song.dart';

class MyHomePage extends StatefulWidget {
  final String _title;
  const MyHomePage(this._title, {super.key});

  @override
  State<StatefulWidget> createState() => _MyHomePAgeState();
}

class _MyHomePAgeState extends State<MyHomePage> {
  List<Song> songs = [
    Song("a", "a", "a", 1, "a"),
    Song("b", "b", "b", 1, "b"),
    Song("c", "c", "c", 1, "c"),
    Song("d", "d", "d", 1, "d")
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget._title),
      ),
      body: ListView.builder(
        itemCount: songs.length,
        itemBuilder: (context, index) {
          return ListTile(
            onTap: () {
              Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (_) => EditSongForm(songs[index])))
                  .then((newContact) {
                if (newContact != null) {
                  setState(() {
                    songs.removeAt(index);
                    songs.insert(index, newContact);
                  });
                }
              });
            },
            title: Text(songs[index].title),
            subtitle: Text(songs[index].artist),
            trailing: IconButton(
              icon: const Icon(Icons.delete),
              color: Colors.red,
              onPressed: () {
                removeSong(context, songs[index]);
              },
            ),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
              context, MaterialPageRoute(builder: (_) => const AddSongForm()))
              .then((newSong) {
            if (newSong != null) {
              setState(() {
                songs.add(newSong);
              });
            }
          });
        },
        tooltip: "add song",
        child: const Icon(Icons.add),
      ),
    );
  }

  removeSong(BuildContext context, Song song) {
    showDialog(
        context: context,
        builder: (_) => AlertDialog(
          title: const Text("Remove song"),
          content: Text("Are you sure you want to delete: ${song.title}?"),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: const Text(
                "cancel",
                style: TextStyle(color: Colors.blue),
              ),
            ),
            TextButton(
              onPressed: () {
                setState(() {
                  songs.remove(song);
                  Navigator.pop(context);
                });
              },
              child: const Text(
                "remove",
                style: TextStyle(color: Colors.red),
              ),
            ),
          ],
        ));
  }

}