package Table;

public class Matiere {
    private Integer ID;
    private String NOM;
    private String NomFiliere;
    private String NomNiveau;

    private Filiere filiere;
    private Niveau niveau;

    public Matiere()
    {
        this.ID =0;
        this.NOM = "";
        this.NomFiliere = "";
        this.NomNiveau = "";
    }

    public Matiere(String nom,String nomFiliere,String nomNiveau){
        this.NOM = nom;
        this.NomFiliere = nomFiliere;
        this.NomNiveau = nomNiveau;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getNomFiliere() {
        return NomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        NomFiliere = nomFiliere;
    }

    public String getNomNiveau() {
        return NomNiveau;
    }

    public void setNomNiveau(String nomNiveau) {
        NomNiveau = nomNiveau;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
