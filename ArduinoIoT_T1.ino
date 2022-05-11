//문자열 싹 다 합쳐놓은 버전
#include <ArduinoBLE.h>
#include <Arduino_LSM6DS3.h>

BLEService sensorService("66df5109-edde-4f8a-a5e1-02e02a69cbd5");
//자이로(각속도)
BLEStringCharacteristic xSensorLevel("741c12b9-e13c-4992-8a5e-fce46dec0bff", BLERead | BLENotify,100); // 길이 변경
BLEStringCharacteristic ySensorLevel("baad41b2-f12e-4322-9ba6-22cd9ce09832", BLERead | BLENotify,15);
BLEStringCharacteristic zSensorLevel("5748a25d-1834-4c68-a49b-81bf3aeb2e50", BLERead | BLENotify,15);
//가속도
BLEStringCharacteristic axSensorLevel("3aae57bc-efe5-4a17-8f24-f610b1b985e5", BLERead | BLENotify,15);
BLEStringCharacteristic aySensorLevel("7ad8f8f6-2da4-45a1-adbd-ab5a83351426", BLERead | BLENotify,15);
BLEStringCharacteristic azSensorLevel("7cac39b1-89e0-47be-aaf4-30277f05d763", BLERead | BLENotify,15);
//Flex
BLEStringCharacteristic flex1Sensor("a9d33c96-c5cf-41c6-bec4-3e28305ae1ab", BLERead | BLENotify,15);
BLEStringCharacteristic flex2Sensor("112170b3-c9f0-4f4c-84fa-d440ce80ff06", BLERead | BLENotify,15);
BLEStringCharacteristic flex3Sensor("442b3f31-8d10-494d-bef4-85c0faa9d28a", BLERead | BLENotify,15);
BLEStringCharacteristic flex4Sensor("29e632b7-f055-4daa-beb3-f66635a38ad2", BLERead | BLENotify,15);
BLEStringCharacteristic flex5Sensor("00481010-9c4e-4ab5-9e37-d2641d6c7755", BLERead | BLENotify,15);

//flex sensor pin
int flexpin_5 = A7;
int flexpin_4 = A3;
int flexpin_3 = A2;
int flexpin_2 = A1;
int flexpin_1 = A0;

// last sensor data
float oldXLevel = 0;
float oldYLevel = 0;
float oldZLevel = 0;

float a_oldXLevel = 0;
float a_oldYLevel = 0;
float a_oldZLevel = 0;

int old_flex1 = 0;
int old_flex2 = 0;
int old_flex3 = 0;
int old_flex4 = 0;
int old_flex5 = 0;

long previousMillis = 0;

void setup() {
 Serial.begin(9600);
 
 while (!Serial);
 
 if (!IMU.begin()) {
  Serial.println("Failed to initialize IMU!");
  while (1);
 }
 
 if (!BLE.begin()) {
  Serial.println("starting BLE failed!");
  while (1);
 }

 BLE.setLocalName("Demo Gyroscope");
 BLE.setAdvertisedService(sensorService);
 sensorService.addCharacteristic(xSensorLevel);
 sensorService.addCharacteristic(ySensorLevel);
 sensorService.addCharacteristic(zSensorLevel);
 sensorService.addCharacteristic(axSensorLevel);
 sensorService.addCharacteristic(aySensorLevel);
 sensorService.addCharacteristic(azSensorLevel);
 sensorService.addCharacteristic(flex1Sensor);
 sensorService.addCharacteristic(flex2Sensor);
 sensorService.addCharacteristic(flex3Sensor);
 sensorService.addCharacteristic(flex4Sensor);
 sensorService.addCharacteristic(flex5Sensor);
 BLE.addService(sensorService);

 BLE.advertise();
 Serial.println("Bluetooth device active, waiting for connections...");
}

