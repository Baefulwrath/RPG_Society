package scripts;

import javax.swing.JOptionPane;

import main.Assets;
import main.Main;
import main.Script;
import main.ScriptHandler;


public class SCRIPT_changeTileset extends Script{

	@Override
	public void activate(String line) {
		String[] choices = Assets.getTilesetNames();
		String input = (String) JOptionPane.showInputDialog(Main.frame, "Choose tileset", "Choose tileset", JOptionPane.QUESTION_MESSAGE, null, choices, choices[Main.brush.type]);
		if(input != null){
			Assets.setTileImages(input);
		}
	}
	
}
