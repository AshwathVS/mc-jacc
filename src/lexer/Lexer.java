// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: lex.jflex

package lexer;

import core.Type;
import core.Token;


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class Lexer {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\25\u0100\1\u0200\11\u0100\1\u0300\17\u0100\1\u0400\247\u0100"+
    "\10\u0500\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\4\2\22\0\1\1\1\3\1\4\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\12\22\1\23\1\24\1\25"+
    "\1\26\1\27\2\0\1\30\2\31\1\32\1\33\1\31"+
    "\1\34\4\31\1\35\1\31\1\36\1\37\2\31\1\40"+
    "\1\31\1\41\6\31\1\42\1\43\1\44\1\45\1\46"+
    "\1\0\1\47\1\50\1\51\1\52\1\53\1\54\1\55"+
    "\1\56\1\57\1\31\1\60\1\61\1\31\1\62\1\63"+
    "\2\31\1\64\1\65\1\66\1\67\1\31\1\70\3\31"+
    "\1\71\1\72\1\73\1\74\6\0\1\2\32\0\1\1"+
    "\u01df\0\1\1\177\0\13\1\35\0\2\2\5\0\1\1"+
    "\57\0\1\1\240\0\1\1\377\0\u0100\75";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1536];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\1\1\22\1\23\1\24\1\25\1\26\7\27"+
    "\1\30\1\31\1\32\1\33\1\34\12\27\1\35\1\36"+
    "\1\37\1\40\1\41\1\0\1\42\1\0\1\43\1\44"+
    "\1\45\1\0\1\46\1\47\1\50\1\51\1\52\1\53"+
    "\1\54\2\0\1\55\1\56\1\57\1\60\1\61\1\27"+
    "\1\0\1\57\1\60\1\26\1\56\1\24\1\41\1\27"+
    "\1\62\1\63\5\27\1\64\4\27\1\65\5\27\1\66"+
    "\1\62\1\67\1\0\1\1\1\70\1\71\1\72\1\44"+
    "\1\3\10\27\1\73\6\27\1\0\1\53\6\27\1\74"+
    "\1\27\1\75\2\27\1\76\1\27\1\77\1\27\1\100"+
    "\1\101\2\27\1\0\1\102\2\27\1\103\1\104\2\27"+
    "\1\0\3\27\1\105\1\106\1\107\1\110\1\27\1\111";

  private static int [] zzUnpackAction() {
    int [] result = new int[162];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\76\0\174\0\272\0\370\0\370\0\u0136\0\u0174"+
    "\0\u01b2\0\370\0\370\0\u01f0\0\u022e\0\370\0\u026c\0\u02aa"+
    "\0\u02e8\0\u0326\0\370\0\370\0\u0364\0\u03a2\0\u03e0\0\u041e"+
    "\0\u045c\0\u049a\0\u04d8\0\u0516\0\u0554\0\u0592\0\370\0\370"+
    "\0\370\0\u05d0\0\u045c\0\u060e\0\u064c\0\u068a\0\u06c8\0\u0706"+
    "\0\u0744\0\u0782\0\u07c0\0\u07fe\0\u083c\0\370\0\u087a\0\370"+
    "\0\370\0\370\0\272\0\370\0\u08b8\0\370\0\370\0\370"+
    "\0\u08f6\0\370\0\370\0\370\0\370\0\370\0\u0934\0\370"+
    "\0\u02aa\0\u0972\0\u09b0\0\370\0\370\0\370\0\u09ee\0\u0a2c"+
    "\0\u0a6a\0\u045c\0\u045c\0\u045c\0\u045c\0\u045c\0\u045c\0\u0aa8"+
    "\0\u045c\0\370\0\u0ae6\0\u0b24\0\u0b62\0\u0ba0\0\u0bde\0\u0c1c"+
    "\0\u0c5a\0\u0c98\0\u0cd6\0\u0d14\0\u045c\0\u0d52\0\u0d90\0\u0dce"+
    "\0\u0e0c\0\u0e4a\0\370\0\370\0\370\0\u0e88\0\u0a6a\0\370"+
    "\0\370\0\370\0\u045c\0\u045c\0\u0ec6\0\u0f04\0\u0f42\0\u0f80"+
    "\0\u0fbe\0\u0ffc\0\u103a\0\u1078\0\u045c\0\u10b6\0\u10f4\0\u1132"+
    "\0\u1170\0\u11ae\0\u11ec\0\u122a\0\u122a\0\u1268\0\u12a6\0\u12e4"+
    "\0\u1322\0\u1360\0\u139e\0\u13dc\0\u141a\0\370\0\u1458\0\u1496"+
    "\0\u045c\0\u14d4\0\370\0\u1512\0\370\0\370\0\u1550\0\u158e"+
    "\0\u15cc\0\370\0\u160a\0\u1648\0\u045c\0\370\0\u1686\0\u16c4"+
    "\0\u1702\0\u1740\0\u177e\0\u17bc\0\370\0\370\0\370\0\370"+
    "\0\u17fa\0\370";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[162];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\2\2\1\3\1\4\1\5\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\2\31\1\32\1\33\1\34\1\35\1\36\2\31\1\37"+
    "\1\40\1\41\1\42\1\43\1\31\1\44\1\45\1\46"+
    "\1\47\1\50\1\51\1\31\1\52\5\31\1\53\1\54"+
    "\1\31\1\55\1\56\1\57\1\60\1\61\2\0\2\2"+
    "\121\0\1\62\47\0\4\63\1\64\36\63\1\65\32\63"+
    "\124\0\1\66\57\0\1\67\15\0\1\70\47\0\2\71"+
    "\1\0\72\71\27\0\1\72\64\0\1\73\10\0\1\74"+
    "\66\0\1\75\6\0\1\76\71\0\1\77\101\0\1\100"+
    "\67\0\1\101\1\0\1\22\10\0\1\102\17\0\1\102"+
    "\47\0\1\103\1\104\75\0\1\105\75\0\1\106\1\107"+
    "\70\0\1\31\5\0\6\31\1\110\3\31\4\0\23\31"+
    "\27\0\1\31\5\0\12\31\4\0\23\31\22\0\1\111"+
    "\1\0\1\111\2\0\1\31\5\0\3\31\1\112\6\31"+
    "\4\0\23\31\27\0\1\31\5\0\3\31\1\113\5\31"+
    "\1\114\4\0\23\31\27\0\1\31\5\0\3\31\1\115"+
    "\5\31\1\116\4\0\23\31\27\0\1\31\5\0\3\31"+
    "\1\117\3\31\1\120\2\31\4\0\23\31\27\0\1\31"+
    "\5\0\10\31\1\121\1\31\4\0\23\31\33\0\1\122"+
    "\71\0\1\31\5\0\12\31\4\0\15\31\1\123\1\124"+
    "\4\31\27\0\1\31\5\0\12\31\4\0\1\31\1\125"+
    "\6\31\1\126\4\31\1\127\5\31\27\0\1\31\5\0"+
    "\12\31\4\0\15\31\1\130\5\31\22\0\1\111\1\0"+
    "\1\111\2\0\1\31\5\0\12\31\4\0\13\31\1\131"+
    "\7\31\27\0\1\31\5\0\12\31\4\0\1\31\1\132"+
    "\13\31\1\133\5\31\27\0\1\31\5\0\12\31\4\0"+
    "\15\31\1\134\5\31\27\0\1\31\5\0\12\31\4\0"+
    "\6\31\1\135\5\31\1\136\6\31\27\0\1\31\5\0"+
    "\12\31\4\0\20\31\1\137\1\31\1\140\27\0\1\31"+
    "\5\0\12\31\4\0\16\31\1\141\4\31\27\0\1\31"+
    "\5\0\12\31\4\0\10\31\1\142\12\31\33\0\1\143"+
    "\43\0\1\144\3\0\2\63\1\0\72\63\12\0\1\145"+
    "\104\0\1\101\1\0\1\77\10\0\1\146\17\0\1\146"+
    "\37\0\1\111\1\0\1\111\2\0\1\147\101\0\1\150"+
    "\75\0\1\151\1\152\70\0\1\31\5\0\2\31\1\153"+
    "\7\31\4\0\23\31\27\0\1\147\75\0\1\31\5\0"+
    "\11\31\1\154\4\0\23\31\27\0\1\31\5\0\12\31"+
    "\4\0\15\31\1\155\5\31\27\0\1\31\5\0\12\31"+
    "\4\0\5\31\1\156\15\31\27\0\1\31\5\0\12\31"+
    "\4\0\17\31\1\157\3\31\27\0\1\31\5\0\12\31"+
    "\4\0\1\31\1\160\21\31\27\0\1\31\5\0\12\31"+
    "\4\0\14\31\1\161\6\31\27\0\1\31\5\0\12\31"+
    "\4\0\21\31\1\162\1\31\27\0\1\31\5\0\12\31"+
    "\4\0\17\31\1\163\3\31\27\0\1\31\5\0\12\31"+
    "\4\0\13\31\1\164\7\31\27\0\1\31\5\0\12\31"+
    "\4\0\16\31\1\165\4\31\27\0\1\31\5\0\12\31"+
    "\4\0\20\31\1\166\2\31\27\0\1\31\5\0\12\31"+
    "\4\0\20\31\1\167\2\31\27\0\1\31\5\0\12\31"+
    "\4\0\16\31\1\170\4\31\27\0\1\31\5\0\12\31"+
    "\4\0\11\31\1\171\11\31\27\0\1\31\5\0\12\31"+
    "\4\0\21\31\1\172\1\31\27\0\1\31\5\0\12\31"+
    "\4\0\11\31\1\173\11\31\22\0\1\174\1\0\1\174"+
    "\2\0\1\175\75\0\1\31\5\0\12\31\4\0\13\31"+
    "\1\176\7\31\27\0\1\31\5\0\12\31\4\0\1\31"+
    "\1\177\21\31\27\0\1\31\5\0\12\31\4\0\5\31"+
    "\1\200\15\31\27\0\1\31\5\0\12\31\4\0\16\31"+
    "\1\201\4\31\27\0\1\31\5\0\12\31\4\0\20\31"+
    "\1\202\2\31\27\0\1\31\5\0\12\31\4\0\2\31"+
    "\1\203\20\31\27\0\1\31\5\0\12\31\4\0\5\31"+
    "\1\204\15\31\27\0\1\31\5\0\12\31\4\0\17\31"+
    "\1\172\3\31\27\0\1\31\5\0\12\31\4\0\15\31"+
    "\1\205\5\31\6\0\2\206\17\0\1\31\5\0\12\31"+
    "\4\0\23\31\27\0\1\31\5\0\12\31\4\0\11\31"+
    "\1\207\11\31\27\0\1\31\5\0\12\31\4\0\20\31"+
    "\1\210\2\31\27\0\1\31\5\0\12\31\4\0\5\31"+
    "\1\211\15\31\27\0\1\31\5\0\12\31\4\0\13\31"+
    "\1\212\7\31\27\0\1\175\54\0\2\213\17\0\1\31"+
    "\5\0\12\31\4\0\23\31\27\0\1\31\5\0\12\31"+
    "\4\0\12\31\1\214\10\31\6\0\2\215\17\0\1\31"+
    "\5\0\12\31\4\0\23\31\6\0\2\216\17\0\1\31"+
    "\5\0\12\31\4\0\23\31\27\0\1\31\5\0\12\31"+
    "\4\0\11\31\1\217\11\31\27\0\1\31\5\0\12\31"+
    "\4\0\13\31\1\220\7\31\6\0\2\221\17\0\1\31"+
    "\5\0\12\31\4\0\23\31\6\0\2\222\17\0\1\31"+
    "\5\0\12\31\4\0\23\31\27\0\1\31\5\0\12\31"+
    "\4\0\14\31\1\223\6\31\27\0\1\31\5\0\12\31"+
    "\4\0\3\31\1\224\17\31\27\0\1\31\5\0\12\31"+
    "\4\0\5\31\1\225\15\31\6\0\2\226\17\0\1\31"+
    "\5\0\12\31\4\0\23\31\27\0\1\31\5\0\12\31"+
    "\4\0\14\31\1\227\6\31\27\0\1\31\5\0\12\31"+
    "\4\0\5\31\1\230\15\31\64\0\1\231\40\0\1\31"+
    "\5\0\12\31\4\0\7\31\1\232\13\31\27\0\1\31"+
    "\5\0\12\31\4\0\10\31\1\233\12\31\27\0\1\31"+
    "\5\0\12\31\4\0\21\31\1\234\1\31\6\0\2\235"+
    "\17\0\1\31\5\0\12\31\4\0\23\31\61\0\1\236"+
    "\22\0\2\237\17\0\1\31\5\0\12\31\4\0\23\31"+
    "\6\0\2\240\17\0\1\31\5\0\12\31\4\0\23\31"+
    "\27\0\1\31\5\0\12\31\4\0\5\31\1\241\15\31"+
    "\6\0\2\242\17\0\1\31\5\0\12\31\4\0\23\31"+
    "\5\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6200];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\1\2\11\3\1\2\11\2\1\1\11\4\1\2\11"+
    "\12\1\3\11\14\1\1\11\1\1\3\11\1\0\1\11"+
    "\1\0\3\11\1\0\5\11\1\1\1\11\2\0\1\1"+
    "\3\11\2\1\1\0\10\1\1\11\20\1\3\11\1\0"+
    "\1\1\3\11\21\1\1\0\11\1\1\11\4\1\1\11"+
    "\1\1\2\11\2\1\1\0\1\11\3\1\1\11\2\1"+
    "\1\0\3\1\4\11\1\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[162];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public core.Token next() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { return new Token(Type.INTEGER_LITERAL, yytext());
            }
            // fall through
          case 74: break;
          case 2:
            { return new Token(Type.WHITE_SPACE);
            }
            // fall through
          case 75: break;
          case 3:
            { return new Token(Type.NOT);
            }
            // fall through
          case 76: break;
          case 4:
            { return new Token(Type.DOUBLE_QUOTE);
            }
            // fall through
          case 77: break;
          case 5:
            { return new Token(Type.HASH_TAG);
            }
            // fall through
          case 78: break;
          case 6:
            { return new Token(Type.EOF);
            }
            // fall through
          case 79: break;
          case 7:
            { return new Token(Type.MODULUS);
            }
            // fall through
          case 80: break;
          case 8:
            { return new Token(Type.B_AND);
            }
            // fall through
          case 81: break;
          case 9:
            { return new Token(Type.SINGLE_QUOTE);
            }
            // fall through
          case 82: break;
          case 10:
            { return new Token(Type.OPEN_BRACKET);
            }
            // fall through
          case 83: break;
          case 11:
            { return new Token(Type.CLOSE_BRACKET);
            }
            // fall through
          case 84: break;
          case 12:
            { return new Token(Type.MULTIPLY);
            }
            // fall through
          case 85: break;
          case 13:
            { return new Token(Type.ADD);
            }
            // fall through
          case 86: break;
          case 14:
            { return new Token(Type.COMMA);
            }
            // fall through
          case 87: break;
          case 15:
            { return new Token(Type.SUBTRACT);
            }
            // fall through
          case 88: break;
          case 16:
            { return new Token(Type.DOT);
            }
            // fall through
          case 89: break;
          case 17:
            { return new Token(Type.DIVIDE);
            }
            // fall through
          case 90: break;
          case 18:
            { return new Token(Type.COLON);
            }
            // fall through
          case 91: break;
          case 19:
            { return new Token(Type.SEMI_COLON);
            }
            // fall through
          case 92: break;
          case 20:
            { return new Token(Type.LESS_THAN);
            }
            // fall through
          case 93: break;
          case 21:
            { return new Token(Type.EQUAL_TO);
            }
            // fall through
          case 94: break;
          case 22:
            { return new Token(Type.GREATER_THAN);
            }
            // fall through
          case 95: break;
          case 23:
            { return new Token(Type.ID, yytext());
            }
            // fall through
          case 96: break;
          case 24:
            { return new Token(Type.OPEN_SQUARE_BRACKET);
            }
            // fall through
          case 97: break;
          case 25:
            { return new Token(Type.BACKWARD_SLASH);
            }
            // fall through
          case 98: break;
          case 26:
            { return new Token(Type.CLOSE_SQUARE_BRACKET);
            }
            // fall through
          case 99: break;
          case 27:
            { return new Token(Type.B_XOR);
            }
            // fall through
          case 100: break;
          case 28:
            { return new Token(Type.UNDER_SCORE);
            }
            // fall through
          case 101: break;
          case 29:
            { return new Token(Type.OPEN_CURLY_BRACE);
            }
            // fall through
          case 102: break;
          case 30:
            { return new Token(Type.B_OR);
            }
            // fall through
          case 103: break;
          case 31:
            { return new Token(Type.CLOSE_CURLY_BRACE);
            }
            // fall through
          case 104: break;
          case 32:
            { return new Token(Type.B_NOT);
            }
            // fall through
          case 105: break;
          case 33:
            { return new Token(Type.NOT_EQUALS);
            }
            // fall through
          case 106: break;
          case 34:
            { return new Token(Type.TEXT_LITERAL, yytext());
            }
            // fall through
          case 107: break;
          case 35:
            { return new Token(Type.MODULUS_EQUALS);
            }
            // fall through
          case 108: break;
          case 36:
            { return new Token(Type.AND);
            }
            // fall through
          case 109: break;
          case 37:
            { return new Token(Type.B_AND_EQUALS);
            }
            // fall through
          case 110: break;
          case 38:
            { return new Token(Type.PRODUCT_EQUALS);
            }
            // fall through
          case 111: break;
          case 39:
            { return new Token(Type.INCREMENT);
            }
            // fall through
          case 112: break;
          case 40:
            { return new Token(Type.ADD_EQUALS);
            }
            // fall through
          case 113: break;
          case 41:
            { return new Token(Type.DECREMENT);
            }
            // fall through
          case 114: break;
          case 42:
            { return new Token(Type.SUBTRACT_EQUALS);
            }
            // fall through
          case 115: break;
          case 43:
            { return new Token(Type.DOUBLE_LITERAL, yytext());
            }
            // fall through
          case 116: break;
          case 44:
            { return new Token(Type.DIVIDE_EQUALS);
            }
            // fall through
          case 117: break;
          case 45:
            { return new Token(Type.B_LEFT_SHIFT);
            }
            // fall through
          case 118: break;
          case 46:
            { return new Token(Type.LESS_THAN_EQUAL);
            }
            // fall through
          case 119: break;
          case 47:
            { return new Token(Type.EQUALS);
            }
            // fall through
          case 120: break;
          case 48:
            { return new Token(Type.GREATER_THAN_EQUAL);
            }
            // fall through
          case 121: break;
          case 49:
            { return new Token(Type.B_RIGHT_SHIFT);
            }
            // fall through
          case 122: break;
          case 50:
            { return new Token(Type.OR);
            }
            // fall through
          case 123: break;
          case 51:
            { return new Token(Type.B_XOR_EQUALS);
            }
            // fall through
          case 124: break;
          case 52:
            { return new Token(Type.DO);
            }
            // fall through
          case 125: break;
          case 53:
            { return new Token(Type.IF);
            }
            // fall through
          case 126: break;
          case 54:
            { return new Token(Type.B_OR_EQUALS);
            }
            // fall through
          case 127: break;
          case 55:
            { return new Token(Type.CHARACTER_LITERAL, yytext());
            }
            // fall through
          case 128: break;
          case 56:
            { return new Token(Type.B_LEFT_SHIFT_EQUALS);
            }
            // fall through
          case 129: break;
          case 57:
            { return new Token(Type.B_RIGHT_SHIFT_EQUALS);
            }
            // fall through
          case 130: break;
          case 58:
            { return new Token(Type.B_RIGHT_SHIFT_ZERO);
            }
            // fall through
          case 131: break;
          case 59:
            { return new Token(Type.FOR);
            }
            // fall through
          case 132: break;
          case 60:
            { return new Token(Type.ELSE);
            }
            // fall through
          case 133: break;
          case 61:
            { return new Token(Type.INTEGER);
            }
            // fall through
          case 134: break;
          case 62:
            { return new Token(Type.BOOLEAN_LITERAL, yytext());
            }
            // fall through
          case 135: break;
          case 63:
            { return new Token(Type.BOOLEAN);
            }
            // fall through
          case 136: break;
          case 64:
            { return new Token(Type.CASE);
            }
            // fall through
          case 137: break;
          case 65:
            { return new Token(Type.CHARACTER);
            }
            // fall through
          case 138: break;
          case 66:
            { return new Token(Type.GOTO);
            }
            // fall through
          case 139: break;
          case 67:
            { return new Token(Type.WHILE);
            }
            // fall through
          case 140: break;
          case 68:
            { return new Token(Type.BREAK);
            }
            // fall through
          case 141: break;
          case 69:
            { return new Token(Type.DOUBLE);
            }
            // fall through
          case 142: break;
          case 70:
            { return new Token(Type.ELSE_IF);
            }
            // fall through
          case 143: break;
          case 71:
            { return new Token(Type.STRING);
            }
            // fall through
          case 144: break;
          case 72:
            { return new Token(Type.SWITCH);
            }
            // fall through
          case 145: break;
          case 73:
            { return new Token(Type.CONTINUE);
            }
            // fall through
          case 146: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
