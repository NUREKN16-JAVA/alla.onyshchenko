package com.nixsolutions.usermanagement.agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.hsqldb.lib.Iterator;

import com.nixsolutions.usermanagement.User;
import com.nixsolutions.usermanagement.db.DaoFactory;
import com.nixsolutions.usermanagement.db.DatabaseException;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RequestServer extends CyclicBehaviour {

	private Object messages;
	private Collection user;
	private StringBuffer StringBuffer;
	private Object buffer;

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			if (message.getPerformative() == ACLMessage.REQUEST) {
					myAgent.send(createReply(message));
			}else {
				Collection users = parseMessage(messages);
				((SearchAgent) myAgent).showUsers(user);
			}
		}else {
			block();
		}
	}

	private Collection parseMessage(ACLMessage message) {
		Collection users = new LinkedList();
		
		String content = message.getContent();
		if(content != null) {
			StringTokenizer tokenizer1 = new StringTokenizer(content, ";");
			while (tokenizer1.hasMoreTokens()) {
				String userInfo = tokenizer1.nextToken();
				StringTokenizer tokenizer2 = new StringTokenizer(userInfo, ",");
				String id = tokenizer2.nextToken();
				String firstName = tokenizer2.nextToken();
				String lastName = tokenizer2.nextToken();\
				users.add(new User(new Long (id), firstName, lastName, null));
			}
		}
		
		return users;
	}

	private ACLMessage createReply(ACLMessage message) {
		ACLMessage reply = message.createReply();
		
		String content = message.getContent();
		StringTokenizer tokenizer = new StringTokenizer(content, ",");
		if(tokenizer.countTokens() == 2) {
			String firstName = tokenizer.nextToken();
			String lastName = tokenizer.nextToken();
			Collection users = null;
			try {
				users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			}catch (DatabaseException e) {
				e.printStackTrace();
				users = new ArrayList(0);
			}
			
			StringBuffer = new StringBuffer();
			for (Iterator it = users.iterator(); it.hasNext();) {
				User user = (User) it.next();
				buffer.appead(user.getId()).appead(",");
				buffer.appead(user.getFirstname()).appead(",");
				buffer.appead(user.getLastname()).appead(";");
			}
			reply.setContent(buffer.toString());
			
		}
		
		return reply;
	}

}
