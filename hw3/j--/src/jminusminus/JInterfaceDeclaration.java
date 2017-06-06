package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.*;

class JInterfaceDeclaration extends JAST implements JTypeDecl, JMember {

    private ArrayList<String> mods;
    private String name;
    private ArrayList<TypeName> superClasses;
    private ArrayList<JMember> interfaceBlock;
   
    public JInterfaceDeclaration(int line, ArrayList<String> mods, String name, ArrayList<TypeName> superClasses, ArrayList<JMember> interfaceBlock) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.superClasses = superClasses;
        this.interfaceBlock = interfaceBlock;
 }

    public String name() {
        return name;
    }

    public Type superType() {
        return null;
    }
    
    public void preAnalyze(Context context, CLEmitter partial) {
    	
    }

    public Type thisType() {
        return null;
    }


    public ArrayList<JFieldDeclaration> instanceFieldInitializations() {
        return null;
    }

    public void declareThisType(Context context) {
        
    }


    public void preAnalyze(Context context) {
      
    }

 
    public JAST analyze(Context context) {
        return this;
    }

 
    public void codegen(CLEmitter output) {
        }

    public void writeToStdOut(PrettyPrinter p) {
    	 p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\">\n", line(), name);
        
         p.indentRight();
         p.printf("<SuperClasses>\n");
         for (TypeName a : superClasses) {
         	p.indentRight();
         	p.printf("<SuperClass name=\"%s\"/>\n", a.toString());
         	p.indentLeft();
         }
         p.printf("</SuperClasses>\n");
         p.indentLeft();
        
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
         p.println("<InterfaceBlock>");
         if (interfaceBlock != null) {    
             p.indentRight();
             for (JMember member : interfaceBlock) {
                 ((JAST) member).writeToStdOut(p);
             }
             p.indentLeft();
         }
         p.println("</InterfaceBlock>");
         p.indentLeft();
         p.println("</JInterfaceDeclaration>");
    }

    private void codegenPartialImplicitConstructor(CLEmitter partial) {
      
    }

    private void codegenImplicitConstructor(CLEmitter output) {
       
    }

    private void codegenClassInit(CLEmitter output) {
    }
}