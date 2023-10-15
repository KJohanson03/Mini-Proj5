// Class for Mini-Project 5 in Computer Science 207 Grinnell College 
// @auther Kevin Johanson
// Worked with Luke Walters 
// 10/14/2023


package structures;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AACMappings {

	AACCategory home;
	
	AACCategory curCat;

	AssociativeArray<String,AACCategory> allCat;
	
// ----------------------- Constructors ------------------------------------------------------
	public AACMappings(String filename) {

		this.home = new AACCategory("home");
		this.curCat = this.home;
		this.allCat = new AssociativeArray<>();

		String[] locAndText = new String[2]; // holder for splitting the string input from file
	    try {
	        File myObj = new File(filename);
	        Scanner reader = new Scanner(myObj);
			while (reader.hasNextLine()) {
	          String data = reader.nextLine();
	          if (!(data.charAt(0) == '>')) {
	        	  locAndText = data.split(" ");  
	        	  this.allCat.set(locAndText[0], new AACCategory(locAndText[1]));

				  this.home.addItem(locAndText[0], locAndText[1]);
				  try {
					this.curCat = this.allCat.get(locAndText[0]);
				  } catch (KeyNotFoundException e) {
					System.err.println("Key not found");
				  } // catch
	        	  
	          } else {
				locAndText = data.split(" ");  
				locAndText[0] = locAndText[0].substring(1,locAndText[0].length());
				this.curCat.addItem(locAndText[0], locAndText[1]);
				
			  } // else 
			 
			} // while()
			 reader.close();
			 this.curCat = this.home;
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} // catch

	} // AACMappings

	
// ----------------------- Methods ------------------------------------------------------
	/*
	 * Given the image location selected, it determines the associated text with the
	 * image. If the image provided is a category it also updates the AAC's current
	 * category to be the category associated with that image
	 * 
	 * imageLoc - the location of where the image is stored
	 */
	public String getText(String imageLoc) {
		if (curCat.getCategory().equals(this.home.getCategory())) { // in the home category
			try {
				this.curCat = this.allCat.get(imageLoc);
				return this.home.getCategory();
			} catch (KeyNotFoundException e) {
				System.err.println("Key not found");
				return ("Image does not exist");
			} // catch
		} else { // not in the home category
			return this.curCat.getText(imageLoc);
		} //  
		
	} // getText ()

	/*
	 * Provides an array of all the images in the current category
	 * 
	 */
	public String[] getImageLocs() {
		return curCat.getImages();

	} // getImageLocs()

	/*
	 * Resets the current category of the AAC back to the default category
	 */
	public void reset() {
		this.curCat = this.home;
	} // reset()


	/*
	 * Determines if the image represents a category or text to speak imageLoc -
	 * location of the image
	 */
	public boolean isCategory(String imageLoc) {
		return false;
	} // isCategory()

	/*
	 * Writes the ACC mappings stored to a file. The file is formatted as the text
	 * location of the category followed by the text name of the category and then
	 * one line per item in the category that starts with > and then has the file
	 * name and text of that image for instance: img/food/plate.png food
	 * >img/food/icons8-french-fries-96.png french fries
	 * >img/food/icons8-watermelon-96.png watermelon img/clothing/hanger.png
	 * clothing >img/clothing/collaredshirt.png collared shirt represents the file
	 * with two categories, food and clothing and food has french fries and
	 * watermelon and clothing has a collared shirt
	 * 
	 * filename - the name of the file to write the AAC mapping to
	 */
	public void writeToFile(String filename) {
		File file = new File(filename);
		String[] keys;
		String[] values;
		try {
			
		 PrintWriter pen = new PrintWriter(file); // creates a pen object to ouput to a file
		 for (int i = 0; i < allCat.size(); i++) { // iterates through the outside categories

			pen.println(allCat.getKeyAt(i) + " " + allCat.getValueAt(i).getCategory());// prints the home category
			

			keys = allCat.getValueAt(i).getImages();
			values = allCat.getValueAt(i).getTexts();

			for (int y = 0; y < keys.length; y++) {
				pen.println(">" + keys[y] + " " + values[y]);
			} // for 
		
		 } // for
		 pen.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found");
		} // catch
		
	} // writeToFile()

	/*
	 * Adds the mapping to the current category (or the default category if that is
	 * the current category)
	 * 
	 * imageLoc - the location of the image text - the text associated with the
	 * image
	 */
	public void add(String imageLoc, String text) {
		if (getCurrentCategory().equals("")) {

	        	  this.allCat.set(imageLoc, new AACCategory(text));

				  this.home.addItem(imageLoc, text);
		} else {
		curCat.addItem(imageLoc, text);
		} // else
	} // add()

	/*
	 * gets the current category
	 */
	public String getCurrentCategory() {
		if (curCat.getCategory().equals(this.home.getCategory())) {
			return ""; //returns empty string if current category is home
		} else {
			return curCat.getCategory();
		} // else 
		
	} // getCurrentCategory()

} // AACMappings
