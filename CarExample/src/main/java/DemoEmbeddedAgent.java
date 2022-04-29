package src.main.java;

import embedded.mas.bridges.jacamo.EmbeddedAgent;
import embedded.mas.bridges.jacamo.DefaultDevice;
import embedded.mas.bridges.jacamo.DemoDevice;
import embedded.mas.bridges.javard.Arduino4EmbeddedMas;

import jason.asSyntax.Atom;


public class DemoEmbeddedAgent extends EmbeddedAgent {
	
	@Override
	public void initAg() {
		super.initAg();
	}

	@Override
	protected void setupSensors() {
		Arduino4EmbeddedMas arduino = new Arduino4EmbeddedMas("/dev/ttyUSB0",9600);
		arduino.openConnection();
		
		MyDemoDevice device = new MyDemoDevice(new Atom("arduino1"), arduino);
		this.addSensor(device);	
	}
	
	
	// #Acoes que o agente eh capaz de realizar
	public boolean lightOn() {
		System.out.println("Acendendo Led...");
		//MyDemoDevice device = (MyDemoDevice)this.devices.get(0);
        MyDemoDevice device = (MyDemoDevice)this.getDevices().get(0);
		device.doLightOn();
		return true;
	}
	public boolean lightOff() {
		System.out.println("Apagando Led...");
		MyDemoDevice device =(MyDemoDevice)this.getDevices().get(0);
		device.doLightOff();
		return true;
	}
	public boolean defaultInternalAction() {
		//print de monitoramento da ação
		System.out.println("Doing default internal action...");
		
		//seleciona os devices que serão utilizados
		MyDemoDevice device1 =(MyDemoDevice)this.getDevices().get(0);
		//chama as funções especificas que a ação precisa para ser realizada
		device1.doActuator1On();
		
		//MyDemoDevice device2 =(MyDemoDevice)this.getDevices().get(1);
		//device2.doDefaultActuation();
		
		return true;
	}
	
	public boolean frente() {
		//print de monitoramento da ação
		System.out.println("Fazendo o carro andar para frente");
		
		//seleciona os devices e suas atuacoes que que serão utilizados na acao
		MyDemoDevice device1 =(MyDemoDevice)this.getDevices().get(0);
		device1.frente();
		return true;
	}
	public boolean tras() {
		System.out.println("Fazendo o carro andar para trás");
		MyDemoDevice device1 =(MyDemoDevice)this.getDevices().get(0);
		device1.tras();
		return true;
	}
	public boolean esquerda() {
		System.out.println("Fazendo o carro andar para esquerda");
		MyDemoDevice device1 =(MyDemoDevice)this.getDevices().get(0);
		device1.esquerda();
		return true;
	}
	public boolean direita() {
		System.out.println("Fazendo o carro andar para direita");
		MyDemoDevice device1 =(MyDemoDevice)this.getDevices().get(0);
		device1.direita();
		return true;
	}
	public boolean para() {
		System.out.println("Fazendo o carro andar para para");
		MyDemoDevice device1 =(MyDemoDevice)this.getDevices().get(0);
		device1.para();
		return true;
	}

	// public boolean frente() {
	// 	//print de monitoramento da ação
	// 	System.out.println("Fazendo o carro andar para frente");
		
	// 	//seleciona os devices e suas atuacoes que que serão utilizados na acao
	// 	MyDemoDevice device1 =(MyDemoDevice)this.getDevices().get(0);
	// 	device1.doMotor1frente();
	// 	device1.doMotor2frente();
		
	// 	return true;
	// }
}
