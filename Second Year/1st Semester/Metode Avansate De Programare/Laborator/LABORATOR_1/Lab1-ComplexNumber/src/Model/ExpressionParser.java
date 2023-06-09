package Model;
import Enum.Operation;

import java.util.Objects;

public class ExpressionParser {
    private String[] args;

    public ExpressionParser(String[] args)
    {
        this.args = args;
    }

    public ComplexExpression parse() {
        String op = args[1];
        NumarComplex[] argss = new NumarComplex[ (args.length+1)/2];
        for(int i = 0; i < args.length ; i++)
        { // pe pozitii pare sunt nr complexe, impare operanzii
            if (i % 2 == 0)
            { // nr complex
                //System.out.println(args[i]);
                String el[] = args[i].split("\\+", 2);
                int re = Integer.parseInt(el[0]);
                String IM[] = el[1].split("\\*", 2);
                int im = Integer.parseInt((IM[0]));
                NumarComplex nr = new NumarComplex(re, im);
                argss[i / 2] = nr;
                System.out.println(nr);
            }
            else
            {
                if(!Objects.equals(args[i], op)) {
                    //throw new Exception(); // nu e acelasi operator peste tot!
                    return null;
                }
            }
        }

        switch (op)
        {
            case "+":
                return new AddExpression(argss);
            case "-":
                return new SubstractExpression(argss);
            case "*":
                return new MultipleExpression(argss);
            case "/":
                return new DivisionExpression(argss);
        }
        return null;
    }
}
