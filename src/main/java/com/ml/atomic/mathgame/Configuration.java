package com.ml.atomic.mathgame;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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
     * 是否将最后的计算结果打乱
     */
    @Builder.Default
    boolean useRandomSeq = Boolean.TRUE;

    /**
     * 从第二个数开始后面的被计算数配置
     */
    @Builder.Default
    List<ConfigurationPart> subItems = Lists.newArrayList();

    public int getTotalCount() {
        return this.subItems.stream()
                .mapToInt(ConfigurationPart::getCount).sum();
    }
}
