#include <dht.h>

#define SensorPin A0
#define DHT_PIN A5

float sensorValue = 0;
const float MAX_MOISTURE_VALUE = 1023.00;

const int MOTOR_PIN = 3;
dht dhtSensor;

float getMoisturePercetage(int value) {
  return 100 - (value / MAX_MOISTURE_VALUE) * 100;
}

void fillWithWaterForTime(int seconds) {

  digitalWrite(MOTOR_PIN, HIGH);
  delay(seconds * 1000);
  digitalWrite(MOTOR_PIN, LOW);
}

void setup() {
  Serial.begin(9600); // init serial communication same as python code
  
}

int command;
const int OK = 200;
int tresholdLevel;

void loop() {
  
  if (Serial.available()) {
//    char input[] = Serial.read();
    String input = Serial.readString();
    
    if (input.length() == 1) {
      command = input[0] - '0';
    } else {
      command = input[0] - '0';
      tresholdLevel = input.substring(2).toInt();
      Serial.println(tresholdLevel);
    }    
  }
  
  if (command == 1) {
    
    float value = analogRead(SensorPin);
    int output = map(value, 550, 0, 0, 100);
    
    dhtSensor.read11(DHT_PIN);
    float humidity = dhtSensor.humidity;
    float temperature = dhtSensor.temperature;
    
    String response = String(output);
    response += " " +  String((int)temperature) + " " + String((int)humidity);
    
    Serial.println(response);
  
    delay(5000);
    
    command = 0;
  } else if (command == 2) {
     float value = analogRead(SensorPin);
     int output = map(value, 550, 0, 0, 100);
     
     if (output < tresholdLevel) {
       fillWithWaterForTime(100);
       
       dhtSensor.read11(DHT_PIN);
       float humidity = dhtSensor.humidity;
       float temperature = dhtSensor.temperature;
       
       String response = "Filled with water|";
       response += String(output) + "|" + String((int)temperature) + "|" + String((int)temperature);
       Serial.println(response);  
     } else {
       dhtSensor.read11(DHT_PIN);
       float humidity = dhtSensor.
       float temperature = dhtSens
      
       String response = "No need for refill|";
       response += String(output) 
       Serial.println(response);  
     }
     command = 0;
  }
}
