import time
import multiprocessing
import playsound

from data import Data


def loop():
    while True:
        printOnScreen = multiprocessing.Process(target=lambda: printLastData(), daemon=True)
        printOnScreen.start()
        sirenProcess = multiprocessing.Process(target=lambda: playSiren(), daemon=True)
        sirenProcess.start()
        time.sleep(1)
        printOnScreen.terminate()
        sirenProcess.terminate()


def printLastData():
    data = Data(ip, port, path)
    dataString = "Id: " + str(data.dataId) + ", Date/Time: " + data.insertionDateTime + ", isOnFire: " + \
                 str(data.isOnFire) + ", IR Level: " + str(data.irLevel) + ", Temperature: " + str(data.temperature) + " °C."

    print(dataString)


def playSiren():
    data = Data(ip, port, path)
    if data.isOnFire:
        sound_file = "siren.mp3"
        playsound.playsound(sound_file)


ip = "192.168.12.13"
port = 49152
path = "/firetracker/data/getLast"
loop()


