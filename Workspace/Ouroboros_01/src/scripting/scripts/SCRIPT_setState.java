package scripting.scripts;

import ouroboros.OS;
import scripting.Script;

public class SCRIPT_setState extends Script{

	@Override
	public void activate(String line) {
		OS.changeState(line);
	}

}
