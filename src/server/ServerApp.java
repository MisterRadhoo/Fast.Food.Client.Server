package server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServerApp {
    private static final int PORT = 1234;
    private static List<Produs> meniu = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        loadMeniuDinFisier();
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Server pornit pe portul " + PORT + "....");

            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client conectat: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch(IOException e) {
            System.out.println("Eroare Server: " + e.getMessage());
        }
    }
    private static void loadMeniuDinFisier() {
        try(BufferedReader reader = new BufferedReader(new FileReader("src/server/meniu.txt"))) {
            String linie;
            while((linie = reader.readLine()) != null ) {
                String[] parts = linie.split(";");
                if(parts.length == 3) {
                    String nume = parts[0];
                    double pret = Double.parseDouble(parts[1]);
                    int cantitate = Integer.parseInt(parts[2]);
                    meniu.add(new Produs(nume, pret, cantitate));
                }
            }

        }catch(IOException e) {
            System.out.println("Produsele nu s-au incarcat in meniu.txt!");
        }
    }

    private static void saveMeniuInFisier() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/server/meniu.txt"))) {
            for(Produs p : meniu) {
                writer.write(p.getNume() + ";" + p.getPret() + ";" + p.getCantitate());
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("Eroare la salvare meniu.txt !!");
        }
    }

    private static class ClientHandler implements  Runnable{
        private Socket socket;
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
                String cerere;
                while((cerere = in.readLine())!= null) {
                    System.out.println("Cerere primita: " + cerere);
                    String[] parts = cerere.split(" ");
                    if(parts.length >= 2) {
                        String comanda = parts[0].toUpperCase();
                        String produsCautat = parts[1];

                        Optional<Produs> produsOpt = meniu.stream()
                                .filter(p->p.getNume().equalsIgnoreCase(produsCautat))
                                .findFirst();

                        switch (comanda) {
                            case "GET":
                                if(produsOpt.isPresent()) {
                                    out.println("Info: " + produsOpt.get());
                                }else {
                                    out.println("Eroare: produsul nu exista.");
                                }
                                break;

                            case "UPDATE":
                                if(parts.length == 3 && produsOpt.isPresent()) {
                                    try {
                                        int cantNoua = Integer.parseInt(parts[2]);
                                        produsOpt.get().setCantitate(cantNoua);
                                        saveMeniuInFisier();
                                        out.println("Succes: cantitatea a fost actualizata");
                                    } catch(NumberFormatException e) {
                                        out.println("Eroare: cantitate invalida. ");
                                    }
                                }else {
                                    out.println("Eroare: comanda gresita sau produs inexistent!");
                                }
                                break;
                            default:
                                out.println("Comanda necunoscuta.");
                        }

                    }else {
                        out.println("Format cerere invalid.");
                    }
                }
            } catch(IOException e) {
                System.out.println("Client deconectat.");
            }
        }
    }
}
