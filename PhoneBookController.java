import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JFileChooser;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PhoneBookController {

	private TreeMap<String,String> t=new TreeMap<String,String>();
	private int i=0,j;
	private boolean loaded;
    @FXML
    private TextField input;

    @FXML
    private TextArea ouput;

    @FXML
    void addButtonPressed(ActionEvent event) 
    {
    	String line=input.getText();
    	i=0;
		while(!Character.isWhitespace(line.charAt(i)))
			i++;
		t.put(line.substring(0,i+1).trim(), line.substring(i).trim());
    }

    @FXML
    void deleteButtonPressed(ActionEvent event) 
    {
    	String line=input.getText(); 
    	System.out.println(line);
    	if(t.containsKey(line))
    		t.remove(line);
    }

    @FXML
    void loadButtonPressed(ActionEvent event) 
    {
    	ouput.clear();
    	if(!loaded) {
	    	try
	        {
		    		loaded=true;
		    		JFileChooser fc=new JFileChooser("./");
		    		fc.showOpenDialog(null);
		    		File file=fc.getSelectedFile();
    				Scanner scan=new Scanner(file);
		    		String line;
		    		int i=0;
		    		while(scan.hasNext())
		    		{
		    			i=0;
		    			line=scan.nextLine();
		    			while(!Character.isWhitespace(line.charAt(i)))
		    				i++;
		    			t.put(line.substring(0,i+1).trim(), line.substring(i).trim());
		    		}
		    		for (String entry : t.keySet())
						ouput.appendText(entry+" "+t.get(entry)+"\n");
	    	 }
	    	catch (IOException e)
	        {
	        	System.out.println(e.getMessage());
	        }
    	}
    	else
    	{
    		for (String entry : t.keySet())
				ouput.appendText(entry+" "+t.get(entry)+"\n");
    	}
    }

    @FXML
    void searchButtonPressed(ActionEvent event) 
    {
    	String line=input.getText();
    	if(t.containsKey(line))
    	{
    		ouput.clear();
    		ouput.setText(t.get(line));
    	}
    	else
    		ouput.setText("Name not found.");
    }

    @FXML
    void updateButtonPressed(ActionEvent event) 
    {
    	String line=input.getText();
    	i=0;
		while(!Character.isWhitespace(line.charAt(i)))
			i++;
		if(t.containsKey(line.substring(0,i+1).trim()))
		{
			t.put(line.substring(0,i+1).trim(), line.substring(i).trim());
			ouput.setText("Phone updated.");
		}
		else
			ouput.setText("Name not found.");
    }

}
