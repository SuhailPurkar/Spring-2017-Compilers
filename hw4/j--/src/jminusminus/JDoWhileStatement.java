
package jminusminus;

import static jminusminus.CLConstants.*;



class JDoWhileStatement extends JStatement {

    private JExpression condition;

    private JStatement body;

    public JDoWhileStatement(int line, JStatement body, JExpression condition) {
        super(line);
        this.body = body;
        this.condition = condition;
    }

    public JDoWhileStatement analyze(Context context) {
        condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        body.analyze(context);
        return this;
    }

    public void codegen(CLEmitter output) {

    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JDoWhileStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.indentLeft();
        p.printf("</JDoWhileStatement>\n");
    }

}