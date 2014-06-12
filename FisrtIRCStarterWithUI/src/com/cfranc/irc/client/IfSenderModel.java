package com.cfranc.irc.client;

import java.io.IOException;

public interface IfSenderModel {

	public abstract void setMsgToSend(String msgToSend);
	
	public abstract void quitServer() throws IOException;

}