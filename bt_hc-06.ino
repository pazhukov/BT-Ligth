// Работа с Bluetooth (HC-06) с использованием SoftwareSerial
// RX - цифровой вывод 10 (необходимо соединить с выводом TX другого устройства)
// TX - цифровой вывод 11 (необходимо соединить с выводом RX другого устройства)
// Подробности https://www.arduino.cc/en/Tutorial/SoftwareSerialExample
//
// Автор Pazhukov Max
// Дата создания 05/10/2015

#include <SoftwareSerial.h>

SoftwareSerial btSerial(10, 11); // RX, TX
int ledpin = 13; 
int BluetoothData;

void setup() {
  // put your setup code here, to run once:
  btSerial.begin(9600);
  btSerial.println("Use 1 or 0 for switch LED #13");
  pinMode(ledpin,OUTPUT);
}

void loop() {
   if (btSerial.available()){ 
    BluetoothData = btSerial.read();
    if(BluetoothData == '1'){   
      digitalWrite(ledpin, HIGH);
      btSerial.println("LED #13 ON");
    }
    if(BluetoothData == '0'){
      digitalWrite(ledpin, LOW);
      btSerial.println("LED #13 OFF");
    }
  }
  delay(100);
}
