package com.ml.atomic.mathgame;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Slf4j
@AllArgsConstructor @ToString
public final class CalcStep {
    public static final int MAX_ERRORS = 999;

    Operator operator;

    /**
     * 被操作数的闭区间
     */
    Range<Integer> numRange;

    /**
     * 分步计算结果闭区间
     */
    Range<Integer> resultRange;

    /**
     * 单步计算，计算到符合限制的数据为止
     */
    public Optional<Result> apply(int lastNumber) {
        int errorCount = 0;
        do {
            int nextNumber = nextInt(numRange.lowerEndpoint(), numRange.upperEndpoint());
            int result;
            switch (this.operator) {
                case ADD: {
                    result = lastNumber + nextNumber;
                    break;
                }
                case SUB: {
                    result = lastNumber - nextNumber;
                    break;
                }
                default:
                    throw new IllegalArgumentException("使用了暂未支持的操作符:" + this.operator.label);
            }

            if (this.resultRange.contains(result)) return Optional.of(new Result(nextNumber, result, this.operator));
            else errorCount++;

            // 超过最大重试次数
            if (errorCount >= MAX_ERRORS) {
                System.out.println(String.format("calc false... %s %s ? in %s", lastNumber, this.operator.label, resultRange));
                return Optional.empty();
            }

        } while (true);
    }

    @AllArgsConstructor
    static class Result {

        /**
         * 分步参算值
         */
        int val;

        /**
         * 单步计算结果(最后一步的结果可以直接输出，其他的可以用于打印)
         */
        int result;

        /**
         * 操作符
         */
        Operator operator;
    }

}
