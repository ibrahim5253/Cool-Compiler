parser grammar CoolParser;

options {
	tokenVocab = CoolLexer;
}

@header{
	import cool.AST;
	import java.util.ArrayList;
}

@members{
	String filename;
	public void setFilename(String f){
		filename = f;
	}

	/*
	   DO NOT EDIT THE FILE ABOVE THIS LINE
	   Add member functions, variables below.
	 */

}

/*
   Add Grammar rules and appropriate actions for building AST below.
 */

// A COOL program is a list of classes
program returns [AST.program value] :
cl=class_list EOF
{
	$value = new AST.program($cl.value, $cl.value.get(0).lineNo);
};

class_list returns [List<AST.class_> value] 
@init {
	$value = new ArrayList<AST.class_>();
} 
// semicolon terminated non-empty list of classes
: (c=class_{$value.add($c.value);} // add this class to the list
 SEMICOLON)+;

class_ returns [AST.class_ value] : 
// class with no explicit inheritance, inherits implicitly from Object
CLASS TYPEID LBRACE feature_list RBRACE
{
	$value = new AST.class_($TYPEID.text, filename, "Object", $feature_list.value,$CLASS.line);
}
| 
// class with explicit inheritance
CLASS curr=TYPEID INHERITS par=TYPEID LBRACE feature_list RBRACE
{
	$value = new AST.class_($curr.text, filename, $par.text, $feature_list.value, $CLASS.line);
};

feature_list returns [List<AST.feature> value] 
@init {
	$value = new ArrayList<AST.feature>();
}
: (f=feature {$value.add($f.value);} // add this feature to list
   SEMICOLON)*;

// A feature is either a method or an attribute
feature returns [AST.feature value] 
@init {
	$value = new AST.feature();
}
: method
{
	$value = $method.value;
}
| attr
{
	$value = $attr.value;
};

// attribute
attr returns [AST.attr value] : 
// without initialization
  o=OBJECTID COLON t=TYPEID
{
	$value = new AST.attr($o.text, $t.text, new AST.no_expr($o.line), $o.line);
}
// with initialization
| o=OBJECTID COLON t=TYPEID ASSIGN e=expr
{
	$value = new AST.attr($o.text, $t.text, $e.value, $o.line);
};

method returns [AST.method value] : 
  o=OBJECTID LPAREN f=formal_list RPAREN COLON t=TYPEID LBRACE e=expr RBRACE
{
	$value = new AST.method($o.text, $f.value, $t.text, $e.value, $o.line);
};

formal_list returns [List<AST.formal> value] 
@init {
	$value = new ArrayList<AST.formal>();
}
// comma separated formals
: g=formal {$value.add($g.value);} // add this formal to list
  (COMMA f=formal {$value.add($f.value);})*
// empty list
| ;

formal returns [AST.formal value] : 
  o=OBJECTID COLON t=TYPEID
{
	$value = new AST.formal($o.text, $t.text, $o.line);
};

