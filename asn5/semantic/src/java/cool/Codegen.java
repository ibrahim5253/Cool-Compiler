	package cool;

	import java.io.PrintWriter;
	import java.util.HashMap;
	import java.util.List;
	import java.util.ArrayList;

	public class Codegen{
		PrintWriter cout;

		// Base classes
		private String OBJ = new String("Object");
		private String INT = new String("Int");
		private String BOOL = new String("Bool");
		private String STR = new String("String");
		private String IO  = new String("IO");
		private String NO_TYPE = new String("no_type");
		private AST.class_ Object_;
		private	IRTable irTable = new IRTable();
		private List<AST.class_> baseClasses = new ArrayList<AST.class_>();

		public Codegen(AST.program program, PrintWriter out){
			//Write Code generator code here
			init(out);
			buildClasses(program);
			ProcessClasses(program, out);
		}

		public void buildClasses(AST.program program) {
			List<AST.feature> ObjectFeatures =  new ArrayList<>();

			ObjectFeatures.add(new AST.method("abort",new ArrayList<AST.formal>(),OBJ,new AST.no_expr(0),0));

			ObjectFeatures.add(new AST.method("type_name",new ArrayList<AST.formal>(),STR,new AST.no_expr(0),0));

			ObjectFeatures.add(new AST.method("copy",new ArrayList<AST.formal>(),"SELF_TYPE",new AST.no_expr(0),0));

			Object_  = new AST.class_(OBJ,"",null,ObjectFeatures,0);

			List<AST.feature> IOFeatures = new ArrayList<>();

			IOFeatures.add( new AST.method(   "out_string",
						new ArrayList<AST.formal>() {{add(new AST.formal("x",STR,0));}},
						"SELF_TYPE",
						new AST.no_expr(0),
						0 ));

			IOFeatures.add( new AST.method(   "out_int",
						new ArrayList<AST.formal>() {{add(new AST.formal("x",INT,0));}},
						"SELF_TYPE",
						new AST.no_expr(0),
						0 ));

			IOFeatures.add( new AST.method(   "in_string",new ArrayList<AST.formal>(),STR,new AST.no_expr(0),0));

			IOFeatures.add( new AST.method(   "in_int",new ArrayList<AST.formal>(),INT,new AST.no_expr(0),0));

			AST.class_ IO_ = new AST.class_(IO,"",OBJ,IOFeatures,0);

			AST.class_ Int_ = new AST.class_(INT,"",OBJ,new ArrayList<AST.feature>(),0);

			AST.class_ Bool_ = new AST.class_(BOOL,"",OBJ,new ArrayList<AST.feature>(),0);

			List<AST.feature> StringFeatures = new ArrayList<>();

			StringFeatures.add(new AST.method("length",new ArrayList<AST.formal>(),INT,new AST.no_expr(0),0));

			StringFeatures.add(new AST.method("concat",
						new ArrayList<AST.formal>(){{add(new AST.formal("s",STR,0));}}
						,STR,new AST.no_expr(0),0));

			StringFeatures.add(new AST.method("substr",
						new ArrayList<AST.formal>(){{add(new AST.formal("i",INT,0));
							add(new AST.formal("l",INT,0));}}
							,STR,new AST.no_expr(0),0));

			AST.class_ String_ = new AST.class_(STR,"",OBJ,StringFeatures,0);

			baseClasses.add(Object_);
			baseClasses.add(IO_);
			baseClasses.add(Int_);
			baseClasses.add(Bool_);
			baseClasses.add(String_);
		}

		public void init(PrintWriter out) {
			out.println(BoilerPlate.DATA_LAYOUT);
			out.println(BoilerPlate.TARGET_TRIPLE);
			out.println();
		}

		public String toIRtype(String typeid) {
			if (BoilerPlate.IRTYPE.containsKey(typeid))
				return BoilerPlate.IRTYPE.get(typeid);
			else 
				return "%class." + typeid + "*";
		}

		public void PrintClassDecl(AST.class_ c, PrintWriter out) {
			out.print("%class." + c.name + ".Base = type {");
			for (int i=0; i<c.features.size(); i++) {
				AST.feature ft = c.features.get(i);
				if (ft instanceof AST.attr) {
					if(i != 0)
						out.print(", ");
					out.print(toIRtype(ft.typeid));
				}
			}
			out.println(" } ");
			out.println();
		}

		public void PrintClassVT(AST.class_ c, PrintWriter out) {
			List<AST.method> methods = new ArrayList<AST.method>();
			for (AST.feature ft: c.features) 
				if (ft instanceof AST.method) 
					methods.add((AST.method)ft);	

			out.print("@_ZTV" + c.name.length() + c.name + " = constant " +
				"[" + methods.size() + " x i8*] [");	

			for (int i=0; i<methods.size(); i++) {
				if (i != 0)
					out.print(", ");
				AST.method m = methods.get(i);
				out.print("i8 bitcast (");
				out.print(toIRtype(m.typeid));
				out.print(" ( ");
				for (int j=0; j<m.formals.size(); j++) {
					AST.formal f = m.formals.get(j);
					if (j != 0)
						out.print(", ");
					out.print(toIRtype(f.typeid));
				}
				out.print(" )* ");

				// name of method
				String m_name = "_ZN"+c.name.length()+c.name+m.name.length()+m.name;
				out.print(m_name);
				out.print(" to i8*)");
			}
			out.println("] ");
		}

		public void PrintClassMethods(AST.class_ c, PrintWriter out) {
			if (c.name.equals("Object")) {
				out.println(BoilerPlate.OBJECT_ABORT);
				out.println(BoilerPlate.OBJECT_TYPENAME);
				out.println(BoilerPlate.OBJECT_COPY);
			}
			else if (c.name.equals("String")) {
				out.println(BoilerPlate.STRING_CONCAT);
				out.println(BoilerPlate.STRING_COPY);
				out.println(BoilerPlate.STRING_LENGTH);
				out.println(BoilerPlate.STRING_SUBSTR);
			}
		}

		public void ProcessClasses(AST.program program, PrintWriter out) {
			for (AST.class_ c: baseClasses) {
				PrintClassDecl(c,out);
				PrintClassVT(c,out);
				PrintClassMethods(c,out);
			}
			for (AST.class_ c: program.classes) {
				PrintClassDecl(c,out);				
				PrintClassVT(c,out);				
				PrintClassMethods(c,out);				
			}
		}
	}
