package cool;
import java.util.*;

@SuppressWarnings("serial")
public class Semantic{
	private boolean errorFlag = false;
	public void reportError(String filename, int lineNo, String error){
		errorFlag = true;
		System.err.println(filename+":"+lineNo+": "+error);
	}
	public boolean getErrorFlag(){
		return errorFlag;
	}

	/*
	   Don't change code above this line
	   */

	// Base classes
	private String OBJ = new String("Object");
	private String INT = new String("Int");
	private String BOOL = new String("Bool");
	private String STR = new String("String");
	private String IO  = new String("IO");
	private String NO_TYPE = new String("no_type");
	private AST.class_ Object_;

	private HashMap<AST.class_, List<AST.class_>> Graph  = new HashMap<>();
	private HashMap<AST.class_, Integer> node_status     = new HashMap<>();
	private HashMap<String, AST.class_>  str2class       = new HashMap<>();
	private boolean hasCycles=false;

	public void DFS(AST.class_ node) {

		if (Graph.get(node)==null) return;
		for (AST.class_ child:Graph.get(node)) {
		
			if (node_status.get(child)==null) {
				node_status.put(child, 1);
				DFS(child);
			}

			else if (node_status.get(child)==1) {
				hasCycles=true;
				reportError(node.filename,node.lineNo,"Class "+node.name+
					    ", or an ancestor of "+node.name+
					    ", is involved in an inheritance cycle");
				return;
			}


			if (hasCycles) {
				reportError(node.filename,node.lineNo,"Class "+node.name+
						", or an ancestor of "+node.name+
						", is involved in an inheritance cycle");
				return;
			}
		}
		node_status.put(node, 2);
	}

	public void init() {

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

		str2class.put(OBJ,Object_);
		str2class.put(IO,IO_);
		str2class.put(INT,Int_);
		str2class.put(BOOL,Bool_);
		str2class.put(STR,String_);
		Graph.put(Object_,new ArrayList<AST.class_>(Arrays.asList(Int_,String_,Bool_,IO_)));
	}

	public void buildInheritanceGraphAndCheckCycles(AST.program program)
	{

		List<String> non_inheritable = Arrays.asList(INT,STR,BOOL);

		String fname=new String(); 

		for (AST.class_ c:program.classes) {
			fname=c.filename;
			if (str2class.get(c.name)!=null) {
				reportError(c.filename,c.lineNo,"Class "+c.name+" was previously defined");
			}
			str2class.put(c.name,c);

			if (non_inheritable.contains(c.parent)) {
				reportError(c.filename,c.lineNo,"Class "+c.name+" cannot inherit from Class "+c.parent);
			}
		}

		for (AST.class_ c:program.classes) {

			AST.class_ parent = str2class.get(c.parent);
			if (parent==null)
				reportError(c.filename,c.lineNo,"Class "+c.name+" inherits from an undefined class "+c.parent);

			if (Graph.get(parent)==null)
				Graph.put(parent,new ArrayList<AST.class_>());
			Graph.get(parent).add(c);
		}

		node_status.put(Object_, 1);
		DFS(Object_);
		for (String node: str2class.keySet()) {
			AST.class_ tmp = str2class.get(node);
			if (node_status.get(tmp)==null) {
				node_status.put(tmp,1);
				hasCycles=false;
				DFS(tmp);
			}
		}

		if (!str2class.keySet().contains("Main")) reportError(fname,1,"Class Main is not defined");
	}

	public Semantic(AST.program program){
		//Write Semantic analyzer code here
		init();

		buildInheritanceGraphAndCheckCycles(program);

		if (errorFlag) return;   // halt compilation if any errors

		checkNameScope(Object_); // Do all other semantic checks
	}
	
	ScopeTable<AST.method> s_table_method = new ScopeTable<>();
	ScopeTable<String>   s_table_attr   = new ScopeTable<>();

