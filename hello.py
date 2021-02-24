from enum import Enum, unique


class Hello(object):
    def hi(self):
        print("Hello World")


@unique
class Color(Enum):
    RED = "红"
    GREEN = "绿"
    BLUE = "蓝"