// The rest are all expressions
expr returns [AST.expression value] : 
// static dispatch
  c=expr ATSYM t=TYPEID DOT n=OBJECTID LPAREN al=arg_list RPAREN 
{
	$value = new AST.static_dispatch($c.value, $t.text, $n.text, $al.value, $c.value.lineNo);
}
// dispatch
| c=expr DOT n=OBJECTID LPAREN al=arg_list RPAREN 
{
	$value = new AST.dispatch($c.value, $n.text, $al.value, $c.value.lineNo);
}
// dispatch without explicit caller, self implicitly
| n=OBJECTID LPAREN al=arg_list RPAREN 
{
	$value = new AST.dispatch(new AST.string_const("self",$n.line), $n.text, $al.value, $n.line);
}
// if-then-else conditional
| IF p=expr THEN i=expr ELSE e=expr FI 
{
	$value = new AST.cond($p.value, $i.value, $e.value, $IF.line);
}
// loop
| WHILE p=expr LOOP b=expr POOL 
{
	$value = new AST.loop($p.value, $b.value, $WHILE.line);
}
// expression block
| LBRACE el=expr_list RBRACE 
{
	$value = new AST.block($el.value, $LBRACE.line);
}
// let expression
| LET ll=let_list 
{
	$value = new AST.let($ll.name, $ll.typeid, $ll.value, $ll.body, $LET.line);
}
// case expression
| CASE p=expr OF bl=branch_list ESAC 
{
	$value = new AST.typcase($p.value, $bl.value, $CASE.line);
}
// new expression
| NEW t=TYPEID 
{
	$value = new AST.new_($t.text, $NEW.line);
}
// complement
| TILDE e=expr 
{
	$value = new AST.comp($e.value, $TILDE.line);
}
// isvoid expression
| ISVOID e = expr
{ 
	$value = new AST.isvoid($e.value,$ISVOID.line); 
}
// multiplication expression
| e1 = expr STAR e2 = expr
{
	$value = new AST.mul($e1.value,$e2.value,$e1.value.lineNo); 
}
// division expression
| e1 = expr SLASH e2 = expr
{
	$value = new AST.divide($e1.value,$e2.value,$e1.value.lineNo); 
}
// addition expression
| e1 = expr PLUS e2 = expr
{ 
	$value = new AST.plus($e1.value,$e2.value,$e1.value.lineNo); 
}
// substraction expression
| e1 = expr MINUS e2 = expr
{ 
	$value = new AST.sub($e1.value,$e2.value,$e1.value.lineNo); 
}
// less-than-equals operator
| e1 = expr LE e2 = expr
{
	$value = new AST.leq($e1.value,$e2.value,$e1.value.lineNo); 
}
// less-than operator
| e1 = expr LT e2 = expr
{
	$value = new AST.lt($e1.value,$e2.value,$e1.value.lineNo); 
}
// equality operator
| e1 = expr EQUALS e2 = expr
{ 
	$value = new AST.eq($e1.value,$e2.value,$e1.value.lineNo); 
}
// logical not
| NOT e = expr
{ 
	$value = new AST.neg($e.value,$NOT.line); 
}
// assignment expression, it is the only operator with right associativity
|<assoc=right> o=OBJECTID ASSIGN e = expr
{ 
	$value = new AST.assign($o.text,$e.value,$o.line); 
}
// paranthesised expression
| LPAREN e = expr RPAREN
{ 
	$value = new AST.expression();
	$value = $e.value; 
}
// just an object identifier
| o = OBJECTID
{ 
	$value = new AST.object($o.text, $o.line); 
}
// integer constant
| ic = INT_CONST
{ 
	$value = new AST.int_const($ic.int, $ic.line); 
}
// string constant
| str = STR_CONST
{ 
	$value = new AST.string_const($str.text,$str.line); 
}
// bool constant
| bc = BOOL_CONST
{ 
	if($bc.text.toLowerCase().equals("true"))
		$value = new AST.bool_const(true,$bc.line);
	else
		$value = new AST.bool_const(false,$bc.line);
}
;

arg_list returns [List<AST.expression> value] 
@init {
	$value = new ArrayList<AST.expression>();
}
// comma separated list of expressions
: e=expr {$value.add($e.value);}  // add this expression to the list
  (COMMA f=expr {$value.add($f.value);})*
// empty list
|;

expr_list returns [List<AST.expression> value] 
@init {
	$value = new ArrayList<AST.expression>();
}
// semicolon terminated list of expressions
: (e=expr {$value.add($e.value);} SEMICOLON)+;

let_list returns [String name, String typeid, AST.expression value, AST.expression body] 
@init {
	$name = new String();
	$typeid = new String();
	$value = new AST.expression();
	$body = new AST.expression();
}
// single declaration without initialization
: o=OBJECTID COLON t=TYPEID IN b=expr {
	$name = $o.text;
	$typeid = $t.text;
	$value = new AST.no_expr($o.line);
	$body  = $b.value;
}
// single declaration with initialization
| o=OBJECTID COLON t=TYPEID ASSIGN v=expr IN b=expr{
	$name = $o.text;
	$typeid = $t.text;
	$value = $v.value;
	$body  = $b.value;
}
// multiple declaration, first one without initialization
| o=OBJECTID COLON t=TYPEID COMMA ll=let_list{
	$name = $o.text;
	$typeid = $t.text;
	$value = new AST.no_expr($o.line);
	$body  = new AST.let($ll.name, $ll.typeid, $ll.value, $ll.body, $o.line);
}
// multiple declaration, first one with initialization
| o=OBJECTID COLON t=TYPEID ASSIGN v=expr COMMA ll=let_list{
	$name = $o.text;
	$typeid = $t.text;
	$value = $v.value;
	$body  = new AST.let($ll.name, $ll.typeid, $ll.value, $ll.body, $o.line);
};

branch_list returns [List<AST.branch> value] 
@init {
	$value = new ArrayList<AST.branch>();
}
// semicolon terminated list of branches
: (b=branch {$value.add($b.value);} SEMICOLON)+;

branch returns [AST.branch value] 
: o=OBJECTID COLON t=TYPEID DARROW e=expr
{
	$value = new AST.branch($o.text, $t.text, $e.value, $o.line);
};
