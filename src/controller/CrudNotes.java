package controller;

import conn.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrudNotes {

    private static Connection conn;

    private static Scanner scan = new Scanner(System.in);
//hashmap como banco de dados em memoria
    public static void main(String[] args) throws SQLException {
        System.out.println(
                "Digite o numero de operação que deseja realizar: " +
                "\n 1 - Caso deseje inserir uma anotação " +
                "\n 2 - Caso deseje ver suas anotações " +
                "\n 3 - Caso deseje alterar uma anotação " +
                "\n 4 - Caso deseje apagar alguma anotação"
        );

        int operationValue = scan.nextInt();

        switch (operationValue) {
            case 1:
                insertNotes();
                break;
            case 2:
                readNotes();
                break;
            case 3:
                updateNotes();
                break;
            case 4:
                deleteNotes();
                break;
            default:
                System.out.println("Valor inválido");
        }

        System.out.println("Processo realizado.");
    }

    public static void insertNotes() throws SQLException {
        System.out.println("Digite o texto que deseja adicionar em suas notas: ");
        String text = scan.next();
        scan.nextLine();

        System.out.println("Digite o tema do texto adicionado: ");
        String theme = scan.next();

        String query = "INSERT INTO notes.annotations values(null, '"+text+"', '"+theme+"')";
        doQuery(query);
    }

    private static void readNotes() {
        try {
            String query = "";
            System.out.println("Você deseja buscar sua nota por id, tema ou buscar todos? \n 1 - Para buscar por ID \n 2 - Para buscar por tema \n 3 - Para buscar todas as anotações");
            int searchValue = scan.nextInt();

            if (searchValue == 1) {
                System.out.println("Digite o ID a ser pesquisado: ");
                int id = scan.nextInt();
                query = "SELECT * FROM notes.annotations WHERE id = '" + id + "'";
            } else if (searchValue == 2) {
                System.out.println("Digite o tema a ser pesquisado: ");
                String theme = scan.next();
                query = "SELECT * FROM notes.annotations where theme = '"+theme+"'";
            } else if (searchValue == 3) {
                query = "SELECT * FROM notes.annotations";
            }

            readAnnotations(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateNotes() throws SQLException {
        String query = "";

        System.out.println("Digite o Id que deseja alterar: ");
        int id = scan.nextInt();

        System.out.println("Deseja alterar o texto ou o tema? \n 1 - Digite para alterar o texo \n 2 - Digite para alterar o tema");
        int searchValue = scan.nextInt();

        if (searchValue == 1) {
            System.out.println("Digite o novo texto: ");
            String text = scan.next();
            query = "UPDATE notes.annotations SET text = '"+text+"' where id = '"+id+"'";
        } else {
            System.out.println("Digite o novo tema: ");
            String theme = scan.next();
            query = "UPDATE notes.annotations set theme = '"+theme+"' where id = '"+id+"'";
        }
        doQuery(query);
    }

    private static void deleteNotes() throws SQLException {
        System.out.println("Digite o ID que deseja deletar: ");
        int id = scan.nextInt();
        String query = "DELETE FROM notes.annotations WHERE ID = '"+id+"'";
        doQuery(query);
    }

    private static void doQuery(String query) throws SQLException {
        conn = ConnectionFactory.getConnection();
        Statement stm = conn.createStatement();
        stm.executeUpdate(query);
        stm.close();
    }

    private static void readAnnotations(String query) throws SQLException {
        conn = ConnectionFactory.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(query);

        while (rst.next()) {
            int id = rst.getInt("id");
            String annotation = rst.getString("text");
            String theme = rst.getString("theme");
            System.out.println(id + " " + annotation+ " " + theme);
        }

        conn.close();
    }
}
