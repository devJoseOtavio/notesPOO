package domain;

import java.util.Date;
import java.util.HashMap;

public class Notes {

    private HashMap<String, String> noteAndTheme = new HashMap<String, String>();

    public void createNoteAndTheme(String note, String theme) {
        noteAndTheme.put(note, theme);
    }

    public HashMap<String, String> getNoteAndTheme() {
        return noteAndTheme;
    }
}
