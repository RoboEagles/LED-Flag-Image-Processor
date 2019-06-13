package window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Window implements Runnable{	
	
	
	public boolean running = true;
	
	JFrame window = new JFrame("LED Flag Image Processor");
	
	JPanel mainPanel = new JPanel();
	JPanel images = new JPanel();
	
	public JTextField fileLocationText = new JTextField(10);
	public JTextField saveLocationText = new JTextField(10);
	
	JButton convertButton = new JButton("Convert Image");
	
	JLabel initialImage = new JLabel();
	JLabel finalImage = new JLabel();
	
	public int imageWidth;
	public int imageHeight;
	
	
	// ****************************** Window Button Actions *************************** //
	public void convertButton_Action() {
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				convertButton_Pressed = true;
			}
			
		});
	}
	
	// ***************************** End of Button Actions ****************************** //
	
	// *************************** Constructor ******************************** //
	public Window(int RES_WIDTH, int RES_HEIGHT) {		

		// Initializes the buttons and sets what they do when clicked
		convertButton_Action();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setSize(1000, 800);
		
		imageWidth = RES_WIDTH;
		imageHeight = RES_HEIGHT;
		
		initialImage.setSize(imageWidth, imageHeight);
		finalImage.setSize(imageWidth, imageHeight);

		images.add(initialImage);
		images.add(finalImage);
		
		mainPanel.add(images);
		mainPanel.add(convertButton);
		mainPanel.add(fileLocationText);
		mainPanel.add(saveLocationText);
		
		window.add(mainPanel);
	}
	// ************************* Constructor END ******************************* //
	
	public void closeWindow() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		running = false;
	}

	/**
	 * 
	 * @return whether the convertButton was pressed since this method was last called
	 */	
	public boolean isConvertButtonPressed(){
		if(convertButton_Pressed){
			convertButton_Pressed = false;
			return true;
		}
		else {
			return false;
		}
	}
	boolean convertButton_Pressed = false;


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
