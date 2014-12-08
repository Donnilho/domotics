package com.nielsroos42ES06A.domotics;
import com.thetransactioncompany.jsonrpc2.*;
import java.util.*;
public class Parser {
	int ID;

	public String ParsRequest(String cmd, List<Object>params){
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
}
