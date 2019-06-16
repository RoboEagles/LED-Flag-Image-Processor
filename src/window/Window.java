package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/* Contains all the code for the UI of this program
 * 
*/
public class Window{	
	
	
	public boolean running = true;
	
	JFrame window = new JFrame("LED Flag Image Processor");
	
	public JPanel container = new JPanel(),
					buttons = new JPanel(),
					images = new JPanel();
	
	public JTextArea array = new JTextArea(1, 100);
	
//	public JTextField fileLocationText = new JTextField(10),
//						saveLocationText = new JTextField(10);
	
	public JButton chooseFileButton = new JButton("Select Image File"),
					convertButton = new JButton("Convert Image"),
					saveButton = new JButton("Save Image");
	
	public JCheckBox showWhiteCheck = new JCheckBox("Show White: ");
	
	public JLabel initialImage = new JLabel(new ImageIcon()),
					resizedImage = new JLabel(new ImageIcon());
		
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
	
	public String getFolderLocation() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	
        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           return chooser.getCurrentDirectory().getAbsolutePath();
        }
        else {
        	System.out.println("User did not pick a folder");
        	return "";
        }
    }
	
	// ****************************** Window Button Actions *************************** //
	public void chooseFileButton_Action() {
		chooseFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseFileButton_Pressed = true;
			}
			
		});
	}

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
	
	// ***************************** End of Button Actions ****************************** //
	
	// *************************** Constructor ******************************** //
	public Window(int RES_WIDTH, int RES_HEIGHT) {		

		// Initializes the buttons and sets what they do when clicked
		convertButton_Action();
		saveButton_Action();
		chooseFileButton_Action();	

		images.add(initialImage, BorderLayout.WEST);
		images.add(resizedImage, BorderLayout.EAST);
		
		buttons.add(chooseFileButton);
		buttons.add(convertButton);
		buttons.add(saveButton);
		
//		array.setLineWrap(true);
		
		container.add(images, BorderLayout.NORTH);
		container.add(array, BorderLayout.CENTER);
		container.add(buttons, BorderLayout.SOUTH);
//		container.add(fileLocationText);
//		container.add(saveLocationText);
		
		window.add(container);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setSize(RES_WIDTH, RES_HEIGHT);
	}
	// ************************* Constructor END ******************************* //
	/**
	 * Closes the window and sets running to false
	 */
	public void closeWindow() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		running = false;
	}

	/**
	 * 
	 * @return whether the chooseFileButton was pressed since this method was last called
	 */
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
	
	/**
	 * 
	 * @return whether the saveButton was pressed since this method was last called
	 */
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
	
}
