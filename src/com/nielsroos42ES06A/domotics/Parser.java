package com.nielsroos42ES06A.domotics;
import com.thetransactioncompany.jsonrpc2.*;
import java.util.*;
public class Parser {

	public void ParsRequest(String cmd, List<Object>params){
	JSONRPC2Notification ReqOut = new JSONRPC2Notification(cmd, params);
	String jsons = ReqOut.toJSONString();
	System.out.println("J's Son : "+jsons);
	}
}
