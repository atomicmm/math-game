package com.ml.atomic.mathgame;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;

import java.util.List;

public class MathGameApplication {

    public static void main(String[] args) {

        //计算结果在1-10以内的加减法
        Configuration confLsc = Configuration.builder()
                .firstNumRange(Range.closed(1, 10))
                .secondNumRange(Range.closed(1, 10))
                .resultRange(Range.closed(1, 10))
                .operatorPercent(ImmutableMap.of(Operator.ADD, 40, Operator.SUB, 35))
                .build();

        ArithmeticGenerator generator = new ArithmeticGenerator();
        List<String> item1 = generator.execute(confLsc);
        List<String> item2 = generator.execute(confLsc);
        item1.addAll(item2);

        IResultRenderer renderer = new XlsxExportRenderer();
        renderer.execute(item1);
    }

}
