package main;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import image_processor.ImageProcessor;
import window.Window;

public class Main {
	static String fileLocation = "C:\\Users\\suhey\\OneDrive\\Desktop\\Eagle Design.png";
	static String saveLocation = "C:\\Users\\suhey\\OneDrive\\Desktop\\ProcessedImage.png";
	
	static Mat originalImage;
	

	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		ImageProcessor.saveImage(fileLocation, saveLocation, ImageProcessor.FileType.JPG);
	}
	
}





//File input = new File("C:\\File\\1.tif");
//BufferedImage image = ImageIO.read(input);         
//byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();            
//Mat img = new Mat(image.getHeight(),image.getWidth(), CvType.CV_8UC3);
//img.put(0, 0, data);            
//Imgcodecs.imwrite("C:\\File\\input.jpg", img);






















