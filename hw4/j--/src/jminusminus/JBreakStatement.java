package jminusminus;

public class JBreakStatement extends JStatement {

	private JExpression expr;
	protected JBreakStatement(int line, JExpression expr) {
		super(line);
		this.expr = expr;
	}

	@Override
	public JAST analyze(Context context) {
		return null;
	}

	@Override
	public void codegen(CLEmitter output) {
	
	}
    public void writeToStdOut(PrettyPrinter p) {
        if (expr != null) {
            p.printf("<JBreakStatement line=\"%d\">\n", line());
            p.indentRight();
            expr.writeToStdOut(p);
            p.indentLeft();
            p.printf("</JBreakStatement>\n");
        } else {
            p.printf("<JBreakStatement line=\"%d\"/>\n", line());
        }
    }
	
}