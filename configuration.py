import portion

from calc_step import CalcStep
from opt import Operator

# 1-100的开区间
one_to_hundred = portion.open(1, 100)


class ConfigurationPart(object):
    def __init__(self, count: int):
        self.count = count
        self.steps = []

    def add_step(self, operator: Operator, num_range: portion, result_range: portion):
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
    sub_items = []

    def total_count(self):
        return sum(map(lambda i: i.count, self.sub_items))
