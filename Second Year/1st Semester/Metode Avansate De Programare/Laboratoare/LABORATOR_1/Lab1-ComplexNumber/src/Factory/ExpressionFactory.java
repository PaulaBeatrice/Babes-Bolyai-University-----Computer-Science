package Factory;

import Model.*;
import Enum.Operation;

public class ExpressionFactory {
    private ExpressionFactory(){};
    private static ExpressionFactory instance=new ExpressionFactory();

    public static ExpressionFactory getInstance()
    {
        return instance;
    }

    public ComplexExpression createExpression(Operation operation, NumarComplex[] args)
    {
        switch (operation) {
            case ADDITION: return new AddExpression(args);
            case SUBSTRACTION: return new SubstractExpression(args);
            case MULTIPLICATION: return  new MultipleExpression(args);
            case DIVISION: return new DivisionExpression(args);
            default: return null;
        }
    }
}
