package com.ml.atomic.mathgame;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;
import org.apache.commons.math3.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class MathGameApplication {

    public static void main(String[] args) {

        Range<Integer> oneToHundred = Range.closed(1, 100);
        // 三个数都必须在100以内，先加后减50题，先减后加50题, 打乱顺序
        Configuration confLsc = Configuration.builder()
            .firstNumRange(oneToHundred)
            .subItems(ImmutableMap.of(
                new ConfigurationPart()
                    .addStep(Operator.ADD, oneToHundred, oneToHundred)
                    .addStep(Operator.SUB, oneToHundred, oneToHundred),
                50,
                new ConfigurationPart()
                    .addStep(Operator.ADD, oneToHundred, oneToHundred)
                    .addStep(Operator.ADD, oneToHundred, oneToHundred),
                50,
                new ConfigurationPart()
                    .addStep(Operator.SUB, oneToHundred, oneToHundred)
                    .addStep(Operator.SUB, oneToHundred, oneToHundred),
                50,
                new ConfigurationPart()
                    .addStep(Operator.SUB, oneToHundred, oneToHundred)
                    .addStep(Operator.ADD, oneToHundred, oneToHundred),
                50
            ))
            .resultRange(oneToHundred)
            .build();

        ArithmeticGenerator generator = new ArithmeticGenerator();
        List<Pair<String, String>> item = generator.execute(confLsc);
        List<String> blank = item.stream().map(Pair::getFirst).collect(Collectors.toList());
        List<String> answer = item.stream().map(Pair::getSecond).collect(Collectors.toList());


        new XlsxExportRenderer("/home/atomic/tmp/math_game.xlsx").execute(blank);
        new XlsxExportRenderer("/home/atomic/tmp/math_game_answer.xlsx").execute(answer);

    }

}
