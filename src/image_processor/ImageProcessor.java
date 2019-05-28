package image_processor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

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
	
	public ImageProcessor(){
		
	}

	
	public static void saveImage(String imgLocation, String saveLocation, FileType type) {
		
	}
		
	public static void convertImage(String imgLocation, String saveLocation, FileType type) throws IOException {
		File file = new File(imgLocation);

		BufferedImage image = ImageIO.read(file);
		// Here we convert into *supported* format
		BufferedImage imageCopy;
		switch(type) {
			case PNG:
				imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
				break;
			default:
				imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
				break;
		}imageCopy.getGraphics().drawImage(image, 0, 0, null);

		byte[] data = ((DataBufferByte) imageCopy.getRaster().getDataBuffer()).getData();  
		Mat img = new Mat(image.getHeight(),image.getWidth(), CvType.CV_8UC3);
		img.put(0, 0, data);           
		Imgcodecs.imwrite(saveLocation, img);
	}
	
	public static Mat fileToMat() {
		Mat image;
		
		return image;
	}
	
	public static Mat downScale(Mat originalImage, int width, int height){
		Mat finalImage = new Mat();
		Size size = new Size(width, height);
		
		Imgproc.resize(originalImage, finalImage, size);
		
		return finalImage;
	}
}
