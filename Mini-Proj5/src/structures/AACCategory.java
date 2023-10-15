// Class for Mini-Project 5 in Computer Science 207 Grinnell College 
// @auther Kevin Johanson
// Worked with Luke Walters 
// 10/14/2023

package structures;

public class AACCategory {
	
	AssociativeArray<String,String> locationAndText;
	
	String catName;
// ----------------------- Constructors ------------------------------------------------------	
	/*
	 * Creates a new empty category with the given name
	 * name - the name of the category
	 */
	public AACCategory(String name) {
		
		this.catName = name;
		this.locationAndText = new AssociativeArray<String, String>();
	} // AACCategory()
	

// ----------------------- Methods ------------------------------------------------------	
	/*
	 * Adds the mapping of the imageLoc to the text to the category.
	 * imageLoc - the location of the category
	 * text - the text that image maps to
	 */
	public void addItem(String imageLoc, String text) {
		locationAndText.set(imageLoc, text);
		
	} //addItem()
	
	/*
	 * Returns the name of the category
	 */
	public String getCategory() {
		return this.catName;
		
	} // getCategory()
	
	/*
	 * Returns the text associated with the given image loc in this category
	 * imageLoc - the location of the category
	 */
	public String getText(String imageLoc) {
		try {
			return locationAndText.get(imageLoc);
		} catch (KeyNotFoundException e) {
			System.err.println("Key not found");
		} //catch
		return "";
	} //getText()
	
	/*
	 * Determines if the provided images is stored in the category
	 * imageLoc - the location of the category
	 */
	public boolean hasImage(String imageLoc) {
		return locationAndText.hasKey(imageLoc);
	} // hasImage()
	
	/*
	 * Returns an array of all the images in the category 
	 */
	public String[] getImages() {
		String[] imageList = new String[locationAndText.size];
			imageList = this.locationAndText.getKeys();
		
		return imageList;
	} // getImages
	
	/*
	 * Returns an array of all the images in the category 
	 */
	public String[] getTexts() {
		String[] TextList= new String[locationAndText.size];
			TextList = this.locationAndText.getValues();
		
		return TextList;
	} // getTexts
	
} // AACCategory 
