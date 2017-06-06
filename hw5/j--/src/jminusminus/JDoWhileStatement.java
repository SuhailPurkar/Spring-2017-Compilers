//Mark
//cs451
//hw3
package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a while-statement.
 */

class JDoWhileStatement extends JStatement {

    /** Test expression. */
    private JExpression test_expression;

    /** The body. */
    private JStatement body;

    /**
     * Construct an AST node for a while-statement given its line number, the
     * test expression, and the body.
     * 
     * @param line
     *            line in which the while-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param body
     *            the body.
     */

    public JDoWhileStatement(int line, JExpression condition, JStatement body) {
        super(line);
        this.test_expression = test_expression;
        this.body = body;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
//HW5 Added
    public JDoWhileStatement analyze(Context context) {
    	test_expression.analyze(context);
    	test_expression.type().mustMatchExpected(line(), Type.BOOLEAN);
        body.analyze(context);
        return this;
        
    }

    /**
     * Generate code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
	//empty for a bit
    	
    	//HW5 Added For
    	String input_test = output.createLabel();
    	String output_test = output.createLabel();
    	output.addLabel(input_test);
    	test_expression.codegen(output, output_test, false);
    	body.codegen(output); 
    	output.addBranchInstruction(GOTO, input_test);
    	output.addLabel(output_test);
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JDoWhileStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        test_expression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JDoWhileStatement>\n");
    }

}
