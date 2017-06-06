//HW3
//Mark Vo
//cs451
//This file is used to declare interfaces in java

package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;


class JInterfaceDeclaration extends JAST implements JTypeDecl, JMember {

    /** Class modifiers. */
    private ArrayList<String> mods;

    /** Class name. */
    private String name;

    /** Class block. */
    private ArrayList<JMember> classBlock;

    /** Super class type. */
    private Type superType;

    /** This class type. */
    private Type thisType;

    /** Context for this class. */
    private ClassContext context;

    /** Whether this class has an explicit constructor. */
    private boolean hasExplicitConstructor;

    /** Instance fields of this class. */
    private ArrayList<JFieldDeclaration> instanceFieldInitializations;

    /** Static (class) fields of this class. */
    private ArrayList<JFieldDeclaration> staticFieldInitializations;
	
	private ArrayList<TypeName> extendedClasses;
	private ArrayList<JMember> interfaceBlock;


    
    public JInterfaceDeclaration(int line, ArrayList<String> mods, String name,
         ArrayList<TypeName> extendedClasses, ArrayList<JMember> interfaceBlock) {
        super(line);
        this.mods = mods;
        this.name = name;
		this.extendedClasses = extendedClasses;
        this.interfaceBlock = interfaceBlock;

    }

    /**
     * Return the class name.
     * 
     * @return the class name.
     */

    public String name() {
		return null;
        //return name;
    }

    /**
     * Return the class' super class type.
     * 
     * @return the super class type.
     */

    public Type superType() {
        return null;
		//return superType;
    }

    /**
     * Return the type that this class declaration defines.
     * 
     * @return the defined type.
     */

    public Type thisType() {
		return null;
       // return thisType;
    }

    /**
     * The initializations for instance fields (now expressed as assignment
     * statments).
     * 
     * @return the field declarations having initializations.
     */

    public ArrayList<JFieldDeclaration> instanceFieldInitializations() {
        return null;
		//return instanceFieldInitializations;
    }

    /**
     * Declare this class in the parent (compilation unit) context.
     * 
     * @param context
     *            the parent (compilation unit) context.
     */

    public void declareThisType(Context context) {
		/*
		This is a stub
		*/
    }


    public void preAnalyze(Context context) {
		
		/*
		This is a stub
		*/
	
    }
	
	     public void preAnalyze(Context context, CLEmitter partial) {
    	
    }
	

    public JAST analyze(Context context) {
		/*
		This is a stub just returns null for now
		*/
		return null;
    }

    public void codegen(CLEmitter output) {
		/*
		This is a stub
		*/
    }



	 /*Nodified to print out interface info when parsed*/
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\"",line(), name);
        p.indentRight();
		
        if(extendedClasses != null) {
            p.printf("<ExtendedClasses>\n");
			int i = 0;
			for(i = 0; i < extendedClasses.size(); i++){
				p.indentRight();
				p.printf("<extendedClass name=\"" 
				+ extendedClasses.get(i).toString() + "\"/>\n");
			}
			
            p.indentLeft();
            p.printf("</ExtendedClasses>\n");
        }
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (classBlock != null) {
            p.println("<InterfaceBlock>");
            for (JMember member : classBlock) {
                ((JAST) member).writeToStdOut(p);
            }
            p.println("</InterfaceBlock>");
        }
        p.indentLeft();
        p.println("</JInterfaceDeclaration>");
    }



    private void codegenPartialImplicitConstructor(CLEmitter partial) {
		/*
		This is a stub
		*/
    }

    private void codegenImplicitConstructor(CLEmitter output) {
		/*
		This is a stub
		*/
    }
	
    private void codegenClassInit(CLEmitter output) {
		/*
		This is a stub
		*/
    }
	

}

