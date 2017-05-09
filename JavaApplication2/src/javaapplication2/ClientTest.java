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
 *
 * @author feded
 */
public class ClientTest {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) throws IOException {
       
        int port = 8888;
        String hostName = "10.87.186.234";
        try (Socket clientSocket = new Socket(hostName, port); PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            Client c = new Client(in, out,);
            c.start();
            c.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
