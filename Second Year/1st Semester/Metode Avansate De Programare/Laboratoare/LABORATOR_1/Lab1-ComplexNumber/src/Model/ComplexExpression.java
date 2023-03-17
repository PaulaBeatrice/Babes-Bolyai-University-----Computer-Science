package Model;
import Enum.Operation;
import Model.NumarComplex;

public abstract class ComplexExpression {
    private Operation operation;
    private NumarComplex args[];

    public ComplexExpression(Operation operation, NumarComplex[] args) {
        this.operation = operation;
        this.args = args;
    }

    public NumarComplex execute()
    {
        NumarComplex result = this.args[0];
        for(int i = 1; i <= this.args.length-1; i++)
        {
            result = executeOneOperation(result, this.args[i]);
        }
        return result;
    }

    public abstract NumarComplex executeOneOperation(NumarComplex n1, NumarComplex n2);
}
