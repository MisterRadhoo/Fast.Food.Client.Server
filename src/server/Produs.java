package server;

public class Produs {
    private String nume;
    private double pret;
    private int cantitate;

    public Produs(String nume, double pret, int qty) {
        this.nume = nume;
        this.pret = pret;
        this.cantitate = qty;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
         final StringBuilder sb  = new StringBuilder();
         String separator = " / ";
         sb.append(this.nume).append(separator);
         sb.append(this.pret).append(" lei").append(separator);
         sb.append(this.cantitate).append(" buc").append(separator);
         return sb.toString();

    }
}
