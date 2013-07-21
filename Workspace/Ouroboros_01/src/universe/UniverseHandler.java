package universe;

import java.util.HashMap;

import ouroboros.ProgramNode;

public class UniverseHandler extends ProgramNode{
	public static HashMap<String, TopDownWorld> TDWorlds = new HashMap<String, TopDownWorld>();
	public static String currentTDW = "";

	public static void setup(){
	}
	
	public static void update(){
	}
	
	public static void loadWorlds(){
	}
	
	public static TopDownWorld getTDWorld(String id){
		return TDWorlds.get(id);
	}
	
	public static TopDownWorld getTDWorld(){
		return getTDWorld(currentTDW);
	}

	public static void dispose() {
	}
}
