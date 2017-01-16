class Stack inherits IO{
	container : List <- new List;
	size : Int <- 0;
	max_size : Int <- 1000000;

	push(x : String) : SELF_TYPE {	--push into the stack if there is any space
		if size = max_size then {out_string("Stack Overflow!\n");abort();self;}
		else {
			container <- container.cons(x);
			size <- size + 1;
			self;
		}
		fi
	};

	pop() : SELF_TYPE {		--pop if there is any element available
		if size = 0 then {out_string("Stack Underflow!\n");abort(); self;}
		else {
			container <- container.tail();
			size <- size - 1;
			self;
		}
		fi
	};

	top() : String {			--return the top element
		container.head()
	};

	isempty() : Bool {
		size = 0
	};

	getsize() : Int {
		size
	};

	print() : Object {
		container.print()
	};
};

(*class Main inherits IO {
	mystack : Stack <- new Stack;
	cvt : A2I <- new A2I;
	main() : Object {
		{
			mystack.push(cvt.i2a(0)).push(cvt.i2a(1)).push(cvt.i2a(2));
			mystack.print();
			mystack.pop().pop();
			mystack.print();
			mystack.pop().pop();
		}
	};
};
*)
