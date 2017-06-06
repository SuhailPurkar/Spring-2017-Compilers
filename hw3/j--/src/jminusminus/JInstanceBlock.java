package jminusminus;

class JInstanceBlock extends JAST implements JMember {

	private JBlock body;

	public JInstanceBlock(int line, JBlock body) {
		super(line);
		this.body = body;
	}

	@Override
	public void preAnalyze(Context context, CLEmitter partial) {

	}
	
    public JInstanceBlock analyze(Context context) {
        return this;
    }
	
    public void codegen(CLEmitter output) {
        }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInstanceBlock line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JInstanceBlock>\n");
    }
}