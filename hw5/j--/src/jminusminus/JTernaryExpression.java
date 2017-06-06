//Mark Vo
//CS451
//HW3

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a ternary expression. A ternary expression has an operator
 and three operands and a colon
 */

abstract class JTernaryExpression extends JExpression {

    /** The ternary operator. */
    protected String operator;
	
	//This is the colon that divides the mhs and rhs
	protected String colon;

    /** The lhs operand. */
    protected JExpression lhs;

    /** The mhs operand. */
    protected JExpression mhs;
	
	    /** The rhs operand. */
    protected JExpression rhs;
	


    /**
     * Construct an AST node for a ternary expression given its line number, the
     * ternary operator, and lhs, mhs, and rhs operands, and a colon.
     * 
     * @param line
     *            line in which the ternary expression occurs in the source file.
     * @param operator
     *            the binary operator.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
	 * @param mhs
     *            the mhs operand.
	 * @param colon
     *            the colon that divides operand.
     */

    protected JTernaryExpression(int line, String operator, String colon, 
	JExpression lhs, JExpression mhs, JExpression rhs) {
        super(line);
        this.operator = operator;
		this.colon = colon;
        this.lhs = lhs;
		this.mhs = mhs;
        this.rhs = rhs;
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
		
		//Beginning 
        p.printf("<JTernaryExpression line=\"%d\" type=\"%s\" "
                + "operator=\"%s\">\n", line(), ((type == null) ? "" : type
                .toString()), Util.escapeSpecialXMLChars(operator));
        p.indentRight();
		
		//Print out the boolean lhs which is the lhs 
		p.printf("<Booleanlhs>\n");
		p.indentRight();
        lhs.writeToStdOut(p);
        p.indentLeft();
		p.printf("<Booleanlhs>\n");
				
		//Print out the first consquence which is the mhs
        p.printf("<Consequence-1>\n");
        p.indentRight();
        mhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Consequence-1>>\n");
		
		
		//Print out the second consquence which is the rhs
        p.printf("<Consequence-2>\n");
        p.indentRight();
        rhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Consequence-2>>\n");
		
		
		//end
        p.printf("</JTernaryExpression>\n");
    }

}

/**
 * The AST node for a ternary expression
 */

class JTernaryOp extends JTernaryExpression {

  
	
    public JTernaryOp(int line, JExpression lhs, JExpression mhs, JExpression rhs) {
        super(line, "?", ":", lhs, mhs, rhs);
    }

    //HW5 Conditional Modified
    public JExpression analyze(Context context) {
    	lhs = (JExpression) lhs.analyze(context);
		lhs.type().mustMatchExpected(line(), Type.BOOLEAN);
		
		mhs = (JExpression) mhs.analyze(context);
		rhs = (JExpression) rhs.analyze(context);
		mhs.type().mustMatchExpected(line(), rhs.type());
		
		type = mhs.type();
        return this;
    }


    public void codegen(CLEmitter output) {
    	String elseLabel = output.createLabel();
		String endLabel = output.createLabel();
		lhs.codegen(output, elseLabel, false);
		mhs.codegen(output);
		output.addBranchInstruction(GOTO, endLabel);
		output.addLabel(elseLabel);
		rhs.codegen(output);
		output.addLabel(endLabel);
    }

}

