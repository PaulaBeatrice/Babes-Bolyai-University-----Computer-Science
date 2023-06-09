package Model;
import Enum.Operation;

import static Enum.Operation.ADDITION;

public class AddExpression extends ComplexExpression{
    public AddExpression(NumarComplex[] args) {
        super(ADDITION, args);
    }
    public NumarComplex executeOneOperation(NumarComplex n1, NumarComplex n2)
    {
        return n1.adunare(n2);
    }
}
