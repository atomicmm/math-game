from opt import Operator
from configuration import Configuration, ConfigurationPart
from arithmetic_generator import ArithmeticGenerator
from excel_writer import ExcelWriter

confLsc = Configuration()
confLsc.sub_items = [
    ConfigurationPart(50).add_step(Operator.ADD).add_step(Operator.ADD),
    ConfigurationPart(50).add_step(Operator.ADD).add_step(Operator.SUB),
]

items = ArithmeticGenerator().execute(confLsc)
blank = list(map(lambda i: i[0], items))
with_answer = list(map(lambda i: i[1], items))

print(blank)
ExcelWriter("result.xlsx").render(blank)
