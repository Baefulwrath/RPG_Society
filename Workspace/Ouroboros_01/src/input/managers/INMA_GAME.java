package input.managers;

import ouroboros.ProgramState;
import input.*;
import input.regions.*;

public class INMA_GAME extends InputManager{

	public INMA_GAME(ProgramState s) {
		super(s, new INRE_GAME_PAUSED(), new INRE_GAME_UI(), new INRE_GAME_REST());
	}

}
