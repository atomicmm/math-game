import portion
import time
from configuration import Configuration, ConfigurationPart


class Result(object):
    """临时计算结果的holder"""
    pass


class ArithmeticGenerator(object):
    """ 算术题生成器 """

    def execute(self, config: Configuration) -> list[Result]:
        start_time = time.time()

        if len(config.sub_items) == 0:
            raise ValueError("至少提供一个生成序列")

        total_count = config.total_count()
        print("使用配置config = (", config, ")，开始生成算术题...")

        result = []
        for configItem in config.sub_items:
            counter = 0
            while counter < configItem.count:
                counter += 1

        end_time = time.time()
        print("生成[", "", "]条算术题完毕, 共耗时 = ", end_time - start_time, "ms")

    def do_generate_item(self, first_num: portion, result_range: portion, subSeq: ConfigurationPart) -> Optional[Result]:
        """
        生成一条完整序列的算式
        """
        
