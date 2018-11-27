package com.ml.atomic.mathgame;

import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * 生成算式的配置
 */
@Getter @Builder @ToString
class Configuration {

    /**
     * 每条算式的计算结果的闭区间
     */
    Range<Integer> resultRange;

    /**
     * 第一个操作数的闭区间
     */
    Range<Integer> firstNumRange;

    /**
     * 第二个操作数的闭区间
     */
    Range<Integer> secondNumRange;

    /**
     * 每种算法各生成多少条题目
     */
    Map<Operator, Integer> operatorPercent;

}
