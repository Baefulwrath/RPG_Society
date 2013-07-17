package scripting.scripts;

import scripting.Script;
import ui.UIHandler;

public class SCRIPT_setMenu extends Script{

	@Override
	public void activate(String line) {
		UIHandler.setMenu(line);
	}

}
