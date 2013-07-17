package ui;

import input.Pointer;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;

import ouroboros.NodeType;
import ouroboros.ProgramNode;

import ui.huds.*;
import ui.menus.*;


public class UIHandler extends ProgramNode{
	
	public static HashMap<String, Menu> menus = new HashMap<String, Menu>();
	public static HashMap<String, Hud> huds = new HashMap<String, Hud>();
	public static ArrayList<Message> messages = new ArrayList<Message>();
	public static String currentMenu = "";
	public static String defaultMenu = "main";
	public static String currentHudGroup = "";
	
	public static void setup(){
		setupMenus();
		setupHuds();
	}
	
	public static void setupMenus(){
		menus.put("main", new MENU_main());
		menus.put("about", new MENU_about());
		menus.put("exit", new MENU_exit());
		setMenu(defaultMenu);
	}
	
	public static void setupHuds(){
		currentHudGroup = "test";
	}

	public static boolean intersects(Rectangle r) {
		boolean temp = false;
		if(getMenu().intersects(r)){
			temp = true;
		}
		if(!temp){
			Hud[] h = getHuds();
			for(int i = 0; i < h.length; i++){
				if(h[i].intersects(r)){
					temp = true;
					break;
				}
			}
		}
		return temp;
	}

	public static void dispose() {
		menus.clear();
	}

	public static Menu getMenu() {
		return getMenu(currentMenu);
	}
	
	public static Menu getMenu(String id) {
		if(menus.containsKey(id)){
			return menus.get(id);
		}else{
			print("Menu not found, returning default menu...");
			return menus.get(defaultMenu);
		}
	}
	
	public static Hud[] getHuds(){
		return getHuds(currentHudGroup);
	}
	
	public static Hud[] getHuds(String group){
		ArrayList<Hud> hal = new ArrayList<Hud>();
	    for(Map.Entry<String, Hud> entry : huds.entrySet()){
	    	Hud h = huds.get(entry.getKey());
			if(h.contains(group)){
				hal.add(h);
			}
		}
		Hud[] har = new Hud[hal.size()];
	    for(int i = 0; i < hal.size(); i++){
	    	har[i] = hal.get(i);
	    }
	    return har;
	}
	
	public static void update(Pointer p){
		if(readyToUpdate(25, NodeType.UIHANDLER)){
			getMenu().update(p);
			updateMessages();
			updateHuds(p);
		}
	}

	private static void updateHuds(Pointer p) {
		Hud[] h = getHuds();
		for(int i = 0; i < h.length; i++){
			h[i].update(p);
		}
	}

	public static void touchDown() {
		getMenu().touchDown();
		Hud[] h = getHuds();
		for(int i = 0; i < h.length; i++){
			h[i].touchDown();
		}
	}
	
	public static void touchUp() {
		getMenu().touchUp();
		Hud[] h = getHuds();
		for(int i = 0; i < h.length; i++){
			h[i].touchUp();
		}
	}
	
	public static void print(String s){
		print(s, 4000);
	}
	
	public static void print(String s, int time){
		System.out.println(s);
		messages.add(new Message(s, time));
	}

	public static void updateMessages(){
    	for(int i = 0; i < messages.size(); i++){
    		Message m = messages.get(i);
    		if(m.creationTime + m.LIFETIME <= System.currentTimeMillis()){
    			//System.out.println("--<" + m.TEXT + ">--");
    			messages.remove(i);
    			updateMessages();
    			break;
    		}
    	}
	}

	public static void setMenu(String id) {
		if(menus.containsKey(id)){
			currentMenu = id;
		}else{
			print("Menu does not exist");
		}
	}

	public static void setDefaultMenu(String defMenu) {
		defaultMenu = defMenu;
		System.out.println(defaultMenu);
	}
	
}
