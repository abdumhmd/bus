package com.abd.abcrbts.abcrbts;

import java.io.*;
import java.util.*;
import hu.ozeki.*;

public class MyOzSmsClient extends OzSmsClient{

	public MyOzSmsClient(String host, int port) throws IOException, InterruptedException {
		super(host, port);
		// TODO Auto-generated constructor stub
	}	

	@Override
	public void doOnMessageAcceptedForDelivery(OzSMSMessage sms) {
		Date now = new Date();
		System.out.println(now.toString() + " Message accepted for delivery. ID: " + sms.messageId);
	}

	@Override
	public void doOnMessageDeliveredToHandset(OzSMSMessage sms) {
		Date now = new Date();
		System.out.println(now.toString() + " Message delivered to handset. ID: " + sms.messageId);
	}

	@Override
	public void doOnMessageDeliveredToNetwork(OzSMSMessage sms) {
		Date now = new Date();
		System.out.println(now.toString() + " Message delivered to network. ID: " + sms.messageId);
	}

	@Override
	public void doOnMessageDeliveryError(OzSMSMessage sms) {
		Date now = new Date();
		System.out.println(now.toString() + " Message could not be delivered. ID: " + sms.messageId + " Error message: " + sms.errorMessage + "\r\n");
	}

	@Override
	public void doOnMessageReceived(OzSMSMessage sms) {
		Date now = new Date();
		System.out.println(now.toString() + " Message received. Sender address: " + sms.sender + " Message text: " + sms.messageData  + " at: "+ sms.receivedDate);
	}

	@Override
	public void doOnClientConnectionError(int errorCode, String errorMessage) {
		Date now = new Date();
		System.out.println(now.toString() + " Message code: " + errorCode + ", Message: " + errorMessage);
	}
	
}
