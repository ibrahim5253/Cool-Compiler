(*
*  Bad test cases for semantic check
*  Semantic check is halted when class checks fails,
*  hence uncomment code to test such errors
*)

(* --uncomment to see errors--

class Duplicate{}; -- class multiply defined
class Duplicate inherits Undefined {}; -- inherits from undefined class

class A inherits B{};   -- inheritance cycle error
class B inherits C{};
class C inherits A{};

class Int2 inherits Int{}; -- cannot inherit from Int,Bool,String

*)

class Main {

   x: Int;        -- no main method error
   x: String;     -- multipy defined attribute error

	main2():Int {{
      y <- 1;        -- undefined attribute error

      x <- true;     -- type conformity error

      "ret_string";  -- return type should be Int

      if 1 then 2 else 3 fi;  -- predicate of if should be Bool type

      1 = "Int";  -- illegal comparison

      "string" + 1;  -- non integer argument in arithmetic expr error

      not"string";   -- operand to not should be Bool type

      ~"string";     -- operand to ~ should be Int type

      0;
	}};
};

class A {
   varA: String;

   foo():MyType {2}; -- undefined data type MyType

   bar():Int{2};

   baz(x:Int):Int {
      true              -- return value type and declared return type error
   };
};

class B inherits A {
   varA: Int;        -- already defined in class A

   -- redefine method errors
   bar(x:Int):Bool{true}; -- different number of formal parameters error
                          -- different return type error

   baz(x:String):Int {0}; -- different formal parameter type error
};

class C {
   foo(x:Int,x:Int): Int {0}; -- formal parameter multiply defined error

   bar(x:Int): Int {0};
};

class D {
   var: C;

   foo(): Int {{
      var.bar();   -- invalid call to method bar
      var.func();  -- function call to undefined function func()
   }
   };
   func(): String {
      0
   };
};
