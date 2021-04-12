package Table;

public class Users {
    private int ID;
    private String PSEUDO;
    private Integer SCORE;


    public Users(){
        this.SCORE= 0;
        this.ID = 0;
        this.PSEUDO = "";
    }

    public Users(String pseudo){
        this.PSEUDO = pseudo;
        this.SCORE = 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPSEUDO() {
        return PSEUDO;
    }

    public void setPSEUDO(String PSEUDO) {
        this.PSEUDO = PSEUDO;
    }

    public Integer getSCORE() {
        return SCORE;
    }

    public void setSCORE(Integer SCORE) {
        this.SCORE = SCORE;
    }
}
