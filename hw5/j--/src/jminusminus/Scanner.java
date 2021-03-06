// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Hashtable;
import static jminusminus.TokenKind.*;

/**
 * A lexical analyzer for j--, that has no backtracking mechanism.
 * 
 * When you add a new token to the scanner, you must also add an entry in the
 * TokenKind enum in TokenInfo.java specifying the kind and image of the new
 * token.
 */

class Scanner {

    /** End of file character. */
    public final static char EOFCH = CharReader.EOFCH;

    /** Keywords in j--. */
    private Hashtable<String, TokenKind> reserved;

    /** Source characters. */
    private CharReader input;

    /** Next unscanned character. */
    private char ch;

    /** Whether a scanner error has been found. */
    private boolean isInError;

    /** Source file name. */
    private String fileName;

    /** Line number of current token. */
    private int line;

    /**
     * Construct a Scanner object.
     * 
     * @param fileName
     *            the name of the file containing the source.
     * @exception FileNotFoundException
     *                when the named file cannot be found.
     */

    public Scanner(String fileName) throws FileNotFoundException {
        this.input = new CharReader(fileName);
        this.fileName = fileName;
        isInError = false;

        // Keywords in j--
        reserved = new Hashtable<String, TokenKind>();
        reserved.put(ABSTRACT.image(), ABSTRACT);
        reserved.put(BOOLEAN.image(), BOOLEAN);
        reserved.put(CHAR.image(), CHAR);
        reserved.put(CLASS.image(), CLASS);
        reserved.put(ELSE.image(), ELSE);
        reserved.put(EXTENDS.image(), EXTENDS);
        reserved.put(FALSE.image(), FALSE);
        reserved.put(IF.image(), IF);
        reserved.put(IMPORT.image(), IMPORT);
        reserved.put(INSTANCEOF.image(), INSTANCEOF);
        reserved.put(INT.image(), INT);
        reserved.put(NEW.image(), NEW);
        reserved.put(NULL.image(), NULL);
        reserved.put(PACKAGE.image(), PACKAGE);
        reserved.put(PRIVATE.image(), PRIVATE);
        reserved.put(PROTECTED.image(), PROTECTED);
        reserved.put(PUBLIC.image(), PUBLIC);
        reserved.put(RETURN.image(), RETURN);
        reserved.put(STATIC.image(), STATIC);
        reserved.put(SUPER.image(), SUPER);
        reserved.put(THIS.image(), THIS);
        reserved.put(TRUE.image(), TRUE);
        reserved.put(VOID.image(), VOID);
        reserved.put(WHILE.image(), WHILE);
		reserved.put(CONTINUE.image(), CONTINUE);
		//New keywords added
		reserved.put(FOR.image(), FOR);
        reserved.put(SWITCH.image(), SWITCH);
        reserved.put(ASSERT.image(), ASSERT);
        reserved.put(DEFAULT.image(), DEFAULT);
        reserved.put(GOTO.image(), GOTO);
        reserved.put(SYNCHRONIZED.image(), SYNCHRONIZED);
        reserved.put(DO.image(), DO);
        reserved.put(BREAK.image(), BREAK);
        reserved.put(BYTE.image(), BYTE);
        reserved.put(CASE.image(), CASE);
        reserved.put(ENUM.image(), ENUM);
        reserved.put(TRANSIENT.image(), TRANSIENT);
        reserved.put(CATCH.image(), CATCH);
        reserved.put(TRY.image(), TRY);
        reserved.put(FINAL.image(), FINAL);
        reserved.put(INTERFACE.image(), INTERFACE);
        reserved.put(LONG.image(), LONG);
        reserved.put(STRICTFP.image(), STRICTFP);
        reserved.put(VOLATILE.image(), VOLATILE);
        reserved.put(FLOAT.image(), FLOAT);
        reserved.put(NATIVE.image(), NATIVE);
        reserved.put(CONTINUE.image(), CONTINUE);
		reserved.put(DOUBLE.image(), DOUBLE);
		reserved.put(IMPLEMENTS.image(), IMPLEMENTS);

        // Prime the pump.
        nextCh();
    }

