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
      
      private Connector c = null;
      //Parser p = new Parser();
	  public int roomSize;
	  
	  public String cmd;
	  public ArrayList<Object> rooms = new ArrayList<Object>();
	  
      public void setRoomsize(int roomsize) {
		this.roomSize = roomsize;
	}

	public void setRooms(ArrayList<Object> rooms) {
		this.rooms = rooms;
	}



	final Handler handler = new Handler() {
			/*	private static final int HANDLER_PLAY = 1;
				private static final int HANDLER_PAUSE = 2;
				private static final int HANDLER_ARTIST = 3;
				private static final int HANDLER_TITLE = 4;
				private static final int HANDLER_CUR_TIME = 5;
				private static final int HANDLER_TOT_TIME = 6;
				private static final int HANDLER_NOT_CONNECTED = 7;*/

				@Override
				public void handleMessage(Message msg) {
					//TextView serverreturn = (TextView) findViewById(R.id.returntext);
	//				serverreturn.setHint("Message");
					//c.ParseResponse((String) msg.obj);
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
						case 99:
							
							//serverreturn.setText("Return2 : " + (CharSequence) msg.obj);
							c.ParseResponse((String) msg.obj);
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
            
            c = new Connector(handler);
            IntroActivity intro = new IntroActivity();
    		/*c.start();
    		List<Object> params = new ArrayList<Object>();
            cmd = c.ParsRequest("getAllRooms", params);
            c.giveCommand(cmd);*/
            
			//roomsize = c.getArraysize();
            //roomSize = intro.getSizerooms();
            roomSize = getIntent().getExtras().getInt("size");
           // rooms = (ArrayList<Object>) getIntent().getSerializableExtra("rooms");
            
			System.out.println("grootte room array: " + roomSize);
			
			for(int i = 0; i < roomSize; i++){
				System.out.println("FUCK");
				String x = String.valueOf(i);
				rooms.add(getIntent().getStringExtra(x).toString());
				System.out.println(rooms.get(i).toString());
		           //System.out.println("roomsvullen: " + intro.getLocalrooms().get(i).toString());
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
            //dataList.add(new DrawerItem("Message", R.drawable.ic_action_email));
            //dataList.add(new DrawerItem("Likes", R.drawable.ic_action_good));
            //dataList.add(new DrawerItem("Games", R.drawable.ic_action_gamepad));
            //dataList.add(new DrawerItem("Lables", R.drawable.ic_action_labels));
            //dataList.add(new DrawerItem("Search", R.drawable.ic_action_search));
            //dataList.add(new DrawerItem("Cloud", R.drawable.ic_action_cloud));
            //dataList.add(new DrawerItem("Camara", R.drawable.ic_action_camera));
            //dataList.add(new DrawerItem("Video", R.drawable.ic_action_video));
            //dataList.add(new DrawerItem("Groups", R.drawable.ic_action_group));
            //dataList.add(new DrawerItem("Import & Export",R.drawable.ic_action_import_export));
            dataList.add(new DrawerItem("Algemeen", R.drawable.ic_action_group));
            for(int i = 0 ; i < roomSize ; i++){
            	//String x = "Room" + i;
            	String x = rooms.get(i).toString();
                dataList.add(new DrawerItem(x, R.drawable.ic_action_group));
                System.out.println("Room : "+ x);
            }
            dataList.add(new DrawerItem("Test Room", R.drawable.ic_action_group));
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
 
            Fragment fragment = null;
            Bundle args = new Bundle();
            if(possition == 0){
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition > 0){
                for(int i = 0 ; i < roomSize; i++){
                    if(possition == i+1){
                        fragment = new FragmentOne();
                        args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                                    .getItemName());
                        args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                                    .getImgResID());
                        break;
                    }
                 }
            }
            if(possition == (roomSize + 1)){ //testroom
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition == (roomSize + 2)){ //over
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition == (roomSize + 3)){ //instellingen
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition == (roomSize + 4)){ //netwerk
                fragment = new FragmentSocket();
                args.putString(FragmentSocket.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentSocket.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            if(possition == (roomSize + 5)){ //help
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
            }
            
            
            
            
         /*   
            switch (possition) {
            case 0:
                  fragment = new FragmentOne();
                  args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            case 1:
                  fragment = new FragmentTwo();
                  args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            case 2:
                  fragment = new FragmentThree();
                  args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            case 3:
                  fragment = new FragmentOne();
                  args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            case 4:
                  fragment = new FragmentTwo();
                  args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            case 5:
                  fragment = new FragmentThree();
                  args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            case 6:
                  fragment = new FragmentTwo();
                  args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            case 7: //network
                fragment = new FragmentSocket();
                args.putString(FragmentSocket.ITEM_NAME, dataList.get(possition)
                            .getItemName());
                args.putInt(FragmentSocket.IMAGE_RESOURCE_ID, dataList.get(possition)
                            .getImgResID());
                break;
            case 8:
                  fragment = new FragmentThree();
                  args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                              .getItemName());
                  args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                              .getImgResID());
                  break;
            default:
                  break;
            }
 */
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