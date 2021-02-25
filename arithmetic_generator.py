from calc_step import Result
import time
from random import randint
from typing import List, Optional

import portion

from configuration import Configuration, ConfigurationPart


class ArithmeticGenerator(object):
    """ 算术题生成器 """

    def execute(self, config: Configuration):
        start_time = time.time()

        if len(config.sub_items) == 0:
            raise ValueError("至少提供一个生成序列")

        total_count = config.total_count()
        print("使用配置config = (", config, ")，开始生成算术题...")

        result = []
        for item in config.sub_items:
            sub_seq_count = 0
            while sub_seq_count < item.count:
                result_item = self.do_generate_item(config.first_num_range, config.result_range, item)
                if result_item is not None:
                    result.append(result_item)
                    sub_seq_count += 1

        cost = 1000 * (time.time() - start_time)
        print("生成[", total_count, "]条算术题完毕, 共耗时 = ",
              cost, "ms")
        return result

    def do_generate_item(self, first_num: portion, result_range: portion, sub_seq: ConfigurationPart):
        """
        生成一条完整序列的算式
        """
        first = randint(first_num.lower, first_num.upper)
        while True:
            result_holder: List[Result] = []
            for i, step in enumerate(sub_seq.steps):
                last_val = first if i == 0 else result_holder[-1]["result"]
                step_result = step.apply(last_val)
                if step_result is not None:
                    result_holder.append(step_result)
                else:
                    return

            # 序列生成结束后，最后计算结果是最后一个元素
            final_result = result_holder[-1]["result"]
            if final_result in result_range:
                # 组合后续序列的值
                other = " ".join(map(lambda i: "{} {}".format(i["operator"].value, i["val"]), result_holder))
                blank = "{} {} =".format(first, other)
                with_answer = "{} {} = {}".format(first, other, final_result)
                # print(with_answer)

                return (blank, with_answer)
