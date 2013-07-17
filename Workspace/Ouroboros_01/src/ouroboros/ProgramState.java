package ouroboros;

public enum ProgramState {
    DEFAULT, MENU, GAME, EDITOR;
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static ProgramState parseState(String state){
    	ProgramState temp = DEFAULT;
    	for(int i = 0; i < values().length; i++){
    		if(state.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	return temp;
    }
}
