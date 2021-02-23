package com.ml.atomic.mathgame;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;

import java.util.List;
import java.util.stream.Collectors;

public class MathGameApplication {

    public static void main(String[] args) {

        Range<Integer> oneToHundred = Range.closed(1, 100);
        // 三个数都必须在100以内，先加后减50题，先减后加50题, 打乱顺序
        Configuration confLsc = Configuration.builder()
                .firstNumRange(oneToHundred)
                .subItems(ImmutableList.of(
                        new ConfigurationPart()
                                .count(50)
                                .addStep(Operator.ADD, oneToHundred, oneToHundred)
                                .addStep(Operator.SUB, oneToHundred, oneToHundred)
                        ,
                        new ConfigurationPart()
                                .count(50)
                                .addStep(Operator.ADD, oneToHundred, oneToHundred)
                                .addStep(Operator.ADD, oneToHundred, oneToHundred)
                        ,
                        new ConfigurationPart()
                                .count(50)
                                .addStep(Operator.SUB, oneToHundred, oneToHundred)
                                .addStep(Operator.SUB, oneToHundred, oneToHundred),
                        new ConfigurationPart()
                                .count(50)
                                .addStep(Operator.SUB, oneToHundred, oneToHundred)
                                .addStep(Operator.ADD, oneToHundred, oneToHundred)
                ))
                .resultRange(oneToHundred)
                .build();

        ArithmeticGenerator generator = new ArithmeticGenerator();
        List<ArithmeticGenerator.Result> item = generator.execute(confLsc);
        List<String> blank = item.stream().map(ArithmeticGenerator.Result::getBlank).collect(Collectors.toList());
        List<String> answer = item.stream().map(ArithmeticGenerator.Result::getWithAnswer).collect(Collectors.toList());


        new XlsxExportRenderer("/home/atomic/tmp/math_game.xlsx").execute(blank);
        new XlsxExportRenderer("/home/atomic/tmp/math_game_answer.xlsx").execute(answer);

    }

}
