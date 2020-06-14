# endpoint


# send data to Arduino

# read data from ardiono


# responde to HTTP


from flask import Flask, json
from flask_cors import CORS

import serial
import time



print("Initialize")
arduinoSerial = serial.Serial('/dev/ttyACM0', 9600)

time.sleep(5)

arduinoSerial.flush()

api = Flask(__name__)
CORS(api)

print("Initialized")


def sendData(command):
    print("Write data started")
    arduinoSerial.write(command.encode())
    print("Write data ended")
    time.sleep(1)
    
    print("after sleep")
    
def recieveData():
    index = 0
    while True:
        index = index + 1
        print (index)
        print  ("---------------------")
        if  arduinoSerial.inWaiting() :
            print  ("data found -> ")
            #time.sleep(3)
            data = arduinoSerial.readline().decode('utf-8').rstrip()
            print (data)
            return data
        else:
            print (">>>>>> No data<<<<<")
            time.sleep(2)
        print  ("---------------------")    

@api.route('/data', methods=['GET'])
#@cross_origin
def get_data():
    print ("------------ start -----------")
    data = {
        "text": "water info",
        "moisture": "0.0",
        "temperture": "0.0",
        "humidity": "0.0",
        "waterLevel": "45"}
    sendData('1')
    
    recieve = recieveData()
    print("check data ")
    print (recieve)
    
    splitData = recieve.split()
    print("check data ")
    print (recieve)
    moisture = splitData[0]
    temperture = splitData[1]
    humidity = splitData[2]
    
    data["moisture"] = moisture
    data["temperture"] = temperture
    data["humidity"] = humidity
    
    print (data)
    print ("------------ end -----------")
    return json.dumps(data)

@api.route('/waterCheck/<int:moistureLevel>', methods=['GET']) # water check for moisture level
def water_check(moistureLevel):
    data = {
        "text": "undefined",
        "moisture": "0.0",
        "temperture": "0.0",
        "humidity": "0.0",
        "waterLevel": "45"}
    
    expr = "{command} {level}"
    expr = expr.format(command = 2, level = moistureLevel)
    
    
    sendData(expr)

    recieve = recieveData()
    print("check data ")
    print (recieve)
    
    splitData = recieve.split("|")
    text = splitData[0]
    moisture = splitData[1]
    temperture = splitData[2]
    humidity = splitData[3]
    
    data["text"] = text
    data["moisture"] = moisture
    data["temperture"] = temperture
    data["humidity"] = humidity
    
    return json.dumps(data);
    
