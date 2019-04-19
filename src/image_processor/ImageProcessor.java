package image_processor;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageProcessor {
	Mat image;
	
	public ImageProcessor(String imageFile){
		
	}
	
	public static Mat downScale(Mat originalImage, Size size){
		Mat finalImage = new Mat();
		
		Imgproc.resize(originalImage, finalImage, size);
		
		return finalImage;
	}
}
