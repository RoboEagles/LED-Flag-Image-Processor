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

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public final class ImageProcessor {
	Mat image;
	
	// Constructor
	// Does nothing for now
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
	
	// Converts a buffered image into a byte array
//	public static byte[] convertImage(BufferedImage img){		
//		try {
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//			
//			ImageIO.write(img, "jpg", output);
//			
//			byte[] data = output.toByteArray();
//			
//			return data;
//		}catch(Exception e) {
//			System.out.println("Unable to convert the image into a byte array");
//			e.printStackTrace();
//			return null;
//		}
//
//	}
	
	// Manually parses through an image to store its RGB values
	public static int[] convertImage(BufferedImage img) {
		int[] array = new int[7200];
		
		try {
			for(int i = 0; i < img.getWidth(); i++) {
				for(int x = 0; x < img.getHeight(); x++) {
					int index = i*img.getHeight() + x;
					
					// Places the RGB value of the pixel into the array
					// Subtracts by 4278190080L to get rid of the white in the RGB value
					array[index] = (int) (img.getRGB(i,x) - 4278190080L);
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
				f.format("%h, ", data[i]);
			}
			f.format("%h }", data[data.length-1]);
			
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
