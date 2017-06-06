// Mark Vo
//cs451
//hw3

package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.*;

class JInstanceBlock extends JAST implements  JMember {

	private ArrayList<String> mods;
	private JBlock body;
	private LocalContext context;
	private ArrayList<JStatement> statements;
	
	public ArrayList<JStatement> statements() { 
    	return statements; 
    	}
    public JInstanceBlock(int line, ArrayList<String> mods, JBlock body) {
        super(line);
        this.mods = mods;
		this.body = body;
		
    }
    


 
    public JInstanceBlock analyze(Context context) {
    	this.context = new LocalContext(context);

		for (int i = 0; i < statements.size(); i++) {
			statements.set(i, (JStatement) statements.get(i).analyze(this.context));
		}
        return this;
    }

    public void codegen(CLEmitter output) {
    	for (JStatement body : statements) {
			body.codegen(output);
		}
    }
		
	     public void preAnalyze(Context context, CLEmitter partial) {
    
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInstanceBlock line=\"%d\"",line());
        p.indentRight();
 
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
		
	
            p.println("<Body>");
            p.indentRight();
			body.writeToStdOut(p);
            p.indentLeft();
            p.println("</Body>");
        
		
  
        p.indentLeft();
        p.println("</JInstanceBlock>");
    }

}
