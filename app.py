import portion
from opt import Operator
from configuration import Configuration, ConfigurationPart
from arithmetic_generator import ArithmeticGenerator

one_to_hundred = portion.open(1, 100)

confLsc = Configuration()
confLsc.sub_items = [
    ConfigurationPart(50)
    .add_step(Operator.ADD, one_to_hundred, one_to_hundred)
    .add_step(Operator.ADD, one_to_hundred, one_to_hundred),

    ConfigurationPart(50)
    .add_step(Operator.ADD, one_to_hundred, one_to_hundred)
    .add_step(Operator.SUB, one_to_hundred, one_to_hundred),
]

items = ArithmeticGenerator().execute(confLsc)
print(map(lambda i: i[0], items))
print(map(lambda i: i[1], items))
