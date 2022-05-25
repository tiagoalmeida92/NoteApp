## :loudspeaker: TODO App
- New UI flows to view notes and create, edit and delete notes
- 2 Activity screens
  - MainActivity(list) and AddEditActivity
- Persistent data for notes via room database

## :bulb: Considerations and implementation

Implemented feature following clean architecture principles and multi-module approach

Modules

- app
- core
  - database-room
- feature
  - add-edit
  - list
- ui

Libraries used:

UI module: ViewModel, StateFlow, Hilt Data module: Room Common: Flow, Coroutines Testing: mockito,
assertj

UI tests included written in BDD style class NoteTest e.g.

```
    @Test
    fun savingNewNote_appearsOnList() {
        val title = "Title"
        val description = "Description"

        `when`.user.onMainScreen()
            .clicksNewNote()

        `when`.user.onAddEditScreen()
            .writeFields(title, description)
            .save()

        then.user.onMainScreen()
            .seesNote(title, description)
            .doesNotSeeImage()
            .doesNotSeeEdited()
    }
```

## :camera_flash: Screenshots

<delete section if not applicable>

| |  |
| ------ | ----- |
| ![Screenshot_20220512_163207](https://user-images.githubusercontent.com/3733055/168309938-61e9eca1-4235-4728-9fb3-6aff4b0a5dd8.png) | ![Screenshot_20220512_163218](https://user-images.githubusercontent.com/3733055/168309906-503e653d-cf6a-4e94-9ec7-4b5018766404.png) |  
