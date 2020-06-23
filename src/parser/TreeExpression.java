package parser;

// N -> Node type
// O -> Operator
public interface TreeExpression<N, O> {
    N getLeft();
    N getRight();
    O getOperator();
}
