package classe;

public class Filiere {
    public int id =0;
    public String nom;

    public Filiere(String nom){
        this.id = id + 1;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
