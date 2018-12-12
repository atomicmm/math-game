package com.ml.atomic.mathgame;

import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import lombok.extern.java.Log;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.RandomUtils.nextInt;

/**
 * 算术题生成器
 */
@Log
class ArithmeticGenerator {
    private static final String TPL_ITEM = " %s %s %s = (   )";


    List<String> execute(Configuration configuration) {

        var sw = new StopWatch();
        sw.start();
        log.info("使用配置".concat(configuration.toString()).concat("开始生成算术题..."));

        int total = configuration.getOperatorPercent().values().stream()
                .mapToInt(i -> i).sum();
        if (total <= 0) throw new IllegalArgumentException("未提供生成算数条数要求");
        Set<String> result = Sets.newHashSetWithExpectedSize(total);
        configuration.getOperatorPercent().forEach(((operator, count) -> {
            for (int i = 0; i < count; i++) {
                do {
                    String item = generateItem(operator, configuration.firstNumRange, configuration.secondNumRange, configuration.resultRange);
                    if (!result.contains(item)) { // 避免重复
                        result.add(item);
                        break;
                    }
                } while (true);
            }

        }));

        sw.stop();
        log.info("生成[" + result.size() + "]条算术题完毕, 总耗时[ " + sw.getTime() + " ]ms");
        return new ArrayList<>(result);
    }

    private static String generateItem(Operator operator, Range<Integer> firstNum, Range<Integer> secondNum, Range<Integer> result) {
        do {
            int first = nextInt(firstNum.lowerEndpoint(), firstNum.upperEndpoint());
            int second = nextInt(secondNum.lowerEndpoint(), secondNum.upperEndpoint());

            int calcResult = 0;
            switch (operator) {
                case ADD:
                    calcResult = first + second;
                    break;
                case DIV:
                    calcResult = first / second;
                    break;
                case MUL:
                    calcResult = first * second;
                    break;
                case SUB:
                    calcResult = first - second;
            }
            if (result.contains(calcResult)) return String.format(TPL_ITEM, first, operator.getLabel(), second);
        } while (true);

    }

}
