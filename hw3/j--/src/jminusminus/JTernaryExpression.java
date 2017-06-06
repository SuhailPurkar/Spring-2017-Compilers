//hw3 added based on JBinaryExpression
package jminusminus;
import static jminusminus.CLConstants.*;

abstract class JTernaryExpression extends JExpression {

    protected String operator;
    protected String separator;
    protected JExpression lhs;
    protected JExpression rhs;
    protected JExpression rhs2;
    protected JTernaryExpression(int line, String operator, String separator, JExpression lhs, JExpression rhs, JExpression rhs2) {
        super(line);
        this.operator = operator;
        this.separator = separator;
        this.lhs = lhs;
        this.rhs = rhs;
        this.rhs2 = rhs2;
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTernaryExpression line=\"%d\" type=\"%s\" operator=\"%s\">\n", line(), ((type == null) ? "" : type.toString()), Util.escapeSpecialXMLChars(operator));
        	p.indentRight();
        	p.printf("<Lhs>\n");
        		p.indentRight();
        		lhs.writeToStdOut(p);
        	p.indentLeft();
        	p.printf("</Lhs>\n");
    
        	p.printf("<TrueBranch>\n");
        		p.indentRight();
        		rhs.writeToStdOut(p);
        	p.indentLeft();
        	p.printf("</TrueBranch>\n");
        	p.printf("<FalseBranch>\n");
        		p.indentRight();
        		rhs2.writeToStdOut(p);
        	p.indentLeft();
        	p.printf("</FalseBranch>\n");
        p.indentLeft();
        p.printf("</JTernaryExpression>\n");
    }

}

class JQuestionOp extends JTernaryExpression {
	
    public JQuestionOp(int line, JExpression condition, JExpression correct, JExpression incorrect) {
        super(line, "?", ":", condition, correct, incorrect);
    }

    public JExpression analyze(Context context) { 
    	return this;
    }

    public void codegen(CLEmitter output) { 
        }
}