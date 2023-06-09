package Model;

import static Enum.Operation.MULTIPLICATION;
import static Enum.Operation.SUBSTRACTION;

public class MultipleExpression extends ComplexExpression{
    public MultipleExpression(NumarComplex[] args) {
        super(MULTIPLICATION, args);
    }
    public NumarComplex executeOneOperation(NumarComplex n1, NumarComplex n2)
    {
        return n1.inmultire(n2);
    }
}
