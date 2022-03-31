//pinos de controle dos sencores ultrassonicos
const int TRIG_FRENTE = 6; 
const int ECHO_FRENTE = 7;
const int TRIG_ESQ = 8; 
const int ECHO_ESQ = 9;
const int TRIG_DIR = 10; 
const int ECHO_DIR = 11;

//pinos de controle do motor 1(IN1 e IN2)  motor 2 (IN3 e IN4) 
const int IN_1 = 1;
const int IN_2 = 2;
const int IN_3 = 3;
const int IN_4 = 4;

const int LIGHT = 13;

#include<Embedded_Protocol_2.h> // biblioteca para envio de crenças
Communication com;

#include <Ultrasonic.h>
Ultrasonic sensorFrente(TRIG_FRENTE,ECHO_FRENTE);
Ultrasonic sensorEsq(TRIG_ESQ,ECHO_ESQ);
Ultrasonic sensorDir(TRIG_DIR,ECHO_DIR); 

// Crenças
int lightState = 0;
String carState = "parado";
float distanciaFrente = 0;
float distanciaEsq = 0;
float distanciaDir = 0;



//Classe para facilitar o uso da ponte H L298N na manipulação dos motores na função Setup e Loop.
class DCMotor {  
  int spd = 255, pin1, pin2;
  public:  
    void Pinout(int in1, int in2){ // Pinout é o método para a declaração dos pinos que vão controlar o objeto motor
      pin1 = in1;
      pin2 = in2;
      pinMode(pin1, OUTPUT);
      pinMode(pin2, OUTPUT);
      }   
    void Speed(int in1){ // Speed é o método que irá ser responsável por salvar a velocidade de atuação do motor
      spd = in1;
      }     
    void Forward(){ // Forward é o método para fazer o motor girar para frente
      analogWrite(pin1, spd);
      digitalWrite(pin2, LOW);
      }   
    void Backward(){ // Backward é o método para fazer o motor girar para trás
      digitalWrite(pin1, LOW);
      analogWrite(pin2, spd);
      }
    void Stop(){ // Stop é o metodo para fazer o motor ficar parado.
      digitalWrite(pin1, LOW);
      digitalWrite(pin2, LOW);
      }
   };

   DCMotor MotorEsq, MotorDir;
   
   
   
   
void setup() {
  MotorEsq.Pinout(IN_1,IN_2);
  MotorDir.Pinout(IN_3,IN_4);
  
  MotorEsq.Speed(200); // A velocidade do motor pode variar de 0 a 255, onde 255 é a velocidade máxima.
  MotorDir.Speed(200);
  
  pinMode(LIGHT,OUTPUT);
  digitalWrite(LIGHT,1);  //light starts off
  Serial.begin(9600);

  delay(1000); //wait 30 seconds (to set up the multi-agent system)
}


void loop() 
{
  //Açoes-----------------------------------------------------------------------------------------------------------
  while(Serial.available() > 0){ //check whether there is some information from the serial (possibly from the agent)
     String s = Serial.readString();
     
     if(s.equals("lightOn")){ //if the agent sends "light_on", then switch the light on
        digitalWrite(LIGHT,1); 
        lightState = 1; 
     }
        
     if(s.equals("lightOff")){
      digitalWrite(LIGHT,0); 
      lightState = 0;  
     }
  
    if(s.equals("forward")){
      MotorEsq.Forward(); // Comando para o carrro ir para frente
      MotorDir.Forward();
      carState = "frente"; 
     }
 
    if(s.equals("backward")){
      MotorEsq.Backward(); // Comando para o carro ir para trás
      MotorDir.Backward();
      carState = "tras"; 
     } 
 
    if(s.equals("stop")){
      MotorEsq.Stop(); // Comando para o carro parar
      MotorDir.Stop();
      carState = "parado";
     }     
   }  
  
   //Crenças----------------------------------------------------------------------------------------------------------
    distanciaFrente = sensorFrente.Ranging(CM);// ultrassom.Ranging(CM) retorna a distancia em centímetros(CM) ou polegadas(INC)
    distanciaEsq = sensorEsq.Ranging(CM);
    distanciaDir = sensorDir.Ranging(CM); 

    com.startBelief("distanceFrente");
    com.beliefAdd(distanciaFrente);
    com.endBelief();
    com.startBelief("distanciaEsq");
    com.beliefAdd(distanciaEsq);
    com.endBelief();
    com.startBelief("distanceDir");
    com.beliefAdd(distanciaDir);
    com.endBelief();
    
    com.startBelief("lightState");
    com.beliefAdd(lightState);
    com.endBelief();
    
    com.startBelief("carState");
    com.beliefAdd(carState);
    com.endBelief();

    com.sendMessage();
     delay(2000);
}