    /**
     * Scan the next token from input.
     * 
     * @return the the next scanned token.
     */

    public TokenInfo getNextToken() {
        StringBuffer buffer;
        boolean moreWhiteSpace = true;
        while (moreWhiteSpace) {
            while (isWhitespace(ch)) {
                nextCh();
            }
			
			if (ch == '/') {
				nextCh();
				
				//If a '*' appears after the first '/' that means there
				//is a multiline comment
				if(ch == '*'){
					while(ch != EOFCH){
						nextCh();
						//At the end of a multiline comment?
						if(ch == '*'){
							nextCh();
							//Found end of multiline comment break out of lopp
							if(ch == '/'){
								break;
							}
						}
					}
					
					if(ch == EOFCH){
					//Ending to the multiline comment is missing, report error.
					reportScannerError("Closing to MultiLine Comment Missing");
					}
				}

				
			if(ch == '='){
				nextCh();
				return new TokenInfo(DIV_ASSIGN, line);
			}
				
				if (ch == '/') {
					// CharReader maps all new lines to ’\n’
					while (ch != '\n' && ch != EOFCH) {
						nextCh();
						}
					}
					
				else {
					return new TokenInfo(DIV , line);
				}	
			}

			else {
                moreWhiteSpace = false;
            }
        }
        line = input.line();
        switch (ch) {
        case '(':
            nextCh();
            return new TokenInfo(LPAREN, line);
        case ')':
            nextCh();
            return new TokenInfo(RPAREN, line);
        case '{':
            nextCh();
            return new TokenInfo(LCURLY, line);
        case '}':
            nextCh();
            return new TokenInfo(RCURLY, line);
        case '[':
            nextCh();
            return new TokenInfo(LBRACK, line);
        case ']':
            nextCh();
            return new TokenInfo(RBRACK, line);
        case ';':
            nextCh();
            return new TokenInfo(SEMI, line);
        case ',':
            nextCh();
            return new TokenInfo(COMMA, line);
        case '=':
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(EQUAL, line);
            } else {
                return new TokenInfo(ASSIGN, line);
            }
        case '!':
            nextCh();
			//if next token is '=' that means there is a '!=' operator
			if(ch == '='){
				nextCh();
				return new TokenInfo(NOTEQUAL, line);
			}
			//Then treat as '!' operator
			else{
				    return new TokenInfo(LNOT, line);
			}
        
        case '*':
            nextCh();
			if(ch == '='){
				nextCh();
				return new TokenInfo(MULT_ASSIGN, line);
			}
			else
            return new TokenInfo(STAR, line);
		case '%':
            nextCh();
			if(ch == '='){
				nextCh();
				return new TokenInfo(MOD_ASSIGN, line);
			}
			else
				return new TokenInfo(MOD, line);
        case '+':
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(PLUS_ASSIGN, line);
            } else if (ch == '+') {
                nextCh();
                return new TokenInfo(INC, line);
            } else {
                return new TokenInfo(PLUS, line);
            }
        case '-':
            nextCh();
            if (ch == '-') {
                nextCh();
                return new TokenInfo(DEC, line);
            } 
			if(ch == '='){
				nextCh();
				return new TokenInfo(SUB_ASSIGN, line);
			}
			else {
                return new TokenInfo(MINUS, line);
            }
        case '&':
            nextCh();
            if (ch == '&') {
                nextCh();
                return new TokenInfo(LAND, line);
            }
			if(ch == '='){
				nextCh();
				return new TokenInfo(BITAND_ASSIGN, line);
			}
			else {
				return new TokenInfo(BITAND, line);
            }
        case '>':
            nextCh();
			//if the following char  is '=' that means its the '>=' operator
			if(ch == '='){
				nextCh();
				return new TokenInfo(GTE, line);
			}
			//if another > appears check if the next one is another >
			if(ch == '>'){
				nextCh();
				
				if(ch == '='){
					nextCh();
					return new TokenInfo(RSHIFT_ASSIGN, line);
				}
				//if it does appear then its a unsigned right shift
				if(ch == '>'){
					nextCh();
					if(ch == '='){
						nextCh();
						return new TokenInfo(URSHIFT_ASSIGN, line);
					}
					else
					return new TokenInfo(URSHIFT, line);
				}
				//if not then its a singed right shift
				else{
					return new TokenInfo(RSHIFT, line);
				}
			}
			//otherwise its the regular '>' operator
			else
            return new TokenInfo(GT, line);
        case '<':
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(LE, line);
            }
			//if another < appears then it is bit shift left
			if(ch == '<'){
				nextCh();
				
				if(ch == '='){
					nextCh();
					return new TokenInfo(LSHIFT_ASSIGN, line);
				}
				else
					return new TokenInfo(LSHIFT, line);
			}
			//otherwise its the less than symbol
			else {
                return new TokenInfo(LT, line);
            }
		//Creating a new case for the char '|'
		case '|':
			nextCh();
			if(ch == '='){
				nextCh();
				return new TokenInfo(BINOR_ASSIGN, line);
			}
			
			//If another '|' appears that means its a logical or operator
			if(ch == '|'){
				nextCh();
				return new TokenInfo(OR, line);
			}
		    //else its for bit wise inlusive or
			else{
				return new TokenInfo(BINOR, line);
			}
			
	//Creating a new case for ?:
		case '?':
			nextCh();
			return new TokenInfo(TERNARY, line);	
			
			
		case ':':
			nextCh();
			return new TokenInfo(COLON, line);
		//creating case for bit complement
		case '~':
			nextCh();
			return new TokenInfo(BITCOMP, line);
			
		//creating case for bit wise exclusive or
		case '^':
			nextCh();
			if(ch == '='){
				nextCh();
				return new TokenInfo(BEXOR_ASSIGN, line);
			}
			else
				return new TokenInfo(BEXOR, line);
        case '\'':
            buffer = new StringBuffer();
            buffer.append('\'');
            nextCh();
            if (ch == '\\') {
                nextCh();
                buffer.append(escape());
            } else {
                buffer.append(ch);
                nextCh();
            }
            if (ch == '\'') {
                buffer.append('\'');
                nextCh();
                return new TokenInfo(CHAR_LITERAL, buffer.toString(), line);
            } else {
                // Expected a ' ; report error and try to
                // recover.
                reportScannerError(ch
                        + " found by scanner where closing ' was expected.");
                while (ch != '\'' && ch != ';' && ch != '\n') {
                    nextCh();
                }
                return new TokenInfo(CHAR_LITERAL, buffer.toString(), line);
            }
        case '"':
            buffer = new StringBuffer();
            buffer.append("\"");
            nextCh();
            while (ch != '"' && ch != '\n' && ch != EOFCH) {
                if (ch == '\\') {
                    nextCh();
                    buffer.append(escape());
                } else {
                    buffer.append(ch);
                    nextCh();
                }
            }
            if (ch == '\n') {
                reportScannerError("Unexpected end of line found in String");
            } else if (ch == EOFCH) {
                reportScannerError("Unexpected end of file found in String");
            } else {
                // Scan the closing "
                nextCh();
                buffer.append("\"");
            }
            return new TokenInfo(STRING_LITERAL, buffer.toString(), line);
        case '.':
            nextCh();
            return new TokenInfo(DOT, line);
        case EOFCH:
            return new TokenInfo(EOF, line);
        case '0':
            // hw2 editted to handled octal and hexal numbers
            nextCh();
			//if the next char is an 'x' thats beginning of hexal
			if(ch == 'x' || ch == 'X'){
				nextCh();
				if(ch == '_'){
					nextCh();
					reportScannerError(" 0x_ is Invalid Format");
					return getNextToken();					
				}
				
				//if ch is 0-9 or A-F then its part of the hex number
				buffer = new StringBuffer();
				buffer.append("0x");
				while(ishexNumber(ch)){
					if(ch == '_'){
						buffer.append(ch);
						nextCh();
						//if the next is not an hex number then its an invalid
						//underscore format, return error
						if(!ishexNumber(ch)){
							buffer.append(ch);
							reportScannerError("" +buffer + " is Invalid Format");
							return getNextToken();	
						}
					}
					buffer.append(ch);
					nextCh();
				}
				return new TokenInfo(HEXADECIMAL_LITERAL, buffer.toString(), line);
			}
			//else it could be an octal number
			if(isoctalNumber(ch)){
				//if ch is 0-7 then its part of the octal number
				buffer = new StringBuffer();
				buffer.append("0");
				while(isoctalNumber(ch)){
					if(ch == '_'){
						buffer.append(ch);
						nextCh();
						//if the next is not an octal number then its an invalid
						//underscore format, return error
						if(!(isoctalNumber(ch))){
							buffer.append(ch);
							reportScannerError("" +buffer + " is Invalid Format");
							 return getNextToken();
						}
					}
					buffer.append(ch);
					nextCh();	
				}	
				return new TokenInfo(OCTALDECIMAL_LITERAL, buffer.toString(), line);
			}
			else
            return new TokenInfo(INT_LITERAL, "0", line);
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            buffer = new StringBuffer();
            while (isDigit(ch)) {
                buffer.append(ch);
                nextCh();
				//an underscore has appeared
				if(ch == '_'){
					//while there are still more underscores
					while(ch == '_'){
						buffer.append(ch);
						nextCh();
					}
					//if the next char isnt a number or a underscore it is a bad format
					if((!(ch == '_')) && (!(isDigit(ch)))){
							buffer.append(ch);
							reportScannerError("" +buffer + " is Invalid Format");
							return getNextToken();
					}
				}
				//if there is a decimal there is double precision
				if(ch == '.'){
					buffer.append(ch);
					nextCh();
					//if anything else besides a number appears, bad format
					if(!(isDigit(ch))){
							buffer.append(ch);
							nextCh();
							reportScannerError("" +buffer + " is Invalid Format");
							return getNextToken();
					}
					
					//while there is still a digit, at the end report a decimal
					while(isDigit(ch)){
						buffer.append(ch);
						nextCh();
						//an underscore has appeared
						if(ch == '_'){
						//while there are still more underscores
							while(ch == '_'){
								buffer.append(ch);
								nextCh();
							}

							//if the next char isnt a number or a underscore it is a bad format
							if((!(ch == '_')) && (!(isDigit(ch)))){
								buffer.append(ch);
								nextCh();
								reportScannerError("" +buffer + " is Invalid Format");
								return getNextToken();
							}	
						}
						//if there is an exponent, has to be after a '.' or in
						//or in a number without a '.' at all
						if(ch == 'E' || ch == 'e'){
							buffer.append(ch);
							nextCh();
							//change the exponent negative
							if(ch == '-'){
								buffer.append(ch);
								nextCh();
							}								
							if(isDigit(ch)){
								while(isDigit(ch)){
									buffer.append(ch);
									nextCh();
									//an underscore has appeared
									if(ch == '_'){
									//while there are still more underscores
										while(ch == '_'){
											buffer.append(ch);
											nextCh();
										}

										//if the next char isnt a number or a underscore it is a bad format
										if((!(ch == '_')) && (!(isDigit(ch)))){
											buffer.append(ch);
											nextCh();
											reportScannerError("" +buffer + " is Invalid Format");
											return getNextToken();
										}	
									}
								}
								//if it ends with an f or F then its a float
								if(ch == 'F' || ch == 'f'){
									nextCh();
									return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
								}
								return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);	
							}
							//if there is no number then bad format
							else{
								buffer.append(ch);
								nextCh();
								reportScannerError("" +buffer + " is Invalid Format");
								return getNextToken();
							}
							
						}
					}
					//if it ends with an f or F then its a float
					if(ch == 'F' || ch == 'f'){
						nextCh();
						return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
					}
					 return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
				}
				
						//if there is an exponent, has to be after a '.' or in
						//or in a number without a '.' at all
						if(ch == 'E' || ch == 'e'){
							buffer.append(ch);
							nextCh();
							//change the exponent negative
							if(ch == '-'){
								buffer.append(ch);
								nextCh();
							}
							
							else{
								if(ch == '+'){
									buffer.append(ch);
									nextCh();
								}
							}

							
							if(isDigit(ch)){
								while(isDigit(ch)){
									buffer.append(ch);
									nextCh();
									//an underscore has appeared
									if(ch == '_'){
									//while there are still more underscores
										while(ch == '_'){
											buffer.append(ch);
											nextCh();
										}

										//if the next char isnt a number or a underscore it is a bad format
										if((!(ch == '_')) && (!(isDigit(ch)))){
											buffer.append(ch);
											nextCh();
											reportScannerError("" +buffer + " is Invalid Format");
											return getNextToken();
										}	
									}
								}
								//if it ends with an f or F then its a float
								if(ch == 'F' || ch == 'f'){
									nextCh();
									return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
								}
								return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);	
							}
							//if there is no number then bad format
							else{
								buffer.append(ch);
								nextCh();
								reportScannerError("" +buffer + " is Invalid Format");
								return getNextToken();
							}
							
						}
				
				if(ch == 'L' || ch == 'l'){
					nextCh();
				buffer.append("L");
					return new TokenInfo(LONG_LITERAL, buffer.toString(), line);
				}
            }
			
			//if it ends with an f or F then its a float
			if(ch == 'F' || ch == 'f'){
				buffer.append(".0");
				nextCh();
				return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
			}			
            return new TokenInfo(INT_LITERAL, buffer.toString(), line);
        default:
            if (isIdentifierStart(ch)) {
                buffer = new StringBuffer();
                while (isIdentifierPart(ch)) {
                    buffer.append(ch);
                    nextCh();
                }
                String identifier = buffer.toString();
                if (reserved.containsKey(identifier)) {
                    return new TokenInfo(reserved.get(identifier), line);
                } else {
                    return new TokenInfo(IDENTIFIER, identifier, line);
                }
            } else {
                reportScannerError("Unidentified input token: '%c'", ch);
                nextCh();
                return getNextToken();
            }
        }
    }

    /**
     * Scan and return an escaped character.
     * 
     * @return escaped character.
     */

    private String escape() {
        switch (ch) {
        case 'b':
            nextCh();
            return "\\b";
        case 't':
            nextCh();
            return "\\t";
        case 'n':
            nextCh();
            return "\\n";
        case 'f':
            nextCh();
            return "\\f";
        case 'r':
            nextCh();
            return "\\r";
        case '"':
            nextCh();
            return "\"";
        case '\'':
            nextCh();
            return "\\'";
        case '\\':
            nextCh();
            return "\\\\";
		//if a unicode character appears
		case 'u':
			//Create a new string buffer to return the full unicode
			StringBuffer temp = new StringBuffer();
			temp.append("\\u");
			nextCh();
			//Check the next four chars and if one is not a unicode number then
			//report an error
			if(isUniChar(ch)){
				temp.append(ch);
				nextCh();
				if(isUniChar(ch)){
					temp.append(ch);
					nextCh();
					if(isUniChar(ch)){
						temp.append(ch);
						nextCh();						
						if(isUniChar(ch)){
							temp.append(ch);
							nextCh();
							return temp.toString();
						}
						else{
							reportScannerError("Badly formed unicode escape char", ch);
							nextCh();
							return "";
						}
					}
					else{
						reportScannerError("Badly formed unicode escape char", ch);
						nextCh();
						return "";
					}
				}
				else{
					reportScannerError("Badly formed unicode escape char", ch);
					nextCh();
					return "";
				}
			}
			else{
				 reportScannerError("Badly formed unicode escape char", ch);
				nextCh();
				return "";
			}
        default:
            reportScannerError("Badly formed escape: \\%c", ch);
            nextCh();
            return "";
        }
    }
	
	private boolean isUniChar(char c){
		if((ch == 'A') || (ch == 'B') || (ch == 'C') || (ch == 'D') || 
		(ch == 'E') || (ch == 'F') || (ch == 'a') || (ch == 'b') || 
		(ch == 'c') || (ch == 'd') || (ch == 'e') || (ch == 'f') ||
		(ch >= '0' && ch <= '9')){
			return true;
		}
		else return false;
	}
	private boolean ishexNumber(char ch){
		if((ch == 'A') || (ch == 'B') || (ch == 'C') || (ch == 'D') || 
		(ch == 'E') || (ch == 'F') || (ch == 'a') || (ch == 'b') || 
		(ch == 'c') || (ch == 'd') || (ch == 'e') || (ch == 'f') ||
		(ch >= '0' && ch <= '9') || (ch == '_')){
			return true;
		}
		else return false;
	}
	
	private boolean isoctalNumber(char ch){
		if((ch >= '0' && ch <= '7') || (ch == '_')){
			return true;
		}
		else return false;
		
	}
    /**
     * Advance ch to the next character from input, and update the line number.
     */

    private void nextCh() {
        line = input.line();
        try {
            ch = input.nextChar();
        } catch (Exception e) {
            reportScannerError("Unable to read characters from input");
        }
    }

    /**
     * Report a lexcial error and record the fact that an error has occured.
     * This fact can be ascertained from the Scanner by sending it an
     * errorHasOccurred() message.
     * 
     * @param message
     *            message identifying the error.
     * @param args
     *            related values.
     */

    private void reportScannerError(String message, Object... args) {
        isInError = true;
        System.err.printf("%s:%d: ", fileName, line);
        System.err.printf(message, args);
        System.err.println();
    }

    /**
     * Return true if the specified character is a digit (0-9); false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    /**
     * Return true if the specified character is a whitespace; false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isWhitespace(char c) {
        switch (c) {
        case ' ':
        case '\t':
        case '\n': // CharReader maps all new lines to '\n'
        case '\f':
            return true;
        }
        return false;
    }

    /**
     * Return true if the specified character can start an identifier name;
     * false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isIdentifierStart(char c) {
        return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c == '$');
    }

    /**
     * Return true if the specified character can be part of an identifier name;
     * false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isIdentifierPart(char c) {
        return (isIdentifierStart(c) || isDigit(c));
    }

    /**
     * Has an error occurred up to now in lexical analysis?
     * 
     * @return true or false.
     */

    public boolean errorHasOccurred() {
        return isInError;
    }

    /**
     * The name of the source file.
     * 
     * @return name of the source file.
     */

    public String fileName() {
        return fileName;
    }

}

