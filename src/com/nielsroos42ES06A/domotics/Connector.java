package com.nielsroos42ES06A.domotics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

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
	
	public void setHost(String host) {
		this.host = host;
	}

	private static final int addRoom = 1;
	private static final int deleteRoom = 2;
	private static final int renameRoom = 3;
	private static final int getAllRooms = 4;
	private static final int deleteModule = 5;
	private static final int disableModule = 6;
	private static final int enableModule = 7;
	private static final int changeModuleRoom = 8;
	private static final int removeModuleFromRoom = 9;
	private static final int getModuleInfo = 10;
	private static final int getAllModulesInRoom = 11;
	private static final int getAllModulesNotInARoom = 12;
	private static final int getAllModules = 13;
	private static final int add_Module = 14;
	private static final int rename_Module = 15;
	private static final int addSensor = 16;
	private static final int addActuator = 17;
	private static final int deleteDevice = 18;
	private static final int getDeviceInfo = 19;
	private static final int enableDevice = 20;
	private static final int disableDevice = 21;
	private static final int renameDevice = 22;
	private static final int getAllDevicesInModule = 23;
	private static final int getAllDevices = 24;
	private static final int log = 25;	
	private static final int getLogs = 26;
	private static final int addScript = 27;
	private static final int deleteScript = 28;
	private static final int enableScript = 29;
	private static final int disableScript = 30;
	private static final int getScript = 31;
	private static final int getAllScripts = 32;
	private static final int addCondition = 33;
	private static final int addAction = 34;
	private static final int getAllConditions = 35;
	private static final int getAllActions = 36;
	
	private static final int HANDLER_NOT_CONNECTED = 37;
	
	
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
						msg.what = 1;
						msg.obj = inp;
						/*if (inp.equals("Playing...")) {
							msg.what = HANDLER_PLAY;
						} else if (inp.equals("Paused...")) {
							msg.what = HANDLER_PAUSE;
						} else if (inp.startsWith("ARTIST")) {
							msg.what = HANDLER_ARTIST;
							msg.obj = inp.split("=")[1];

						} else if (inp.startsWith("TITLE")) {
							msg.what = HANDLER_TITLE;
							msg.obj = inp.split("=")[1];
						} else if (inp.startsWith("CURRENTTIME")) {
							msg.what = HANDLER_CUR_TIME;
							msg.obj = inp.split("=")[1];
						} else if (inp.startsWith("TOTALTIME")) {
							msg.what = HANDLER_TOT_TIME;
							msg.obj = inp.split("=")[1];
						} else if (inp.startsWith("Paused=")) {
							if (inp.split("=")[1].equals("false")) {
								msg.what = HANDLER_PLAY;
							} else {
								msg.what = HANDLER_PAUSE;
							}
						}*/
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
}