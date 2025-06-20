package client;

import java.io.*;
import java.net.*;
import java.util.*;


public class ClientApp {
    private static final String Server_Adress = "localhost";
    private static final int Server_Port = 1234;

    public static void main(String[] args) {
        try(
            Socket socket = new Socket(Server_Adress, Server_Port);
            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)) {
                System.out.println("Conectat la server FastFoodTCP.");

                System.out.println("Alege optiunea: ");
                System.out.println("1.Introdu' comenzi manual.");
                System.out.println("2.Trimite comenzi din fisier (comenzi.txt).");
                System.out.println("Optiune: ");
                String opt = scanner.nextLine();

                if(opt.equals("1")) {
                    while(true) {
                        System.out.print("Comanda (GET <Produs> / UPDATE <Produs> <Cantitate>, exit pentru iesire): ");
                        String linie = scanner.nextLine();
                        if(linie.equalsIgnoreCase("exit")) break;
                        out.println(linie);
                        String raspuns = in.readLine();
                        System.out.println("Server: " + raspuns);

                    }
                } else if(opt.equals("2")) {
                    try(BufferedReader fileReader = new BufferedReader(new FileReader("src/client/comenzi.txt"))) {
                        String linie;
                        while((linie = fileReader.readLine()) != null) {
                            linie = linie.trim();
                            if(linie.isEmpty()) continue;
                            System.out.println("Trimit: " + linie);
                            out.println(linie);
                            String rasp = in.readLine();
                            System.out.println("Server: " + rasp);
                            Thread.sleep(500);
                        }
                    }catch(IOException  | InterruptedException e) {
                        System.out.println("Eroare la citirea fisierului comenzi.txt !!");
                    }
                }else {
                    System.out.println("Optiune invalid.");
                }
        }catch(IOException e) {
                System.out.println("Eroare de conectare la server: " + e.getMessage());

        }
    }
}
