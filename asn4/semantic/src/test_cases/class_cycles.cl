class Main {
	main():IO {
		new IO.out_string("Hello world!\n")
	};
};

--In Cycle
class A inherits B {};
class B inherits C {};
class C inherits A {};

--outside cycle
class X inherits A {};
class Y inherits B {};

--In Cycle
class D inherits E {};
class E inherits F {};
class F inherits D {};
