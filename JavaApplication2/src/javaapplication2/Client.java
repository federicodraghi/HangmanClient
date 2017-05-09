package javaapplication2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author feded
 */
public class Client extends Thread {

    private Socket clientSocket;
    private String hostName;
    private int port;
    private PrintWriter out;
    private BufferedReader in;
    private String str;
    private HangmanForm hangmanform;

    public void setHangmanform(HangmanForm hangmanform) {
        this.hangmanform = hangmanform;
    }

    Client(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public PrintWriter getOut() {
        return out;
    }

    @Override
    public void run() {
        boolean cond = true;
        while (cond) {
            try {
                
                String str1 = in.readLine();
                String parts[] = str1.split(";");

                switch (parts[0]) {
                    case "FAILED":
                        printBanner("Hai perso!  La parola da indovinare era '"
                                + parts[1] + "'");
                        cond=false;
                        break;
                    case "SOLVED":
                        printBanner("Hai indovinato!   (" + parts[1] + ")");
                        cond=false;
                        break;
                    case "OPEN":
                        String rem = parts[1];
                        this.hangmanform.outTextArea.append("\n" + rem + " tentativi rimasti\n");
                        this.hangmanform.outTextArea.append(gameRepresentation(Integer.parseInt(parts[2])));
                        this.hangmanform.outTextArea.append(parts[3]);
                        break;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private String gameRepresentation(int a) {

        String s = "   ___________\n  /       |   \n  |       ";
        s += (a == 0 ? "\n" : "O\n");
        s += "  |     " + (a == 0 ? "\n" : (a < 5
                ? "  +\n"
                : (a == 5 ? "--+\n" : "--+--\n")));
        s += "  |       " + (a < 2 ? "\n" : "|\n");
        s += "  |      " + (a < 3 ? "\n" : (a == 3 ? "/\n" : "/ \\\n"));
        s += "  |\n================\n";
        return s;
    }

    private void printBanner(String message) {
        this.hangmanform.outTextArea.append("");
        for (int i = 0; i < 80; i++) {
            this.hangmanform.outTextArea.append("*");
        }
        this.hangmanform.outTextArea.append("\n***  " + message);
        for (int i = 0; i < 80; i++) {
            this.hangmanform.outTextArea.append("*");
        }
        this.hangmanform.outTextArea.append("\n");
    }

    /**
     * Ask the user to guess a letter.
     *
     * @param game
     * @return
     */
    public void chooseLetter() {
        Scanner scan = new Scanner(System.in);
        for (;;) {
            System.out.print("Inserisci una lettera: ");
            String line = scan.nextLine().trim();
            if (line.length() == 1 && Character.isLetter(line.charAt(0))) {
                out.println(line.charAt(0));
                break;
            } else {
                System.out.println("Lettera non valida.");
            }
        }
    }
    
    public void chooseLetter(char l) {
        out.println(l);
    }
        
}
