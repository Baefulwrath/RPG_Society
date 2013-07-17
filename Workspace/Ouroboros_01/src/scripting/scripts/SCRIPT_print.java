package scripting.scripts;

import scripting.Script;
import ui.UIHandler;

public class SCRIPT_print extends Script{

	@Override
	public void activate(String line) {
		UIHandler.print(line);
	}

}
