
package jminusminus;

import static jminusminus.CLConstants.*;


class JLiteralLong extends JExpression {

    private String text;


    public JLiteralLong(int line, String text) {
        super(line);
        this.text = text;
    }

    public JExpression analyze(Context context) {
    	type = Type.LONG;
        return this;
    }

    public void codegen(CLEmitter output) {
        
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralLong line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }

}
