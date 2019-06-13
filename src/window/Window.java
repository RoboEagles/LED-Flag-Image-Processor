package window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/* Contains all the code for the UI of this program
 * 
*/
public class Window{	
	
	
	public boolean running = true;
	
	JFrame window = new JFrame("LED Flag Image Processor");
	
	public JPanel mainPanel = new JPanel(),
					images = new JPanel();
	
	public JTextField fileLocationText = new JTextField(10),
						saveLocationText = new JTextField(10);
	
	public JButton chooseFileButton = new JButton("Select Image File"),
					chooseSaveButton = new JButton("Select Save Location"),
					convertButton = new JButton("Convert Image"),
					saveButton = new JButton("Save Image");
	
	public JCheckBox showWhiteCheck = new JCheckBox("Show White: ");
	
	public JLabel initialImage = new JLabel(),
					finalArray = new JLabel();
	
	public int imageWidth,
				imageHeight;
		
	public String getFileLocation() {
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("JPG & PNG Files", "JPG", "PNG");
    	chooser.setFileFilter(filter);
    	
        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           return chooser.getSelectedFile().getAbsolutePath();
        }
        else {
        	System.out.println("User did not pick a file");
        	return "";
        }
    }
	
	// ****************************** Window Button Actions *************************** //
	public void convertButton_Action() {
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				convertButton_Pressed = true;
			}
			
		});
	}
	
	public void saveButton_Action() {
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveButton_Pressed = true;
			}
			
		});
	}
	
	public void chooseFileButton_Action() {
		chooseFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseFileButton_Pressed = true;
			}
			
		});
	}
	
	public void chooseSaveButton_Action() {
		chooseSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseSaveButton_Pressed = true;
			}
			
		});
	}
	
	// ***************************** End of Button Actions ****************************** //
	
	// *************************** Constructor ******************************** //
	public Window(int RES_WIDTH, int RES_HEIGHT) {		

		// Initializes the buttons and sets what they do when clicked
		convertButton_Action();
		saveButton_Action();
		chooseFileButton_Action();
		chooseSaveButton_Action();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setSize(1000, 800);
		
		imageWidth = RES_WIDTH;
		imageHeight = RES_HEIGHT;
		
		initialImage.setSize(imageWidth, imageHeight);

		images.add(initialImage);
		
		mainPanel.add(images);
		mainPanel.add(convertButton);
		mainPanel.add(saveButton);
		mainPanel.add(chooseFileButton);
		mainPanel.add(chooseSaveButton);
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
	
	public boolean isSaveButtonPressed(){
		if(saveButton_Pressed){
			saveButton_Pressed = false;
			return true;
		}
		else {
			return false;
		}
	}
	boolean saveButton_Pressed = false;
	
	public boolean isChooseFilePressed(){
		if(chooseFileButton_Pressed){
			chooseFileButton_Pressed = false;
			return true;
		}
		else {
			return false;
		}
	}
	boolean chooseFileButton_Pressed = false;
	
	public boolean isChooseSavePressed(){
		if(chooseSaveButton_Pressed){
			chooseSaveButton_Pressed = false;
			return true;
		}
		else {
			return false;
		}
	}
	boolean chooseSaveButton_Pressed = false;
}
