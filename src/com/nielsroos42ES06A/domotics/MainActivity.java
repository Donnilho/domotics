package com.nielsroos42ES06A.domotics;

import java.util.ArrayList;
import java.util.List;

import net.minidev.json.JSONObject;
 
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
 
      List<DrawerItem> dataList;
    //  private int listsize = 4;
   // 1. get passed intent 
      
      //Parser p = new Parser();
	  public int roomSize;
	  
	  public String cmd;
	  public ArrayList<Object> rooms = new ArrayList<Object>();
      public Intent intent;
      public static Connector c;
      public String selectedRoom;
	  



	final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
				
						switch (msg.what) {
						case 3:
							//serverreturn.setText("Return1 : " + (CharSequence) msg.obj);
							/*c.ParseResponse((String) msg.obj);
							roomsize = c.getArraysize();
							System.out.println("grootte room array: " + roomsize);
							
							for(int i = 0; i < roomsize; i++){
						           rooms.add(c.getArrays().get(i));
						           System.out.println("roomsvullen: " + rooms.get(i).toString());
							}*/
							break;
						case 10:
							
							break;
						case 99:
							
							//serverreturn.setText("Return2 : " + (CharSequence) msg.obj);
							//c.ParseResponse((String) msg.obj);
							break;
					
						}
					System.out.println("default: " + msg.obj);
					

					super.handleMessage(msg);
				}
				
			};
			
			
	  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            intent = getIntent();

            c = (Connector) intent.getSerializableExtra("connector1");
            
            
            //c = new Connector(handler);
    		//c.start();
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
    	  c = (Connector) intent.getSerializableExtra("connector1");
 
            Fragment fragment = null;
            Bundle args = new Bundle();
            if(possition == 0){ //ALGEMEEN
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
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

         	            cmd = c.ParsRequest("getAllModulesInRoom",params);
         	            System.out.println("cmd of getAllModulesInRoom  =  " + cmd);
         	            c.giveCommand(cmd);
         	            
                    	
                        fragment = new FragmentOne();
                        args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                                    .getItemName());
                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                                    .getImgResID());
                        break;
                    }
                 }
            }
            if(possition == (roomSize + 1)){ //over
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition == (roomSize + 2)){ //instellingen
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition == (roomSize + 3)){ //netwerk
                fragment = new FragmentSocket();
                args.putString(FragmentSocket.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentSocket.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition == (roomSize + 4)){ //help
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
          
            fragment.setArguments(args);
            FragmentManager frgManager = getFragmentManager();
            frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                        .commit();
 
            mDrawerList.setItemChecked(possition, true);
            setTitle(dataList.get(possition).getItemName());
            mDrawerLayout.closeDrawer(mDrawerList);
 
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