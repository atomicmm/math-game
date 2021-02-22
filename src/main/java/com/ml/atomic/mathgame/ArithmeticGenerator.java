package com.ml.atomic.mathgame;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import lombok.extern.java.Log;
import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.RandomUtils.nextInt;

/**
 * 算术题生成器
 */
@Log
class ArithmeticGenerator {

    List<String> execute(Configuration configuration) {

        var sw = new StopWatch();
        sw.start();
        log.info("使用配置".concat(configuration.toString()).concat("开始生成算术题..."));

        if (configuration.subItems.isEmpty())
            throw new IllegalArgumentException("至少提供一个生成序列");

        // 使用set避免重复
        int totalCount = configuration.getTotalCount();
        Set<String> result = Sets.newHashSetWithExpectedSize(totalCount);

        // 每一条子配置生成count条
        configuration.subItems.forEach((config, count) -> {
            int subSeqCount = 0;
            do {
                String resultItem = doGenerateItem(configuration.getFirstNumRange(), configuration.getResultRange(), config);
                result.add(resultItem);
                subSeqCount++;
            } while (subSeqCount < count);
        });

        sw.stop();
        log.info("生成[" + totalCount + "]条算术题完毕, 总耗时[ " + sw.getTime() + " ]ms");
        return Lists.newArrayList(result);
    }

    private static String doGenerateItem(Range<Integer> firstNum, Range<Integer> resultRange, ConfigurationPart subSeq) {

        List<CalcStep> steps = subSeq.steps;
        int first = nextInt(firstNum.lowerEndpoint(), firstNum.upperEndpoint());
        do {
            CalcStep.Result result = steps.stream().reduce(null, (partialResult, step) -> (Objects.isNull(partialResult))
                    ? step.apply(first)
                    : step.apply(partialResult.result), (i, j) -> j);
            if (resultRange.contains(result.result))
                return "";
        } while (true);

    }

}
