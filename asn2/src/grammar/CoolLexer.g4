lexer grammar CoolLexer;

tokens{
	ERROR,
	TYPEID,
	OBJECTID,
	BOOL_CONST,
	INT_CONST,
	STR_CONST,
	LPAREN,
	RPAREN,
	COLON,
	ATSYM,
	SEMICOLON,
	COMMA,
	PLUS,
	MINUS,
	STAR,
	SLASH,
	TILDE,
	LT,
	EQUALS,
	LBRACE,
	RBRACE,
	DOT,
	DARROW,
	LE,
	ASSIGN,
	CLASS,
	ELSE,
	FI,
	IF,
	IN,
	INHERITS,
	LET,
	LOOP,
	POOL,
	THEN,
	WHILE,
	CASE,
	ESAC,
	OF,
	NEW,
	ISVOID,
	NOT
}

/*
  DO NOT EDIT CODE ABOVE THIS LINE
*/

@members{

	/*
		YOU CAN ADD YOUR MEMBER VARIABLES AND METHODS HERE
	*/

	/**
	* Function to report errors.
	* Use this function whenever your lexer encounters any erroneous input
	* DO NOT EDIT THIS FUNCTION
	*/
	public void reportError(String errorString){
		setText(errorString);
		setType(ERROR);
	}

	public void processString() {
		Token t = _factory.create(_tokenFactorySourcePair, _type, _text, _channel, _tokenStartCharIndex, getCharIndex()-1, _tokenStartLine, _tokenStartCharPositionInLine);
		String text = t.getText();
		
		String res = new String();

		int n = text.length();

		if(n>1024)  {	//too long string
			reportError("String constant too long");
			return;
		}

		for (int i=1; i<n-1; i++) {
			char c = text.charAt(i), nx = text.charAt(i+1);
			if (c=='\\'){ //convert '\c' to 'c' when c is not 'n', 't', 'f', 'b'
				if (nx=='n' || nx=='t' || nx=='f' || nx=='b' || nx=='\\') 
					res+='\\';
				if(nx == '\\')
					i += 1;
			}
			else
				res+=c;
		}
		setText(res);		//modify the text of the token
		setType(STR_CONST);	//set token type to STR_CONST
	}
}


/*
	WRITE ALL LEXER RULES BELOW
*/

SEMICOLON   : ';';
DARROW      : '=>';
COLON       : ':';
COMMA       : ',';
PLUS        : '+';
ATSYM       : '@';
BOOL_CONST  : ('f' A L S E) | ('t' R U E);
CLASS       : C L A S S;
ELSE        : E L S E;
IF          : I F;
THEN        : T H E N;
FI          : F I;
ESLE        : E S L E;
WHILE       : W H I L E;
CASE        : C A S E;
ESAC        : E S A C;
INHERITS    : I N H E R I T S;
IN          : I N;
ISVOID      : I S V O I D;
LET         : L E T;
LOOP        : L O O P;
POOL        : P O O L;
NEW         : N E W;
OF          : O F;
NOT         : N O T;
WS          : [ \t\n\r\b\f]+ -> skip;
TYPEID      : [A-Z]ID;
OBJECTID    : [a-z]ID;
LPAREN      : '(';
RPAREN      : ')';
MINUS       : '-';
STAR        : '*';
SLASH       : '/';
TILDE       : '~';
LT          : '<';
EQUALS      : '=';
LBRACE      : '{';
RBRACE      : '}';
DOT         : '.';
LE          : '<=';
ASSIGN      : '<-';
INT_CONST   : Digit+;

//when a left quote in encountered, switch mode to INSTR
L_QUOTE     : '"' -> more, mode(INSTR);

//when (* in encountered, switch mode to INCOMM
L_COMM      : '(*' -> more, pushMode(INCOMM);


//unmatched *) 
UN_COMM     : '*)' {reportError("Unmatched *)");};

//single line comments
SL_COMM     : '--' .*? '\n' -> skip;

//invalid character
INVALID     : . {reportError(getText());};

fragment ID    : [a-zA-Z0-9_]*;
fragment Digit : [0-9];
fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];

/*-----------------Inside a string--------------------------*/

mode INSTR;	//this mode is active when inside a string

/*when the string in completed (by a matching '"'), process it
  and switch to default mode.
  */
R_QUOTE     : '"'{processString();} -> mode(DEFAULT_MODE);

EOFINSTR    : ('\n')* EOF {reportError("EOF in string constant");};

//when an unescaped newline is encountered, report error and switch to DEFAULT_MODE
UNESC_N     : '\n'{reportError("Unterminated string constant");} -> mode(DEFAULT_MODE);

//handle escaped characters
ESC         : '\\' ('\n'|'\b'|'"'|'\\'|'\t'|'\f') -> more;

//when a null character is encountered, switch to NULLCHR to ignore the substring following it
NULL        : '\u0000' (.|ESC)*? ('\n'|'"') 
	      {reportError("Null character in string constant");} -> mode(NULLCHR);

//consume all other characters
TEXT        : . -> more;

/*------------------------------------------------------------*/

/*--------------------Inside a comment------------------------*/

mode INCOMM;	//this mode is active when inside a comment

//on encountering a *), go a level up. Meaning out of the current comment block
R_COMM      : '*)' -> skip, popMode;

/*when a (* is encountered inside a comment, go a level deep.
  Meaning begin another nested block comment
*/
L_AGAIN     : '(*' -> more, pushMode(INCOMM);

//when eof is encountered inside a comment, report error			      
EOFINCOM    : EOF {reportError("EOF in comment");};

//consume all other characters
IGNORE      : . -> more;			      

/*------------------------------------------------------------*/

/*------------null character detected inside a string---------*/

mode NULLCHR;

STOP        : ('"'|'\n') -> skip, mode(DEFAULT_MODE);
ESCP        : '\\' ('\n'|'\b'|'"'|'\\'|'\t'|'\f') -> more;
THROW       : . -> more;

/*-------------------------------------------------------------*/
