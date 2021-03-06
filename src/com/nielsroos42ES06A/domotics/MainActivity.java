package com.nielsroos42ES06A.domotics;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.minidev.json.JSONObject;
 
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
      private DrawerLayout mDrawerLayout;
      private ListView mDrawerList;
      private ActionBarDrawerToggle mDrawerToggle;
 
      private CharSequence mDrawerTitle;
      private CharSequence mTitle;
      CustomDrawerAdapter adapter;
 
      public static List<DrawerItem> dataList;
	  public static int roomSize;
	  public static int positie;
	  public String cmd;
	  public static ArrayList<Object> rooms = new ArrayList<Object>();
      public Intent intent;
      public static Connector c;
      public String selectedRoom;
      public int logModuleID;
      public int logDeviceID;
      public static  ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> sensorsInRoom = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> actuatorsInRoom = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> sensact = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> scripts = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> devices = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> sensors = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> actuators = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> logs = new ArrayList<ArrayList>();
      public static ArrayList<ArrayList> devicesWithLogs = new ArrayList<ArrayList>();
      public static ArrayList<Object> singledevice = new ArrayList<Object>();

      public static boolean load = false;
      public static boolean next = false;
      public  Fragment fragment = null;
      public  Bundle args = new Bundle();
      public int currentModules;
      public int targetModules;
      public int aantalmodules;
      private boolean main = false;
      public CharSequence selected;
      public int select;
      public static ProgressDialog test;
	  



	final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					System.out.println("Main Activity msg.what : "+msg.what);
						switch (msg.what) {
						case 43:
							CharSequence tekst43 = (CharSequence) msg.obj;
							System.out.println(tekst43);
							//Toast.makeText(MainActivity.this, tekst, Toast.LENGTH_SHORT).show();
					    	
							List<Object> param = new ArrayList<Object>();	
					         cmd = c.ParsRequest("getAllRooms", param);
					         System.out.println("getAllRooms command given");
					         c.giveCommand(cmd);
					         
					         Toast.makeText(MainActivity.this, tekst43, Toast.LENGTH_SHORT).show();
							break;
						case 1:
							CharSequence tekst1 = (CharSequence) msg.obj;
							System.out.println(tekst1);
							//Toast.makeText(MainActivity.this, tekst1, Toast.LENGTH_SHORT).show();
							
					    	List<Object> param1 = new ArrayList<Object>();
					         cmd = c.ParsRequest("getAllRooms", param1);
					         System.out.println("getAllRooms command given");
					         c.giveCommand(cmd);
					         Toast.makeText(MainActivity.this, tekst1, Toast.LENGTH_SHORT).show();
							break;
						case 2:
							CharSequence tekst2 = (CharSequence) msg.obj;
							System.out.println(tekst2);
							//Toast.makeText(MainActivity.this, tekst2, Toast.LENGTH_SHORT).show();
							
					    	List<Object> param2 = new ArrayList<Object>();
					         cmd = c.ParsRequest("getAllRooms", param2);
					         System.out.println("getAllRooms command given");
					         c.giveCommand(cmd);
					         Toast.makeText(MainActivity.this, tekst2, Toast.LENGTH_SHORT).show();
							break;
						case 3:
							System.out.println("Mainactivity get all rooms ");
							rooms.clear();
							System.out.println("sizeeee: " + ((ArrayList<Object>)msg.obj).size());
							for(int i = 0; i < ((ArrayList<Object>)msg.obj).size(); i++){
								rooms.add(((ArrayList<Object>) msg.obj).get(i).toString());
								//System.out.println("Position : "+ i + " contains : " + rooms.get(i).toString());
							}
							
							System.out.println("About to startActivity inside main");
						    
						    dataList.clear();
				            dataList.add(new DrawerItem("Algemeen", R.drawable.ic_action_group));
				            for(int i = 0 ; i < rooms.size() ; i++){
				            	String x = rooms.get(i).toString();
				                dataList.add(new DrawerItem(x, R.drawable.ic_action_group));
				            }
				            dataList.add(new DrawerItem("Over", R.drawable.ic_action_about));
				            dataList.add(new DrawerItem("Instellingen", R.drawable.ic_action_settings));
				            dataList.add(new DrawerItem("Script", R.drawable.ic_action_settings));
				            //dataList.add(new DrawerItem("Charts", R.drawable.ic_action_settings));
				            dataList.add(new DrawerItem("History", R.drawable.ic_action_settings));
				            dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));
				            dataList.add(new DrawerItem("Logout", R.drawable.ic_action_good));
				            
				            

				            
						  
						    for(int i = 0; i < rooms.size() ; i++){
							   String x = String.valueOf(i);
							    intent.putExtra(x, rooms.get(i).toString());	
							    //System.out.println("Addednn to intent: "+ localrooms.get(i).toString());
						    }
						    //startActivity(intent);
						    if(main == true){
						    finish();
						    startActivity(intent);
						    main = false;
						    }
						    
						
							
							break;
						case 7:
							CharSequence tekst7 = (CharSequence) msg.obj;
							System.out.println(tekst7);
							Toast.makeText(MainActivity.this, tekst7, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager7 = getFragmentManager();
		                        frgManager7.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        SelectItem(positie);
							
							break;
						
						case 8:
							CharSequence tekst8 = (CharSequence) msg.obj;
							System.out.println(tekst8);
							Toast.makeText(MainActivity.this, tekst8, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager8 = getFragmentManager();
		                        frgManager8.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        SelectItem(positie);
							
							break;
						case 10:
							if(msg.arg1 > 0){
								aantalmodules = msg.arg1;
								System.out.println("Case 10");
								modules.clear();
								sensorsInRoom.clear();
								actuatorsInRoom.clear();
								sensact.clear();
								currentModules = 0;
								System.out.println("Hello inside case 10 mainactivity");
								for(int i = 0; i < msg.arg1; i++){
									modules.add( ((ArrayList<ArrayList>) msg.obj).get(i));
									System.out.println("Module ID MAIN : " + i);
									for(int j = 0; j < msg.arg2 ; j++){
										String x = modules.get(i).get(j).toString();
										//System.out.println("Switch case 10 Main : " + x);
									}
								}
								targetModules = 0;
								for(int i = 0; i < aantalmodules; i++){
									List<Object> param10 = new ArrayList<Object>();
									int moduleID = Integer.parseInt((String) modules.get(i).get(0));
									System.out.println("ModuleID: " + moduleID);
									param10.add(moduleID);
			         	            cmd = c.ParsRequest("getAllSensorsInModule",param10);
			         	            System.out.println("cmd of getAllSensorsInModule  =  " + cmd);
			         	            c.giveCommand(cmd);
			         	            targetModules++;
								}
							}
							else{
								/*
								  load = false;
						    	  closeDialog();*/
								AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
								alertDialog.setTitle("Alert");
								alertDialog.setMessage("No Modules in Room");
								alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
								    new DialogInterface.OnClickListener() {
								        public void onClick(DialogInterface dialog, int which) {
								            dialog.dismiss();
										    finish();
										    startActivity(intent);
								        }
								    });
								alertDialog.show();
							
							}
							
							break;
						case 11:	
							//if(msg.arg1 > 0){
								//aantalmodules = msg.arg1;
								System.out.println("Case 11");
								modules.clear();
								//sensorsInRoom.clear();
								//actuatorsInRoom.clear();
								//sensact.clear();
								//currentModules = 0;
								System.out.println("Hello inside case 11 mainactivity");
								for(int i = 0; i < msg.arg1; i++){
									modules.add( ((ArrayList<ArrayList>) msg.obj).get(i));
									//System.out.println("Module ID MAIN : " + i);
									/*for(int j = 0; j < msg.arg2 ; j++){
										String x = modules.get(i).get(j).toString();
										//System.out.println("Switch case 10 Main : " + x);
									}*/
								}
								 fragment = new FragmentSettings();
					                args.putString(FragmentSettings.ITEM_NAME, dataList.get(positie)
					                            .getItemName());
					                args.putInt(FragmentSettings.IMAGE_RESOURCE_ID, dataList.get(positie)
					                            .getImgResID());
					                
					                fragment.setArguments(args);
					                FragmentManager frgManager11 = getFragmentManager();
					                frgManager11.beginTransaction().replace(R.id.content_frame, fragment)
					                            .commit();
					     
					                mDrawerList.setItemChecked(positie, true);
					                setTitle(dataList.get(positie).getItemName());
					                mDrawerLayout.closeDrawer(mDrawerList);
								
								
							/*}
							else{
								  load = false;
						    	  closeDialog();
							}*/
							break;
							
						case 14:
							CharSequence tekst14 = (CharSequence) msg.obj;
							System.out.println(tekst14);
							Toast.makeText(MainActivity.this, tekst14, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager14 = getFragmentManager();
		                        frgManager14.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        SelectItem(positie);
							break;
						
						case 18:
							singledevice.clear();
							for(int i = 0; i < ((ArrayList<Object>)msg.obj).size(); i++){
								singledevice.add(((ArrayList<Object>) msg.obj).get(i));
							}
							load = false;
			          	  	closeDialog();
			          	  	
			          		final CharSequence[] items2 = {/*"3 Uur","Etmaal","Dag","Week","Maand",*/"Eeuw"};

                           	AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                           	builder2.setTitle("Select Sensors which will be used for condition");

                           	builder2.setSingleChoiceItems(items2, -1, new android.content.DialogInterface.OnClickListener() {
       							@Override
       							public void onClick(DialogInterface dialog,
       									int which) {
      
       								selected = items2[which];
       								select = which;
       								 
       							}
                           	})
       				       .setPositiveButton("Select", new android.content.DialogInterface.OnClickListener() {
       						@Override
       						public void onClick(DialogInterface dialog, int which) {
       							
     							CharSequence tekst = "Ophalen van logs van afgelopen " + selected;
     							
       							Toast.makeText(MainActivity.this, tekst, Toast.LENGTH_SHORT).show();
       							String xray = (String) selected;
       							
       							long nul = 0;
    							long currenttime;
    			            	long hour = (60*60*1000);
    			            	long etmaal = (hour*12);
    			            	long day = (hour*24);
    			            	long week = (day*7);
    			            	long month = (day*31);
    			            	String currentTimeStamp;
    			            	
    			                currenttime = System.currentTimeMillis();
    			                long starttijd;
    			                
    			                if(xray.equalsIgnoreCase("Uur")){
    			                	starttijd = System.currentTimeMillis() - hour;
    			                }
    			                else if(xray.equalsIgnoreCase("Etmaal")){
    			                	starttijd = System.currentTimeMillis() - etmaal;
    			                }
    			                else if(xray.equalsIgnoreCase("Dag")){
    			                	starttijd = System.currentTimeMillis() - day;
    			                }
    			                else if(xray.equalsIgnoreCase("Week")){
    			                	starttijd = System.currentTimeMillis() - week;
    			                }
    			                else if(xray.equalsIgnoreCase("Maand")){
    			                	starttijd = System.currentTimeMillis() - month;
    			                }
    			                else{
    			                	starttijd = nul;
    			                }
    			                
    			                Date date = new Date(starttijd); // *1000 is to convert seconds to milliseconds
    		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
    		                    //sdf.setTimeZone(TimeZone.getTimeZone("GMT-1")); // give a timezone reference for formating (see comment at the bottom
    		                    currentTimeStamp = sdf.format(date);
    		                    System.out.println("starttijd: " + currentTimeStamp);
    							
    							List<Object> param18 = new ArrayList<Object>();
    			            	param18.add(logModuleID);
    			            	param18.add(logDeviceID);
    			            	param18.add(String.valueOf(starttijd));
    			            	param18.add(String.valueOf(currenttime));
    			                cmd = c.ParsRequest("getLogs",param18);
    			                System.out.println("cmd of getLogs  =  " + cmd);
    			                c.giveCommand(cmd);
    			                dialog.dismiss();
			          	  	
       						}
       				       });
                           	builder2.show();
							
							
							
			                
							
							break;
						case 19:
							CharSequence tekst19 = (CharSequence) msg.obj;
							System.out.println(tekst19);
							Toast.makeText(MainActivity.this, tekst19, Toast.LENGTH_SHORT).show();
						    /*finish();
						    startActivity(intent);
						    */
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager19 = getFragmentManager();
		                        frgManager19.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        SelectItem(positie);
							
							break;
							
						case 20:
							CharSequence tekst20 = (CharSequence) msg.obj;
							System.out.println(tekst20);
							Toast.makeText(MainActivity.this, tekst20, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager20 = getFragmentManager();
		                        frgManager20.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        SelectItem(positie);
							break;
							
						case 21:
							CharSequence tekst21 = (CharSequence) msg.obj;
							System.out.println(tekst21);
							Toast.makeText(MainActivity.this, tekst21, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager21 = getFragmentManager();
		                        frgManager21.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        
		                        SelectItem(positie);
							break;
						case 23:
							System.out.println("Case 23");
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								devices.add( ((ArrayList<ArrayList>) msg.obj).get(i));
							
							}
							break;
							
						case 25:
							System.out.println("Case 25");
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								logs.add( ((ArrayList<ArrayList>) msg.obj).get(i));
							
							}
							System.out.println("!@#$ :::"+ singledevice.get(4).toString()+":::");
							if(singledevice.get(4).toString().equalsIgnoreCase("String")){
								 fragment = new Fragmentchart();
					                args.putString(Fragmentchart.ITEM_NAME, dataList.get(positie)
					                            .getItemName());
					                args.putInt(Fragmentchart.IMAGE_RESOURCE_ID, dataList.get(positie)
					                            .getImgResID());
					                fragment.setArguments(args);
					               
					                FragmentManager frgManager25 = getFragmentManager();
					                frgManager25.beginTransaction().replace(R.id.content_frame, fragment)
					                            .commit();
					     
					                mDrawerList.setItemChecked(positie, true);
					                setTitle(dataList.get(positie).getItemName());
					                mDrawerLayout.closeDrawer(mDrawerList);
							}
							else{
								 fragment = new Fragmentchart2();
					                args.putString(Fragmentchart2.ITEM_NAME, dataList.get(positie)
					                            .getItemName());
					                args.putInt(Fragmentchart2.IMAGE_RESOURCE_ID, dataList.get(positie)
					                            .getImgResID());
					                fragment.setArguments(args);
					               
					                FragmentManager frgManager26 = getFragmentManager();
					                frgManager26.beginTransaction().replace(R.id.content_frame, fragment)
					                            .commit();
					     
					                mDrawerList.setItemChecked(positie, true);
					                setTitle(dataList.get(positie).getItemName());
					                mDrawerLayout.closeDrawer(mDrawerList);
							}
   
							break;
						case 26:
							CharSequence tekst26 = (CharSequence) msg.obj;
							System.out.println(tekst26);
							Toast.makeText(MainActivity.this, tekst26, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentEvent();
		                        args.putString(FragmentEvent.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentEvent.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager26 = getFragmentManager();
		                        frgManager26.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        
		                        SelectItem(positie);
							break;
						case 31:
							System.out.println("Case 31");
							scripts.clear();
							
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								scripts.add( ((ArrayList<ArrayList>) msg.obj).get(i));
								
								//sensact.add(((ArrayList<ArrayList>) msg.obj).get(i));
								//System.out.println("Module ID MAIN : " + i);
								/*for(int j = 0; j < msg.arg2 ; j++){
									String x = modules.get(i).get(j).toString();
									System.out.println("Switch case 10 Main : " + x);
								}*/
							}
							
							fragment = new FragmentEvent();
			                args.putString(FragmentEvent.ITEM_NAME, dataList.get(positie)
			                            .getItemName());
			                args.putInt(FragmentEvent.IMAGE_RESOURCE_ID, dataList.get(positie)
			                            .getImgResID());
			                
			                fragment.setArguments(args);
			                FragmentManager frgManager = getFragmentManager();
			                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
			                            .commit();
			     
			                mDrawerList.setItemChecked(positie, true);
			                setTitle(dataList.get(positie).getItemName());
			                mDrawerLayout.closeDrawer(mDrawerList);
							break;	
							
						case 32:
							CharSequence tekst32 = (CharSequence) msg.obj;
							System.out.println(tekst32);
							Toast.makeText(MainActivity.this, tekst32, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentEvent();
		                        args.putString(FragmentEvent.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentEvent.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager32 = getFragmentManager();
		                        frgManager32.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        
		                        SelectItem(positie);
							break;
						case 33:
							CharSequence tekst33 = (CharSequence) msg.obj;
							System.out.println(tekst33);
							Toast.makeText(MainActivity.this, tekst33, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentEvent();
		                        args.putString(FragmentEvent.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentEvent.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager33 = getFragmentManager();
		                        frgManager33.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        
		                        SelectItem(positie);
							break;
						case 36:
							load = false;
			          	  	closeDialog();
							AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
							alertDialog.setTitle("Alert");
							String x = (String) msg.obj;
							alertDialog.setMessage("Not Connected to Server " + x);
							alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
							    new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) {
							            dialog.dismiss();
							        }
							    });
							alertDialog.show();
							
							break;
							
						case 38:
							load = false;
			          	  	closeDialog();
							AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this).create();
							alertDialog1.setTitle("Alert");
							String x1 = (String) msg.obj;
							alertDialog1.setMessage(x1);
							alertDialog1.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
							    new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) {
							            dialog.dismiss();
							        }
							    });
							alertDialog1.show();
							
							break;
							
						case 39:
							System.out.println("Case 39");
							
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								sensorsInRoom.add( ((ArrayList<ArrayList>) msg.obj).get(i));
								sensact.add(((ArrayList<ArrayList>) msg.obj).get(i));
								//System.out.println("Module ID MAIN : " + i);
								/*for(int j = 0; j < msg.arg2 ; j++){
									String x = modules.get(i).get(j).toString();
									System.out.println("Switch case 10 Main : " + x);
								}*/
							}
							currentModules++;
							System.out.println("CurrentModules: "+ currentModules + " / TargetModules: " + targetModules);
							
							if(currentModules == targetModules){
								for(int i = 0; i < aantalmodules; i++){
									List<Object> param39 = new ArrayList<Object>();
									int moduleID = Integer.parseInt((String) modules.get(i).get(0));
									System.out.println("ModuleID: " + moduleID);
									param39.add(moduleID);
			         	            cmd = c.ParsRequest("getAllActuatorsInModule",param39);
			         	            System.out.println("cmd of getAllActuatorsInModule  =  " + cmd);
			         	            c.giveCommand(cmd);
			         	            currentModules = 0;
								}
							}
							else{
								break;
							}
							
					
							break;
						
						case 40:
							System.out.println("Case 40");
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								actuatorsInRoom.add( ((ArrayList<ArrayList>) msg.obj).get(i));
								sensact.add(((ArrayList<ArrayList>) msg.obj).get(i));
								//System.out.println("Module ID MAIN : " + i);
								/*for(int j = 0; j < msg.arg2 ; j++){
									String x = modules.get(i).get(j).toString();
									System.out.println("Switch case 10 Main : " + x);
								}*/
							}
							//actuatorsInRoom.add((ArrayList<ArrayList>) msg.obj);
							currentModules++;
							System.out.println("CurrentModules: "+ currentModules + " / TargetModules: " + targetModules);
							
							if(currentModules == targetModules){
								currentModules = 0;
								 fragment = new FragmentOne();
			                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
			                                    .getItemName());
			                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
			                                    .getImgResID());
			                        fragment.setArguments(args);
			                        
			                        FragmentManager frgManager1 = getFragmentManager();
			                        frgManager1.beginTransaction().replace(R.id.content_frame, fragment)
			                                    .commit();
			             
			                        mDrawerList.setItemChecked(positie, true);
			                        setTitle(dataList.get(positie).getItemName());
			                        mDrawerLayout.closeDrawer(mDrawerList);
							}
							break;
						case 41:
							sensors.clear();
							System.out.println("Case 41");
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								sensors.add( ((ArrayList<ArrayList>) msg.obj).get(i));
								
							}
							break;
							
						case 42:
							actuators.clear();
							System.out.println("Case 42");
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								actuators.add( ((ArrayList<ArrayList>) msg.obj).get(i));
								
							}
							MainActivity.load = false;
					  	  	MainActivity.closeDialog();
							
							break;
						case 44:
							CharSequence tekst44 = (CharSequence) msg.obj;
							System.out.println(tekst44);
							Toast.makeText(MainActivity.this, tekst44, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager44 = getFragmentManager();
		                        frgManager44.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        
		                        SelectItem(positie);
							break;
							
	
						case 45:
							CharSequence tekst45 = (CharSequence) msg.obj;
							System.out.println(tekst45);
							Toast.makeText(MainActivity.this, tekst45, Toast.LENGTH_SHORT).show();
							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager45 = getFragmentManager();
		                        frgManager45.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
		                        
		                        SelectItem(positie);
							
							break;
						case 46:
							
							devicesWithLogs.clear();
							System.out.println("Case 46");
							for(int i = 0; i < ((ArrayList<ArrayList>) msg.obj).size(); i++){
								devicesWithLogs.add( ((ArrayList<ArrayList>) msg.obj).get(i));
								
							}
							
							ArrayList<String> bravo = new ArrayList<String>();
                         	for(int a = 0; a < MainActivity.devicesWithLogs.size(); a++){
                        		
                      			String xray = "Module: " + devicesWithLogs.get(a).get(1).toString() + 
                      					" - Device: "+ devicesWithLogs.get(a).get(0).toString() +
                      					" - " + devicesWithLogs.get(a).get(2).toString();
                      					
                      			bravo.add(xray);	
                        	}
                         	load = false;
			          	  	closeDialog();
                        	final CharSequence[] items = bravo.toArray(new CharSequence[bravo.size()]);
							
							AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        	builder.setTitle("Select a Device");
                        	//builder.setCancelable(false);
                        	builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
    							@Override
    							public void onClick(DialogInterface dialog,
    									int which) {
    								
    								// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
    								 //dialog.dismiss();
    								selected = items[which];
    								select = which;
    								 
    							}
                        	})
                                  // .setCancelable(false)
    				       .setPositiveButton("Select", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							CharSequence tekst = "Making Chart of: " + selected + " ...";
    							
    							logModuleID = Integer.parseInt(devicesWithLogs.get(select).get(1).toString()); //moduleID
    							System.out.println("Module: " + devicesWithLogs.get(select).get(1).toString());
    							logDeviceID = Integer.parseInt(devicesWithLogs.get(select).get(0).toString()); //deviceID
    							System.out.println("Device: " + devicesWithLogs.get(select).get(0).toString());
    							
    							List<Object> param46 = new ArrayList<Object>();
				            	param46.add(logModuleID);
				            	param46.add(logDeviceID);
				                cmd = c.ParsRequest("getDeviceInfo",param46);
				                System.out.println("cmd of getDeviceInfo  =  " + cmd);
				                c.giveCommand(cmd);
    							
    							Toast.makeText(MainActivity.this, tekst, Toast.LENGTH_SHORT).show();
    							
    							//deleteRoom(String roomName)
    							
    							dialog.dismiss();
    						}
    				       })
    				       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							dialog.cancel();
    						}
    				       });
                        	AlertDialog alert = builder.create();
                        	//And if the line above didn't bring ur dialog up use this bellow also:
                        	alert.show();
							break;
							
						case 99:
							System.out.println("&&&&&&&CASE 99&&&&&&&&&");
							if(load == false){
								loadingDialog();
								load = true;
								System.out.println("Load = false, changing now");
							}
							else{
								System.out.println("Load = true");
							}
							
							
							break;
							
						default: System.out.println("default: " + msg.obj);
					
						}

					

					super.handleMessage(msg);
				}
				
			};
			
			
	  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            load = false;
            intent = getIntent();
            rooms.clear();
            c.changeHandler(handler);
            main = true;
            int mid = 1;
            int sid = 1;
            long currenttime;
            String currentTimeStamp;

            currenttime = System.currentTimeMillis() / 1000L;
            long time = currenttime - (60 * 25);
            System.out.println("UnixTime: " + currenttime);
            Date date = new Date(currenttime*1000L); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT-1")); // give a timezone reference for formating (see comment at the bottom
            currentTimeStamp = sdf.format(date);
            System.out.println(currentTimeStamp);
            
       
          
            

            roomSize = intent.getExtras().getInt("size");
			System.out.println("grootte room array: " + roomSize);
			
			for(int i = 0; i < roomSize; i++){
				String x = String.valueOf(i);
				rooms.add(intent.getStringExtra(x).toString());
				System.out.println(rooms.get(i).toString());
			}


 
            // Initializing
            dataList = new ArrayList<DrawerItem>();
            mTitle = mDrawerTitle = getTitle();
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer);
 
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                        GravityCompat.START);
 //System.out.println("++++++++++++++++++++++++++++++++");
            // Add Drawer Item to dataList
            //dataList.add(new DrawerItem("Import & Export",R.drawable.ic_action_import_export));
            dataList.add(new DrawerItem("Algemeen", R.drawable.ic_action_group));
            for(int i = 0 ; i < roomSize ; i++){
            	//String x = "Room" + i;
            	String x = rooms.get(i).toString();
                dataList.add(new DrawerItem(x, R.drawable.ic_action_group));
                //System.out.println("Room : "+ x);
            }
            //dataList.add(new DrawerItem("Test Room", R.drawable.ic_action_group));
            dataList.add(new DrawerItem("Over", R.drawable.ic_action_about));
            dataList.add(new DrawerItem("Instellingen", R.drawable.ic_action_settings));
            dataList.add(new DrawerItem("Scripts", R.drawable.ic_action_settings));
           // dataList.add(new DrawerItem("Charts", R.drawable.ic_action_settings));
            dataList.add(new DrawerItem("History", R.drawable.ic_action_settings));
            dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));
            dataList.add(new DrawerItem("Logout", R.drawable.ic_action_good));
          //  System.out.println("++++++++++++++++++++++++++++++++");
 
            adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                        dataList);
 
            mDrawerList.setAdapter(adapter);
 
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
 
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
 
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                        R.drawable.ic_drawer, R.string.drawer_open,
                        R.string.drawer_close) {
                  public void onDrawerClosed(View view) {
                        getActionBar().setTitle(mTitle);
                        invalidateOptionsMenu(); // creates call to
                                                                  // onPrepareOptionsMenu()
                  }
 
                  public void onDrawerOpened(View drawerView) {
                        getActionBar().setTitle(mDrawerTitle);
                        invalidateOptionsMenu(); // creates call to
                                                                  // onPrepareOptionsMenu()
                  }
            };
 
            mDrawerLayout.setDrawerListener(mDrawerToggle);
 
            if (savedInstanceState == null) {
                  SelectItem(0);
            }
 
      }
      
      //when this activity is started again with new intent and the activity has already been created, onNewIntent will be called instead of onCreate
      protected void onNewIntent(Intent intent) {
    	  super.onNewIntent(intent);
    	  setIntent(intent);
    	  load = false;
    	  intent = getIntent();
          rooms.clear();
          c.changeHandler(handler);
          main = true;
          int mid = 1;
          int sid = 1;
          long currenttime;
          String currentTimeStamp;

          currenttime = System.currentTimeMillis() / 1000L;
          long time = currenttime - (60 * 25);
          System.out.println("UnixTime: " + currenttime);
          Date date = new Date(currenttime*1000L); // *1000 is to convert seconds to milliseconds
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
          //sdf.setTimeZone(TimeZone.getTimeZone("GMT-1")); // give a timezone reference for formating (see comment at the bottom
          currentTimeStamp = sdf.format(date);
          System.out.println(currentTimeStamp);
          
         
          

          roomSize = intent.getExtras().getInt("size");
			System.out.println("grootte room array: " + roomSize);
			
			for(int i = 0; i < roomSize; i++){
				String x = String.valueOf(i);
				rooms.add(intent.getStringExtra(x).toString());
				System.out.println(rooms.get(i).toString());
			}



          // Initializing
          dataList = new ArrayList<DrawerItem>();
          mTitle = mDrawerTitle = getTitle();
          mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
          mDrawerList = (ListView) findViewById(R.id.left_drawer);

          mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                      GravityCompat.START);
