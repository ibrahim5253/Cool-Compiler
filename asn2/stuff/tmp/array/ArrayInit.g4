lexer grammar ArrayInit;

INT : [0-9]+;
WS  : [ \t\r\n]+ -> skip;
GAR : [^0-9]+;
