package input.managers;

import ouroboros.ProgramState;
import input.*;
import input.regions.*;

public class INMA_MENU extends InputManager{

	public INMA_MENU(ProgramState s) {
		super(s, new INRE_GAME_PAUSED(), new INRE_GAME_UI(), new INRE_GAME_REST());
	}

}
