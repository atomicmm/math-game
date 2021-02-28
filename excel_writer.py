from typing import List
import xlwt


class ExcelWriter(object):

    """write with xlwt"""

    def __init__(self, file_name: str):
        self.file_name = file_name

    def render(self, items: List[str]):
        workbook = xlwt.Workbook()

        worksheet = workbook.add_sheet("sheet1")

        # 写入内容
        worksheet.write(0, 0, "标题1")
        worksheet.write(0, 1, "标题2")
        worksheet.write(1, 0, "内容2")

        # 列宽
        worksheet.col(0).width = 256 * 20

        workbook.save("result.xlsx")
