target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

%class.Object.Base = type { } 

@_ZTV6Object = constant [3 x i8*] [i8 bitcast (%class.Object* (  )* _ZN6Object5abort to i8*), i8 bitcast ([1024 x i8]* (  )* _ZN6Object9type_name to i8*), i8 bitcast (%class.SELF_TYPE* (  )* _ZN6Object4copy to i8*)] 
define %classObject* @_ZN6Object5abort(%class.Object* %this) norerturn {
entry:
call void @exit(i32 1)
ret %classObject* null
}
define [1024 x i8]* @_ZN6Object9type_name( %class.Object* %this ) {
entry:
%0 = getelementptr inbounds %classObject, %classObject* %this, i32 0, i32 0
%1 = load i32, i32* %0
%2 = getelementptr inbounds [8 x [1024 x i8]], [8 x [1024 x i8]]* @classnames, i32 0, i32 %1
%retval = call [1024 x i8]* @_ZN6String4copy( [1024 x i8]* %2 )
ret [1024 x i8]* %retval
}

define %classObject* @_ZN6Object4copy( %class.Object* %this ) {
entry:
%call = call i8* @malloc( i64 32 )
%retval = bitcast i8* %call to %classObject*
%0 = getelementptr inbounds %classObject, %classObject* %retval, i32 0, i32 0
store i32 0, i32* %0
%1 = getelementptr inbounds %classObject, %classObject* %retval, i32 0, i32 1
store i8* bitcast ( [3 x i8*]* @VTObject to i8*), i8** %1
%2 = getelementptr inbounds %classObject, %classObject* %retval, i32 0, i32 2
%3 = getelementptr inbounds %classObject, %classObject* %this, i32 0, i32 2
call void @_ZN6Object4copyTo( %classbaseObject* %2, %classbaseObject* %3 )
ret %classObject* %retval
}

%class.IO.Base = type { } 

@_ZTV2IO = constant [4 x i8*] [i8 bitcast (%class.SELF_TYPE* ( [1024 x i8]* )* _ZN2IO10out_string to i8*), i8 bitcast (%class.SELF_TYPE* ( i32 )* _ZN2IO7out_int to i8*), i8 bitcast ([1024 x i8]* (  )* _ZN2IO9in_string to i8*), i8 bitcast (i32 (  )* _ZN2IO6in_int to i8*)] 
%class.Int.Base = type { } 

@_ZTV3Int = constant [0 x i8*] [] 
%class.Bool.Base = type { } 

@_ZTV4Bool = constant [0 x i8*] [] 
%class.String.Base = type { } 

@_ZTV6String = constant [3 x i8*] [i8 bitcast (i32 (  )* _ZN6String6length to i8*), i8 bitcast ([1024 x i8]* ( [1024 x i8]* )* _ZN6String6concat to i8*), i8 bitcast ([1024 x i8]* ( i32, i32 )* _ZN6String6substr to i8*)] 
define [1024 x i8]* @_ZN6String6concat( [1024 x i8]* %this, [1024 x i8]* %that ) {
entry:
%retval = call [1024 x i8]* @_ZN6String4copy( [1024 x i8]* %this )
%0 = bitcast [1024 x i8]* %retval to i8*
%1 = bitcast [1024 x i8]* %that to i8*
%2 = call i8* @strcat( i8* %0, i8* %1 )
ret [1024 x i8]* %retval
}

define [1024 x i8]* @_ZN6String4copy( [1024 x i8]* %this ) {
entry:
%0 = call i8* @malloc( i64 1024 )
%retval = bitcast i8* %0 to [1024 x i8]*
%1 = bitcast [1024 x i8]* %this to i8*
%2 = bitcast [1024 x i8]* %retval to i8*
%3 = call i8* @strcpy( i8* %2, i8* %1)
ret [1024 x i8]* %retval
}

define i32 @_ZN6String6length( [1024 x i8]* %this ) {
entry:
%0 = bitcast [1024 x i8]* %this to i8*
%1 = call i64 @strlen( i8* %0 )
%retval = trunc i64 %1 to i32
ret i32 %retval
}

define [1024 x i8]* @_ZN6String6substr( [1024 x i8]* %this, i32 %start, i32 %len ) {
entry:
%0 = getelementptr inbounds [1024 x i8], [1024 x i8]* %this, i32 0, i32 %start
%1 = call i8* @malloc( i64 1024 )
%retval = bitcast i8* %1 to [1024 x i8]*
%2 = bitcast [1024 x i8]* %retval to i8*
%3 = call i8* @strncpy( i8* %2, i8* %0, i32 %len )
%4 = getelementptr inbounds [1024 x i8], [1024 x i8]* %retval, i32 0, i32 %len
store i8 0, i8* %4
ret [1024 x i8]* %retval
}

%class.C.Base = type {i32, i32 } 

@_ZTV1C = constant [1 x i8*] [i8 bitcast (%class.C* ( i32, i32 )* _ZN1C4init to i8*)] 
%class.A.Base = type { } 

@_ZTV1A = constant [1 x i8*] [i8 bitcast (i32 ( [1024 x i8]*, i32 )* _ZN1A3abc to i8*)] 
%class.D.Base = type { } 

@_ZTV1D = constant [1 x i8*] [i8 bitcast (%class.Object* ( i32 )* _ZN1D4main to i8*)] 
%class.E.Base = type { } 

@_ZTV1E = constant [1 x i8*] [i8 bitcast (%class.Object* (  )* _ZN1E3xyz to i8*)] 
%class.F.Base = type { } 

@_ZTV1F = constant [0 x i8*] [] 
%class.Main.Base = type { } 

@_ZTV4Main = constant [1 x i8*] [i8 bitcast (%class.C* (  )* _ZN4Main4main to i8*)] 
