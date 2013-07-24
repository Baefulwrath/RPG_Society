package scripts;

import javax.swing.JOptionPane;

import main.Assets;
import main.Main;
import main.Script;
import main.ScriptHandler;


public class SCRIPT_changeBrushType extends Script{

	@Override
	public void activate(String line) {
		String[] choices = Assets.getTileTitles(true);
		String input = (String) JOptionPane.showInputDialog(Main.frame, "", "", JOptionPane.QUESTION_MESSAGE, null, choices, choices[Main.brush.type]);
		if(input != null){
			Main.brush.type = Integer.parseInt(input.substring(0, 1));
		}
	}
	
}
