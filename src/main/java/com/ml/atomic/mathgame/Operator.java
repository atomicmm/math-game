package com.ml.atomic.mathgame;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作符
 */
@AllArgsConstructor
@Getter
public enum Operator {
    ADD("+"),
    SUB("-"),
    MUL("×"),
    DIV("÷"),

    //
    ;
    String label;
}
