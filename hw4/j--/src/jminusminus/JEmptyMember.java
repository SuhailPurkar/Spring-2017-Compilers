
package jminusminus;

class JEmptyMember extends JStatement implements JMember {

    protected JEmptyMember(int line) {
        super(line);
    }

    public JAST analyze(Context context) {
        return this;
    }


    public void codegen(CLEmitter output) {
    }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JEmptyMember line=\"%d\"/>\n", line());
    }

	@Override
	public void preAnalyze(Context context, CLEmitter partial) {
	}
}