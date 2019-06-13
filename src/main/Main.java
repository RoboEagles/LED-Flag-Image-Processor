package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import image_processor.ImageProcessor;
import window.Window;

public class Main {
	
	public static void main(String[] args) {		
		String fileLocation = "C:\\Users\\suhey\\OneDrive\\Desktop\\x-mark.png";
		String saveLocation = "C:\\Users\\suhey\\OneDrive\\Desktop\\NewImage.txt";
		
		final int LEDS = 60;
		final int SLICES = 120;
		
		final int RES_WIDTH = 1280;
		final int RES_HEIGHT = 720;
		
		int[] data = new int[LEDS*SLICES];
			
			System.out.println("Convert Button has been pressed");
			try {
				// Gets the original image that will be processed
				BufferedImage img = ImageIO.read(new File(fileLocation));
				System.out.println("Loaded the image...");
				
				// Resizes the image to the according to how many columns and rows you want
				BufferedImage resizedImg = ImageProcessor.resize(img, LEDS, SLICES);
				System.out.println("Resized the image...");
				
				// Converts the resized image to a byte array
				data = ImageProcessor.convertImage(resizedImg, false);
				System.out.println("Converted the image...");
				
//				//Checks each value in the array to make sure the values aren't nonsense
//				ImageProcessor.checkArray(data);
				
				System.out.println("Image has been converted to an array");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Saves the byte array to a text file at the designated location
			ImageProcessor.saveArray(data, saveLocation);
		
	}
	
}























