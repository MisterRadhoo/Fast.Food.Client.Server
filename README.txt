Tema Aplicatiei
Gestionare comenzi Fast-Food - Arhitectura client-server TCP in Java.

Student:Craciun Radu-Mihai
Grupa: 1113

1.Descriere Aplicatie

Aplicatia Java implementeaza o arhitectura client-server prin sockets TCP, in care
clienti pot consulta si actualiza un meniu Fast-Food pastrat de server intr-un
fisier de tip text numit (meniu.txt).
Clienti pot transmite comenzi de tip GET sau UPDATE din console sau din fisierul
(comenzi.txt). Serverul gestioneaza fiecare client in thread separat, asigurand
conexiuni multiple simultane.

2.Functionalitati implementate;

Proiect: ServerApp.java, Produs.java, ClientApp.java;
Comunicare TCP intre client server: Socket + ServerSocket;
Cerere de informare/Request Server: GET <Produs> -> returneaza informatii din meniu.txt;
Cerere de update: UPDATE <Produs> <Cantitate> -> modifica si salveaza cantitate;
Comenzi din consola si fisier.txt: Scanner + BufferedReader;
Persistenta datelor (meniu.txt);
Conexiuni multiple - > fiecare client ruleaza intr-un Thread;


3.Structura Proiectului.

FastFoodTCP/
├── src/
│   ├── client/
│   │   ├── ClientApp.java
│   │   └── comenzi.txt
│   ├── server/
│   │   ├── ServerApp.java
│   │   ├── Produs.java
│   │   └── meniu.txt
├── README.txt
├── tema.txt

4.Datele/Continut fisier/meniu.txt

Burger;30.55;15
Cartofi;15.55;7
Cola;12.3;25
Ketchup;9.49;3
Crispy;29.99;6
Inghetata;12.99;2
Sprite;10.59;1
Apa;9.99;8

5.Date/Continut fisier/comenzi.txt;

GET Burger
UPDATE Burger 15
GET Cartofi
UPDATE Cola 25
GET Fanta
GET Ketchup
UPDATE Ketchup 3
GET Crispy
UPDATE Crispy 6
GET Apa
UPDATE Apa 8
GET Shaorma
UPDATE Shaorma 2
GET Sprite
UPDATE Sprite 1
GET Inghetata
UPDATE Inghetata 2

6.Exemple interactiuni client-server;

Client:
GET Burger
Server:
Info: Burger / 30.55 lei / 15 buc /

Client:
UPDATE Cola 25
Server:
Succes: cantitatea a fost actualizata

Client:
GET Shaorma
Server:
Eroare: produsul nu exista.

7.Cerinte

Java JDK 8+
IDE recomandat: IntelliJ IDEA

8.Pasii pentru rularea proiectului cu IntelliJ IDEA

Pas.1
Deschidere proiect: File > Open > Selecteaza folderul FastFoodTCP
Pas.2
Ruleaza Serverul: Deschide ServerApp.java, click dreapta main() -> Run 'ServerApp.java';
Pas.3
Ruleaza Clientul: Deschide ClientApp.java, click dreapta main() -> Run 'ClientApp.java';
Alege:
1.Pentru introducerea comenzilor manual.
2.Pentru a citii comenzile din fisier comenzi.txt.

9.Atentie:
Fisierul meniu.txt trebuie sa fie localizat in src/server/
Fisierul comenzi.txt trebuie sa fie localizat in src/client/
Server-ul trebuie rulat inaintea clientului.


