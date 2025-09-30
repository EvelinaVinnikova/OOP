package org.example;

public abstract class BinaryOperation extends Expression {

    protected final Expression left;
    protected final Expression right;

    /**
     * Constructs a binary operation with two sub-expressions.
     * @param left the left-hand side expression.
     * @param right the right-hand side expression.
     */
    protected BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
