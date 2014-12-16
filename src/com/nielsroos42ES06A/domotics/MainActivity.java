package com.nielsroos42ES06A.domotics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minidev.json.JSONObject;
 
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.ListView;
import android.widget.TextView;
 
public class MainActivity extends Activity {
 
      private DrawerLayout mDrawerLayout;
      private ListView mDrawerList;
      private ActionBarDrawerToggle mDrawerToggle;
 
      private CharSequence mDrawerTitle;
      private CharSequence mTitle;
      CustomDrawerAdapter adapter;
 
      public static List<DrawerItem> dataList;
	  public int roomSize;
	  public static int positie;
	  public String cmd;
	  public ArrayList<Object> rooms = new ArrayList<Object>();
      public Intent intent;
      public static Connector c;
      public String selectedRoom;
      public static  ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
      public static boolean next = false;
      public  Fragment fragment = null;
      public  Bundle args = new Bundle();
     // public ArrayList<Object> moduleinfo = new ArrayList<Object>();
	  



	final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					System.out.println("Main Activity msg.what : "+msg.what);
						switch (msg.what) {
						case 10:
							modules.clear();
							System.out.println("Hello inside case 10 mainactivity");
							for(int i = 0; i < msg.arg1; i++){
								modules.add( ((ArrayList<ArrayList>) msg.obj).get(i));
								System.out.println("Module ID MAIN : " + i);
								for(int j = 0; j < msg.arg2 ; j++){
									String x = modules.get(i).get(j).toString();
									System.out.println("Switch case 10 Main : " + x);
								}
							}

							 fragment = new FragmentOne();
		                        args.putString(FragmentOne.ITEM_NAME, dataList.get(positie)
		                                    .getItemName());
		                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(positie)
		                                    .getImgResID());
		                        fragment.setArguments(args);
		                        
		                        FragmentManager frgManager = getFragmentManager();
		                        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
		                                    .commit();
		             
		                        mDrawerList.setItemChecked(positie, true);
		                        setTitle(dataList.get(positie).getItemName());
		                        mDrawerLayout.closeDrawer(mDrawerList);
							
							break;
						case 38:
							AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
							alertDialog.setTitle("Alert");
							String x = (String) msg.obj;
							alertDialog.setMessage(x);
							alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
							    new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) {
							            dialog.dismiss();
							        }
							    });
							alertDialog.show();
							
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
            intent = getIntent();

            c.changeHandler(handler);

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
 System.out.println("++++++++++++++++++++++++++++++++");
            // Add Drawer Item to dataList
            //dataList.add(new DrawerItem("Import & Export",R.drawable.ic_action_import_export));
            dataList.add(new DrawerItem("Algemeen", R.drawable.ic_action_group));
            for(int i = 0 ; i < roomSize ; i++){
            	//String x = "Room" + i;
            	String x = rooms.get(i).toString();
                dataList.add(new DrawerItem(x, R.drawable.ic_action_group));
                System.out.println("Room : "+ x);
            }
            //dataList.add(new DrawerItem("Test Room", R.drawable.ic_action_group));
            dataList.add(new DrawerItem("Over", R.drawable.ic_action_about));
            dataList.add(new DrawerItem("Instellingen", R.drawable.ic_action_settings));
            dataList.add(new DrawerItem("Netwerk", R.drawable.ic_action_settings));
            dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));
            System.out.println("++++++++++++++++++++++++++++++++");
 
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
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
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
                       /* fragment = new FragmentOne();
                        args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                                    .getItemName());
                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                                    .getImgResID());*/
                   
                        break;
                    }
                 }
            }
            if(possition == (roomSize + 1)){ //over
                fragment = new FragmentTwo();
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
                fragment = new FragmentTwo();
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
            if(possition == (roomSize + 3)){ //netwerk
                fragment = new FragmentSocket();
                args.putString(FragmentSocket.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentSocket.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
                
                fragment.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                            .commit();
     
                mDrawerList.setItemChecked(possition, true);
                setTitle(dataList.get(possition).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);
    
            }
            if(possition == (roomSize + 4)){ //help
                fragment = new FragmentTwo();
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
            
            /*fragment.setArguments(args);
            FragmentManager frgManager = getFragmentManager();
            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
 
            mDrawerList.setItemChecked(possition, true);
            setTitle(dataList.get(possition).getItemName());
            mDrawerLayout.closeDrawer(mDrawerList);*/
 
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
 
}