/**
 * A buffered character reader. Abstracts out differences between platforms,
 * mapping all new lines to '\n'. Also, keeps track of line numbers where the
 * first line is numbered 1.
 */

class CharReader {

    /** A representation of the end of file as a character. */
    public final static char EOFCH = (char) -1;

    /** The underlying reader records line numbers. */
    private LineNumberReader lineNumberReader;

    /** Name of the file that is being read. */
    private String fileName;

    /**
     * Construct a CharReader from a file name.
     * 
     * @param fileName
     *            the name of the input file.
     * @exception FileNotFoundException
     *                if the file is not found.
     */

    public CharReader(String fileName) throws FileNotFoundException {
        lineNumberReader = new LineNumberReader(new FileReader(fileName));
        this.fileName = fileName;
    }

    /**
     * Scan the next character.
     * 
     * @return the character scanned.
     * @exception IOException
     *                if an I/O error occurs.
     */

    public char nextChar() throws IOException {
        return (char) lineNumberReader.read();
    }

    /**
     * The current line number in the source file, starting at 1.
     * 
     * @return the current line number.
     */

    public int line() {
        // LineNumberReader counts lines from 0.
        return lineNumberReader.getLineNumber() + 1;
    }

    /**
     * Return the file name.
     * 
     * @return the file name.
     */

    public String fileName() {
        return fileName;
    }

    /**
     * Close the file.
     * 
     * @exception IOException
     *                if an I/O error occurs.
     */

    public void close() throws IOException {
        lineNumberReader.close();
    }

}
