from typing import Any, List
import xlwt


class ExcelWriter(object):

    """write with xlwt"""

    def __init__(self, file_name: str):
        self.file_name = file_name

    def render(self, items: List[str]):
        workbook = xlwt.Workbook()

        worksheet = workbook.add_sheet("sheet1")

        # 一行两列, 设置每列列宽
        for i in range(0, 2):
            worksheet.col(i).width = 256 * 30

        # 写入内容
        for idx, line in enumerate(grouping_by(items, 2)):

            # 行高不生效？
            worksheet.row(idx).height = 50
            worksheet.write(idx, 0, line[0])
            worksheet.write(idx, 1, line[1])

        workbook.save(self.file_name)


def grouping_by(items: List[Any], size: int) -> List[List[Any]]:
    """
    以size为分组单位，将items切成一个一个小的list
    """

    return list(map(lambda i: items[i:i + size], range(0, len(items), size)))
