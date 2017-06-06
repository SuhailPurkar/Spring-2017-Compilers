package jminusminus;

public class JEnhancedForStatement extends JStatement {

	/** forInit a variable. */
	private JVariableDeclarator forInit;

	/** Statement that holds the expression */
	private JExpression expression;

	/** Statement that occurs on every loop */
	private JBlock body;
	
	public JEnhancedForStatement(int line, JVariableDeclarator forInit, JExpression expression, JBlock body) {
		super(line);
		this.forInit = forInit;
		this.expression = expression;
		this.body = body;
	}

	public JAST analyze(Context context) {
        forInit.analyze(context);
        expression.analyze(context);
        body.analyze(context);
        return this;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JEnhancedForStatement line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<Initialization>\n");
		p.indentRight();
		forInit.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Initialization>\n");
		p.printf("<expression>\n");
		p.indentRight();
		expression.writeToStdOut(p);
		p.indentLeft();
		p.printf("</expression>\n");
		p.indentRight();
		p.printf("<body>\n");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.printf("</body>\n");
		p.indentLeft();
		p.printf("</JEnhancedForStatement>\n");
	}

}