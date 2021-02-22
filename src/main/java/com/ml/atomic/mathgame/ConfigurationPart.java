package com.ml.atomic.mathgame;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
public class ConfigurationPart {

    List<CalcStep> steps = Lists.newArrayList();

    /**
     * 生成多少题
     */
    int count;

    public ConfigurationPart count(int count) {
        this.count = count;
        return this;
    }

    public ConfigurationPart addStep(Operator operator, Range<Integer> numRange, Range<Integer> resultRange) {
        CalcStep detail = new CalcStep(operator, numRange, resultRange);
        this.steps.add(detail);

        return this;
    }
}
