// Generated from CoolLexer.g4 by ANTLR 4.5.3
package cool;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, TYPEID=2, OBJECTID=3, BOOL_CONST=4, INT_CONST=5, STR_CONST=6, 
		LPAREN=7, RPAREN=8, COLON=9, ATSYM=10, SEMICOLON=11, COMMA=12, PLUS=13, 
		MINUS=14, STAR=15, SLASH=16, TILDE=17, LT=18, EQUALS=19, LBRACE=20, RBRACE=21, 
		DOT=22, DARROW=23, LE=24, ASSIGN=25, CLASS=26, ELSE=27, FI=28, IF=29, 
		IN=30, INHERITS=31, LET=32, LOOP=33, POOL=34, THEN=35, WHILE=36, CASE=37, 
		ESAC=38, OF=39, NEW=40, ISVOID=41, NOT=42, ESLE=43, WS=44, UN_COMM=45, 
		SL_COMM=46, INVALID=47, R_QUOTE=48, EOFINSTR=49, UNESC_N=50, NULL=51, 
		R_COMM=52, EOFINCOM=53, STOP=54, L_QUOTE=55, L_COMM=56;
	public static final int INSTR = 1;
	public static final int INCOMM = 2;
	public static final int NULLCHR = 3;
	public static String[] modeNames = {
		"DEFAULT_MODE", "INSTR", "INCOMM", "NULLCHR"
	};

	public static final String[] ruleNames = {
		"SEMICOLON", "DARROW", "COLON", "COMMA", "PLUS", "ATSYM", "BOOL_CONST", 
		"CLASS", "ELSE", "IF", "THEN", "FI", "ESLE", "WHILE", "CASE", "ESAC", 
		"INHERITS", "IN", "ISVOID", "LET", "LOOP", "POOL", "NEW", "OF", "NOT", 
		"WS", "TYPEID", "OBJECTID", "LPAREN", "RPAREN", "MINUS", "STAR", "SLASH", 
		"TILDE", "LT", "EQUALS", "LBRACE", "RBRACE", "DOT", "LE", "ASSIGN", "INT_CONST", 
		"L_QUOTE", "L_COMM", "UN_COMM", "SL_COMM", "INVALID", "ID", "Digit", "A", 
		"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", 
		"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "R_QUOTE", "EOFINSTR", 
		"UNESC_N", "ESC", "NULL", "TEXT", "R_COMM", "L_AGAIN", "EOFINCOM", "IGNORE", 
		"STOP", "ESCP", "THROW"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'('", "')'", "':'", "'@'", 
		"';'", "','", "'+'", "'-'", "'*'", "'/'", "'~'", "'<'", "'='", "'{'", 
		"'}'", "'.'", "'=>'", "'<='", "'<-'", null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ERROR", "TYPEID", "OBJECTID", "BOOL_CONST", "INT_CONST", "STR_CONST", 
		"LPAREN", "RPAREN", "COLON", "ATSYM", "SEMICOLON", "COMMA", "PLUS", "MINUS", 
		"STAR", "SLASH", "TILDE", "LT", "EQUALS", "LBRACE", "RBRACE", "DOT", "DARROW", 
		"LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "LET", 
		"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "OF", "NEW", "ISVOID", 
		"NOT", "ESLE", "WS", "UN_COMM", "SL_COMM", "INVALID", "R_QUOTE", "EOFINSTR", 
		"UNESC_N", "NULL", "R_COMM", "EOFINCOM", "STOP", "L_QUOTE", "L_COMM"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}



		/*
			YOU CAN ADD YOUR MEMBER VARIABLES AND METHODS HERE
		*/

		/**
		* Function to report errors.
		* Use this function whenever your lexer encounters any erroneous input
		* DO NOT EDIT THIS FUNCTION
		*/
		public void reportError(String errorString){
			setText(errorString);
			setType(ERROR);
		}

		public void processString() {
			Token t = _factory.create(_tokenFactorySourcePair, _type, _text, _channel, _tokenStartCharIndex, getCharIndex()-1, _tokenStartLine, _tokenStartCharPositionInLine);
			String text = t.getText();
			
			String res = new String();

			int n = text.length();

			if(n>1024)  {	//too long string
				reportError("String constant too long");
				return;
			}

			for (int i=1; i<n-1; i++) {
				char c = text.charAt(i), nx = text.charAt(i+1);
				if (c=='\\'){ //convert '\c' to 'c' when c is not 'n', 't', 'f', 'b'
					if (nx=='n' || nx=='t' || nx=='f' || nx=='b' || nx=='\\') 
						res+='\\';
					if(nx == '\\')
						i += 1;
				}
				else
					res+=c;
			}
			setText(res);		//modify the text of the token
			setType(STR_CONST);	//set token type to STR_CONST
		}


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 44:
			UN_COMM_action((RuleContext)_localctx, actionIndex);
			break;
		case 46:
			INVALID_action((RuleContext)_localctx, actionIndex);
			break;
		case 75:
			R_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 76:
			EOFINSTR_action((RuleContext)_localctx, actionIndex);
			break;
		case 77:
			UNESC_N_action((RuleContext)_localctx, actionIndex);
			break;
		case 79:
			NULL_action((RuleContext)_localctx, actionIndex);
			break;
		case 83:
			EOFINCOM_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void UN_COMM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			reportError("Unmatched *)");
			break;
		}
	}
	private void INVALID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			reportError(getText());
			break;
		}
	}
	private void R_QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			processString();
			break;
		}
	}
	private void EOFINSTR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			reportError("EOF in string constant");
			break;
		}
	}
	private void UNESC_N_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			reportError("Unterminated string constant");
			break;
		}
	}
	private void NULL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			reportError("Null character in string constant");
			break;
		}
	}
	private void EOFINCOM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			reportError("EOF in comment");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2:\u01fb\b\1\b\1\b"+
		"\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t"+
		"\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21"+
		"\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30"+
		"\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37"+
		"\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)"+
		"\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63"+
		"\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;"+
		"\4<\t<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G"+
		"\tG\4H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR"+
		"\4S\tS\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\3\2\3\2\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5"+
		"\b\u00cf\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\33\6\33\u0129\n\33\r\33\16\33\u012a\3\33\3\33\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3"+
		"%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3*\3+\6+\u0152\n+\r+\16+\u0153"+
		"\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\7/\u016a"+
		"\n/\f/\16/\u016d\13/\3/\3/\3/\3/\3\60\3\60\3\60\3\61\7\61\u0177\n\61\f"+
		"\61\16\61\u017a\13\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66"+
		"\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3"+
		"A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3"+
		"M\3M\3M\3M\3M\3N\7N\u01b8\nN\fN\16N\u01bb\13N\3N\3N\3N\3O\3O\3O\3O\3O"+
		"\3P\3P\3P\3P\3P\3Q\3Q\3Q\7Q\u01cd\nQ\fQ\16Q\u01d0\13Q\3Q\3Q\3Q\3Q\3Q\3"+
		"R\3R\3R\3R\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T\3U\3U\3U\3V\3V\3V\3V\3"+
		"W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\4\u016b\u01ce\2Z\6\r\b\31\n\13"+
		"\f\16\16\17\20\f\22\6\24\34\26\35\30\37\32%\34\36\36- &\"\'$(&!( *+,\""+
		".#\60$\62*\64)\66,8.:\4<\5>\t@\nB\20D\21F\22H\23J\24L\25N\26P\27R\30T"+
		"\32V\33X\7Z9\\:^/`\60b\61d\2f\2h\2j\2l\2n\2p\2r\2t\2v\2x\2z\2|\2~\2\u0080"+
		"\2\u0082\2\u0084\2\u0086\2\u0088\2\u008a\2\u008c\2\u008e\2\u0090\2\u0092"+
		"\2\u0094\2\u0096\2\u0098\2\u009a\2\u009c\62\u009e\63\u00a0\64\u00a2\2"+
		"\u00a4\65\u00a6\2\u00a8\66\u00aa\2\u00ac\67\u00ae\2\u00b08\u00b2\2\u00b4"+
		"\2\6\2\3\4\5#\5\2\n\f\16\17\"\"\3\2C\\\3\2c|\6\2\62;C\\aac|\3\2\62;\4"+
		"\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKk"+
		"k\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2"+
		"TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\|"+
		"|\6\2\n\f\16\16$$^^\4\2\f\f$$\u01e3\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2"+
		"\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26"+
		"\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2"+
		"\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2"+
		"\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2"+
		"\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2"+
		"\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R"+
		"\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3"+
		"\2\2\2\2`\3\2\2\2\2b\3\2\2\2\3\u009c\3\2\2\2\3\u009e\3\2\2\2\3\u00a0\3"+
		"\2\2\2\3\u00a2\3\2\2\2\3\u00a4\3\2\2\2\3\u00a6\3\2\2\2\4\u00a8\3\2\2\2"+
		"\4\u00aa\3\2\2\2\4\u00ac\3\2\2\2\4\u00ae\3\2\2\2\5\u00b0\3\2\2\2\5\u00b2"+
		"\3\2\2\2\5\u00b4\3\2\2\2\6\u00b6\3\2\2\2\b\u00b8\3\2\2\2\n\u00bb\3\2\2"+
		"\2\f\u00bd\3\2\2\2\16\u00bf\3\2\2\2\20\u00c1\3\2\2\2\22\u00ce\3\2\2\2"+
		"\24\u00d0\3\2\2\2\26\u00d6\3\2\2\2\30\u00db\3\2\2\2\32\u00de\3\2\2\2\34"+
		"\u00e3\3\2\2\2\36\u00e6\3\2\2\2 \u00eb\3\2\2\2\"\u00f1\3\2\2\2$\u00f6"+
		"\3\2\2\2&\u00fb\3\2\2\2(\u0104\3\2\2\2*\u0107\3\2\2\2,\u010e\3\2\2\2."+
		"\u0112\3\2\2\2\60\u0117\3\2\2\2\62\u011c\3\2\2\2\64\u0120\3\2\2\2\66\u0123"+
		"\3\2\2\28\u0128\3\2\2\2:\u012e\3\2\2\2<\u0131\3\2\2\2>\u0134\3\2\2\2@"+
		"\u0136\3\2\2\2B\u0138\3\2\2\2D\u013a\3\2\2\2F\u013c\3\2\2\2H\u013e\3\2"+
		"\2\2J\u0140\3\2\2\2L\u0142\3\2\2\2N\u0144\3\2\2\2P\u0146\3\2\2\2R\u0148"+
		"\3\2\2\2T\u014a\3\2\2\2V\u014d\3\2\2\2X\u0151\3\2\2\2Z\u0155\3\2\2\2\\"+
		"\u015a\3\2\2\2^\u0160\3\2\2\2`\u0165\3\2\2\2b\u0172\3\2\2\2d\u0178\3\2"+
		"\2\2f\u017b\3\2\2\2h\u017d\3\2\2\2j\u017f\3\2\2\2l\u0181\3\2\2\2n\u0183"+
		"\3\2\2\2p\u0185\3\2\2\2r\u0187\3\2\2\2t\u0189\3\2\2\2v\u018b\3\2\2\2x"+
		"\u018d\3\2\2\2z\u018f\3\2\2\2|\u0191\3\2\2\2~\u0193\3\2\2\2\u0080\u0195"+
		"\3\2\2\2\u0082\u0197\3\2\2\2\u0084\u0199\3\2\2\2\u0086\u019b\3\2\2\2\u0088"+
		"\u019d\3\2\2\2\u008a\u019f\3\2\2\2\u008c\u01a1\3\2\2\2\u008e\u01a3\3\2"+
		"\2\2\u0090\u01a5\3\2\2\2\u0092\u01a7\3\2\2\2\u0094\u01a9\3\2\2\2\u0096"+
		"\u01ab\3\2\2\2\u0098\u01ad\3\2\2\2\u009a\u01af\3\2\2\2\u009c\u01b1\3\2"+
		"\2\2\u009e\u01b9\3\2\2\2\u00a0\u01bf\3\2\2\2\u00a2\u01c4\3\2\2\2\u00a4"+
		"\u01c9\3\2\2\2\u00a6\u01d6\3\2\2\2\u00a8\u01da\3\2\2\2\u00aa\u01e0\3\2"+
		"\2\2\u00ac\u01e6\3\2\2\2\u00ae\u01e9\3\2\2\2\u00b0\u01ed\3\2\2\2\u00b2"+
		"\u01f2\3\2\2\2\u00b4\u01f7\3\2\2\2\u00b6\u00b7\7=\2\2\u00b7\7\3\2\2\2"+
		"\u00b8\u00b9\7?\2\2\u00b9\u00ba\7@\2\2\u00ba\t\3\2\2\2\u00bb\u00bc\7<"+
		"\2\2\u00bc\13\3\2\2\2\u00bd\u00be\7.\2\2\u00be\r\3\2\2\2\u00bf\u00c0\7"+
		"-\2\2\u00c0\17\3\2\2\2\u00c1\u00c2\7B\2\2\u00c2\21\3\2\2\2\u00c3\u00c4"+
		"\7h\2\2\u00c4\u00c5\5h\63\2\u00c5\u00c6\5~>\2\u00c6\u00c7\5\u008cE\2\u00c7"+
		"\u00c8\5p\67\2\u00c8\u00cf\3\2\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\5\u008a"+
		"D\2\u00cb\u00cc\5\u0090G\2\u00cc\u00cd\5p\67\2\u00cd\u00cf\3\2\2\2\u00ce"+
		"\u00c3\3\2\2\2\u00ce\u00c9\3\2\2\2\u00cf\23\3\2\2\2\u00d0\u00d1\5l\65"+
		"\2\u00d1\u00d2\5~>\2\u00d2\u00d3\5h\63\2\u00d3\u00d4\5\u008cE\2\u00d4"+
		"\u00d5\5\u008cE\2\u00d5\25\3\2\2\2\u00d6\u00d7\5p\67\2\u00d7\u00d8\5~"+
		">\2\u00d8\u00d9\5\u008cE\2\u00d9\u00da\5p\67\2\u00da\27\3\2\2\2\u00db"+
		"\u00dc\5x;\2\u00dc\u00dd\5r8\2\u00dd\31\3\2\2\2\u00de\u00df\5\u008eF\2"+
		"\u00df\u00e0\5v:\2\u00e0\u00e1\5p\67\2\u00e1\u00e2\5\u0082@\2\u00e2\33"+
		"\3\2\2\2\u00e3\u00e4\5r8\2\u00e4\u00e5\5x;\2\u00e5\35\3\2\2\2\u00e6\u00e7"+
		"\5p\67\2\u00e7\u00e8\5\u008cE\2\u00e8\u00e9\5~>\2\u00e9\u00ea\5p\67\2"+
		"\u00ea\37\3\2\2\2\u00eb\u00ec\5\u0094I\2\u00ec\u00ed\5v:\2\u00ed\u00ee"+
		"\5x;\2\u00ee\u00ef\5~>\2\u00ef\u00f0\5p\67\2\u00f0!\3\2\2\2\u00f1\u00f2"+
		"\5l\65\2\u00f2\u00f3\5h\63\2\u00f3\u00f4\5\u008cE\2\u00f4\u00f5\5p\67"+
		"\2\u00f5#\3\2\2\2\u00f6\u00f7\5p\67\2\u00f7\u00f8\5\u008cE\2\u00f8\u00f9"+
		"\5h\63\2\u00f9\u00fa\5l\65\2\u00fa%\3\2\2\2\u00fb\u00fc\5x;\2\u00fc\u00fd"+
		"\5\u0082@\2\u00fd\u00fe\5v:\2\u00fe\u00ff\5p\67\2\u00ff\u0100\5\u008a"+
		"D\2\u0100\u0101\5x;\2\u0101\u0102\5\u008eF\2\u0102\u0103\5\u008cE\2\u0103"+
		"\'\3\2\2\2\u0104\u0105\5x;\2\u0105\u0106\5\u0082@\2\u0106)\3\2\2\2\u0107"+
		"\u0108\5x;\2\u0108\u0109\5\u008cE\2\u0109\u010a\5\u0092H\2\u010a\u010b"+
		"\5\u0084A\2\u010b\u010c\5x;\2\u010c\u010d\5n\66\2\u010d+\3\2\2\2\u010e"+
		"\u010f\5~>\2\u010f\u0110\5p\67\2\u0110\u0111\5\u008eF\2\u0111-\3\2\2\2"+
		"\u0112\u0113\5~>\2\u0113\u0114\5\u0084A\2\u0114\u0115\5\u0084A\2\u0115"+
		"\u0116\5\u0086B\2\u0116/\3\2\2\2\u0117\u0118\5\u0086B\2\u0118\u0119\5"+
		"\u0084A\2\u0119\u011a\5\u0084A\2\u011a\u011b\5~>\2\u011b\61\3\2\2\2\u011c"+
		"\u011d\5\u0082@\2\u011d\u011e\5p\67\2\u011e\u011f\5\u0094I\2\u011f\63"+
		"\3\2\2\2\u0120\u0121\5\u0084A\2\u0121\u0122\5r8\2\u0122\65\3\2\2\2\u0123"+
		"\u0124\5\u0082@\2\u0124\u0125\5\u0084A\2\u0125\u0126\5\u008eF\2\u0126"+
		"\67\3\2\2\2\u0127\u0129\t\2\2\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2"+
		"\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d"+
		"\b\33\2\2\u012d9\3\2\2\2\u012e\u012f\t\3\2\2\u012f\u0130\5d\61\2\u0130"+
		";\3\2\2\2\u0131\u0132\t\4\2\2\u0132\u0133\5d\61\2\u0133=\3\2\2\2\u0134"+
		"\u0135\7*\2\2\u0135?\3\2\2\2\u0136\u0137\7+\2\2\u0137A\3\2\2\2\u0138\u0139"+
		"\7/\2\2\u0139C\3\2\2\2\u013a\u013b\7,\2\2\u013bE\3\2\2\2\u013c\u013d\7"+
		"\61\2\2\u013dG\3\2\2\2\u013e\u013f\7\u0080\2\2\u013fI\3\2\2\2\u0140\u0141"+
		"\7>\2\2\u0141K\3\2\2\2\u0142\u0143\7?\2\2\u0143M\3\2\2\2\u0144\u0145\7"+
		"}\2\2\u0145O\3\2\2\2\u0146\u0147\7\177\2\2\u0147Q\3\2\2\2\u0148\u0149"+
		"\7\60\2\2\u0149S\3\2\2\2\u014a\u014b\7>\2\2\u014b\u014c\7?\2\2\u014cU"+
		"\3\2\2\2\u014d\u014e\7>\2\2\u014e\u014f\7/\2\2\u014fW\3\2\2\2\u0150\u0152"+
		"\5f\62\2\u0151\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154Y\3\2\2\2\u0155\u0156\7$\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0158\b,\3\2\u0158\u0159\b,\4\2\u0159[\3\2\2\2\u015a\u015b\7*\2\2\u015b"+
		"\u015c\7,\2\2\u015c\u015d\3\2\2\2\u015d\u015e\b-\3\2\u015e\u015f\b-\5"+
		"\2\u015f]\3\2\2\2\u0160\u0161\7,\2\2\u0161\u0162\7+\2\2\u0162\u0163\3"+
		"\2\2\2\u0163\u0164\b.\6\2\u0164_\3\2\2\2\u0165\u0166\7/\2\2\u0166\u0167"+
		"\7/\2\2\u0167\u016b\3\2\2\2\u0168\u016a\13\2\2\2\u0169\u0168\3\2\2\2\u016a"+
		"\u016d\3\2\2\2\u016b\u016c\3\2\2\2\u016b\u0169\3\2\2\2\u016c\u016e\3\2"+
		"\2\2\u016d\u016b\3\2\2\2\u016e\u016f\7\f\2\2\u016f\u0170\3\2\2\2\u0170"+
		"\u0171\b/\2\2\u0171a\3\2\2\2\u0172\u0173\13\2\2\2\u0173\u0174\b\60\7\2"+
		"\u0174c\3\2\2\2\u0175\u0177\t\5\2\2\u0176\u0175\3\2\2\2\u0177\u017a\3"+
		"\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179e\3\2\2\2\u017a\u0178"+
		"\3\2\2\2\u017b\u017c\t\6\2\2\u017cg\3\2\2\2\u017d\u017e\t\7\2\2\u017e"+
		"i\3\2\2\2\u017f\u0180\t\b\2\2\u0180k\3\2\2\2\u0181\u0182\t\t\2\2\u0182"+
		"m\3\2\2\2\u0183\u0184\t\n\2\2\u0184o\3\2\2\2\u0185\u0186\t\13\2\2\u0186"+
		"q\3\2\2\2\u0187\u0188\t\f\2\2\u0188s\3\2\2\2\u0189\u018a\t\r\2\2\u018a"+
		"u\3\2\2\2\u018b\u018c\t\16\2\2\u018cw\3\2\2\2\u018d\u018e\t\17\2\2\u018e"+
		"y\3\2\2\2\u018f\u0190\t\20\2\2\u0190{\3\2\2\2\u0191\u0192\t\21\2\2\u0192"+
		"}\3\2\2\2\u0193\u0194\t\22\2\2\u0194\177\3\2\2\2\u0195\u0196\t\23\2\2"+
		"\u0196\u0081\3\2\2\2\u0197\u0198\t\24\2\2\u0198\u0083\3\2\2\2\u0199\u019a"+
		"\t\25\2\2\u019a\u0085\3\2\2\2\u019b\u019c\t\26\2\2\u019c\u0087\3\2\2\2"+
		"\u019d\u019e\t\27\2\2\u019e\u0089\3\2\2\2\u019f\u01a0\t\30\2\2\u01a0\u008b"+
		"\3\2\2\2\u01a1\u01a2\t\31\2\2\u01a2\u008d\3\2\2\2\u01a3\u01a4\t\32\2\2"+
		"\u01a4\u008f\3\2\2\2\u01a5\u01a6\t\33\2\2\u01a6\u0091\3\2\2\2\u01a7\u01a8"+
		"\t\34\2\2\u01a8\u0093\3\2\2\2\u01a9\u01aa\t\35\2\2\u01aa\u0095\3\2\2\2"+
		"\u01ab\u01ac\t\36\2\2\u01ac\u0097\3\2\2\2\u01ad\u01ae\t\37\2\2\u01ae\u0099"+
		"\3\2\2\2\u01af\u01b0\t \2\2\u01b0\u009b\3\2\2\2\u01b1\u01b2\7$\2\2\u01b2"+
		"\u01b3\bM\b\2\u01b3\u01b4\3\2\2\2\u01b4\u01b5\bM\t\2\u01b5\u009d\3\2\2"+
		"\2\u01b6\u01b8\7\f\2\2\u01b7\u01b6\3\2\2\2\u01b8\u01bb\3\2\2\2\u01b9\u01b7"+
		"\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bc\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc"+
		"\u01bd\7\2\2\3\u01bd\u01be\bN\n\2\u01be\u009f\3\2\2\2\u01bf\u01c0\7\f"+
		"\2\2\u01c0\u01c1\bO\13\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3\bO\t\2\u01c3"+
		"\u00a1\3\2\2\2\u01c4\u01c5\7^\2\2\u01c5\u01c6\t!\2\2\u01c6\u01c7\3\2\2"+
		"\2\u01c7\u01c8\bP\3\2\u01c8\u00a3\3\2\2\2\u01c9\u01ce\7\2\2\2\u01ca\u01cd"+
		"\13\2\2\2\u01cb\u01cd\5\u00a2P\2\u01cc\u01ca\3\2\2\2\u01cc\u01cb\3\2\2"+
		"\2\u01cd\u01d0\3\2\2\2\u01ce\u01cf\3\2\2\2\u01ce\u01cc\3\2\2\2\u01cf\u01d1"+
		"\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d1\u01d2\t\"\2\2\u01d2\u01d3\bQ\f\2\u01d3"+
		"\u01d4\3\2\2\2\u01d4\u01d5\bQ\r\2\u01d5\u00a5\3\2\2\2\u01d6\u01d7\13\2"+
		"\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01d9\bR\3\2\u01d9\u00a7\3\2\2\2\u01da"+
		"\u01db\7,\2\2\u01db\u01dc\7+\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01de\bS\2"+
		"\2\u01de\u01df\bS\16\2\u01df\u00a9\3\2\2\2\u01e0\u01e1\7*\2\2\u01e1\u01e2"+
		"\7,\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4\bT\3\2\u01e4\u01e5\bT\5\2\u01e5"+
		"\u00ab\3\2\2\2\u01e6\u01e7\7\2\2\3\u01e7\u01e8\bU\17\2\u01e8\u00ad\3\2"+
		"\2\2\u01e9\u01ea\13\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ec\bV\3\2\u01ec"+
		"\u00af\3\2\2\2\u01ed\u01ee\t\"\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f0\bW"+
		"\2\2\u01f0\u01f1\bW\t\2\u01f1\u00b1\3\2\2\2\u01f2\u01f3\7^\2\2\u01f3\u01f4"+
		"\t!\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f6\bX\3\2\u01f6\u00b3\3\2\2\2\u01f7"+
		"\u01f8\13\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fa\bY\3\2\u01fa\u00b5\3\2"+
		"\2\2\16\2\3\4\5\u00ce\u012a\u0153\u016b\u0178\u01b9\u01cc\u01ce\20\b\2"+
		"\2\5\2\2\4\3\2\7\4\2\3.\2\3\60\3\3M\4\4\2\2\3N\5\3O\6\3Q\7\4\5\2\6\2\2"+
		"\3U\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}