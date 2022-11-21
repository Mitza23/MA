import 'package:flutter/material.dart';
import 'package:flutter_ui/song.dart';

class EditSongForm extends StatefulWidget {
  final Song _song;

  const EditSongForm(this._song, {super.key});

  @override
  State<StatefulWidget> createState() => _EditSongFormState();

}

class _EditSongFormState extends State<EditSongForm> {
  final _formKey = GlobalKey<FormState>();
  late TextEditingController controllerTitle;
  late TextEditingController controllerArtist;
  late TextEditingController controllerGenre;
  late TextEditingController controllerYear;
  late TextEditingController controllerLink;
  // late Song s;
  // final controllerTitle = TextEditingController();
  // final controllerArtist = TextEditingController();
  // final controllerGenre = TextEditingController();
  // final controllerYear = TextEditingController();
  // final controllerLink = TextEditingController();


  @override
  void initState() {
    Song s = widget._song;
    controllerTitle = TextEditingController(text:s.title);
    controllerArtist = TextEditingController(text:s.artist);
    controllerGenre = TextEditingController(text:s.genre);
    controllerYear = TextEditingController(text:s.year.toString());
    controllerLink = TextEditingController(text:s.link);
    super.initState();
  }

  @override
  void dispose() {
    controllerTitle.dispose();
    controllerArtist.dispose();
    controllerGenre.dispose();
    controllerYear.dispose();
    controllerLink.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Edit song"),
      ),
      body: Form(
        key: _formKey,
        child: Column(
          children: <Widget>[
            TextFormField(
              controller: controllerTitle,
              decoration: const InputDecoration(labelText: "title"),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter some text';
                }
                return null;
              },
            ),
            TextFormField(
              controller: controllerArtist,
              decoration: const InputDecoration(labelText: "artist"),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter some text';
                }
                return null;
              },
            ),
            TextFormField(
              controller: controllerGenre,
              decoration: const InputDecoration(labelText: "genre"),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter some text';
                }
                return null;
              },
            ),
            TextFormField(
              controller: controllerYear,
              decoration: const InputDecoration(labelText: "year"),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Please enter some text';
                }
                return null;
              },
            ),
            TextFormField(
              controller: controllerLink,
              decoration: const InputDecoration(labelText: "link"),
            ),
            ElevatedButton(
                onPressed: () {
                  if (_formKey.currentState!.validate()){
                    String title = controllerTitle.text;
                    String artist = controllerArtist.text;
                    String genre = controllerGenre.text;
                    int year = int.parse(controllerYear.text);
                    String link = controllerLink.text;
                    Navigator.pop(context,
                        Song(title, artist, genre, year, link)
                    );
                  }
                },
                child: const Text("Edit")),
          ],
        ),
      ),
    );
  }
}
