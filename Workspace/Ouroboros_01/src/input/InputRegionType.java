package input;


public enum InputRegionType {
	PAUSED, UI, REST;
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static InputRegionType parseInputRegionType(String type){
    	InputRegionType temp = PAUSED;
    	for(int i = 0; i < values().length; i++){
    		if(type.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	return temp;
    }
}
