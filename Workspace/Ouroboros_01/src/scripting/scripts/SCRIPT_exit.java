package scripting.scripts;

import ouroboros.OS;
import scripting.Script;

public class SCRIPT_exit extends Script{

	@Override
	public void activate(String line) {
		OS.exitProgram = true;
	}

}
