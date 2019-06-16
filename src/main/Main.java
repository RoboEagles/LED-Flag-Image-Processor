package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import image_processor.ImageProcessor;
import window.Window;

public class Main {
	
	public static void main(String[] args) {		
		String fileLocation = "";
		String saveLocation = "";
		String saveFileName = "Array";
		
		
		final int LEDS = 60;
		final int SLICES = 120;
		
		BufferedImage img = null;
		BufferedImage resizedImg = null;
		
		int[] data = new int[LEDS*SLICES];
		
		final int RES_WIDTH = 1280;
		final int RES_HEIGHT = 720;
		
		Window window = new Window(RES_WIDTH, RES_HEIGHT);

		while(window.running) {

			// Presents the images if it exists
			if(img != null) window.initialImage.setIcon(new ImageIcon(img));
			if(resizedImg != null) window.resizedImage.setIcon(new ImageIcon(resizedImg));
			
			// Presents the data from the image array
			StringBuilder t = new StringBuilder();
			t.append("{");
			for(int i = 0; i < data.length - 1; i++) {
				String s = String.format("0x%h", data[i]);
				t.append(s);
				t.append(", ");
			}
			t.append(String.format("0x%h }", data[data.length-1]));
			window.array.setText(t.toString());
			
			// Gets the location of the image file once the button is pressed
			if(window.isChooseFilePressed()) {
				fileLocation = window.getFileLocation();
				try {
					img = ImageIO.read(new File(fileLocation));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// Resizes the image and converts it to an array once the convert button is pressed
			if(window.isConvertButtonPressed()) {
				if(fileLocation == "") {
					showMessage("File Not Selected","You must select a JPG/PNG file before you can create an array");
				}
				else {
					try {
						// Gets the original image that will be processed
						img = ImageIO.read(new File(fileLocation));
						System.out.println("Loaded the image...");
						
						// Resizes the image according to how many columns and rows you want
						resizedImg = ImageProcessor.resize(img, LEDS, SLICES);
						System.out.println("Resized the image...");
						
						// Converts the resized image to an int array
						data = ImageProcessor.convertImage(resizedImg, true);
						System.out.println("Converted the image...");
						
//						//Checks each value in the array to make sure the values aren't nonsense
//						ImageProcessor.checkArray(data);
						
						System.out.println("Image has been converted to an array");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Saves the image array into a text file once the save button is pressed
			if(window.isSaveButtonPressed()) {
				if(resizedImg == null) {
					showMessage("Haven't converted an image", "There is no image array to save");
				}
				else{
					// Gets the directory the file will be saved in
					String saveFolder = window.getFolderLocation();
					saveLocation = saveFolder + File.separator + saveFileName + ".txt";
					
					// Saves the byte array to a text file at the designated location
					ImageProcessor.saveArray(data, saveLocation);
					
					System.out.println("Image has been saved!!!");
				}
			}
		}
	}
	
	/**
	 * Gives a notification to the user and you have to press "OK" to close it
	 * @param titleBar The text for the message's title
	 * @param infoMessage The text that will be contained in the message
	 */
	public static void showMessage(String titleBar, String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
}























