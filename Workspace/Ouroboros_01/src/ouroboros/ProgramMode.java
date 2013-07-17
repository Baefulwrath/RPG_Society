package ouroboros;

import static ouroboros.ProgramState.*;

public abstract class ProgramMode {

	public ProgramState state = DEFAULT;
	
	public ProgramMode(ProgramState s){
		state = s;
	}
	
	public abstract void update();
}
