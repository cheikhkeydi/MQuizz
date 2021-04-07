package classe;

public class User {

    public String pseudo;
    protected int id = 0;
    public int score = 0;

    public User(String pseudo)
    {
        this.pseudo =pseudo;
        this.id = id + 1;
        this.score = score;
    }

    public User( )
    {
        this.pseudo =null;
        this.id = 0;
    }

    public int getScore()

    {
        return score;
    }


    public void setScore(int score)
    {
        this.score = score;
    }

    public String getPseudo() {
        return pseudo;
    }


    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
