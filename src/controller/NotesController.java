package controller;

import domain.Notes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NotesController {

    private static Scanner scan = new Scanner(System.in);
    private static final String FILE_NAME = "annotations.txt";

    public static void main(String[] args) {
        int typeOf = 0;
        System.out.println("Você deseja inserir ou ver as anotações criadas? \n 1 - para criar anotações \n 2 - Para ler anotações ");
        typeOf = scan.nextInt();

        switch (typeOf) {
            case 1:
                createAnnotation();
                break;
            case 2:
                readAnnotations();
                break;
        }
    }

    public static void createAnnotation() {
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

        saveAnnotationsToFile(note.getNoteAndTheme());
    }

    public static void readAnnotations() {
        Notes note = new Notes();
        HashMap<String, String> getNote = loadAnnotationsFromFile();
        System.out.println(getNote);
    }

    private static void saveAnnotationsToFile(Map<String, String> notes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : notes.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            System.out.println("Anotações salvas com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar as anotações: " + e.getMessage());
        }
    }

    private static HashMap<String, String> loadAnnotationsFromFile() {
        HashMap<String, String> notes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String annotation = parts[0].trim();
                    String theme = parts[1].trim();
                    notes.put(annotation, theme);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de anotações não encontrado. Criando um novo arquivo.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao carregar as anotações: " + e.getMessage());
        }

        return notes;
    }
}