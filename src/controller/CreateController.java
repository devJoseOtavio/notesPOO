package controller;

import domain.Notes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateController {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Notes note = new Notes();
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
}
