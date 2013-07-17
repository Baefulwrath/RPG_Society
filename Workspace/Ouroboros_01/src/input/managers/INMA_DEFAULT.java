package input.managers;

import ouroboros.ProgramState;
import input.*;
import input.regions.*;

public class INMA_DEFAULT extends InputManager{

	public INMA_DEFAULT(ProgramState s) {
		super(s, new INRE_DEFAULT_PAUSED(), new INRE_DEFAULT_UI(), new INRE_DEFAULT_REST());
	}

}
