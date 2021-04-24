package Table;

public class Question {

	private int ID;
	private String QUESTION;
	private String OPTA;
	private String OPTB;
	private String OPTC;
	private String NomMatiere;
	//private String OPTD;
	private int ANSWER;


	public Question()
	{
		ID=0;
		QUESTION="";
		OPTA="";
		OPTB="";
		OPTC="";
		NomMatiere="";
		ANSWER=0;
	}


	public Question(String qUESTION, String oPTA, String oPTB, String oPTC,
			int aNSWER,String nomMatiere) {
		
		QUESTION = qUESTION;
		OPTA = oPTA;
		OPTB = oPTB;
		OPTC = oPTC;
		NomMatiere = nomMatiere;
		ANSWER = aNSWER;
	}

	public Question(String qUESTION, String oPTA, String oPTB, String oPTC,
					int aNSWER) {

		QUESTION = qUESTION;
		OPTA = oPTA;
		OPTB = oPTB;
		OPTC = oPTC;
		ANSWER = aNSWER;
	}


	public int getID()
	{
		return ID;
	}
	public String getQUESTION() {
		return QUESTION;
	}
	public String getOPTA() {
		return OPTA;
	}
	public String getOPTB() {
		return OPTB;
	}
	public String getOPTC() {
		return OPTC;
	}
	public int getANSWER() {
		return ANSWER;
	}
	public void setID(int id)
	{
		ID=id;
	}
	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}
	public void setOPTA(String oPTA) {
		OPTA = oPTA;
	}
	public void setOPTB(String oPTB) {
		OPTB = oPTB;
	}
	public void setOPTC(String oPTC) {
		OPTC = oPTC;
	}
	public void setANSWER(int aNSWER) {
		ANSWER = aNSWER;
	}

	public String getNomMatiere() {
		return NomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		NomMatiere = nomMatiere;
	}

	/*public String getOPTD() {
		return OPTD;
	}

	public void setOPTD(String OPTD) {
		this.OPTD = OPTD;
	} */
}