void updateGyroscopeLevel() {
 float x, y, z, ax, ay, az;
 
 if (IMU.gyroscopeAvailable() & IMU.accelerationAvailable()) {
  IMU.readGyroscope(x, y, z); // Gyro
  IMU.readAcceleration(ax, ay, az); // Acceleration
  int flex1 = analogRead(flexpin_1);  //센서값을 저장할 변수 설정
  int flex2 = analogRead(flexpin_2); 
  int flex3 = analogRead(flexpin_3);
  int flex4 = analogRead(flexpin_4);
  int flex5 = analogRead(flexpin_5);
//  flex1Sensor.writeValue(String(flex1)+'\t'+String(flex2)+'\t'+String(flex3)+'\t'+String(flex4)+'\t'+String(flex5));
//  ySensorLevel.writeValue(String(y));
//  xSensorLevel.writeValue(String(x)+'\t'+String(y)+'\t'+  String(z));
xSensorLevel.writeValue(String(x)+"\t"+String(y)+"\t"+String(z)+"\t"+String(ax)+"\t"+String(ay)+"\t"+String(az)+"\t"+String(flex1)+'\t'+String(flex2)+'\t'+String(flex3)+'\t'+String(flex4)+'\t'+String(flex5));

//axSensorLevel.writeValue(String(ax)+"\t"+String(ay)+"\t"+String(az));
//axSensorLevel.writeValue(String(ax));
  if (x != oldXLevel) {
   
   
//   xSensorLevel.writeValue(String(z));
   oldXLevel = x;
  }
 if (y != oldYLevel) {
  ySensorLevel.writeValue(String(y));
  oldYLevel = y;
 }
 
 if (z != oldZLevel) {
  zSensorLevel.writeValue(String(z));
  oldZLevel = z;
  }
 Serial.print("Gyro :");
 Serial.print(x);
 Serial.print('\t');
 Serial.print(y);
 Serial.print('\t');
 Serial.println(z);
 }
}
 
 void updateAccelerationLevel() {
 float ax, ay, az;
 
 if (IMU.accelerationAvailable()) {
  IMU.readAcceleration(ax, ay, az);

//  axSensorLevel.writeValue(String(ax)+"\t"+String(ay)+"\t"+String(az));
   if (ax != a_oldXLevel) {
  axSensorLevel.writeValue(String(ax));
  a_oldXLevel = ax;
   }
  
   if (ay != a_oldYLevel) {
    aySensorLevel.writeValue(String(ay));
    a_oldYLevel = ay;
   }
  
   if (az != a_oldZLevel) {
    azSensorLevel.writeValue(String(az));
    a_oldZLevel = az;
   }
  Serial.print("Acceleration :");
  Serial.print(ax);
  Serial.print('\t');
  Serial.print(ay);
  Serial.print('\t');
  Serial.println(az);
  }
 }
 void updateflexsensor(){
  int flex1 = analogRead(flexpin_1);  //센서값을 저장할 변수 설정
  int flex2 = analogRead(flexpin_2); 
  int flex3 = analogRead(flexpin_3);
  int flex4 = analogRead(flexpin_4);
  int flex5 = analogRead(flexpin_5);
  
  if (flex1 != old_flex1) {
  flex1Sensor.writeValue(String(flex1));
  old_flex1 = flex1;
   }
  
  if (flex2 != old_flex2) {
  flex2Sensor.writeValue(String(flex2));
  old_flex2 = flex2;
   }
  
  if (flex3 != old_flex3) {
  flex3Sensor.writeValue(String(flex3));
  old_flex3 = flex3;
   }
   
  if (flex4 != old_flex4) {
  flex4Sensor.writeValue(String(flex4));
  old_flex4 = flex4;
   }
   
  if (flex5 != old_flex5) {
  flex5Sensor.writeValue(String(flex5));
  old_flex5 = flex5;
   }
  
  Serial.print("flex :");
  Serial.print(flex1);
  Serial.print('\t');
  Serial.print(flex2);
  Serial.print('\t');
  Serial.print(flex3);
  Serial.print('\t');
  Serial.print(flex4);
  Serial.print('\t');
  Serial.println(flex5);
  
 }
 
 void loop() {
 BLEDevice central = BLE.central();
 if (central) {
  Serial.print("Connected to central: ");
  Serial.println(central.address());

  while (central.connected()) {
   //long currentMillis = millis();
   updateGyroscopeLevel();
   updateAccelerationLevel();
   updateflexsensor();
   delay(300);
  }

  Serial.print("Disconnected from central: ");
  Serial.println(central.address());
 }
}
