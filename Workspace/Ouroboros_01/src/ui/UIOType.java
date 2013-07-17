package ui;


public enum UIOType {
	DEFAULT, MENU, HUD;
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static UIOType parseType(String type){
    	UIOType temp = DEFAULT;
    	for(int i = 0; i < values().length; i++){
    		if(type.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	return temp;
    }
}
