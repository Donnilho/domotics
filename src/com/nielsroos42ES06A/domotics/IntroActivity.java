package com.nielsroos42ES06A.domotics;


import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntroActivity extends Activity {
	String gebruiker;
	String wachtwoord;
	String cmd;
    private Connector c = null;
    Parser p = new Parser();
    
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
					switch (msg.what) {
					case 1://true
						System.out.println(msg.obj);
						break;
					case 2:
						//serverreturn.setText((CharSequence) msg.obj);
						System.out.println(msg.obj);
						break;
				
					}
				System.out.println(msg.obj);
				

				super.handleMessage(msg);
			}
			
		};	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		Button login = (Button)findViewById(R.id.button1);
		
		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { //whats going to be executed once the button is clicked
				EditText username=(EditText) findViewById(R.id.usernametext);
				EditText password=(EditText) findViewById(R.id.passwordtext);

				
				gebruiker = username.getText().toString();
				wachtwoord = password.getText().toString();
				
				
				System.out.println(gebruiker);
				System.out.println(wachtwoord);
				
			    c = new Connector(handler);
				c.start();
				c.giveCommand("Domotics App online");
				
	            List<Object> params = new ArrayList<Object>();
	            params.add(gebruiker);
	            params.add(wachtwoord);
	            cmd = p.ParsRequest("Login", params);
	            c.giveCommand(cmd);
	            sendMessage1(v);
			}
		});
	}
	
	public void sendMessage1(View v) {
	    Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
}
