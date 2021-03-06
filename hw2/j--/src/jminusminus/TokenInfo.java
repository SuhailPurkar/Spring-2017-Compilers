// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

/**
 * An enum of token kinds. Each entry in this enum represents the kind of a
 * token along with its image (string representation).
 * 
 * When you add a new token to the scanner, you must also add an entry to this
 * enum specifying the kind and image of the new token.
 */

enum TokenKind {
    EOF("<EOF>"), ABSTRACT("abstract"), BOOLEAN("boolean"), CHAR("char"), CLASS(
            "class"), ELSE("else"), EXTENDS("extends"), FALSE("false"), IF("if"), IMPORT(
            "import"), INSTANCEOF("instanceof"), INT("int"), NEW("new"), NULL(
            "null"), PACKAGE("package"), PRIVATE("private"), PROTECTED(
            "protected"), PUBLIC("public"), RETURN("return"), STATIC("static"), SUPER(
            "super"), THIS("this"), TRUE("true"), VOID("void"), WHILE("while"), PLUS(
            "+"), ASSIGN("="), DEC("--"), EQUAL("=="), GT(">"), INC("++"), LAND(
            "&&"), LE("<="), LNOT("!"), MINUS("-"), PLUS_ASSIGN("+="), STAR("*"), LPAREN(
            "("), RPAREN(")"), LCURLY("{"), RCURLY("}"), LBRACK("["), RBRACK(
            "]"), SEMI(";"), COMMA(","), DOT("."), IDENTIFIER("<IDENTIFIER>"), INT_LITERAL(
            "<INT_LITERAL>"), CHAR_LITERAL("<CHAR_LITERAL>"), STRING_LITERAL(
            "<STRING_LITERAL>"),
            //HW1 added with divide and modulo
            DIV("/"), MOD("%"),
            //Hw1 Extracredit
            AND("&"), XOR("^"), OR("|"),
            //Hw2 added keywords
            ASSERT("asset"), BREAK("break"), BYTE("byte"), 
            CASE("case"), CATCH("catch"), CONST("const"), CONTINUE("continue"), 
            DEFAULT("default"), DO("do"), 
            ENUM("enum"),
            FINAL("final") ,FINALLY("finally"), FOR("for"),
            GOTO("goto"),
            IMPLEMENTS("implements"), INTERFACE("interface"),
            LONG("long"), FLOAT("float"), DOUBLE ("double"),
            NATIVE("native"),
            SHORT("short"), STRICTFP("strictfp"), SWITCH("switch"), SYNCHRONIZED("synchronized"),
            THROW("throw"), THROWS("throws"), TRANSIENT("transient"), TRY("try"),
            VOLATILE("volatile"),
            //hw2 added operators
            MINUS_ASSIGN("-="), STAR_ASSIGN("*="),DIV_ASSIGN("/="), MOD_ASSIGN("%="),
            GE(">="),LT("<"),
            AND_ASSIGN("&="), XOR_ASSIGN("^="), OR_ASSIGN("|="),
            RS(">>"), LS("<<"), ZRS(">>>"),
            RS_ASSIGN(">>="), ZRS_ASSIGN(">>>="), LS_ASSIGN("<<="),
            COLON(":"),COMPLEMENT("~"), QUESTION("?"),
            LOR("||"),NOT_EQUAL("!="),
            //hw2 added literals
            LONG_LITERAL("<LONG_LITERAL>"), 
            FLOAT_LITERAL("<FLOAT_LITERAL>"),
            DOUBLE_LITERAL("<DOUBLE_LITERAL>")
            
            
            ;

    /** The token's string representation. */
    private String image;

    /**
     * Construct an instance TokenKind given its string representation.
     * 
     * @param image
     *            string representation of the token.
     */

    private TokenKind(String image) {
        this.image = image;
    }

    /**
     * Return the image of the token.
     * 
     * @return the token's image.
     */

    public String image() {
        return image;
    }

    /**
     * Return the string representation of the token.
     * 
     * @return the token's string representation.
     */

    public String toString() {
        return image;
    }

}

/**
 * A representation of tokens returned by the lexical analyzer method,
 * getNextToken(). A token has a kind identifying what kind of token it is, an
 * image for providing any semantic text, and the line in which it occurred in
 * the source file.
 */

class TokenInfo {

    /** Token kind. */
    private TokenKind kind;

    /**
     * Semantic text (if any). For example, the identifier name when the token
     * kind is IDENTIFIER. For tokens without a semantic text, it is simply its
     * string representation. For example, "+=" when the token kind is
     * PLUS_ASSIGN.
     */
    private String image;

    /** Line in which the token occurs in the source file. */
    private int line;

    /**
     * Construct a TokenInfo from its kind, the semantic text forming the token,
     * and its line number.
     * 
     * @param kind
     *            the token's kind.
     * @param image
     *            the semantic text comprising the token.
     * @param line
     *            the line in which the token occurs in the source file.
     */

    public TokenInfo(TokenKind kind, String image, int line) {
        this.kind = kind;
        this.image = image;
        this.line = line;
    }

    /**
     * Construct a TokenInfo from its kind, and its line number. Its image is
     * simply its string representation.
     * 
     * @param kind
     *            the token's identifying number.
     * @param line
     *            identifying the line on which the token was found.
     */

    public TokenInfo(TokenKind kind, int line) {
        this(kind, kind.toString(), line);
    }

    /**
     * Return the token's string representation.
     * 
     * @return the string representation.
     */

    public String tokenRep() {
        return kind.toString();
    }

    /**
     * Return the semantic text associated with the token.
     * 
     * @return the semantic text.
     */

    public String image() {
        return image;
    }

    /**
     * Return the line number associated with the token.
     * 
     * @return the line number.
     */

    public int line() {
        return line;
    }

    /**
     * Return the token's kind.
     * 
     * @return the kind.
     */

    public TokenKind kind() {
        return kind;
    }

    /**
     * Return the token's image.
     * 
     * @return the image.
     */

    public String toString() {
        return image;
    }

}
