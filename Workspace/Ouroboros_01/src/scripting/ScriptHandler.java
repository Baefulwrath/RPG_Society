package scripting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import ouroboros.NodeType;
import ouroboros.OS;
import ouroboros.ProgramNode;
import ui.UIHandler;
import scripting.scripts.*;

public class ScriptHandler extends ProgramNode{
    private static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static BufferedReader reader = new BufferedReader(inputStreamReader);
    private static boolean initialized = false;
    private static HashMap<String, Integer> genInts = new HashMap<String, Integer>();
    private static ArrayList<String> lines = new ArrayList<String>();
    private static HashMap<String, Script> scripts = new HashMap<String, Script>();
    
    public static void setup(){
    	loadGenericVariables();
    	loadScripts();
    	initialized = true;
    }
    
    public static void update() {
        try {
        	if(readyToUpdate(25, NodeType.SCRIPTHANDLER)){
	            if (reader.ready()) {
	                handleScript(reader.readLine());
	            }
	            if(lines.size() > 0){
	            	readLine(lines.get(0));
	            	lines.remove(0);
	            }
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void dispose(){
    	genInts.clear();
    }
    
    public static void loadGenericVariables(){
    }
    
    public static void loadScripts(){
    	scripts.put("exit_", new SCRIPT_exit());
    	scripts.put("print_", new SCRIPT_print());
    	scripts.put("setMenu_", new SCRIPT_setMenu());
    	scripts.put("setState_", new SCRIPT_setState());
    	scripts.put("test_", new SCRIPT_test());
    }

    public static void handleScript(String script) {
    	if(initialized){
	        // Ta bort kommentarer, mellanrum o.s.v
	        script = cleanupScript(script);
	        // Kolla efter metoder s�som GET_ och fyll i variabler.
	        script = fillScript(script);
	        // Loopa igenom olika kommandon och utf�r dem.
	        while (script.contains("#")) {
	            activateScript(script);
	            script = script.substring(script.indexOf("#") + 1);
	        }
	        activateScript(script);
    	}
        //System.out.println(script);
    }
    
    public static void addLine(String line){
    	lines.add(line);
    }

    public static void activateScript(String script) {
        if (script.length() > 0) {
            if (script.length() > 1) {
                if (script.contains("#")) {
                	addLine(script.substring(0, script.indexOf("#")));
                } else {
                	addLine(script);
                }
            } else {
                if (!script.contains("#")) {
                	addLine(script);
                }
            }
        }
    }

    public static String cleanupScript(String script) {
        while (script.contains(" ")) {
            script = script.substring(0, script.indexOf(" ")) + script.substring(script.indexOf(" ") + 1);
        }
        return script;
    }

    public static String fillScript(String script) {
        while (script.contains("GET_")) {
            script = script.substring(0, script.indexOf("GET_")) + getVar(script.substring(script.indexOf("GET_") + 4, script.indexOf("_TEG"))) + script.substring(script.indexOf("_TEG") + 4);
        }
        return script;
    }

    public static String getVar(String id) {
        String value = "[GET-ERROR]";
        if(id.substring(0, 4).equals("loc_")){
            value += "[LOC]";
            id = id.substring(4);
        }else{
    		if(genInts.containsKey(id)){
    			value = genInts.get(id).toString();
    		}
        }
        return value;
    }

    public static void readLine(String line) {
        String cmd = line.substring(0, line.indexOf("_") + 1);
        line = line.substring(line.indexOf("_") + 1);
        scripts.get(cmd).activate(line);
        
    }
}
