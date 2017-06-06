// Mark Vo
//cs451
//hw3

package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;
/**
 * The AST node for a for-statement.
 
 for loop has these parts:
 for(int a = 0;     i < 10;         a++)
	 initialize; test expression; udpate
	and then the body
 */

class JEnhancedForStatement extends JStatement {

    /** initialize expression. */
    private JVariableDeclaration initialize_expression;

    /** condition expression. */
    private JExpression test_expression;

    /** The body. */
    private JStatement body;

	
    public JEnhancedForStatement(int line, JVariableDeclaration initialize_expression, 
	JExpression test_expression, JStatement body) {
        super(line);
        this.initialize_expression = initialize_expression;
		this.test_expression = test_expression;
        this.body = body;
    }

   
//HW5 EnhancedForStatement added
     public JAST analyze(Context context) {
    	 initialize_expression.analyze(context);
         test_expression.analyze(context);
         body.analyze(context); 
    	 return this;
    }


    public void codegen(CLEmitter output) {
	//Empty for now
    	
    	//HW5 Added For
    	String input_test = output.createLabel();
    	String output_test = output.createLabel();
    	output.addLabel(input_test);
    	initialize_expression.codegen(output);
    	test_expression.codegen(output, output_test, false);
    	body.codegen(output);
    	output.addBranchInstruction(GOTO, input_test);
    	output.addLabel(output_test);
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
		
		//Beginnning
        p.printf("<JEnhancedForStatement line=\"%d\">\n", line());
		
        p.indentRight();
		
		//The init expression
        p.printf("<InitExpression>\n");
        p.indentRight();
        initialize_expression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</InitExpression>\n");
		
		//The test expression
		p.printf("<TestExpression>\n");
        p.indentRight();
        test_expression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
		
		
		//The body statement
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
		
		
        p.indentLeft();
		//end
        p.printf("</JEnhancedForStatement>\n");
    }

}
