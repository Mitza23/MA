import 'package:flutter/material.dart';
import 'package:flutter_ui/song.dart';

class AddSongForm extends StatefulWidget {
  const AddSongForm({super.key});

  @override
  State<StatefulWidget> createState() => _AddSongFormState();

}

class _AddSongFormState extends State<AddSongForm> {
  final _formKey = GlobalKey<FormState>();
  // late TextEditingController controllerTitle;
  // late TextEditingController controllerArtist;
  // late TextEditingController controllerGenre;
  // late TextEditingController controllerYear;
  // late TextEditingController controllerLink;
  final controllerTitle = TextEditingController();
  final controllerArtist = TextEditingController();
  final controllerGenre = TextEditingController();
  final controllerYear = TextEditingController();
  final controllerLink = TextEditingController();


  // @override
  // void initState() {
  //   controllerTitle = TextEditingController();
  //   controllerArtist = TextEditingController();
  //   controllerGenre = TextEditingController();
  //   controllerYear = TextEditingController();
  //   controllerLink = TextEditingController();
  // }

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
        title: const Text("Add song"),
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
                    Song newSong = Song(title, artist, genre, year, link);
                    Navigator.pop(context, newSong);
                  }
                },
                child: const Text("Add")),
          ],
        ),
      ),
    );
  }
}