	public void checkNameScope(AST.class_ node) 
	{
		s_table_method.enterScope();	
		s_table_attr.enterScope();

		s_table_attr.insert("self",node.name);

		for (AST.feature feature:node.features) {

			if (feature instanceof AST.method) {
				AST.method l_entry = s_table_method.lookUpLocal(feature.name);
				AST.method g_entry = s_table_method.lookUpGlobal(feature.name);

				boolean flag=true;

				if (g_entry!=null && l_entry==null) {
					int len1 = g_entry.formals.size(), len2 = ((AST.method)feature).formals.size();
					if (len1!=len2) {
						reportError(node.filename, feature.lineNo, 
								"Incompatible number of formal parameters in redefined method "+g_entry.name);
						flag=false;
					}
					else if (!g_entry.typeid.equals(feature.typeid)) {
						reportError(node.filename, feature.lineNo,
							    "In redefined method "+feature.name+", return type "+
							    feature.typeid+" is different from original type "+g_entry.typeid);
						flag=false;
					}
					else {
						List<AST.formal> L1 = g_entry.formals;
						List<AST.formal> L2 = ((AST.method)feature).formals;
						for (int i=0;i<len1;++i) {
							if(!L1.get(i).typeid.equals(L2.get(i).typeid))	{
								flag=false;
								reportError(node.filename, feature.lineNo,
						                           "In redefined method "+feature.name+", parameter type "+
									   L2.get(i).typeid+" is different from original type "+L1.get(i).typeid);
							}
						}
					}
				}
				
				else if (l_entry!=null) {
					reportError(node.filename, feature.lineNo, "Method "+g_entry.name+" is multiply defined in class.");
				}
				
				if (flag) {
					s_table_method.insert(feature.name, (AST.method)feature);

					s_table_method.enterScope();
					s_table_attr.enterScope();

					List<AST.formal> params = ((AST.method)feature).formals;
					for (AST.formal param:params) {
						if (s_table_attr.lookUpLocal(param.name)!=null)
							reportError(node.filename, param.lineNo, "Formal parameter "+param.name+
									" is multiply defined");
						else
							s_table_attr.insert(param.name, param.typeid);
					}
					String body_type = checkExprGetType(((AST.method)feature).body, node.filename);

					if (!conforms(feature.typeid, body_type))
						reportError(node.filename, feature.lineNo, "Inferred return type "+body_type+
								" of method "+feature.name+" does not conform to declared return type "+
								feature.typeid);

					s_table_method.exitScope();
					s_table_attr.exitScope();
				}
			}

			else {
				String l_entry = s_table_attr.lookUpLocal(feature.name);
				String g_entry = s_table_attr.lookUpGlobal(feature.name);

				if (g_entry!=null && l_entry==null) {
					System.out.println(g_entry);	
					reportError(node.filename,feature.lineNo,"Attribute "+feature.name+
							" is an attribute of an inherited class");
				}
				else if (l_entry!=null)
					reportError(node.filename,feature.lineNo,"Attribute "+feature.name+" is multiply defined in class.");

				else  {
					s_table_attr.insert(feature.name,feature.typeid);
					String init_expr = checkExprGetType(((AST.attr)feature).value,node.filename);
					if (!conforms(feature.typeid, init_expr)) 
						reportError(node.filename, feature.lineNo,
						            "Inferred type "+init_expr+" of initialization of attribute "+feature.name+
							    " does not conform to declared type "+feature.typeid);
				}

			}
		}
		if (Graph.get(node)!=null) {
			for (AST.class_ child:Graph.get(node))
				checkNameScope(child);
		}
		s_table_method.exitScope();
		s_table_attr.exitScope();
	}


