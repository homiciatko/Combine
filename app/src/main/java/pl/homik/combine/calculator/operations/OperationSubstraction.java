package pl.homik.combine.calculator.operations;

/**
 * Created by Pawel on 2016-09-28.
 */
public class OperationSubstraction extends Operation {
    @Override
    public int stress() {
        return 50;
    }

    public OperationSubstraction(double a, double b ) {
        super(a, b);
    }

    @Override
    public Double result() {
        return (getA() - getB());
    }

    @Override
    public String getOperationName() {
        return "dzielenie";
    }
}
