class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

class A inherits IO {
	a:Int <- 1/0;
	b:Int <- a+1;
	c:A;
	print():Object {
		out_int(3)
	};
};

