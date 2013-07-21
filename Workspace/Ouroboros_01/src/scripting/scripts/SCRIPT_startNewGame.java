package scripting.scripts;

import ouroboros.OS;
import scripting.Script;

public class SCRIPT_startNewGame extends Script{

	@Override
	public void activate(String line) {
		OS.startNewGame();
	}

}
