package src.main.java;

import java.util.Collection;

import embedded.mas.bridges.jacamo.JSONWatcherDevice;
import embedded.mas.bridges.jacamo.IPhysicalInterface;
import jason.asSyntax.Atom;
import jason.asSyntax.Literal;


/**
 * This device enables two actions: lightA and lightB, that are meant to turn on different lights in the physical device.
 * 
 * The actions lightA and lightB send to the microcontroller the messages "light_a" and "light_b". The microcontroller is expected to properly handle them,
 * turning on the light. A or B accordingly.
 * 
 * The names of the actions (lightA and lightB) are different from the messages sent to microcontroller ("light_a" and "light_b") to illustrate the 
 * decoupling between the agent level (the actions) and the physical level (the handling of messages). Though actions names and messages could be equal.
 *
 */

public class MyDemoDevice extends JSONWatcherDevice {

	public MyDemoDevice(Atom id, IPhysicalInterface microcontroller) {
		super(id, microcontroller);
	}

	@Override
	public boolean execEmbeddedAction(String actionName, Object[] args) {
		if(actionName.equals("lightOn")) 
			return doLightOn();
		else 
            return false;
	}
	
	public boolean doLightOn() {
		this.microcontroller.write("light_on");
		return true;
	}
	public boolean doLightOff() {
		this.microcontroller.write("light_off");
		return true;
	}
	public boolean doActuator1On() {
		this.microcontroller.write("A");
		return true;
	}
	public boolean doActuator1Off() {
		this.microcontroller.write("B");
		return true;
	}
	public boolean doActuator2On(){
		this.microcontroller.write("C");
		return true;
	}
	public boolean doActuator2Off(){
		this.microcontroller.write("D");
		return true;
	}

	public boolean frente(){
		this.microcontroller.write("f");
		return true;
	}
	public boolean tras(){
		this.microcontroller.write("t");
		return true;
	}
	public boolean esquerda(){
		this.microcontroller.write("e");
		return true;
	}
	public boolean direita(){
		this.microcontroller.write("d");
		return true;
	}
	public boolean para(){
		this.microcontroller.write("p");
		return true;
	}

	
}
