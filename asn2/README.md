# COOL Compiler #
# Ibrahim Dalal, CS14BTECH11041 #

Welcome to COOL compiler.
=========================

Write-Up
--------

Keywords
	
	Keywords are case-insensitive in COOL, except for 'true' and 'false', which must have begin with 't' and 'f' respectively.
	Fragment rules are written to help in matching small and capital letters alike wherever required. For example, the rule 

	fragment A : [aA],

	helps in case-insensitive match of 'a'/'A'. So the keyword 'while' is matched with the following rule:

	WHILE : W H I L E

Identifiers
	
	COOL has two types of identifiers: object identifiers and type identifiers. An identifier is a string containing letters, digits, and underscore symbol, and must begin with a letter. Object identifier must begin with a small case while type identifier with upper case. In order to match keywords before identifiers (as keywords also satisfy identifier rules), the rules for keywords are placed before that of identifiers.

Integers
	
	Integers are non-empty strings of digits 0-9. The fragment rule,
	
	Digit : [0-9]

	matches a single digit. Hence, integers are just 'Digit+'

String constants
	
	Strings are enclosed in double quotes "...". Within a string, a sequence ‘\c’ denotes the character ‘c’, with the exception of '\n', '\f', '\b', '\f'. When a left quote is encountered, lexer switches to INSTR mode which means that it will now match characters as they appear in strings. Switching the mode simplifies the task of enforcing rules for a particular construct of the language. By doing this, different types of constructs can be worked out in isolation. Exceptions such as unterminated string constant, eof inside a string, etc are all handled inside this  mode.

Single Line Comments
	
	Any characters between two dashes “--” and the next newline (or EOF, if there is no next newline) are treated as comments. "--.*?'\n'" handles this.

Block comments
	
	Comments may also be written by enclosing text in (∗ . . . ∗). This form of comment may be nested. The concept of pushMode and popMode helps here. Lexer pushes the current mode on a stack and switches  to mode INCOMM on encountering '(*'. On encountering a '*)' inside this mode, it pops the stack once and switches to that mode. All other characters are consumed with the exception of EOF, which is reported as an error.

Whitespaces
	
	Whitespaces are handled with simple rule [ \t\n\b\f\v]+.

Test cases
	
	Test cases cover most of the errors/fallacies that might go unnoticed. They are designed with a view to build a robust lexer in mind. Although no amount of testing can guarantee 100% success, for all practical purposes the given test cases should be sufficient.
	
