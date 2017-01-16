class StackCommand inherits IO {	--generic class, represents a command
	cmd : String <- new String;	--command read from stdin
	cvt : A2I <- new A2I;		--dummy object to a2i and vice-versa conversions

	read() : StackCommand {
		{	--based on the character(s) read, instantiate and return a command object of appropriate type
			cmd <- in_string();	
			if cmd = "+" then new Plus
			else if cmd = "s" then new Swap
			else if cmd = "e" then new EvaluateTop
			else if cmd = "d" then new Display
			else if cmd = "x" then new Exit			
			--in case an integer is supplied, additionally initialize the command object
			else (new Add).init(cvt.a2i(cmd))
			fi fi fi fi fi;
		}
	};

	--every child class redefines its own version of evaluate
	evaluate(s : Stack) : Bool {true};
};

class Plus inherits StackCommand {
	evaluate(s : Stack) : Bool {	--just push a '+' onto the stack
		{
			s.push("+");
			true;
		}
	};
};

class Swap inherits StackCommand {
	evaluate(s : Stack) : Bool {	--push a 's' onto the stack
		{
			s.push("s");
			true;
		}
	};
};

class EvaluateTop inherits StackCommand {
	evaluate(s : Stack) : Bool {	--read the top of stack, and evaluate accordingly
		{
			if s.top() = "+" then {s.pop(); pop2add(s);}
			else if s.top() = "s" then {s.pop(); pop2swap(s);}
			else 0
			fi fi;
			true;
		}
	};

	pop2add(s : Stack) : Object {	--pop two integers, push back their sum
		let x : Int <- cvt.a2i(s.top()), y : Int in
		{
			s.pop();
			y <- cvt.a2i(s.top());
			s.pop();
			s.push(cvt.i2a(x + y));
		}
	};

	pop2swap(s : Stack) : Object {	--pop two integers, and swap them
		let x : String <- s.top(), y : String in
		{
			s.pop();
			y <- s.top();
			s.pop();
			s.push(x);
			s.push(y);
		}
	};
};

class Display inherits StackCommand {
	evaluate(s : Stack) : Bool {	--print the contents of the stack
		{
			s.print();
			true;
		}
	};
};

class Exit inherits StackCommand {	--time to exit
	evaluate(s : Stack) : Bool {false};
};

class Add inherits StackCommand {
	num : Int;	--number to be pushed onto the stack
	init(i : Int) : StackCommand {	--initialize num
		{
			num <- i;
			self;
		}
	};

	evaluate(s : Stack) : Bool {	--push num onto the stack
		{
			s.push(cvt.i2a(num));
			true;
		}
	};
};

class Main inherits IO {
	mystack : Stack <- new Stack;	
	mycmd : StackCommand <- new StackCommand;
	
	prompt() : Object {out_string(">")};
	main() : Object { {
			prompt();
			mycmd <- mycmd.read();
			while mycmd.evaluate(mystack) loop {
				prompt();
				mycmd <- mycmd.read();
			}
			pool;
		}
	};
};
