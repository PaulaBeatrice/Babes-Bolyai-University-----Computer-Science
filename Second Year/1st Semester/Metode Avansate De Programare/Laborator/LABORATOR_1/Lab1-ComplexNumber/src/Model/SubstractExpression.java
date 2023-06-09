package Model;

import static Enum.Operation.SUBSTRACTION;

public class SubstractExpression extends ComplexExpression{
    public SubstractExpression(NumarComplex[] args) {
        super(SUBSTRACTION, args);
    }
    public NumarComplex executeOneOperation(NumarComplex n1, NumarComplex n2)
    {
        return n1.scadere(n2);
    }
}

