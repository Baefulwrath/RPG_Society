package ouroboros;

import java.util.HashMap;

public abstract class ProgramNode {
	
	protected static HashMap<NodeType, Long> lastUpdates = new HashMap<NodeType, Long>();
    
    protected static boolean readyToUpdate(int interval, NodeType type){
    	boolean temp = false;
		long last = 0;
    	try{
    		last = lastUpdates.get(type);
    	}catch(Exception ex){}
    	if(last + interval <= System.currentTimeMillis()){
    		temp = true;
    		lastUpdates.put(type, System.currentTimeMillis());
    	}
    	return temp;
    }
	
}
