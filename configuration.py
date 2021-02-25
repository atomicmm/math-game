import portion
from typing import List

from calc_step import CalcStep
from opt import Operator

# 1-100的开区间
one_to_hundred = portion.closed(1, 100)


class ConfigurationPart(object):
    def __init__(self, count: int, steps: List[CalcStep] = []):
        self.count = count
        self.steps = steps

    def add_step(self, operator: Operator, num_range: portion = one_to_hundred, result_range: portion = one_to_hundred):
        step = CalcStep(operator, num_range, result_range)
        self.steps.append(step)
        return self


class Configuration(object):
    """ 配置的根节点 """

    # 是否将最后的结果打乱
    useRandomSeq = True

    # 结果数应落在的区间内
    result_range = one_to_hundred

    # 第一个计算数的区间
    first_num_range = one_to_hundred

    # 从第二个数开始的配置子序列
    sub_items: List[ConfigurationPart] = []

    def total_count(self):
        return sum(map(lambda i: i.count, self.sub_items))
