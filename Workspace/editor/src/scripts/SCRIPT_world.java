package scripts;

import javax.swing.JOptionPane;

import main.Main;
import main.Script;
import main.World;


public class SCRIPT_world extends Script{

	@Override
	public void activate(String line) {
		switch(line){
			case "new":
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(Main.frame, "Are you sure?", "New world", JOptionPane.YES_NO_OPTION)){
					String title = JOptionPane.showInputDialog(Main.frame, "Input Title");
					String id = JOptionPane.showInputDialog(Main.frame, "Input Id");
					String cell = JOptionPane.showInputDialog(Main.frame, "Input Cell");
					int width = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Input Width"));
					int height = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Input Height"));
					Main.world = new World(title, id, cell, width, height, 0, 0);
				}
				break;
			case "load":
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(Main.frame, "Are you sure?", "Load world", JOptionPane.YES_NO_OPTION)){
					
				}
				break;
			case "save":
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(Main.frame, "Are you sure?", "Save world", JOptionPane.YES_NO_OPTION)){
					
				}
				break;
		}
	}
	
}
