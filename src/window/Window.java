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

public class Window {	
	
	
	public boolean running = true;
	
	JFrame window = new JFrame("LED Flag Image Processor");
	
	JPanel mainPanel = new JPanel();
	JPanel images = new JPanel();
	
	JButton convertButton = new JButton("Convert Image");
	JButton sendButton = new JButton("Send Image");
	
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
	
	public void sendButton_Action() {
		convertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendButton_Pressed = true;
			}
			
		});
	}
	
	// ***************************** End of Button Actions ****************************** //
	
	// *************************** Constructor ******************************** //
	public Window(int RES_WIDTH, int RES_HEIGHT) {		

		// Initializes the buttons and sets what they do when clicked
		convertButton_Action();
		sendButton_Action();
		
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
		mainPanel.add(sendButton);
		
		window.add(mainPanel);
	}
	// ************************* Constructor END ******************************* //
	
	public void closeWindow() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		running = false;
	}

	/**
	 * 
	 * @return whether the sendButton was pressed since this method was last called
	 */
	public boolean isCameraButtonPressed(){
		if(sendButton_Pressed){
			sendButton_Pressed = false;
			return true;
		}
		else {
			return false;
		}
	}
	boolean sendButton_Pressed = false;
	
	public boolean isPropIDPressed(){
		if(convertButton_Pressed){
			convertButton_Pressed = false;
			return true;
		}
		else {
			return false;
		}
	}
	boolean convertButton_Pressed = false;
	
}
