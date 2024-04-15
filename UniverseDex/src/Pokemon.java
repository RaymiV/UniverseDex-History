import java.util.ArrayList;

public class Pokemon {
	public int NID;
	public String name;
	public String species;
	public String typeOne, typeTwo;
	public String abilities;
	public String weight;
	public String height;
	public String image;
	public String voiceEntry;
	public String health, attack, defense, spAttack, spDefense, speed;
	public String imageName;
	public int altForm;
	public boolean hasAltForm;
	public ArrayList<AlternateForms> pokeAlts;
	
	public Pokemon(String [] a) {
		
		this.NID = Integer.parseInt(a[0], 10);
		this.name = a[1];
		this.species = a[2];
		this.typeOne = a[3];
		this.typeTwo = a[4];
		this.abilities = a[5];
		this.weight = a[6];
		this.height = a[7];
		this.health = a[8];
		this.attack = a[9];
		this.defense = a[10];
		this.spAttack = a[11];
		this.spDefense = a[12];
		this.speed = a[13];
		this.altForm = Integer.parseInt(a[14], 10);
		this.hasAltForm = isTrue(altForm);
		this.imageName = a[15];
		this.image = "https://img.pokemondb.net/artwork/" + imageName + ".jpg";
		
		System.out.println("Data successfully assigned");
				
	}
	
	public static boolean isTrue(int a) {
		
		if(a == 1) {
			return true;
		}else {
			return false;
		}
		
		
	}
	

}
