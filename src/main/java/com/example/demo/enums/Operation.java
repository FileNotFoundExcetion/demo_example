package com.example.demo.enums;

import lombok.Getter;

import java.util.function.BiFunction;

@Getter
public enum Operation {
    ADD(Integer::sum), SUBTRACT((x, y) -> x - y), MULTIPLY((x, y) -> x * y);

    Operation(BiFunction<Integer, Integer, Integer> operation) {
        this.operation = operation;
    }

    private final BiFunction<Integer, Integer, Integer> operation;

    public int apply(int x, int y) {
        return operation.apply(x, y);
    }

    public static void main(String[] args) {
        int result = Operation.ADD.apply(2, 3);
        System.out.println(result);
    }
}
