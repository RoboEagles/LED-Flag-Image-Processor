package image_processor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Formatter;

import javax.imageio.ImageIO;

public final class ImageProcessor {
	
	// Constructor
	public ImageProcessor(){
		
	}

	/**
	 * Resizes a buffered image
	 * @param img The original bufferedImage that is to be resized
	 * @param height The height the image will be resized to
	 * @param width The width the image will be resized to
	 * @return 
	 */
	public static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2D = resized.createGraphics();
		g2D.drawImage(tmp, 0,0,null);
		g2D.dispose();
		return resized;
	}
	
	/**
	 *  Manually parses through an image to store its RGB values. Goes through the image column
	 *  by column
	 * @param img The original buffered image
	 * @param showWhite If true, the array will include all white values. 
	 * Otherwise the image will turn any white into black so the LEDs won't present them.
	 * @return An array of RGB values from the image
	 */
	public static int[] convertImage(BufferedImage img, boolean showWhite) {
		int[] array = new int[7200];
		
		try {
			// Goes through each pixel column by column
			for(int i = 0; i < img.getWidth(); i++) {
				for(int x = 0; x < img.getHeight(); x++) {
					int index = i*img.getHeight() + x;
					
					// Gets the RGB value of the pixel
					// Subtracts by 0xff000000 to get rid of the white in the RGB value
					int value = (int) (img.getRGB(i,x) - 0xff000000);
					
					// Turns all the white into black so they can't be presented by the LEDs
					if(!showWhite) {
						if(value <0xffffff) {
							array[index] = 0xffffff;
						}
						else {
							array[index] = 0x00000000;
						}
					}
					else {
						array[index] = value;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return array;
	}
	
	/**
	 *  Saves the image's array to the specified save location
	 * @param data The array that is to be saved
	 * @param saveLocation The disc location where the data will be saved
	 */
	public static void saveArray(int[] data, String saveLocation) {
		try {
			// Opens the file that the data will be saved to
			Formatter f = new Formatter(saveLocation);
			
			// Saves each value in the array to the file
			f.format("%s", "{");
			for(int i = 0; i < data.length - 1; i++) {
				f.format("0x%h, ", data[i]);
			}
			f.format("0x%h }", data[data.length-1]);
			
			// Closes the file
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Checks each value in the array to make sure the values aren't nonsense
	 * @param data The array that will be checked
	 */
	public static void checkArray(int[] data) {
		// Prints each value in the array
		for(int i = 0; i < data.length; i++) {
			
			System.out.printf("%h", data[i]);
			System.out.println(", " + i);
		}
	}
}
