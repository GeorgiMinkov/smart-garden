# endpoint


# send data to Arduino

# read data from ardiono


# responde to HTTP


from flask import Flask, json
from flask_cors import CORS

import serial
import time



print("Initialize")
arduinoSerial = serial.Serial('/dev/ttyACM1', 9600)

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
        "moisture": "0.0"}
    sendData('1')
    
    recieve = recieveData()
    print("check ata ")
    print (recieve)
    data["moisture"] = recieve
    print (data)
    print ("------------ end -----------")
    return json.dumps(data)

@api.route('/waterCheck/<int:moistureLevel>', methods=['GET']) # water check for moisture level
def water_check(moistureLevel):
    expr = "{command} {level}"
    expr = expr.format(command = 2, level = moistureLevel)

    sendData(expr)
    
    return json.dumps({"status" : "OK"});
    