package com.cfranc.irc.server;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.cfranc.irc.IfClientServerProtocol;

public class BroadcastThread extends Thread {
	
	private static String sendMessage;
	

	public static String getSendMessage() {
		return sendMessage;
	}

	public static HashMap<User, ServerToClientThread> clientTreadsMap = new HashMap<User, ServerToClientThread>();
	static {
		Collections.synchronizedMap(clientTreadsMap);
	}

	public static boolean addClient(User user, ServerToClientThread serverToClientThread) {
		boolean res = true;
		if (clientTreadsMap.containsKey(user)) {
			res = false;

		} else {
			sendAddMessageToHimself(serverToClientThread);
			clientTreadsMap.put(user, serverToClientThread);
			sendAddMessageToAll(user);
		}
		return res;
	}

	public static void sendMessage(User sender, String msg) {
		Collection<ServerToClientThread> clientTreads = clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator = clientTreads.iterator();
		while (receiverClientThreadIterator.hasNext()) {
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();
			
			//clientThread.post("#" + sender.getLogin() + "#" + msg);
			//System.out.println("sendMessage : " + "#" + sender.getLogin() + "#" + msg);
			sendMessage=IfClientServerProtocol.SEPARATOR+msg+IfClientServerProtocol.SEPARATOR;
			clientThread.post(sendMessage);
			System.out.println(sendMessage);
		}
	}

	public static void sendAddMessageToAll(User sender) {
		Collection<ServerToClientThread> clientTreads = clientTreadsMap.values();
		Iterator<ServerToClientThread> receiverClientThreadIterator = clientTreads.iterator();
		while (receiverClientThreadIterator.hasNext()) {
			ServerToClientThread clientThread = (ServerToClientThread) receiverClientThreadIterator.next();			
			//clientThread.post("#+#" + sender.getLogin());
			//System.out.println("#+#" + sender.getLogin());
			clientThread.post(IfClientServerProtocol.ADD + sender.getLogin());
			System.out.println(IfClientServerProtocol.ADD + sender.getLogin());
		}
	}

	public static void sendAddMessageToHimself(ServerToClientThread newUserThread) {
		Collection<User> clientUsers = clientTreadsMap.keySet();
		Iterator<User> receiverClientUserIterator = clientUsers.iterator();
		while (receiverClientUserIterator.hasNext()) {
			User clientUser = (User) receiverClientUserIterator.next();
//			newUserThread.post("#+#" + clientUser.getLogin());
//			System.out.println("#+#" + clientUser.getLogin());
			newUserThread.post(IfClientServerProtocol.ADD + clientUser.getLogin());
			System.out.println(IfClientServerProtocol.ADD + clientUser.getLogin());
		}
	}

	public static void removeClient(User user) {
		clientTreadsMap.remove(user);
	}

	public static boolean accept(User user) {
		boolean res = true;
		if (clientTreadsMap.containsKey(user)) {
			res = false;
		}
		return res;
	}
}
