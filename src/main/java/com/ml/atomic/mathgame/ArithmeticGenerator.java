package com.ml.atomic.mathgame;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomUtils.nextInt;

/**
 * 算术题生成器
 */
@Log
class ArithmeticGenerator {

    List<Result> execute(Configuration configuration) {

        StopWatch sw = new StopWatch();
        sw.start();
        log.info("使用配置".concat(configuration.toString()).concat("开始生成算术题..."));

        if (configuration.subItems.isEmpty())
            throw new IllegalArgumentException("至少提供一个生成序列");

        // 使用set避免重复
        int totalCount = configuration.getTotalCount();
        Set<Result> result = Sets.newHashSetWithExpectedSize(totalCount);

        // 每一条子配置生成count条
        configuration.subItems.forEach(config -> {
            AtomicInteger subSeqCount = new AtomicInteger();
            do {
                doGenerateItem(configuration.getFirstNumRange(), configuration.getResultRange(), config).ifPresent(resultItem -> {
                    result.add(resultItem);
                    subSeqCount.getAndIncrement();
                });
            } while (subSeqCount.get() < config.getCount());
        });

        sw.stop();
        log.info("生成[" + totalCount + "]条算术题完毕, 总耗时[ " + sw.getTime() + " ]ms");
        return Lists.newArrayList(result);
    }

    /**
     * 生成一条完整序列的算式
     */
    private static Optional<Result> doGenerateItem(Range<Integer> firstNum, Range<Integer> resultRange, ConfigurationPart subSeq) {

        List<CalcStep> steps = subSeq.steps;
        int first = nextInt(firstNum.lowerEndpoint(), firstNum.upperEndpoint());
        do {
            List<CalcStep.Result> resultHolder = Lists.newArrayListWithCapacity(steps.size());
            for (int i = 0; i < steps.size(); i++) {
                CalcStep step = steps.get(i);
                int lastVal = i == 0 ? first : resultHolder.get(i - 1).result;
                Optional<CalcStep.Result> stepResult = step.apply(lastVal);

                if (stepResult.isPresent()) resultHolder.add(stepResult.get());
                else return Optional.empty();
            }
            int finalResult = resultHolder.get(resultHolder.size() - 1).result;

            if (resultRange.contains(finalResult)) { // 落到了合理的区间内
                String other = resultHolder.stream().map(i -> String.format("%s %s", i.operator.label, i.val)).collect(Collectors.joining(" ", " ", " "));

                String blank = String.format("%s%s=", first, other);
                String withAnswer = String.format("%s%s= %s", first, other, finalResult);

                return Optional.of(new Result(blank, withAnswer));

            }
        } while (true);

    }

    @AllArgsConstructor
    @Getter
    static class Result {
        String blank;
        String withAnswer;
    }

}
