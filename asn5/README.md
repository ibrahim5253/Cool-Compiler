Writeup
================


Checking if every class has a valid parent
----------------------------------------
This checking is done by iterating over the hashmap and checking if the 
parent of that class is existing or not. 
Further, since COOL does not allow inheritence from the basic classes
Int, String and Bool, we check the parent of every defined class if it is 
in 'non_inheritable' list;
Also, redefinition of classes is not allowed in COOL. We check this 
while adding the classes to the map.

Checking cycles in inheritence graph.
-------------------------------------
Inheritence graph is implemented using a hashmap of <class_ ,List<class_>>.
In this hashmap, every class object is mapped to a list of classes which derives
from it.

Now we do a standard DFS to detect cycles. Once it is established that
the inheritance graph is well-formed we move on the rest of semantic checks.

Scoping, Name and Type checking
----------------------------
To take the advantage of nested scoping structure of cool (i.e., parent class 
features are visible in child class, etc.), we again traverse the inheritance graph
constructed previously in depth first order starting from the root. Note that in
this graph every node represents a class, with Object being the root.

Entering and Exiting scope
----------------------------------------------
Whenever we arrive at a node, we enter a new scope and exit it after we have
traversed the subtree rooted at this node. This naturally allows us to check for names,
methods declared in all the ancestor classes.

Check and save features of class
---------------------------------------------
We iterate over all the features of a class
	1. If its an attribute, we check if its has been previously defined in any of
	the ancestors by scope table lookup. We report multiple declarations, and also
	redeclarations in child class, if any.

	2. If its a method, we check if a method with same name has been defined in some
	of the ancestors. If so, we match number of arguments, return type, and type of each
	argument with the previously defined method; and if any of this test fails, we report
	the error. We also report multiple declarations in the same class. After this, we check
	the body expression of the method and check if it's type conforms with that of the 
	declared return type of the method.

Expressions
----------------------------------------------------------------
The method checkExprGetType checks the given expression and returns its static type.
All kind of expressions are handled using if-else ladder.

* Int, String, Bool, No_Type - set the type as the corresponding type
and return it.

* ObjectID - Check if the object is declared in the current scope's
ancestral hierarchy. If no, throw error, else set the type of expr 
as that of objectID's type.

* not expr - check if type of expr is bool or not. If not, throw error.
Set the type of the expression as bool.

* Comparison operators (= , <, <=) -  For< and LE, check if type of both
operands  Int or not. If not, report error,set Bool as the
type of this node and return Bool.
	
	For equality, just call check both the operands and
	if any of them is of basic type (Int,String,Bool) check that the
	other is also the same.If not, report with appropriate
	error message, set the type of this node as Bool and return Bool.

* ~expr  - it checks if the expr whose negation is to be taken is of 
type Int or not. If not, calls reportError with appropriate
error message. Then, set the type of this node as Int and returns Int.

*Binary operators (+,-,*,/)- Checks if both operands are of type Int
or not. If not call reportError. Then set Int as type of this node 
and return it.(this is most probably done so that the type mismatch does
not propagate futher up the tree. ) 

* isvoid expr - calls checkExprGetType() on the expr, set the type of
this node as Bool and return it.

* new TYPE - check if the TYPE is declared in the Class map. If yes, set
the type of this node as the Symbol TYPE else set type as Object. Then 
return the type of this node.

* a<- expr - calls checkExprGetType() on th expr assigned to A. 
		Then checks if ObjectID A is declared in the scope hierarchy.
		Then checks if the type of expr is same as the declared type of 
		ObjectID A.l
		
* if expr then expr else expr fi - checks if predicate is of type Bool 
or not. If not, call reportError().
Then it calls checkExprGetType() on then expr and else expr.
We then take the union of if and else type by finding the LCA of this two,

* while expr loop expr pool - calls checkExprGetType() on predicate and
checks if the returned type is Bool.
calls checkExprGetType() on the expr within the loop.
Returns the type Object always as the result type of <while-expression>

* [[expr;]] - iterates over the list of Expressions and calls
checkExprGetType() on each expression and returns the type obtained from
the last expression in the block as the type of the block. For this,
define a new Symbol type_expr and set this to the type returned from the 
checkExprGetType() for each expression in the block.
Then after the loop, set the type of the block as this type_expr and 
return this.

* let a:TYPE[<-expr] in expr - get the type of initialisation expr.
check if the declared type of 'a' exists or not. If not call reportError()
. Then check if the declared type conforms to the init type. If no, call
reportError().
Then enter  a new scope in the object table, insert a:TYPE into it and 
exit the scope. Set the return type of <let-expression> to the type of 
body expr and return that type.

