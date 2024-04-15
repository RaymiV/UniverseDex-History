import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class UniverseDex extends Application {
	public final static String FILEPATH = "./src/data/pokemon.csv"; // TODO: Replace parameter later with actual finished filePath
	public final static String ALTSPATH = "./src/data/altForms_1.csv";
	public final static String MEGAALTSPATH = "./src/data/megas.csv";
	public final static String ALOLAALTSPATH = "./src/data/altForms_1.csv";
	public static final String TITLE = "UniverseDex";
	public static int totalTemps = countTemps(FILEPATH);
	public static final int NUMS_COLS = 2;
	public static final int PER_ROW = 5;
	public static final Image icon = new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAMAAABF0y+mAAAAn1BMVEVHcEwPDw4gFhIIDAwfFhIYEQsXEg8BEhEUAgAMDQwKCAkjGBUgGBXxXDrUOzf////RxN8fFBDaPjj3XjoDAAAuHBrazOnxUCXhTjjuVzXCTDBxa3ZOIBmrMi749POFgplDOz3PODaJLCeXNykTFhO9tdSqqKmopML83dhZUlmXk6HybVDl5OS8NTIzKy68ubmFgYR5JyT2oZH0jHikQCt8IvoaAAAADXRSTlMAyyCOTWw6/P3rrI4fPUSOCAAAAYpJREFUKJFlk+tyqyAYRTFJA06PILeo4C2aajTm1un7P9sBMSFp1z9Zs5UPNwA82K4DaAjWW/CbDygblWZZqloJP97UKswV59EM50qGK+82sF2M02kDN96p5HRKMq9Z8bArqPiRkPLkLTP23yzDNjGOkGPylClmTTjvM+cZmXmVmEm7Z6iiaHallwxjXEAzu+SRe+23/6ZxJroFazvF6UjKbx/M9lbe1yBQ9ilJ3iaxSVwEAKbRb+YgxucQQPZHYsceArhPlyNdDjfD+6cMC2ayXOUo/+FuRAdrQxDAvGDpjxi6m8iNcjHGzjkMwCelUN7FjVzLSTTMrhvT5JDST7CliNKDIPV1mEZ0LtrmnlN4oAZTCaprgeTUkfIWC3gwWIHQRdqDr3c7Lcq67LqrcOtCDFVfz2UJb4QIPemOUCov9aDHPt596dD9bHElsai7UdB4Z4mNq9BSo43oyDQgqeMHxvkSCR27iFO99s5W81J9PVV1ea2m3TOSQzX2/Vhpid5LvVwHZHi9Dv8BdAEwG5YuVesAAAAASUVORK5CYII=");
	public static final Image icon2 = new Image("https://archives.bulbagarden.net/media/upload/9/95/Dream_Master_Ball_Sprite.png");
	
	/**
	 * int lineCount is defined to be the total number of lines on a text file.
	 * Method will count how many lines there are in the provided text file and stored that value as lineCount.
	 * @param filePath
	 * @return lineCount
	 */
	public static int countTemps(String filePath) {
        int lineCount = 0;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.readLine() != null) {
                lineCount++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("File could not be loaded.");
        }
        return lineCount;
    }
	
	
	/**
	 * Returns the color (hex code)/symbol for the Pokemon type (as a string) provided.
	 * The Dictionary named "typing" will be the library holding the data (keys and values).
	 * @param type
	 * @return typing.get(type)
	 */
	public static String typeMatch(String type) {
		Dictionary<String, String> typing = new Hashtable<>();
		
		
		if (type == null || type.isEmpty()) {
			return ("#AAB09F");
		}
		
		type = type.toLowerCase();
		typing.put("normal", "#AAB09F");
		typing.put("fire", "#EE8130");
		typing.put("water", "#6390F0");
		typing.put("electric", "#F7D02C");
		typing.put("grass", "#7AC74C");
		typing.put("ice", "#96D9D6");
		typing.put("fighting", "#C22E28");
		typing.put("poison", "#A33EA1");
		typing.put("ground", "#E2BF65");
		typing.put("flying", "#A98FF3");
		typing.put("psychic", "#F95587");
		typing.put("bug", "#A6B91A");
		typing.put("rock", "#B6A136");
		typing.put("ghost", "#735797");
		typing.put("dragon", "#6F35FC");
		typing.put("dark", "#58535A"); // 736c75
		typing.put("steel", "#B7B7CE");
		typing.put("fairy", "#D685AD");
		typing.put("", "#ffffff");
		
		
		return typing.get(type);
	}
	
	
	/**
	 * Returns the array named "myArray" full of elements found on a string
	 * The code will check if the line is valid
	 * If the line is valid then:
	 * -Spaces will be removed
	 * -myArray will contain the contents of the string splitted by ","
	 * @param line
	 * @return myArray
	 * @throws IOException
	 */
	public static String[] readnSplit(String line) throws IOException {
		
        String[] myArray;
        char check = '"';
        String checker = "" + check;
        String b = "" + line.charAt(0);
        if(b.contentEquals(checker)) {
        	line = line.replaceAll(b, "");
        }
        
        myArray = line.split(",");
            
        return myArray;
    }
	
	
	public static String capitalizer(String a) {
		
		if (a == null || a.isEmpty()) {
			return "";
		}
		char first = a.charAt(0);
		char upper = Character.toUpperCase(first);
		StringBuilder result = new StringBuilder(a);
		
		result.setCharAt(0, upper);
		
		return result.toString();
	}
	
	public static Text createStat(String a) {
		int b = Integer.parseInt(a, 10);
		
		Text text = new Text(a);
		
		if(b < 20) {
			text.setFill(Color.RED);
		}else if(b < 50) {
			text.setFill(Color.ORANGE);
		}else if(b < 90) {
			text.setFill(Color.YELLOW);
		}else if(b < 120) {
			text.setFill(Color.GREENYELLOW);
		}else if(b < 150) {
			text.setFill(Color.GREEN);
		}else {
			text.setFill(Color.DARKCYAN);
		}
		
		text.setStyle("-fx-font: bold 20pt \"Arial\";");
		
		return text;
		
	}
	
	public static Button createButton(Pokemon p) {
		int a = p.NID;
		String myString = "" + a + " - " + p.name;
		String imagePath = p.image.toLowerCase();
		Image img = new Image(imagePath, 100, 100, true, true);
		ImageView view = new ImageView(img);
//		view.setPreserveRatio(true);
		Button b = new Button();
		b.setText(myString);
		b.setMinWidth(120);
		b.setMinHeight(120);
		b.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 25; -fx-border-color: #f00000; -fx-border-radius: 25;  -fx-font: bold 10pt \"Arial\"; -fx-border-width: 5;");
		b.setGraphic(view);
		b.setContentDisplay(ContentDisplay.BOTTOM);
		b.setOnAction(event ->{
			
			DPKMN(p);
		});
		
		return b;
		
	}
	
	public static void DPKMN(Pokemon p)  {
		
		
		Stage stage = new Stage();
		String imagePath = p.image.toLowerCase();
		
		String title = p.name;
		String specs = p.species;
		
		
		Text name = new Text();
		name.setText(title);
		name.setStyle("-fx-font: bold 20pt \"Arial\";");
		Text species = new Text();
		species.setText(specs);
		species.setStyle("-fx-font: 20pt \"Arial\";");

		Image img = new Image(imagePath, 300, 300, true, true);
		ImageView view = new ImageView(img);
		view.setPreserveRatio(true);
		view.setFitWidth(300);
		view.setFitHeight(300);
		view.setSmooth(true);
		view.setPreserveRatio(true);
		view.setCache(true);
		view.setX(20);
		view.setStyle("-fx-border-color: #f00000; -fx-border-radius: 25; -fx-border-width: 5;");
		
		VBox present = new VBox();
		present.setAlignment(Pos.CENTER);		
		present.getChildren().add(view);
		present.getChildren().add(name);
		present.getChildren().add(species);
		
		// Pokemon data
		VBox data = new VBox();
		String TP1 = capitalizer(p.typeOne);
		String TP2 = capitalizer(p.typeTwo);
		String type1 = typeMatch(TP1);
		String type2 = typeMatch(TP2);
		
		HBox typing = new HBox();
		Text label = new Text("Types(s): ");
		label.setFill(Color.BLACK);
		label.setStyle("-fx-font: 20pt \"Arial\";");
		
		Text andText = new Text(" AND ");
		andText.setFill(Color.BLACK);
		andText.setStyle("-fx-font: 20pt \"Arial\";");
		
		Text typeOne = new Text(TP1);
		typeOne.setFill(Color.web(type1));
		typeOne.setStyle("-fx-font: 20pt \"Arial\";");
		
		Text typeTwo = new Text(TP2);
		typeTwo.setFill(Color.web(type2));
		typeTwo.setStyle("-fx-font: 20pt \"Arial\";");
		
		typing.getChildren().add(label);
		typing.getChildren().add(typeOne);
		
		if(p.typeTwo != "") {
			typing.getChildren().add(andText);
			typing.getChildren().add(typeTwo);
		}
		
		String abilities = "Abilities: " + p.abilities;
		Text abls = new Text(abilities);
		abls.setFill(Color.BLACK);
		abls.setStyle("-fx-font: 20pt \"Arial\";");
		
		String weight = "Weight(kgs): " + p.weight;
		Text weighten = new Text(weight);
		weighten.setFill(Color.BLACK);
		weighten.setStyle("-fx-font: 20pt \"Arial\";");
		
		String height = "Height(meters): " + p.height;
		Text heighten = new Text(height);
		heighten.setFill(Color.BLACK);
		heighten.setStyle("-fx-font: 20pt \"Arial\";");
		
		String statistics = "Statistics: ";
		Text stats = new Text(statistics);
		stats.setFill(Color.BLACK);
		stats.setStyle("-fx-font: 20pt \"Arial\";");
		
		HBox healthStat = new HBox();
		Text heal = new Text("-Health: ");
		heal.setStyle("-fx-font: 20pt \"Arial\";");
		String health = p.health;
		Text healthS = createStat(health);
		healthStat.getChildren().add(heal);
		healthStat.getChildren().add(healthS);
		
		HBox attackStat = new HBox();
		Text atk = new Text("-Attack: ");
		atk.setStyle("-fx-font: 20pt \"Arial\";");
		String attack = p.attack;
		Text attackS = createStat(attack);
		attackStat.getChildren().add(atk);
		attackStat.getChildren().add(attackS);
		
		HBox defenseStat = new HBox();
		Text def = new Text("-Defense: ");
		def.setStyle("-fx-font: 20pt \"Arial\";");
		String defense = p.defense;
		Text defenseS = createStat(defense);
		defenseStat.getChildren().add(def);
		defenseStat.getChildren().add(defenseS);
		
		HBox spAttackStat = new HBox();
		Text sAtk = new Text("-Sp. Attack: ");
		sAtk.setStyle("-fx-font: 20pt \"Arial\";");
		String spAttack = p.spAttack;
		Text spAttackS = createStat(spAttack);
		spAttackStat.getChildren().add(sAtk);
		spAttackStat.getChildren().add(spAttackS);
		
		HBox spDefenseStat = new HBox();
		Text sDef = new Text("-Sp. Defense: ");
		sDef.setStyle("-fx-font: 20pt \"Arial\";");
		String spDefense = p.spDefense;
		Text spDefenseS = createStat(spDefense);
		spDefenseStat.getChildren().add(sDef);
		spDefenseStat.getChildren().add(spDefenseS);
		
		HBox speedStat = new HBox();
		Text sp = new Text("-Speed: ");
		sp.setStyle("-fx-font: 20pt \"Arial\";");
		String speed = p.speed;
		Text speedS = createStat(speed);
		speedStat.getChildren().add(sp);
		speedStat.getChildren().add(speedS);
		
		int healthPoints = Integer.parseInt(p.health, 10);
		int attackPoints = Integer.parseInt(p.attack, 10);
		int defensePoints = Integer.parseInt(p.defense, 10);
		int spAttackPoints = Integer.parseInt(p.spAttack, 10);
		int spDefensePoints = Integer.parseInt(p.spDefense, 10);
		int speedPoints = Integer.parseInt(p.speed, 10);
		int sum = healthPoints + attackPoints + defensePoints + spAttackPoints + spDefensePoints + speedPoints;
		
		String totalStat = "-Total: " + sum;
		Text total = new Text(totalStat);
		total.setStyle("-fx-font: bold 20pt \"Arial\";");
		
		data.getChildren().add(typing);
		data.getChildren().add(abls);
		data.getChildren().add(weighten);
		data.getChildren().add(heighten);
		data.getChildren().add(stats);
		data.getChildren().add(healthStat);
		data.getChildren().add(attackStat);
		data.getChildren().add(defenseStat);
		data.getChildren().add(spAttackStat);
		data.getChildren().add(spDefenseStat);
		data.getChildren().add(speedStat);
		data.getChildren().add(total);
		
		
		
		
		
		GridPane stack = new GridPane();
		stack.setTranslateY(-50);
		stack.setHgap(10);
		stack.setVgap(10);
		
		for (int i = 0; i < NUMS_COLS; i++) {
			
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(100.0 / NUMS_COLS);
			stack.getColumnConstraints().add(column);
			
		
		}
		
		stack.setConstraints(present, 0, 0);
		stack.getChildren().add(present);
		stack.setConstraints(data, 1, 0);
		stack.getChildren().add(data);
		
		VBox everything = new VBox();
		everything.setAlignment(Pos.CENTER);
		
		everything.getChildren().add(stack);
		Button exit = new Button("Close");
		exit.setTranslateY(50);
		exit.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 25; -fx-border-color: #808080; -fx-border-radius: 25;  -fx-font: bold 20pt \"Arial\"; -fx-border-width: 1;");
		exit.setOnAction(event ->{
			stage.close();
		});
		
		everything.getChildren().add(exit);
		
		Scene scene = new Scene(everything, 1200, 600);
		stage.setTitle(title);
		stage.setMaxWidth(1200);
		stage.setMaxHeight(600);
		stage.setMinWidth(1200);
		stage.setMinHeight(600);
		stage.getIcons().add(icon2);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Map<Integer, Pokemon> pokemonMap = new HashMap<>();
		HashSet <AlternateForms> altForms = new HashSet();
		
		//Hashset Construction
		File aForms = new File(ALTSPATH);
		File megaForms = new File(MEGAALTSPATH);
		File alolaForms = new File(ALOLAALTSPATH);
		Scanner altScan = new Scanner(aForms);
		Scanner megaAlts = new Scanner(megaForms);
		Scanner alolanAlts = new Scanner(alolaForms);
		
		String altFormsLine;
		
		
		while(altScan.hasNext()) {
			altFormsLine = altScan.nextLine();
			String [] altSt = readnSplit(altFormsLine);
			AlternateForms AlternateForms = new AlternateForms(altSt);
			altForms.add(AlternateForms);
			
		}
		
		
		while(megaAlts.hasNextLine()) {
			altFormsLine = megaAlts.nextLine();
			String[] altSt  = readnSplit(altFormsLine);
			AlternateForms AlternateForms = new AlternateForms(altSt);
			altForms.add(AlternateForms);
		}
		
		while(alolanAlts.hasNextLine()) {
			altFormsLine = alolanAlts.nextLine();
			String[] altSt = readnSplit(altFormsLine);
			AlternateForms AlternateForms = new AlternateForms(altSt);
			altForms.add(AlternateForms);
		}
		
		
		int current = 0;
		int place = 1;
		int rowIndex = 0;
		int columIndex = 0;
		int looking = 1;
		String currentLine;
		File f = new File(FILEPATH);
		
		try {
			Scanner s = new Scanner(f);
			// Initialize gridpane and it's dimensions
			
			FlowPane display = new FlowPane();
//			GridPane display = new GridPane();
			display.setPadding(new Insets(10));
			display.setHgap(10);
			display.setVgap(10);
			
			
			// Set colum constraint
//			for (int i = 0; i < NUMS_COLS; i++) {
//	            ColumnConstraints column = new ColumnConstraints();
//	            column.setPercentWidth(100.0 / NUMS_COLS);
//	            display.getColumnConstraints().add(column);
//	        }
			// was there an ;
			String [] store;
			while(s.hasNextLine()) {
				
				currentLine = s.nextLine();
				store = readnSplit(currentLine);
				Pokemon Pokemon = new Pokemon(store);
				pokemonMap.put(Pokemon.NID, Pokemon);
				
				
				place++;
			}
			
//			for(AlternateForms p: altForms) {
//				if(p.NID == pokemonMap.get(p.NID).NID) {
//					AlternateForms Alt = p;
//					pokemonMap.get(p.NID).pokeAlts.add(p);
//				}
//			}
			
			while(looking < pokemonMap.size()) {
				
				int blooking = looking;
				String bName = pokemonMap.get(looking).name;
				String bColor = typeMatch(pokemonMap.get(looking).typeOne);
				String bImage = pokemonMap.get(looking).image;
				
				Button b = createButton(pokemonMap.get(blooking));
				display.getChildren().add(b);
//				display.add(b, columIndex, rowIndex);
//				columIndex++;
				
//				if(columIndex == NUMS_COLS) {
//					columIndex = 0;
//					rowIndex++;
//				}
//				
//				if(looking % PER_ROW == PER_ROW - 1) {
//					display.getRowConstraints().add(new RowConstraints()); // adds a new row
//				}
				
				looking++;
			}
			
			ScrollPane scrollPane = new ScrollPane(display);
			scrollPane.setFitToWidth(true);
			scrollPane.setFitToHeight(true);
			Scene primary = new Scene(scrollPane, 750, 750);
			
			
//			Image icon = new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAMAAABF0y+mAAAAn1BMVEVHcEwPDw4gFhIIDAwfFhIYEQsXEg8BEhEUAgAMDQwKCAkjGBUgGBXxXDrUOzf////RxN8fFBDaPjj3XjoDAAAuHBrazOnxUCXhTjjuVzXCTDBxa3ZOIBmrMi749POFgplDOz3PODaJLCeXNykTFhO9tdSqqKmopML83dhZUlmXk6HybVDl5OS8NTIzKy68ubmFgYR5JyT2oZH0jHikQCt8IvoaAAAADXRSTlMAyyCOTWw6/P3rrI4fPUSOCAAAAYpJREFUKJFlk+tyqyAYRTFJA06PILeo4C2aajTm1un7P9sBMSFp1z9Zs5UPNwA82K4DaAjWW/CbDygblWZZqloJP97UKswV59EM50qGK+82sF2M02kDN96p5HRKMq9Z8bArqPiRkPLkLTP23yzDNjGOkGPylClmTTjvM+cZmXmVmEm7Z6iiaHallwxjXEAzu+SRe+23/6ZxJroFazvF6UjKbx/M9lbe1yBQ9ilJ3iaxSVwEAKbRb+YgxucQQPZHYsceArhPlyNdDjfD+6cMC2ayXOUo/+FuRAdrQxDAvGDpjxi6m8iNcjHGzjkMwCelUN7FjVzLSTTMrhvT5JDST7CliNKDIPV1mEZ0LtrmnlN4oAZTCaprgeTUkfIWC3gwWIHQRdqDr3c7Lcq67LqrcOtCDFVfz2UJb4QIPemOUCov9aDHPt596dD9bHElsai7UdB4Z4mNq9BSo43oyDQgqeMHxvkSCR27iFO99s5W81J9PVV1ea2m3TOSQzX2/Vhpid5LvVwHZHi9Dv8BdAEwG5YuVesAAAAASUVORK5CYII=");
			primaryStage.setScene(primary);
			primaryStage.setResizable(true);
			primaryStage.setMinWidth(700);
			primaryStage.setMinHeight(700);
			primaryStage.getIcons().add(icon);
			
			primaryStage.setTitle(TITLE);
			primaryStage.show();
			
			
		} catch (FileNotFoundException e) {
			
			
			System.out.println("File cannot be reached");
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}
	
	

}
