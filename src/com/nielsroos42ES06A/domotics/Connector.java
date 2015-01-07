package com.nielsroos42ES06A.domotics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class Connector extends Thread implements Serializable{
	//private static Connector charlie = null;
	private static ArrayList<String> command = new ArrayList<String>();
	private static boolean commandGiven = false;
	private Socket echoSocket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Handler handler = null;
	long timeReconnect = System.currentTimeMillis();
	long timeConnection = System.currentTimeMillis();
	private String host;
	private static int ID;
	public static int finalID;
	public static ArrayList<Object> arrays = new ArrayList<Object>();
	public static ArrayList<Object> rooms = new ArrayList<Object>();
	public static HashMap<Integer, String> requestlog = new HashMap<Integer, String>();
	
	public static int arraysize;
	public static int roomsize;
	
	public void setHost(String host) {
		this.host = host;
	}


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
	private static final int addModule = 13;
	private static final int renameModule = 14;
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
	private static final int login = 37;
	private static final int falseError = 38;
	
	private static final int  getAllSensorsInModule = 39;
	private static final int getAllActuatorsInModule = 40;
	private static final int getAllSensors = 41;
	private static final int getAllActuators = 42;
	private static final int addRoom = 43;
	
	static String[] methods = {"test","deleteRoom","renameRoom","getAllRooms","deleteModule","disableModule","enableModule",
"changeModuleRoom","removeModuleFromRoom","getModuleInfo","getAllModulesInRoom","getAllModulesNotInARoom","getAllModules","addModule",
"renameModule","addSensor",
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
			"getAllActions",
			"HANDLER_NOT_CONNECTED",//voor testen
			"login",
			"falseError",
			"getAllSensorsInModule",
			"getAllActuatorsInModule",
			"getAllSensors",
			"getAllActuators",
			"addRoom"}; //eerste = 0 laatste = 43


	public String inp;

	public Connector(Handler handler) {

		this.handler = handler;
	}

	public void changeHandler(Handler handler){
		this.handler = handler;
	}
	
	public static void giveCommand(String cmd) {
		
		//String cmd = ;
		//requestlog.put(ID, cmd);
		//commandGiven = true;
		try {
			command.add(cmd);
		} catch (Exception e) {
		}
		//ID++;
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
				while (command.size() > 0) {
					out.println(command.get(0));
					command.remove(0);
					//commandGiven = false;
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
		boolean connected = false;
		while (connected == false) {
			if(this.host==null){
				this.host = "192.168.0.100"; //raspberry pi
				//this.host = "192.168.0.106"; //local
				//this.host = "94.210.247.190";//external server
			}
			try {
				Socket echosocket = new Socket();
				echosocket.connect(new InetSocketAddress(this.host, 5000), 1000);
				//echosocket.connect(new InetSocketAddress(this.host, 22), 1000); //external server
				out = new PrintWriter(echosocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(
						echosocket.getInputStream()));
				//out.println("HI SERVER");
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
						JSONRPC2Response respIn = null;
						
						try {
							respIn = JSONRPC2Response.parse(inp);
							//System.out.println(inp);
							// Check for success or error
							
							if (respIn.indicatesSuccess()) {
								System.out.println("The request succeeded :");
								// test;
								Object result =  respIn.getResult();
								int oneID = Integer.parseInt(respIn.getID().toString());
								String finalID = requestlog.get(oneID);
								requestlog.remove(oneID);
							 
								JSONRPC2Response respOut = new JSONRPC2Response(result.toString(),oneID);
								//System.out.println("Server output = "+ respOut);
								JSONObject jsonObject = respOut.toJSONObject();
								
								String s = jsonObject.get("result").toString();
								Object obj = JSONValue.parse(s);
								JSONArray newObject = (JSONArray) obj;
								
								String check = newObject.get(0).toString();
								


								
								if (Boolean.parseBoolean(check)==true) {
									if(finalID.equals(methods[addRoom])){ //43
											msg.what = addRoom;
											msg.obj = "Added room succesfully";
										
									}
									else if(finalID.equals(methods[deleteRoom])){ //1
											msg.what = deleteRoom;
											msg.obj = "Deleted room succesfully";
										
									}
									else if(finalID.equals(methods[renameRoom])){ //2
											msg.what = renameRoom;
											msg.obj = "Renamed room succesfully";
									}
									
									else if(finalID.equals(methods[getAllModulesInRoom])){ //10
										int aantalModules;
										ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
										
										JSONArray[] array = getData(newObject.get(1).toString());
										if(array.length != 0){
											int elementsInData = array[0].size();
											for (int i = 0; i < array.length; i++) {
												ArrayList<Object> moduleinfo = new ArrayList<Object>();
												for (int j = 0; j < elementsInData; j++) {
													System.out.println(array[i].get(j).toString());
													moduleinfo.add(array[i].get(j).toString());
												}
												modules.add(moduleinfo);
											}
											System.out.println("msg.what = getAllModulesInRoom / " + getAllModulesInRoom);
											aantalModules = modules.size();
											msg.what = getAllModulesInRoom;
											msg.obj = modules; 
											msg.arg1 = aantalModules;
											msg.arg2 = 4;
											System.out.println("msg setted : "+msg.what);
										}
										else{
											msg.what = getAllModulesInRoom;
											msg.obj = modules;
											msg.arg1 = modules.size();
											msg.arg2 = 4;
										}
									}
									else if(finalID.equalsIgnoreCase(methods[getAllModulesNotInARoom])){ //11
										int aantalModules;
										ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
										
										JSONArray[] array = getData(newObject.get(1).toString());
										if(array.length != 0){
											int elementsInData = array[0].size();
											for (int i = 0; i < array.length; i++) {
												ArrayList<Object> moduleinfo = new ArrayList<Object>();
												for (int j = 0; j < (elementsInData - 1); j++) {
													//System.out.println(array[i].get(j).toString());
													moduleinfo.add(array[i].get(j).toString());
												}
												modules.add(moduleinfo);
											}
											System.out.println("msg.what = getAllModulesNotInARoom / " + getAllModulesNotInARoom);
											aantalModules = modules.size();
											msg.what = getAllModulesNotInARoom;
											msg.obj = modules; 
											msg.arg1 = aantalModules;
											msg.arg2 = 3;
											System.out.println("msg setted : "+msg.what);
										}
										else{
											msg.what = getAllModulesNotInARoom;
											msg.obj = modules;
											msg.arg1 = modules.size();
											msg.arg2 = 4;
										}
									}
									else if(finalID.equalsIgnoreCase(methods[getAllRooms])){ //3
										arrays.clear();
										rooms.clear();
										String arrayS = newObject.get(1).toString();
										Object objArray = JSONValue.parse(arrayS);
										JSONArray newestObject = (JSONArray) objArray;
										arraysize = newestObject.size();
										if(arraysize > 0){
											for(int i = 0; i< arraysize; i++){
												arrays.add(newestObject.get(i));	
												//System.out.println("Inner array i = " + i + " equals " + newestObject.get(i).toString());
											}
										}
										roomsize = (arrays.size());
										for(int i = 0; i< arraysize; i++){
											rooms.add(newestObject.get(i));	
										}
										
										System.out.println("msg.what = getAllRooms / "+getAllRooms);
										msg.what = getAllRooms;
										msg.obj = rooms;
										msg.arg1 = roomsize;
										
									}
									else if(finalID.equalsIgnoreCase(methods[changeModuleRoom])){ //7
										msg.what = changeModuleRoom;
										msg.obj = "Changed Room of module succesfully";
									}
									
									else if(finalID.equalsIgnoreCase(methods[removeModuleFromRoom])){//8
										msg.what = removeModuleFromRoom;
										msg.obj = "Removed Module from Room succesfully";
									}
									else if(finalID.equalsIgnoreCase(methods[renameModule])){ //14
										msg.what = renameModule;
										msg.obj = "Renamed Module succesfully";
									}
									else if(finalID.equalsIgnoreCase(methods[getDeviceInfo])){ // 18
										ArrayList<Object> device = new ArrayList<Object>();
										String arrayS = newObject.get(1).toString();
										Object objArray = JSONValue.parse(arrayS);
										JSONArray newestObject = (JSONArray) objArray;
										
										for(int i = 0; i< newestObject.size(); i++){
											device.add(newestObject.get(i));	
										}
										
										System.out.println("msg.what = getDeviceInfo / "+getDeviceInfo);
										msg.what = getDeviceInfo;
										msg.obj = device;
									}
									
									else if(finalID.equalsIgnoreCase(methods[enableDevice])){//19
										msg.what = enableDevice;
										msg.obj = "Enabled device succesfully";
									}
									
									else if(finalID.equalsIgnoreCase(methods[disableDevice])){//20
										msg.what = disableDevice;
										msg.obj = "Disabled device succesfully";
									}
									
									else if(finalID.equalsIgnoreCase(methods[renameDevice])){//21
										msg.what = renameDevice;
										msg.obj = "Renamed device succesfully";
									}
									else if(finalID.equalsIgnoreCase(methods[getAllDevices])){//23
										ArrayList<ArrayList> devices = new ArrayList<ArrayList>();
										
										JSONArray[] array = getData(newObject.get(1).toString());
										if(array.length != 0){
											int elementsInData = array[0].size();
											for (int i = 0; i < array.length; i++) {
												ArrayList<Object> deviceinfo = new ArrayList<Object>();
												for (int j = 0; j < elementsInData; j++) {
													//System.out.println("c.getAllDevices: " + array[i].get(j).toString());
													deviceinfo.add(array[i].get(j).toString());
												}
												devices.add(deviceinfo);
											}
											System.out.println("msg.what = getAllDevices / " + getAllDevices);
											msg.what = getAllDevices;
											msg.obj = devices; 
											msg.arg1 = devices.size();
											System.out.println("msg setted : "+msg.what);
										}
										else{
											msg.what = getAllDevices;
											msg.obj = devices;
											msg.arg1 = devices.size();
										}
									}
									else if(finalID.equalsIgnoreCase(methods[addScript])){//26
										msg.what = addScript;
										msg.obj = "Added Script succesfully";
									}
									
									else if(finalID.equalsIgnoreCase(methods[getAllScripts])){//31
										System.out.println("msg.what = getAllScripts / "+ getAllScripts);
										ArrayList<ArrayList> scripts = new ArrayList<ArrayList>();
										
										JSONArray[] array = getData(newObject.get(1).toString());
										if(array.length != 0){
											int elementsInData = array[0].size();
											for (int i = 0; i < array.length; i++) {
												ArrayList<Object> scriptinfo = new ArrayList<Object>();
												ArrayList<ArrayList> listtest = new ArrayList<ArrayList>();
												ArrayList<ArrayList> listtest2 = new ArrayList<ArrayList>();
												ArrayList<Object> listcondition = new ArrayList<Object>();
												ArrayList<Object> listaction = new ArrayList<Object>();
												for (int j = 0; j < elementsInData; j++) {
													switch(j){
													case 0:
														System.out.println("c.getAllScripts:"+ j +": " + array[i].get(j).toString());
														scriptinfo.add( array[i].get(j).toString());
														break;
													case 1:
														System.out.println("c.getAllScripts:"+ j +": " + array[i].get(j).toString());
														scriptinfo.add( array[i].get(j).toString());
														break;
													case 2:
														System.out.println("c.getAllScripts:"+ j +": " + array[i].get(j).toString());
														scriptinfo.add( array[i].get(j).toString());
														break;
													case 3:
														System.out.println("c.getAllScripts:"+ j +": " + array[i].get(j).toString());
														listtest.add((ArrayList) array[i].get(j));
														System.out.println("array to listtest: " + listtest.get(0).toString());
														listtest2.add((ArrayList) listtest.get(0).get(0));
														System.out.println("listtest to listtest2 : " + listtest2.get(0).toString());
														for(int x = 0; x < 4; x++){
															System.out.println("print : " + listtest2.get(0).get(x).toString());
															listaction.add(listtest2.get(0).get(x).toString());
															scriptinfo.add(listtest2.get(0).get(x).toString());
														}
														listtest.clear();
														listtest2.clear();
														break;
													case 4:
														System.out.println("c.getAllScripts:"+ j +": " + array[i].get(j).toString());
														listtest.add((ArrayList) array[i].get(j));
														System.out.println("array to listtest: " + listtest.get(0).toString());
														listtest2.add((ArrayList) listtest.get(0).get(0));
														System.out.println("listtest to listtest2 : " + listtest2.get(0).toString());
														for(int x = 0; x < 5; x++){
															System.out.println("print : " + listtest2.get(0).get(x).toString());
															listcondition.add(listtest2.get(0).get(x).toString());
															scriptinfo.add(listtest2.get(0).get(x).toString());
														}
														listtest.clear();
														listtest2.clear();
														
														break;
													//System.out.println("c.getAllScripts:"+ j +": " + array[i].get(j).toString());
													//scriptinfo.add( array[i].get(j).toString());
													}
												}
												
												
												
												scripts.add(scriptinfo);
												//scripts.add(listcondition);
												//scripts.add(listaction);
											}
											System.out.println("msg.what = getAllScripts / " + getAllScripts);
											int aantalscripts = scripts.size();
											msg.what = getAllScripts;
											msg.obj = scripts; 
											msg.arg1 = aantalscripts;
											System.out.println("msg setted : "+msg.what);
										}
										else{
											msg.what = getAllScripts;
											msg.obj = scripts;
											msg.arg1 = scripts.size();
										}
									}
									
									
									else if(finalID.equalsIgnoreCase(methods[login])){ //37
										System.out.println("msg.what = login / "+login);
										msg.what = login;
										msg.obj = "onlytest";
									}
									
									
									else if(finalID.equalsIgnoreCase(methods[getAllSensorsInModule])){ // 39
										//System.out.println("msg.what = getAllSensorsInModule / "+ getAllSensorsInModule);
										ArrayList<ArrayList> sensoren = new ArrayList<ArrayList>();
										
										JSONArray[] array = getData(newObject.get(1).toString());
										if(array.length != 0){
											int elementsInData = array[0].size();
											for (int i = 0; i < array.length; i++) {
												ArrayList<Object> sensorinfo = new ArrayList<Object>();
												for (int j = 0; j < elementsInData; j++) {
													//System.out.println("c.getallsensorsinmodule: " + array[i].get(j).toString());
													sensorinfo.add(array[i].get(j).toString());
												}
												sensoren.add(sensorinfo);
											}
											System.out.println("msg.what = getAllSensorsInModule / " + getAllSensorsInModule);
											int aantalsensoren = sensoren.size();
											msg.what = getAllSensorsInModule;
											msg.obj = sensoren; 
											msg.arg1 = aantalsensoren;
											System.out.println("msg setted : "+msg.what);
										}
										else{
											msg.what = getAllSensorsInModule;
											msg.obj = sensoren;
											msg.arg1 = sensoren.size();
										}
									}
									
									else if(finalID.equalsIgnoreCase(methods[getAllActuatorsInModule])){ //40
										//System.out.println("msg.what = getAllActuatorsInModule / "+ getAllActuatorsInModule);
										ArrayList<ArrayList> actuatoren = new ArrayList<ArrayList>();
										
										JSONArray[] array = getData(newObject.get(1).toString());
										if(array.length != 0){
											int elementsInData = array[0].size();
											for (int i = 0; i < array.length; i++) {
												ArrayList<Object> actuatorinfo = new ArrayList<Object>();
												for (int j = 0; j < elementsInData; j++) {
													//System.out.println("c.getAllActuatorsInModule: " + array[i].get(j).toString());
													actuatorinfo.add(array[i].get(j).toString());
												}
												actuatoren.add(actuatorinfo);
											}
											System.out.println("msg.what = getAllActuatorsInModule / " + getAllActuatorsInModule);
											int aantalactuatoren = actuatoren.size();
											msg.what = getAllActuatorsInModule;
											msg.obj = actuatoren; 
											msg.arg1 = aantalactuatoren;
											System.out.println("msg setted : "+msg.what);
										}
										else{
											msg.what = getAllActuatorsInModule;
											msg.obj = actuatoren;
											msg.arg1 = actuatoren.size();
										}
									}
									else if(finalID.equalsIgnoreCase(methods[getAllSensors])){ //41
										ArrayList<ArrayList> sensoren = new ArrayList<ArrayList>();
										JSONArray[] array = getData(newObject.get(1).toString());
										int elementsInData = array[0].size();
										for (int i = 0; i < array.length; i++) {
											ArrayList<Object> sensorinfo = new ArrayList<Object>();
											for (int j = 0; j < elementsInData; j++) {
												System.out.println(array[i].get(j).toString());
												sensorinfo.add(array[i].get(j).toString());
											}
											sensoren.add(sensorinfo);
										}
										System.out.println("msg.what = getAllSensors / " + getAllSensors);
										int aantalsensoren = sensoren.size();
										msg.what = getAllSensors;
										msg.obj = sensoren; 
										msg.arg1 = aantalsensoren;
										System.out.println("msg setted : "+msg.what);
									}
									else if(finalID.equalsIgnoreCase(methods[getAllActuators])){ //42
										ArrayList<ArrayList> actuatoren = new ArrayList<ArrayList>();
										JSONArray[] array = getData(newObject.get(1).toString());
										int elementsInData = array[0].size();
										for (int i = 0; i < array.length; i++) {
											ArrayList<Object> actuatorinfo = new ArrayList<Object>();
											for (int j = 0; j < elementsInData; j++) {
												System.out.println(array[i].get(j).toString());
												actuatorinfo.add(array[i].get(j).toString());
											}
											actuatoren.add(actuatorinfo);
										}
										System.out.println("msg.what = getAllActuators / " + getAllActuators);
										int aantalactuatoren = actuatoren.size();
										msg.what = getAllActuators;
										msg.obj = actuatoren; 
										msg.arg1 = aantalactuatoren;
										System.out.println("msg setted : "+msg.what);
									}
								}
								
								else{ //WHEN RETURNED BOOLEAN IS FALSE
									System.out.println("Result is false");
									Object result1 =  respIn.getResult();
									int oneID1 = Integer.parseInt(respIn.getID().toString());
									String finalID1 = requestlog.get(oneID1);
									requestlog.remove(oneID1);
								 
									JSONRPC2Response respOut1 = new JSONRPC2Response(result1.toString(),oneID1);
									System.out.println("Server output = "+ respOut1);
									JSONObject jsonObject1 = respOut1.toJSONObject();
									
									String s1 = jsonObject1.get("result").toString();
									Object obj1 = JSONValue.parse(s1);
									JSONArray newObject1 = (JSONArray) obj1;
									
									String errormessage = newObject1.get(1).toString();
									System.out.println("Error message : " + errormessage);
									if(errormessage.equals("asdf")){
										System.out.println("asdf");//bij sensor = 0 || actuator = 0
									}
									else{
									msg.what = falseError;
									msg.obj = errormessage;
									}
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
						
						handler.sendMessage(msg);
						System.out.println("message sended  : " + msg.what);

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

	public static String ParsRequest(String cmd, List<Object>params){
		requestlog.put(ID, cmd);
		System.out.println("Parserequest  key : " + ID + " value : " + cmd);
		JSONRPC2Request ReqOut = new JSONRPC2Request(cmd, params, ID);
		ID++;
		String jsons = ReqOut.toJSONString();
		System.out.println("J's Son : "+jsons);
	return jsons;
	}
	
	static public JSONArray[] getData(String message) {
		System.out.println("Message equals " + message);

		JSONParser parser = new JSONParser();
		JSONArray array;
		try {
			array = (JSONArray) parser.parse(message);
			JSONArray[] data = new JSONArray[array.size()];

			for (int i = 0; i < array.size(); i++) {
				JSONArray innerArray = (JSONArray) array.get(i);
				data[i] = innerArray;
			}
			return data;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}