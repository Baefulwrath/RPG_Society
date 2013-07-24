package scripts;

import main.Main;
import main.Script;


public class SCRIPT_print extends Script{

	@Override
	public void activate(String line) {
		Main.message = line;
		System.out.println(line);
	}
	
}
