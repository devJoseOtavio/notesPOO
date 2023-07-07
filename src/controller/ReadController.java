package controller;

import domain.Notes;

import java.util.HashMap;

public class ReadController {

    public static void main(String[] args) {
        Notes note = new Notes();
        HashMap<String, String> getNote = note.getNoteAndTheme();
        System.out.println(getNote);
    }
}
