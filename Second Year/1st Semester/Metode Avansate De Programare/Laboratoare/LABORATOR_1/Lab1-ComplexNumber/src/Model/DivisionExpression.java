package Model;

import static Enum.Operation.DIVISION;
import static Enum.Operation.MULTIPLICATION;

public class DivisionExpression extends ComplexExpression{
    public DivisionExpression(NumarComplex[] args) {
        super(DIVISION, args);
    }
    public NumarComplex executeOneOperation(NumarComplex n1, NumarComplex n2)
    {
        return n1.impartire(n2);
    }
}
