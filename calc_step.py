from typing import Optional, TypedDict
from random import randint

import portion

from opt import Operator


class Result(TypedDict):
    """
    计算结果的holder
    """
    operator: Operator
    val: int
    result: int


class CalcStep(object):
    """
    计算步骤的抽象
    """

    # 当循环求值重试次数> max_error时，中止本轮计算
    max_error: int = 999

    def __init__(self, operator: Operator, num_range: portion, result_range: portion):
        """
        :param num_range 参与运算的数的区间
        :param result_range 结果的区间
        """

        self.operator = operator
        self.num_range = num_range
        self.result_range = result_range

    def apply(self, last_number: int) -> Optional[Result]:
        """
        :param last_number 上一个数
        """
        error_count = 0
        while True:
            next_number = randint(self.num_range.lower, self.num_range.upper)
            result = -1
            if self.operator == Operator.ADD:
                result = last_number + next_number
            elif self.operator == Operator.SUB:
                result = last_number - next_number
            else:
                raise ValueError("使用了暂未支持的操作符:", self.operator)

            if result in self.result_range:
                return {"operator": self.operator, "val": next_number, "result": result}
            else:
                error_count += 1

            if error_count >= CalcStep.max_error:
                print("求值失败", last_number, " ", self.operator.value,
                      " ? in", self.result_range)
                return None
