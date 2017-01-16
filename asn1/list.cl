(*
 *  This file shows how to implement a list data type for lists of integers.
 *  It makes use of INHERITANCE and DYNAMIC DISPATCH.
 *
 *  The List class has 4 operations defined on List objects. If 'l' is
 *  a list, then the methods dispatched on 'l' have the following effects:
 *
 *    isNil() : Bool		Returns true if 'l' is empty, false otherwise.
 *    head()  : Int		Returns the integer at the head of 'l'.
 *				If 'l' is empty, execution aborts.
 *    tail()  : List		Returns the remainder of the 'l',
 *				i.e. without the first element.
 *    cons(i : Int) : List	Return a new list containing i as the
 *				first element, followed by the
 *				elements in 'l'.
 *
 *  There are 2 kinds of lists, the empty list and a non-empty
 *  list. We can think of the non-empty list as a specialization of
 *  the empty list.
 *  The class List defines the operations on empty list. The class
 *  Cons inherits from List and redefines things to handle non-empty
 *  lists.
 *)


class List inherits IO{
   -- Define operations on empty lists.

   isNil() : Bool { true };

   -- Since abort() has return type Object and head() has return type
   -- Int, we need to have an Int as the result of the method body,
   -- even though abort() never returns.

   head()  : String { { abort(); ""; } };

   -- As for head(), the self is just to make sure the return type of
   -- tail() is correct.

   tail()  : List { { abort(); self; } };

   -- When we cons and element onto the empty list we get a non-empty
   -- list. The (new Cons) expression creates a new list cell of class
   -- Cons, which is initialized by a dispatch to init().
   -- The result of init() is an element of class Cons, but it
   -- conforms to the return type List, because Cons is a subclass of
   -- List.

   cons(i : String) : List {
      (new Cons).init(i, self)
   };

   at(i : Int) : String {{abort(); "";}};

   assign(i : Int, val : String) : Object {{abort(); "";}};

   make(i : Int, val : String) : List {
	   (new Cons).make(i, val)
   };
   
   print() : Object {
      if isNil() then 0
                   else {
			   out_string(head());
			   out_string("\n");
			   tail().print();
		        }
      fi
   };
};


(*
 *  Cons inherits all operations from List. We can reuse only the cons
 *  method though, because adding an element to the front of an emtpy
 *  list is the same as adding it to the front of a non empty
 *  list. All other methods have to be redefined, since the behaviour
 *  for them is different from the empty list.
 *
 *  Cons needs two attributes to hold the integer of this list
 *  cell and to hold the rest of the list.
 *
 *  The init() method is used by the cons() method to initialize the
 *  cell.
 *)

class Cons inherits List {

   car : String;	-- The element in this list cell

   cdr : List;	-- The rest of the list

   isNil() : Bool { false };

   head()  : String { car };

   tail()  : List { cdr };

   init(i : String, rest : List) : List {
      {
	 car <- i;
	 cdr <- rest;
	 self;
      }
   };

   at(i : Int) : String {if i=0 then car else cdr.at(i-1) fi};

   assign(i : Int, val : String) : Object {if i=0 then car <- val else cdr.assign(i-1, val) fi};

   make(i : Int, val : String) : List {
		   if 0<i then {
			   car <- val;
			   cdr <- new List.make(i-1, val);
			   self;
		   }
		   else new List fi
   };
};


(*
 *  The Main class shows how to use the List class. It creates a small
 *  list and then repeatedly prints out its elements and takes off the
 *  first element of the list.
 *

class Main inherits IO {

   mylist : List;

   cvt : A2I <- new A2I;
   -- Print all elements of the list. Calls itself recursively with
   -- the tail of the list, until the end of the list is reached.



   -- Note how the dynamic dispatch mechanism is responsible to end
   -- the while loop. As long as mylist is bound to an object of 
   -- dynamic type Cons, the dispatch to isNil calls the isNil method of
   -- the Cons class, which returns false. However when we reach the
   -- end of the list, mylist gets bound to the object that was
   -- created by the (new List) expression. This object is of dynamic type
   -- List, and thus the method isNil in the List class is called and
   -- returns true.

   main() : Object {
(*	 mylist <- new List.cons(5).cons(4).cons(3).cons(2).cons(1);
	 --mylist <- (new List).make(10,2);
	 out_int(mylist.head()); 
	 let index : Int <- 4 in {
	 out_string("Value at index ");
	 out_int(index);
	 out_string(": ");
	 out_int(mylist.at(index));
	 out_string("\n");
	 out_string("Changed Value at index ");
	 out_int(index);
	 mylist.assign(index, 989);
	 out_string(": ");
	 out_int(mylist.at(index));
	 out_string("\n");
	 };
	 while (not mylist.isNil()) loop
	    {
	       print_list(mylist);
	       mylist <- mylist.tail();
	    }
	 pool;
      }*)
	{
	      mylist <- new List.cons(cvt.i2a(5)).cons(cvt.i2a(0 - 3)).cons(cvt.i2a(9));
	      mylist.print();
	      mylist.tail().print();
	}
   };

};
*)


