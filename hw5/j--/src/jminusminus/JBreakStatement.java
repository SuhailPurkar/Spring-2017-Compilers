//Mark Vo
//cs451
//hw3
//This class is for the break statement node
package jminusminus;

class JBreakStatement extends JStatement {

    /**
     * Construct an AST node for an break statement.
     * 
     * @param line
     *            line in which the empty statement occurs in the source file.
     */
	private JStatement label;
    protected JBreakStatement(int line) {
        super(line);
		//this.label = label;
    }

    /**
     * @inheritDoc
     */

    public JAST analyze(Context context) {
        // Nothing to do.
        return this;
    }

    /**
     * @inheritDoc
     */

    public void codegen(CLEmitter output) {
        // Nothing to do.
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JBreakStatement line=\"%d\"/>\n", line());
		
        p.indentRight();
		
		
		/*
		//The label statement
        p.printf("<Label>\n");
        p.indentRight();
        label.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Label>\n");
		*/
		
        p.indentLeft();
		//end
      p.printf("<JBreakStatement line=\"%d\"/>\n", line());
    }

}
