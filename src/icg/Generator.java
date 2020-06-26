package icg;

import parser.*;
import parser.literal.Literal;
import parser.symbolTable.FunctionSymbolTableEntry;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Generator {

    private ProgramNode programNode;

    public Generator(ProgramNode programNode) {
        this.programNode = programNode;
    }

    public IntermediateCode generateIC() throws ICGException {
        IntermediateCode ic = new IntermediateCode();
        return ic;
    }

    private void generateForVariableDeclaration(IntermediateCode ic, VariableDeclaration variableDeclaration) throws ICGException {
        String value = generateBinaryExpressionCode(ic, variableDeclaration.getRhs());
        postVariableDeclarationRecord(ic, variableDeclaration.getLhs(), value);
    }

    private void postVariableDeclarationRecord(IntermediateCode ic, IdentifierNode lhs, String value) throws ICGException {
        String varName = lhs.getIdentifierName();
        DataType dataType = lhs.getDataType();
        String internalName = ic.convertVariable(varName, dataType);
        ic.addRecord("ALLOC " + dataType.getByteCount(), internalName, null);
        ic.addRecord("=", internalName, value);
    }

    /**
     * This method will generate three address code for the binary expression
     * and sends back the instruction id for the final result
     *
     * @param ic
     * @param rhsExpression
     * @return
     */
    private String generateBinaryExpressionCode(IntermediateCode ic, RhsExpression rhsExpression) throws ICGException {
        switch (rhsExpression.getExpressionType()) {
            case LITERAL: {
                Literal literal = (Literal) rhsExpression;
                return literal.getValue().toString();
            }
            case IDENTIFIER: {
                String identifierNode = ((IdentifierNode) rhsExpression).getIdentifierName();
                return ic.getInternalName(identifierNode);
            }
            case FUNCTION_CALL: {
                return generateFunctionCall(ic, (FunctionCall) rhsExpression);
            }
            case BINARY_EXPRESSION: {
                return analyseBinaryExpression(ic, (BinaryExpression) rhsExpression);
            }
            case BOOLEAN_EXPRESSION: {
                return analyseBooleanExpression(ic, (BooleanExpression) rhsExpression);
            }
            case RELATIONAL_EXPRESSION: {
                return analyseRelationalExpression(ic, (RelationalExpression) rhsExpression);
            }
            default: {
                throw new ICGException("Trying to parse unknown binary expression");
            }
        }
    }

    private String analyseRelationalExpression(IntermediateCode ic, RelationalExpression rhsExpression) throws ICGException {
        RelationalExpression relationalExpression = rhsExpression;
        if (null != relationalExpression.getRelationalOperator()) {
            return generateBinaryExpressionCode(ic, relationalExpression.getLeftExp());
        } else {
            String left = generateBinaryExpressionCode(ic, relationalExpression.getLeftExp());
            String right = generateBinaryExpressionCode(ic, relationalExpression.getRightExp());
            int instructionAddr = ic.addRecord(relationalExpression.getRelationalOperator().value(), left, right);
            return IntermediateCode.INSTRUCTION_ADDRESS_PREFIX + instructionAddr;
        }
    }

    private String analyseBooleanExpression(IntermediateCode ic, BooleanExpression rhsExpression) throws ICGException {
        BooleanExpression booleanExpression = rhsExpression;
        int instructionAddr;
        String left = generateBinaryExpressionCode(ic, booleanExpression.getLeft());
        if (BooleanOperator.NOT.equals(booleanExpression.getOperator())) {
            instructionAddr = ic.addRecord(BooleanOperator.NOT.value(), left, null);
        } else {
            String right = generateBinaryExpressionCode(ic, booleanExpression.getRight());
            instructionAddr = ic.addRecord(booleanExpression.getOperator().value(), left, right);
        }
        return IntermediateCode.INSTRUCTION_ADDRESS_PREFIX + instructionAddr;
    }

    private String analyseBinaryExpression(IntermediateCode ic, BinaryExpression rhsExpression) throws ICGException {
        BinaryExpression binaryExpression = rhsExpression;

        if (null == binaryExpression.getOperator())
            return generateBinaryExpressionCode(ic, binaryExpression.getLeft());
        else {
            String left;
            Integer instructionAddr = null;

            left = generateBinaryExpressionCode(ic, binaryExpression.getLeft());

            if (null != binaryExpression.getRight()) {
                String right = generateBinaryExpressionCode(ic, binaryExpression.getRight());
                instructionAddr = ic.addRecord(binaryExpression.getOperator().value(), left, right);
            } else {
                instructionAddr = ic.addRecord(binaryExpression.getOperator().value(), left, null);
            }
            return IntermediateCode.INSTRUCTION_ADDRESS_PREFIX + instructionAddr;
        }
    }

    private String generateFunctionCall(IntermediateCode ic, FunctionCall functionCall) throws ICGException {
        List<String> tmpNames = new LinkedList<>();
        int argumentCount = functionCall.getArguments().size();

        FunctionSymbolTableEntry funcDeclaration = programNode.getFunction(functionCall.getFunctionName(), argumentCount).value;

        if (funcDeclaration == null)
            throw new ICGException("Cannot invoke undefined function: " + funcDeclaration.getFunctionName() + "with");

        int iter = 0;
        for (Map.Entry<String, VariableDeclaration> entry : funcDeclaration.getArguments().entrySet()) {

            // Evaluate argument
            BinaryExpression expression = functionCall.getArguments().get(iter);
            String value = generateBinaryExpressionCode(ic, expression);
            iter++;

            // Store result in a new variable
            String tmpName = ic.generateTempName();
            postVariableDeclarationRecord(ic, entry.getValue().getLhs(), value);

            // Push variable name to function stack
            ic.pushArgumentToFunctionStack(tmpName);
            tmpNames.add(tmpName);
        }

        tmpNames.forEach(tmpName -> ic.addRecord("param", tmpName, null));

        ic.addRecord("call", functionCall.getFunctionName(), Integer.toString(argumentCount));

        String returnValueVar = ic.generateTempName();
        ic.addRecord("=", returnValueVar, ic.popArgumentFromFunctionStack());

        return returnValueVar;
    }

}
