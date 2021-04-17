package Table;

public class Filiere {
    private int ID;
    private String NOM;

    public Filiere()
    {
        this.NOM="";
        this.ID = 0;
    }

    public Filiere(String nom)
    {
        this.NOM = nom;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }
}
