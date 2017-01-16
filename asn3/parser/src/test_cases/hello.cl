class Main inherits IO{
	a : Int <- 4;
	b : Int <- a;
	c : Int;
	main() : Int {{
		c <- 1+2/3;
		out_string("HI");
		}
	};

	foo() : Object {
		let i : Int <- 1, j : Int <- 2 in 0
	};
};

