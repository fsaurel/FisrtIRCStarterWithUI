package com.cfranc.irc.ui;

import static org.junit.Assert.fail;

import java.net.Socket;
import org.junit.Before;
import org.junit.Test;
import com.cfranc.irc.server.BroadcastThread;
import com.cfranc.irc.server.ServerToClientThread;
import com.cfranc.irc.server.User;
 
public class TU {

	User userHub = new User("HUB", "HH");
	User userFred = new User("FRED", "aa");

	Socket socket = new Socket();
	ServerToClientThread serverToClientThread = new ServerToClientThread(userHub, socket);

	@Before
	public void setUpBeforeClass() throws Exception {
		userHub = new User("HUB", "HH");
	}

	@Test
	public void test1() {
		if (BroadcastThread.addClient(userHub, serverToClientThread)) {
			System.out.println("Test Utilisateur HUB ajouté OK");
		} else {
			fail("Test Utilisateur HUB ajouté KO");
		}
		
		if (BroadcastThread.clientTreadsMap.containsKey(userHub)) {
			System.out.println("Utilisateur HUB ajouté:OK");
		} else {
			fail("Utilisateur HUB ajouté : KO");
		}
		
		if (!BroadcastThread.clientTreadsMap.containsKey(userFred)) {
			System.out.println("Utilisateur Fred absent:OK");
		} else {
			fail("Utilisateur Fred absent:KO");
		}

	}
	


	@Test
	public void test2() {
		BroadcastThread.sendMessage(userHub, "Message HUB");

		String msg = BroadcastThread.getSendMessage();
		if (msg.equals("#Message HUB#")) {
			System.out.println("Envoi du message :Message HUB -->OK");
		} else {
			fail("Envoi du message :Message HUB -->KO");

		}
		;

	}

}
