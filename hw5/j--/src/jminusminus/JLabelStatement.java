//Mark Vo
//cs451
//hw3
//This class is for the break statement node
package jminusminus;

class JLabelStatement extends JStatement {

    /**
     * Construct an AST node for an label statement.
     * 
     * @param line
     *            line in which the empty statement occurs in the source file.
     */
	 
	 private JStatement body;
	 
	 

    protected JLabelStatement(int line, JStatement body) {
        super(line);
		this.body = body;
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
   
				//Beginnning
        p.printf("<JLabelStatement line=\"%d\"/>\n", line());
		
        p.indentRight();
				
		//The body statement
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
		
		
        p.indentLeft();
		//end
        p.printf("<JLabelStatement line=\"%d\"/>\n", line());
    }

}
