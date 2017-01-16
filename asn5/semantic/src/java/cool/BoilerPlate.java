package cool;

import java.util.*;

public class BoilerPlate {

	static String DATA_LAYOUT = "target datalayout = \"e-m:e-i64:64-f80:128-n8:16:32:64-S128\"";

	static String TARGET_TRIPLE = "target triple = \"x86_64-unknown-linux-gnu\"";

	static HashMap <String,String> IRTYPE;
	static {
		IRTYPE = new HashMap<String, String>();
		IRTYPE.put("Int","i32");
		IRTYPE.put("Bool","i32");
		IRTYPE.put("String","[1024 x i8]*");
	}

	static String OBJECT_ABORT = "define %classObject* @_ZN6Object5abort(%class.Object* %this) norerturn {\n"
		+ "entry:\n"
		+ "call void @exit(i32 1)\n"
		+ "ret %classObject* null\n"
		+ "}";

	static String OBJECT_TYPENAME = "define [1024 x i8]* @_ZN6Object9type_name( %class.Object* %this ) {\n"
		+ "entry:\n"
		+ "%0 = getelementptr inbounds %classObject, %classObject* %this, i32 0, i32 0\n"
		+ "%1 = load i32, i32* %0\n"
		+ "%2 = getelementptr inbounds [8 x [1024 x i8]], [8 x [1024 x i8]]* @classnames, i32 0, i32 %1\n"
		+ "%retval = call [1024 x i8]* @_ZN6String4copy( [1024 x i8]* %2 )\n"
		+ "ret [1024 x i8]* %retval\n"
		+ "}\n";

	static String OBJECT_COPY = "define %classObject* @_ZN6Object4copy( %class.Object* %this ) {\n"
		+ "entry:\n"
		+ "%call = call i8* @malloc( i64 32 )\n"
		+ "%retval = bitcast i8* %call to %classObject*\n"
		+ "%0 = getelementptr inbounds %classObject, %classObject* %retval, i32 0, i32 0\n"
		+ "store i32 0, i32* %0\n"
		+ "%1 = getelementptr inbounds %classObject, %classObject* %retval, i32 0, i32 1\n"
		+ "store i8* bitcast ( [3 x i8*]* @VTObject to i8*), i8** %1\n"
		+ "%2 = getelementptr inbounds %classObject, %classObject* %retval, i32 0, i32 2\n"
		+ "%3 = getelementptr inbounds %classObject, %classObject* %this, i32 0, i32 2\n"
		+ "call void @_ZN6Object4copyTo( %classbaseObject* %2, %classbaseObject* %3 )\n"
		+ "ret %classObject* %retval\n"
		+ "}\n";

	static String IO_OUTSTRING = "define %classIO* @_ZN2IO10out_string( %class.IO* %this, [1024 x i8]* %str ) {\n"
		+ "entry:\n"
		+ "%0 = call i32 (i8*, ...) @printf( i8* bitcast ( [3 x i8]* @strformatstr to i8* ), [1024 x i8]* %str )\n"
		+ "ret %classIO* %this\n"
		+ "}\n";

	static String IO_OUTINT = "define %classIO* @_ZN2IO7out_int( %class.IO* %this, i32 %int ) {\n"
		+ "entry:\n"
		+ "%0 = call i32 (i8*, ...) @printf( i8* bitcast ( [3 x i8]* @intformatstr to i8* ), i32 %int )\n"
		+ "ret %classIO* %this\n"
		+ "}\n";

	static String IO_INSTRING = "define [1024 x i8]* @_ZN2IO9in_string( %class.IO* %this ) {\n"
		+ "entry:\n"
		+ "%0 = call i8* @malloc( i64 1024 )\n"
		+ "%retval = bitcast i8* %0 to [1024 x i8]*\n"
		+ "%1 = call i32 (i8*, ...) @scanf( i8* bitcast ( [3 x i8]* @strformatstr to i8* ), [1024 x i8]* %retval )\n"
		+ "ret [1024 x i8]* %retval\n"
		+ "}\n";

	static String IO_ININT = "define i32 @_ZN2IO9in_int( %class.IO* %this ) {\n"
		+ "entry:\n"
		+ "%0 = call i8* @malloc( i64 4 )\n"
		+ "%1 = bitcast i8* %0 to i32*\n"
		+ "%2 = call i32 (i8*, ...) @scanf( i8* bitcast ( [3 x i8]* @intformatstr to i8* ), i32* %1 )\n"
		+ "%retval = load i32, i32* %1\n"
		+ "ret i32 %retval\n"
		+ "}\n";

	static String STRING_COPY = "define [1024 x i8]* @_ZN6String4copy( [1024 x i8]* %this ) {\n"
		+ "entry:\n"
		+ "%0 = call i8* @malloc( i64 1024 )\n"
		+ "%retval = bitcast i8* %0 to [1024 x i8]*\n"
		+ "%1 = bitcast [1024 x i8]* %this to i8*\n"
		+ "%2 = bitcast [1024 x i8]* %retval to i8*\n"
		+ "%3 = call i8* @strcpy( i8* %2, i8* %1)\n"
		+ "ret [1024 x i8]* %retval\n"
		+ "}\n";

	static String STRING_CONCAT = "define [1024 x i8]* @_ZN6String6concat( [1024 x i8]* %this, [1024 x i8]* %that ) {\n"
		+ "entry:\n"
		+ "%retval = call [1024 x i8]* @_ZN6String4copy( [1024 x i8]* %this )\n"
		+ "%0 = bitcast [1024 x i8]* %retval to i8*\n"
		+ "%1 = bitcast [1024 x i8]* %that to i8*\n"
		+ "%2 = call i8* @strcat( i8* %0, i8* %1 )\n"
		+ "ret [1024 x i8]* %retval\n"
		+ "}\n";

	static String STRING_LENGTH = "define i32 @_ZN6String6length( [1024 x i8]* %this ) {\n"
		+ "entry:\n"
		+ "%0 = bitcast [1024 x i8]* %this to i8*\n"
		+ "%1 = call i64 @strlen( i8* %0 )\n"
		+ "%retval = trunc i64 %1 to i32\n"
		+ "ret i32 %retval\n"
		+ "}\n";

	static String STRING_SUBSTR = "define [1024 x i8]* @_ZN6String6substr( [1024 x i8]* %this, i32 %start, i32 %len ) {\n"
		+ "entry:\n"
		+ "%0 = getelementptr inbounds [1024 x i8], [1024 x i8]* %this, i32 0, i32 %start\n"
		+ "%1 = call i8* @malloc( i64 1024 )\n"
		+ "%retval = bitcast i8* %1 to [1024 x i8]*\n"
		+ "%2 = bitcast [1024 x i8]* %retval to i8*\n"
		+ "%3 = call i8* @strncpy( i8* %2, i8* %0, i32 %len )\n"
		+ "%4 = getelementptr inbounds [1024 x i8], [1024 x i8]* %retval, i32 0, i32 %len\n"
		+ "store i8 0, i8* %4\n"
		+ "ret [1024 x i8]* %retval\n"
		+ "}\n";
}
