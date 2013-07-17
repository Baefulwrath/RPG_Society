package input.managers;

import ouroboros.ProgramState;
import input.*;
import input.regions.*;

public class INMA_EDITOR extends InputManager{

	public INMA_EDITOR(ProgramState s) {
		super(s, new INRE_MENU_PAUSED(), new INRE_MENU_UI(), new INRE_MENU_REST());
	}

}
