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
		
		try {
			// Gets the original image that will be processed
			BufferedImage img = ImageIO.read(new File(fileLocation));
			
			// Resizes the image to the according to how many columns and rows you want
			BufferedImage resizedImg = ImageProcessor.resize(img, LEDS, SLICES);
			
			// Converts the resized image to a byte array
//			byte[] data = ImageProcessor.convertImage(resizedImg);
			int[] data = ImageProcessor.convertImage(resizedImg);
			
			// Checks each value in the array to make sure the values aren't nonsense
			for(int i = 0; i < data.length; i++) {
				
				System.out.printf("%h", data[i]);
				System.out.println(", " + i);
			}
			
			System.out.println(resizedImg.getHeight());

			System.out.println(resizedImg.getWidth());
			
			// Saves the byte array to a text file at the designated location
			ImageProcessor.saveArray(data, saveLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}























