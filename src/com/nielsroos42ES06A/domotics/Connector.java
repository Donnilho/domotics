package com.nielsroos42ES06A.domotics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minidev.json.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.minidev.json.JSONObject;

import com.thetransactioncompany.jsonrpc2.*;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class Connector extends Thread {
	private String command = "";
	private boolean commandGiven = false;
	private Socket echoSocket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Handler handler = null;
	long timeReconnect = System.currentTimeMillis();
	long timeConnection = System.currentTimeMillis();
	private String host;
	
	private int ID;
	//public Object firstID;
	public int finalID;
	public ArrayList<Object> arrays = new ArrayList<Object>();
	public ArrayList<Object> rooms = new ArrayList<Object>();
	public HashMap<Integer, String> requestlog = new HashMap<Integer, String>();
	
	
	public int arraysize;
	public int roomsize;

	public ArrayList<Object> getArrays() {
		return this.arrays;
	}

	public int getArraysize() {
		return this.arraysize;
	}
	
	public void setHost(String host) {
		this.host = host;
	}

	private static final int addRoom = 0;
	private static final int deleteRoom = 1;
	private static final int renameRoom = 2;
	private static final int getAllRooms = 3;
	private static final int deleteModule = 4;
	private static final int disableModule = 5;
	private static final int enableModule = 6;
	private static final int changeModuleRoom = 7;
	private static final int removeModuleFromRoom = 8;
	private static final int getModuleInfo = 9;
	private static final int getAllModulesInRoom = 10;
	private static final int getAllModulesNotInARoom = 11;
	private static final int getAllModules = 12;
	private static final int add_Module = 13;
	private static final int rename_Module = 14;
	private static final int addSensor = 15;
	private static final int addActuator = 16;
	private static final int deleteDevice = 17;
	private static final int getDeviceInfo = 18;
	private static final int enableDevice = 19;
	private static final int disableDevice = 20;
	private static final int renameDevice = 21;
	private static final int getAllDevicesInModule = 22;
	private static final int getAllDevices = 23;
	private static final int log = 24;	
	private static final int getLogs = 25;
	private static final int addScript = 26;
	private static final int deleteScript = 27;
	private static final int enableScript = 28;
	private static final int disableScript = 29;
	private static final int getScript = 30;
	private static final int getAllScripts = 31;
	private static final int addCondition = 32;
	private static final int addAction = 33;
	private static final int getAllConditions = 34;
	private static final int getAllActions = 35;
	private static final int HANDLER_NOT_CONNECTED = 36;
	
	String[] methods = {"addRoom","deleteRoom","renameRoom","getAllRooms","deleteModule","disableModule","enableModule",
"changeModuleRoom","removeModuleFromRoom","getModuleInfo","getAllModulesInRoom","getAllModulesNotInARoom","getAllModules","add_Module",
"rename_Module","addSensor",
			"addActuator",
			"deleteDevice",
			"getDeviceInfo",
			"enableDevice",
			"disableDevice",
			"renameDevice",
			"getAllDevicesInModule",
			"getAllDevices",
			"log",
			"getLogs",
			"addScript",
			"deleteScript",
			"enableScript",
			"disableScript",
			"getScript",
			"getAllScripts",
			"addCondition",
			"addAction",
			"getAllConditions",
			"getAllActions"}; //eerste = 0 laatste = 36

	public String inp;

	public Connector(Handler handler) {

		this.handler = handler;
	}

	public void giveCommand(String cmd) {
		commandGiven = true;
		try {
			this.command = cmd;
		} catch (Exception e) {
		}

	}

	public void run() {
		boolean connected = false;
		System.out.println("Trying to connect..");
		connected = init();
		if (connected) {
			Read rd = new Read();
			rd.start();
			while (true) {

				try {
					sleep(10);
				} catch (InterruptedException e1) {
				}
				if (commandGiven) {
					out.println(command);
					commandGiven = false;
					timeConnection = System.currentTimeMillis();
				}
				/*if (!commandGiven) {
					long time2 = System.currentTimeMillis();
					if ((time2 - timeConnection) >= 3000) {
						giveCommand("AREYOUTHERE");
						timeConnection = System.currentTimeMillis();
					}
				}*/ //controleren of er nog steeds een verbinding is
			}
		}
	}
	

	private boolean init() {
		//String Host = "192.168.0.103";
		boolean connected = false;
		while (connected == false) {
			if(this.host==null){
				this.host = "192.168.0.103";
			}
			try {
				Socket echosocket = new Socket();
				echosocket.connect(new InetSocketAddress(this.host, 5000), 1000);
				out = new PrintWriter(echosocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(
						echosocket.getInputStream()));
				out.println("HI SERVER");
				connected = true;
			} catch (IOException e) {
				System.out.println("could not find server on ip " + host);
				Message msg = handler.obtainMessage();
				msg.what = HANDLER_NOT_CONNECTED;
				handler.sendMessage(msg);
				close();
				return false;
			}
		}
		return true;
	}

	private void close() {
		try {
			in.close();
			out.close();
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e){
			
		}
	}

	private class Read extends Thread {

		public void run() {

			while (true) {
				try {
					sleep(10);
				} catch (InterruptedException e1) {
				}


				try {
					inp = in.readLine();
					if (inp != null) {
						System.out.println("Received from Server--> " + inp);
						Message msg = handler.obtainMessage();
						//msg.what = 1;
					//	msg.obj = inp;
						int control = ParseResponse(inp);
						System.out.println("commingthrough id : " + control);
						System.out.println();
						String answer = requestlog.get(control);
						
						for(int i = 0; i < methods.length ;i++){
							System.out.println("variabel: " + methods[i]);
							System.out.println("Base: " + answer);
							if(answer.equals(methods[i]) && !answer.equals(methods[3])){
								System.out.println("Match!");
								msg.what = i; 
								msg.obj = inp;
								break;
							}
							else if(answer.equals(methods[3])){
								System.out.println("inside methods 3");
								msg.what = 3;
								//System.out.println("msg.what : "+ i);
								msg.obj = rooms;
								msg.arg1 = roomsize;
								break;
							}
						}
						
						handler.sendMessage(msg);

					} else {
						reconnect();
					}
				} catch (IOException e) {
					reconnect();
				}
			}
		}
	}

	private void reconnect() {
		long time2 = System.currentTimeMillis();
		if ((time2 - timeReconnect) >= 3000) {
			timeReconnect = System.currentTimeMillis();
			System.out.println("Server not responding..");
			System.out.println("Trying to reconnect..");
			close();
			init();
		}
	}

	//PARSING

	public String ParsRequest(String cmd, List<Object>params){
	requestlog.put(ID, cmd);
	JSONRPC2Request ReqOut = new JSONRPC2Request(cmd, params, ID);
	String jsons = ReqOut.toJSONString();
	System.out.println("J's Son : "+jsons);
	ID++;
	return jsons;
	}
	
	public String ParsAnswer(){
		String jsons = "test";
		return jsons;
	}
	
	public int ParseResponse(String jsonString){
	// Parse response string
		JSONRPC2Response respIn = null;
	
		try {
			respIn = JSONRPC2Response.parse(jsonString);
			System.out.println(jsonString);
			// Check for success or error
			if (respIn.indicatesSuccess()) {
				System.out.println("The request succeeded :");
				Object result =  respIn.getResult();
				//System.out.println("\tresult :: " + result);
				System.out.println("de zogenaamde ID : " + respIn.getID().toString());
				int oneID = Integer.parseInt(respIn.getID().toString());
				String finalID = requestlog.get(oneID);
				System.out.println("FinalID1 : " + finalID);

				//System.out.println("\tid     : " + id);				 
				JSONRPC2Response respOut = new JSONRPC2Response(result.toString(),oneID);
				System.out.println("Server output = "+ respOut);
				JSONObject jsonObject = respOut.toJSONObject();
				
				String s = jsonObject.get("result").toString();
				Object obj = JSONValue.parse(s);
				JSONArray newObject = (JSONArray) obj;
				
				String check = newObject.get(0).toString();
				if(Boolean.parseBoolean(check) == true){
					String arrayS = newObject.get(1).toString();
					Object objArray = JSONValue.parse(arrayS);
					JSONArray newestObject = (JSONArray) objArray;
					System.out.println("finalID2 : " + finalID);
					this.arraysize = newestObject.size();
					System.out.println("Roomsize : "+ this.arraysize);
					//ArrayList<Object> arrays = new ArrayList<Object>();
					if(arraysize > 0){
						for(int i = 0; i< this.arraysize; i++){
							this.arrays.add(newestObject.get(i));	
							System.out.println("Inner array i = " + i + " equals " + newestObject.get(i).toString());
						}
					}
					
					if(finalID.equalsIgnoreCase(methods[3])){
						System.out.println("About to set the roomsm finalID = " + finalID);
						//MainActivity m = new MainActivity();
						roomsize = (this.arrays.size());
						for(int i = 0; i< this.arraysize; i++){
							this.rooms.add(newestObject.get(i));	
						}
						System.out.println("Roomstats setted");
					}
				}
				else{
					System.out.println("Result is false");
				}
			}
			else {
				System.out.println("The request failed :");
		
				JSONRPC2Error err = respIn.getError();
		
				System.out.println("\terror.code    : " + err.getCode());
				System.out.println("\terror.message : " + err.getMessage());
				System.out.println("\terror.data    : " + err.getData());
			}
		} catch (JSONRPC2ParseException e) {
			System.out.println(e.getMessage());
			// Handle exception...
		}
		return finalID; 
	}
}