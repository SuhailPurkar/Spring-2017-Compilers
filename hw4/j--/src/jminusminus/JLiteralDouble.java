
package jminusminus;

import static jminusminus.CLConstants.*;


class JLiteralDouble extends JExpression {

    private String text;

    public JLiteralDouble(int line, String text) {
        super(line);
        this.text = text;
    }


    public JExpression analyze(Context context) {
          type = Type.DOUBLE;
          return this;
    }

    public void codegen(CLEmitter output) {
//        int i = Integer.parseInt(text);
//        switch (i) {
//        case 0:
//            output.addNoArgInstruction(DCONST_0);
//            break;
//        case 1:
//        	output.addNoArgInstruction(DCONST_1);
//            break;
//        default:
//                output.addLDCInstruction(i); 
//      }
    }
  
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralDouble line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }

}