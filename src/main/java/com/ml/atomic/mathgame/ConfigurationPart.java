package com.ml.atomic.mathgame;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
public class ConfigurationPart {

    List<PartDetail> steps = Lists.newArrayList();

    /**
     * 生成多少题
     */
    int count;

    public ConfigurationPart count(int count) {
        this.count = count;
        return this;
    }

    public ConfigurationPart addStep(Operator operator, Range<Integer> numRange, Range<Integer> resultRange) {
        PartDetail detail = new PartDetail(operator, numRange, resultRange);
        this.steps.add(detail);

        return this;
    }

    @AllArgsConstructor
    static final class PartDetail {

        Operator operator;

        /**
         * 被操作数的闭区间
         */
        Range<Integer> numRange;

        /**
         * 分步计算结果闭区间
         */
        Range<Integer> resultRange;

    }
}
