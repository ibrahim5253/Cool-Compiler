class Main {
	main():Int {
		0
	};
};

class A {
};

class B {
};

class C inherits A {
};

class D inherits C {
};

class E inherits B {
};

class F inherits B {
};

class Test {
	a:Int;
	b:String;
	c:Bool;
	foo():Int {0};
	test():Object {
		{
			a<-(2+3)-5;
			b<-"Hi";
			c<-true;
			abort();
			1<=2;
			new IO.out_string("Bye");
			let x:Int <- 1 in while true loop if x=0 then 0 else x fi pool;
			foo();
		}
	};
};
