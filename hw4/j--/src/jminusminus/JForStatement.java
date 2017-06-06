package jminusminus;


import static jminusminus.CLConstants.*;

public class JForStatement extends JStatement {

	/** Initialize a variable. */
	private JVariableDeclarator initialize;
	
	/** Statement that terminates the loop */
	private JExpression terminate;

	/** Statement that updates the variable for next loop */
	private JExpression update;

	/** Statement that occurs on every loop */
	//private JStatement block;
	private JStatement block;

	public JForStatement(int line, JVariableDeclarator initialize, JExpression terminate,
			JExpression update, JStatement block) {
		super(line);
		this.initialize = initialize;
		this.terminate = terminate;
		this.update = update;
		this.block = block;
	}
	
	public JAST analyze(Context context) {
//        initialize.analyze(context);
//        terminate.analyze(context);
//        terminate.type().mustMatchExpected(line(), Type.BOOLEAN);
//        if (update != null) {
//            update.analyze(context);
//        }
//
//        block.analyze(context);

        return this;
	}

	public void codegen(CLEmitter output) {
//        initialize.codegen(output);
//
//        String test = output.createLabel();
//        String out = output.createLabel();
//
//
//        output.addLabel(test);
//        terminate.codegen(output, out, false);
//
//
//        block.codegen(output);
//        update.codegen(output);
//
//        output.addBranchInstruction(GOTO, test);
//
//
//        output.addLabel(out);
	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JForStatement line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<Initialization>\n");
		p.indentRight();
		initialize.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Initialization>\n");
		p.printf("<Termination>\n");
		p.indentRight();
		terminate.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Termination>\n");
		p.printf("<Update>\n");
		p.indentRight();
		update.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Update>\n");
		p.printf("<block>\n");
		p.indentRight();
		block.writeToStdOut(p);
		p.indentLeft();
		p.printf("</block>\n");
		p.indentLeft();
		p.printf("</JForStatement>\n");
	}

}
