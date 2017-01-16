
# COOL Compiler #

Write-Up
--------

Having completed lexical analysis phase last time, here we move onto parsing.
Parser for COOL is developed in this assignment.

The main task was to write grammar rules in Antlr as specified in COOL 
reference manual.

    Precedence and Associativity

All binary operators in COOL are left associative with the exception 
of assignment, which is right associative, and the three comparison operations, 
which do not associate.

    Lists
COOL grammar allows lists of features, classes, formals, etc. The rules
for them were written using Extended Backus Naur Form (EBNF). Since Antlr handles 
direct left recursion, the rules were written as specified in the manual to 
maintain readability.

    Error Handling
The parser was tested against a variety of test cases which included both
correct and incorrect COOL programs. The parser is robust in detecting most of the 
syntactical errors coupled with Antlr's built in error handling mechanism.

***Other details will be updated after the phase B of this assignment***
