package Table;

public class Niveau {

    private int ID;
    private String ANNEE;

    public  Niveau(){
        this.ID = 0;
        this.ANNEE ="";
    }

    public Niveau(String annee){
        this.ANNEE = annee;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getANNEE() {
        return ANNEE;
    }

    public void setANNEE(String ANNEE) {
        this.ANNEE = ANNEE;
    }
}
