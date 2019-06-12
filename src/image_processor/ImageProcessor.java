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
	
	public enum FileType{
		PNG,
		JPG,
		JPEG,
	}
	
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
	
	// Creates a byte array based on the image
	public static byte[] convertImage(BufferedImage img, String saveLocation){		
		try {
			// 
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			
			ImageIO.write(img, "jpg", output);
			
			byte[] data = output.toByteArray();
			
			return data;
		}catch(Exception e) {
			System.out.println("Unable to convert the image into a byte array");
			e.printStackTrace();
			return null;
		}

	}
	
	// Saves the image's byte array to the specified save location
	public static void saveArray(byte[] data, String saveLocation) {
		try {
			
			Formatter f = new Formatter(saveLocation);
			f.format("%s", data.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
