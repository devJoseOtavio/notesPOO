package controller;

import domain.Notes;

import java.util.HashMap;
import java.util.Scanner;

public class TestController {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Notes note = new Notes();
        int typeOf = 0;
        System.out.println("Você deseja inserir ou ver as anotações criadas? \n 1 - para criar anotações \n 2 - Para ler anotações ");
        typeOf = scan.nextInt();

        switch(typeOf) {
            case 1:
                createAnnotation(note);
            case 2:
                readAnnotations(note);
        }
    }

    public static void createAnnotation(Notes note) {
        System.out.println("Digite quantas notas e temas você quer inserir: ");
        int noteQtd = scan.nextInt();
        scan.nextLine();

        for (int i = 1; i <= noteQtd; i++) {
            System.out.println("Digite as notas a serem inseridas: ");
            String annotation = scan.nextLine();

            System.out.println("Digite o tema de sua anotação: ");
            String theme = scan.nextLine();
            note.createNoteAndTheme(annotation, theme);
        }
    }

    public static void readAnnotations(Notes note) {
        HashMap<String, String> getNote = note.getNoteAndTheme();
        System.out.println(getNote);
    }
}
