from enum import Enum, unique

@unique
class Operator(Enum):
    """
    支持的计算操作符
    """

    ADD = "+"
    SUB = "-"
    MUL = "x"
    DIV = "÷"

# print(repr(Operator.ADD))
