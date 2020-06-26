package intermediateCodeGenerator;

public class Triple {
    private String operator;
    private String arg1, arg2;

    public Triple(String operator, String arg1, String arg2) {
        this.operator = operator;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public String getOperator() {
        return operator;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }
}
