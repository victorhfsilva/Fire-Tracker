import requests
import re


class Data:

    def __init__(self, ip: str, port: int, path: str):
        self.ip = ip
        self.port = port
        self.path = path
        self.__assert_data_is_correct()
        self.data = self.__request_data()
        self.dataJson = self.data.json()

        self.isOnFire = self.dataJson['onFire']
        self.temperature = self.dataJson['temperature']
        self.irLevel = self.dataJson['irLevel']
        self.insertionDateTime = self.dataJson['insertionDateTime']
        self.dataId = self.dataJson['id']

    def __assert_data_is_correct(self):
        assert isinstance(self.ip, str), "IP isn't a String."
        assert re.match(r"^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$", self.ip), "Ip doesn't match IP format."
        assert isinstance(self.port, int), "Port isn't an Integer."
        assert 49152 <= self.port <= 65535, "Port isn't a Dynamic Port."

    def __request_data(self):
        address = "http://" + self.ip + ":" + str(self.port) + self.path
        data = requests.get(address)
        return data


