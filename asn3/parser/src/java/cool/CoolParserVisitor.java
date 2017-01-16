// Generated from CoolParser.g4 by ANTLR 4.5.3
package cool;

	import cool.AST;
	import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_list(CoolParser.Class_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_(CoolParser.Class_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#feature_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature_list(CoolParser.Feature_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature(CoolParser.FeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(CoolParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(CoolParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_list(CoolParser.Formal_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CoolParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CoolParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#arg_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_list(CoolParser.Arg_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(CoolParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#let_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_list(CoolParser.Let_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#branch_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch_list(CoolParser.Branch_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(CoolParser.BranchContext ctx);
}