	public String checkExprGetType(AST.expression expr_, String fname)
	{
		if (expr_ instanceof AST.assign) {
			AST.assign expr = (AST.assign)expr_;
			String id = s_table_attr.lookUpGlobal(expr.name);
			String rhs_type = checkExprGetType(expr.e1, fname);
			if (id==null) 
				reportError(fname,expr.lineNo,"Assignment to undeclared variable "+expr.name+".");
			else {
				if (!conforms(rhs_type, id))
					reportError(fname, expr.lineNo, "Type "+rhs_type+" of assigned expression"+
							" does not conform to declared type "+id+" of "+expr.name);
			}
			return expr_.type=rhs_type;
		}
		else if (expr_ instanceof AST.int_const) {
			return expr_.type=INT;
		}
		else if (expr_ instanceof AST.string_const) {
			return expr_.type=STR;
		}
		else if (expr_ instanceof AST.bool_const) {
			return expr_.type=BOOL;
		}
		else if (expr_ instanceof AST.object) {
			AST.object expr = (AST.object)expr_;
			String tmp = s_table_attr.lookUpGlobal(expr.name);
			if (tmp==null) {
				reportError(fname, expr.lineNo, "Undefined variable "+expr.name+".");
				return OBJ;
			}
			return expr_.type=tmp;
		}
		else if (expr_ instanceof AST.arith_expr) { 
			AST.arith_expr expr = (AST.arith_expr)expr_;

			String type1 = checkExprGetType(expr.e1,fname);
			String type2 = checkExprGetType(expr.e2,fname);

			if (!type1.equals(INT) || !type2.equals(INT))
				reportError(fname, expr.lineNo, "non-Int arguments: "+type1+", "+type2);

			return expr_.type=INT;
		}
		else if (expr_ instanceof AST.comp) {
			AST.comp expr = (AST.comp)expr_;

			String arg_type = checkExprGetType(expr.e1,fname);

			if (!arg_type.equals(BOOL))
				reportError(fname, expr.lineNo, "Argument of \'not\' has type "+arg_type+" instead of Bool");

			return expr_.type=BOOL;
		}
		
		else if (expr_ instanceof AST.neg) {
			AST.neg expr = (AST.neg)expr_;

			String arg_type = checkExprGetType(expr.e1,fname);

			if (!arg_type.equals(INT))
				reportError(fname, expr.lineNo, "Argument of ~ has type "+arg_type+" instead of Int");

			return expr_.type=INT;
		}

		else if (expr_ instanceof AST.cmpr_expr) {

			AST.cmpr_expr expr = (AST.cmpr_expr)expr_;
			
			String type1 = checkExprGetType(expr.e1,fname);
			String type2 = checkExprGetType(expr.e2,fname);

			if (expr instanceof AST.eq) {
				if ((type1.equals(INT) || type1.equals(STR) || type1.equals(BOOL) ||
				     type2.equals(INT) || type2.equals(STR) || type2.equals(BOOL))
						&&
					!type1.equals(type2) )

					reportError(fname,expr.lineNo,"Illegal comparison with a basic type.");
			}

			else {
				if (!type1.equals(INT) || !type2.equals(INT))
					reportError(fname, expr.lineNo, "non-Int arguments: "+type1+", "+type2);
			}
			return expr_.type=BOOL;
		}

		else if (expr_ instanceof AST.isvoid) {
			AST.isvoid expr = (AST.isvoid)expr_;
			checkExprGetType(expr.e1, fname);
			return expr_.type=BOOL; 
		}

		else if (expr_ instanceof AST.new_) {
			AST.new_ expr = (AST.new_)expr_;
			if (str2class.get(expr.typeid)==null) {
				reportError(fname, expr.lineNo, "\'new\' used with undefined class "+expr.typeid);
				return OBJ;
			}
			return expr_.type=expr.typeid;
		}

		else if (expr_ instanceof AST.block) {
			AST.block expr = (AST.block)expr_;
			String typeid=new String();
			for (AST.expression e:expr.l1) 
				typeid = checkExprGetType(e, fname);
			return expr_.type=typeid;
		}

		else if (expr_ instanceof AST.loop) {
			AST.loop expr = (AST.loop)expr_;
			String pred_type = checkExprGetType(expr.predicate, fname);
			if (!pred_type.equals(BOOL))
				reportError(fname, expr.predicate.lineNo, "Loop condition does not have type Bool.");
			checkExprGetType(expr.body,fname);
			return expr_.type=OBJ;
		}

		else if (expr_ instanceof AST.cond) {
			AST.cond expr = (AST.cond)expr_;
			String pred_type = checkExprGetType(expr.predicate, fname);

			if (!pred_type.equals(BOOL))
				reportError(fname, expr.predicate.lineNo, "If condition does not have type Bool.");

			String if_type   = checkExprGetType(expr.ifbody, fname);
			String else_type = checkExprGetType(expr.elsebody, fname);

			return expr_.type = typeUnion(if_type, else_type);
		}

		else if (expr_ instanceof AST.let) {
			AST.let expr = (AST.let)expr_;

			s_table_attr.enterScope();

			s_table_attr.insert(expr.name, expr.typeid);

			String init_type = checkExprGetType(expr.value, fname);

			if (!conforms(expr.typeid, init_type))
				reportError(fname, expr.lineNo, "Inferred type "+init_type+" of initialization of "+
					    expr.name+" does not conform to identifier\'s declared type "+expr.typeid);

			String body_type =  checkExprGetType(expr.body, fname);
			
			s_table_attr.exitScope();

			return expr_.type=body_type;
		}

		else if (expr_ instanceof AST.typcase) {
			AST.typcase expr = (AST.typcase)expr_;

			checkExprGetType(expr.predicate, fname);

			List<String> bList = new ArrayList<>();

			String ret_type = null;
			for (AST.branch b:expr.branches) {

				s_table_attr.enterScope();

				if (bList.contains(b.type))
					reportError(fname, b.lineNo, "Duplicate branch "+b.type+" in case statement.");
				else 
					bList.add(b.type);
				
				s_table_attr.insert(b.name, b.type);

				String branch_exp_type = checkExprGetType(b.value, fname);
				ret_type = typeUnion(ret_type, branch_exp_type);

				s_table_attr.exitScope();
			}
			return expr_.type=ret_type;
		}

		else if (expr_ instanceof AST.dispatch) {
			AST.dispatch expr  = (AST.dispatch)expr_;

			String caller_type = checkExprGetType(expr.caller,fname);
			
			AST.method function= getMethod(caller_type, expr.name);

			if(function==null) { 
				reportError(fname, expr.lineNo, "Dispatch to undefined method "+expr.name);
				return OBJ;
			}			

			List<AST.formal>     L1 = function.formals;
			List<AST.expression> L2 = expr.actuals;

			int l_formals = L1.size();
			int l_actuals = L2.size();

			if (l_formals != l_actuals) 
				reportError(fname,expr.lineNo,"Method "+expr.name+" called with wrong number of arguments.");
			else {
				for (int i=0;i<l_formals;++i) {

					String arg_type = checkExprGetType(L2.get(i), fname);

					if (!conforms(L1.get(i).typeid, arg_type))
						reportError(fname,L2.get(i).lineNo,"In call of method "+expr.name+
							    ", type "+arg_type+" of parameter "+L1.get(i).name+
							    " does not conform to declared type "+L1.get(i).typeid);
				}
			}
			return expr_.type=function.typeid;
		}

		else if (expr_ instanceof AST.static_dispatch) {
			AST.static_dispatch expr = (AST.static_dispatch)expr_;
			
			String caller_type  = checkExprGetType(expr.caller, fname);

			if (!conforms(expr.typeid, caller_type)) 
				reportError(fname, expr.lineNo, "Expression type "+caller_type+" does not conform to "+
					    "declared static dispatch type "+expr.typeid);

			return checkExprGetType(new AST.dispatch(new AST.new_(expr.typeid,expr.lineNo), expr.name, expr.actuals, expr.lineNo),
					        fname);
		}

		else if (expr_ instanceof AST.no_expr) {
			return expr_.type=NO_TYPE;
		}
		return expr_.type=null;
	}

	public boolean conforms(String c1, String c2) {
		if (c2.equals(NO_TYPE)) return true;
		if (str2class.get(c2)==null) return false;
		
		String p = str2class.get(c2).parent;
		if (c1.equals(c2)) return true;
		else if (p==null) return false;
		else return conforms(c1, p);
	}

	public String typeUnion(String t1, String t2) {

		if (t1==null) return t2;
		else if (t2==null) return t1;

		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();

		while (t1!=null) {
			l1.add(t1);
			t1 = str2class.get(t1).parent;
		}

		while (t2!=null) {
			l2.add(t2);
			t2 = str2class.get(t2).parent;
		}

		for (String s:l1)
			if (l2.contains(s)) return s;

		return OBJ;
	}

	public AST.method getMethod(String type, String func)
	{
		if (type==null) return null;

		for (AST.feature f:str2class.get(type).features) 
			if (f instanceof AST.method && f.name.equals(func))
				return (AST.method)f;

		return getMethod(str2class.get(type).parent, func);	
	}
}
