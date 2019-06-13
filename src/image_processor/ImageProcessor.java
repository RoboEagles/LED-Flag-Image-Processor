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

	// Resizes a buffered image
	public static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2D = resized.createGraphics();
		g2D.drawImage(tmp, 0,0,null);
		g2D.dispose();
		return resized;
	}
	
	// Manually parses through an image to store its RGB values
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
	
	// Saves the image's byte array to the specified save location
	public static void saveArray(int[] data, String saveLocation) {
		try {
			
			Formatter f = new Formatter(saveLocation);
			
			f.format("%s", "{");
			for(int i = 0; i < data.length - 1; i++) {
				f.format("0x%h, ", data[i]);
			}
			f.format("0x%h }", data[data.length-1]);
			
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Checks each value in the array to make sure the values aren't nonsense
	public static void checkArray(int[] data) {
		for(int i = 0; i < data.length; i++) {
			
			System.out.printf("%h", data[i]);
			System.out.println(", " + i);
		}
	}
}