//System.out.println("++++++++++++++++++++++++++++++++");
          // Add Drawer Item to dataList
          //dataList.add(new DrawerItem("Import & Export",R.drawable.ic_action_import_export));
          dataList.add(new DrawerItem("Algemeen", R.drawable.ic_action_group));
          for(int i = 0 ; i < roomSize ; i++){
          	//String x = "Room" + i;
          	String x = rooms.get(i).toString();
              dataList.add(new DrawerItem(x, R.drawable.ic_action_group));
              //System.out.println("Room : "+ x);
          }
          //dataList.add(new DrawerItem("Test Room", R.drawable.ic_action_group));
          dataList.add(new DrawerItem("Over", R.drawable.ic_action_about));
          dataList.add(new DrawerItem("Instellingen", R.drawable.ic_action_settings));
          dataList.add(new DrawerItem("Scripts", R.drawable.ic_action_settings));
          //dataList.add(new DrawerItem("Charts", R.drawable.ic_action_settings));
          dataList.add(new DrawerItem("History", R.drawable.ic_action_settings));
          dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));
          dataList.add(new DrawerItem("Logout", R.drawable.ic_action_good));
        //  System.out.println("++++++++++++++++++++++++++++++++");

          adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                      dataList);

          mDrawerList.setAdapter(adapter);

          mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

          getActionBar().setDisplayHomeAsUpEnabled(true);
          getActionBar().setHomeButtonEnabled(true);

          mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                      R.drawable.ic_drawer, R.string.drawer_open,
                      R.string.drawer_close) {
                public void onDrawerClosed(View view) {
                      getActionBar().setTitle(mTitle);
                      invalidateOptionsMenu(); // creates call to
                                                                // onPrepareOptionsMenu()
                }

                public void onDrawerOpened(View drawerView) {
                      getActionBar().setTitle(mDrawerTitle);
                      invalidateOptionsMenu(); // creates call to
                                                                // onPrepareOptionsMenu()
                }
          };

          mDrawerLayout.setDrawerListener(mDrawerToggle);
          
          SelectItem(0);

     
    	}
      
     
 
      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
      }
 
      public void SelectItem(int possition) {
    	 //c = (Connector) intent.getSerializableExtra("connector1");
 
            /*Fragment fragment = null;
            Bundle args = new Bundle();*/
            if(possition == 0){ //ALGEMEEN
            	positie = possition;
                fragment = new FragmentAlgemeen();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
                fragment.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                            .commit();
     
                mDrawerList.setItemChecked(possition, true);
                setTitle(dataList.get(possition).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);
            }
            if(possition > 0){ //ROOMS
                for(int i = 0 ; i < roomSize; i++){
                    if(possition == i+1){
                    	
                    	List<Object> params = new ArrayList<Object>();
                    	selectedRoom = dataList.get(possition).getItemName();
                    	System.out.println("Get modules of : "+ selectedRoom);
         	            params.add(selectedRoom);
         	            System.out.println("Parsing request method: getallmodulesinroom, in room : " + selectedRoom);
         	            for(int y = 0 ;y< params.size();y++){
             	            System.out.println("Room : " + selectedRoom + " with Parameter : " + params.get(y).toString());
         	            }
         	            positie = possition;

         	            cmd = c.ParsRequest("getAllModulesInRoom",params);
         	            System.out.println("cmd of getAllModulesInRoom  =  " + cmd);
         	            c.giveCommand(cmd);
  
                   
                        break;
                    }
                 }
            }
            if(possition == (roomSize + 1)){ //over
            	positie = possition;
                fragment = new FragmentAbout();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
                fragment.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                            .commit();
     
                mDrawerList.setItemChecked(possition, true);
                setTitle(dataList.get(possition).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);

            }
            if(possition == (roomSize + 2)){ //instellingen
            	positie = possition;
            	
            	List<Object> param = new ArrayList<Object>();
                cmd = c.ParsRequest("getAllModulesNotInARoom",param);
                System.out.println("cmd of getAllModulesNotInARoom  =  " + cmd);
               c.giveCommand(cmd);
      
            }
            if(possition == (roomSize + 3)){ //Scripts
            	positie = possition;
            	
                List<Object> param = new ArrayList<Object>();
                cmd = c.ParsRequest("getAllScripts",param);
                System.out.println("cmd of getAllScripts  =  " + cmd);
                c.giveCommand(cmd);
            	
       
    
            }

            if(possition == (roomSize + 4)){ //History
            	logs.clear();
            	singledevice.clear();
            	devicesWithLogs.clear();
           
            	
 	
                List<Object> params2 = new ArrayList<Object>();
                cmd = c.ParsRequest("getAllDevicesWithLogs", params2);
                System.out.println("cmd of getAllDevicesWithLogs = " + cmd);
                c.giveCommand(cmd);
               
            	positie = possition;
                
            }
            
            if(possition == (roomSize + 5)){ //help
            	positie = possition;
                fragment = new FragmentHelp();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
                fragment.setArguments(args);
               
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                            .commit();
     
                mDrawerList.setItemChecked(possition, true);
                setTitle(dataList.get(possition).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);
   
            }
            if(possition ==(roomSize + 6)){ //Logout
            	
            	sendMessage1();
            }
 
      }
  	public void sendMessage1() {
	    Intent intent = new Intent(MainActivity.this, IntroActivity.class);
	    startActivity(intent);
	    this.finish();
	}
  	
    public void loadingDialog(){
    	//AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
    	final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Please wait ...", "Downloading Image ...", true);  
    		ringProgressDialog.setCancelable(true);

    	        ringProgressDialog.setTitle("Refreshing");
    	        ringProgressDialog.setMessage("Loading new Data");
    	        ringProgressDialog.setCancelable(false);
    	        /*ringProgressDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DO NOT TOUCH",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				            dialog.dismiss();
				        }
				    });*/
    	        ringProgressDialog.show();
    	        final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                    	ringProgressDialog.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 15000); // after 15 second (or 15000 miliseconds), the task will be active.
    	        test = ringProgressDialog;
    }
    public static void closeDialog(){
    	test.dismiss();
    	load = false;
    }
    
  
 
      @Override
      public void setTitle(CharSequence title) {
            mTitle = title;
            getActionBar().setTitle(mTitle);
      }
 
      @Override
      protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            mDrawerToggle.syncState();
      }
 
      @Override
      public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            // Pass any configuration change to the drawer toggles
            mDrawerToggle.onConfigurationChanged(newConfig);
      }
 
      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
            // The action bar home/up action should open or close the drawer.
            // ActionBarDrawerToggle will take care of this.
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                  return true;
            }
 
            return false;
      }
      
        private class DrawerItemClickListener implements
                  ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                        long id) {
            	
                  SelectItem(position);
 
            }
      }
        
        public static int randInt(int min, int max) {

            // NOTE: Usually this should be a field rather than a method
            // variable so that it is not re-seeded every call.
            Random rand = new Random();

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            int randomNum = rand.nextInt((max - min) + 1) + min;

            return randomNum;
        }
 
}