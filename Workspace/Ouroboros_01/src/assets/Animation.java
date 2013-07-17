package assets;

public abstract class Animation {
	
	private long last = 0;
	private int interval = 5;
	protected int cImg = 0;
	
	protected boolean readytoUpdate(){
		boolean temp = false;
    	if(last + interval <= System.currentTimeMillis()){
    		temp = true;
    		last = System.currentTimeMillis();
    	}
    	return temp;
	}
	
	protected int getCurrent(int current, int length){
		if(current < length){
			current++;
		}else if(current >= length){
			current = 0;
		}
		return current;
	}
}
