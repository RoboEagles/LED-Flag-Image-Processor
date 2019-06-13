package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import image_processor.ImageProcessor;
import window.Window;

public class Main {
	
	public static void main(String[] args) {		
		String fileLocation = "C:\\Users\\suhey\\OneDrive\\Desktop\\Eagle Design.png";
		String saveLocation = "C:\\Users\\suhey\\OneDrive\\Desktop\\ProcessedImage.txt";
		
		final int LEDS = 60;
		final int SLICES = 120;
		
		final int RES_WIDTH = 1280;
		final int RES_HEIGHT = 720;
		
		Window window = new Window(RES_WIDTH,RES_HEIGHT);
		
		int[] data = new int[LEDS*SLICES];
		
		while(window.running) {
			// Updates the location of the image file
			fileLocation = window.fileLocationText.getText();
			
			// Updates the save location of the final image array
			fileLocation = window.fileLocationText.getText();

			if(window.isConvertButtonPressed()) {
				try {
					// Gets the original image that will be processed
					BufferedImage img = ImageIO.read(new File(fileLocation));
					
					// Resizes the image to the according to how many columns and rows you want
					BufferedImage resizedImg = ImageProcessor.resize(img, LEDS, SLICES);
					
					// Converts the resized image to a byte array
					data = ImageProcessor.convertImage(resizedImg, window.showWhiteCheck.isSelected());
					
//					//Checks each value in the array to make sure the values aren't nonsense
//					ImageProcessor.checkArray(data);
					
					System.out.println("Image has been converted to an array");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(window.isSaveButtonPressed()) {
				// Saves the byte array to a text file at the designated location
				ImageProcessor.saveArray(data, saveLocation);
			}
		}
		
	}
	
}























