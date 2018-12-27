package com.nixsolutions.usermanagement.agent;

import java.core.AID;
import java.core.behaviours.Behaviour;

import jade.lang.acl.ACLMessage;

public class SearchRequestBehavior extends Behaviour {
	
	private AID[] aids;
	private String firstName;
	private String lastName;
	private Object myAgent;

	public SearchRequestBehavior(AID[] aids, String firstName, String lastName) {
		this.firstName = firstName;
		this.firstName = lastName;
		this.aids = aids;
	}
	
	public void action() {
		if (aids != null) {
			ACLMessage message = new ACLMessage (ACLMessage.REQUEST);
			message.setContent(firstName + "," + lastName);
			for (int i=0; i<aids.length; i++) {
				message.addReceiver(aids[i]);
			}
			myAgent.send(message);
		}
	}
	
	public boolean done() {
		return true;
	}
	
}
