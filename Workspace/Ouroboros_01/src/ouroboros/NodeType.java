package ouroboros;

public enum NodeType {
	DEFAULT, ASSETHANDLER, INPUTHANDLER, RENERINGHANDLER, SCRIPTHANDLER, UIHANDLER, UNIVERSEHANDLER;

    @Override
    public String toString() {
        return super.toString();
    }
    
    public static NodeType parseType(String type){
    	NodeType temp = DEFAULT;
    	for(int i = 0; i < values().length; i++){
    		if(type.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	return temp;
    }
}
