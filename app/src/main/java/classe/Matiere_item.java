package classe;

public class Matiere_item {

    public String nom;
    public int id = 0;
    annee nam;

    public Matiere_item(String nom){
        this.nom = nom;
        this.id = id + 1;
    }

    public Matiere_item(annee nam){
        this.id = id + 1;
        this.nam = nam;
    }



    public annee getAnnee(){
        return nam;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
