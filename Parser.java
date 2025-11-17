//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploGC.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
  import java.util.*;
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short INT=258;
public final static short FLOAT=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short LIT=262;
public final static short VOID=263;
public final static short MAIN=264;
public final static short READ=265;
public final static short WRITE=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short DO=269;
public final static short WHILE=270;
public final static short TRUE=271;
public final static short FALSE=272;
public final static short FOR=273;
public final static short EQ=274;
public final static short LEQ=275;
public final static short GEQ=276;
public final static short NEQ=277;
public final static short AND=278;
public final static short OR=279;
public final static short MAISMAIS=280;
public final static short MENOSMENOS=281;
public final static short MAISIGUAL=282;
public final static short BREAK=283;
public final static short CONTINUE=284;
public final static short STRUCT=285;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    5,    7,    4,    2,    2,    2,   10,    9,
   11,   11,   12,   12,    8,    8,    8,    8,    1,    1,
    1,    6,    6,   13,   13,   13,   15,   13,   13,   16,
   17,   13,   18,   13,   20,   13,   22,   24,   25,   13,
   13,   13,   26,   19,   19,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   21,
   21,   23,   23,
};
final static short yylen[] = {                            2,
    0,    3,    0,    0,    9,    2,    2,    0,    0,    7,
    2,    0,    3,    6,    6,    3,    3,    6,    1,    1,
    1,    2,    0,    2,    3,    5,    0,    8,    5,    0,
    0,    7,    0,    7,    0,    8,    0,    0,    0,   12,
    2,    2,    0,    3,    0,    1,    1,    1,    1,    3,
    4,    3,    2,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    8,    8,    5,
    6,    3,    2,    2,    2,    2,    5,    6,    6,    1,
    0,    1,    0,
};
final static short yydefred[] = {                         1,
    0,    0,    0,   19,   20,   21,    0,    0,    0,    0,
    0,    0,    9,    0,    0,    2,    6,    7,   17,    0,
    0,   16,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    3,   18,    0,    0,   11,   15,    0,
   13,    0,   10,   23,    0,    0,    0,    0,   46,    0,
    0,    0,   35,   30,   47,   48,    0,    0,    0,    0,
    0,    0,    0,   23,    0,   22,    0,   14,   74,   76,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   73,   75,   41,   42,   53,    0,    0,    5,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   24,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   52,   25,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   56,   57,   58,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   37,
    0,    0,    0,    0,    0,   29,   26,    0,    0,    0,
   31,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   43,   34,    0,   32,   38,    0,
    0,   28,    0,   36,    0,   44,    0,    0,   39,    0,
   40,
};
final static short yydgoto[] = {                          1,
    8,    9,    2,   16,   40,   46,   65,   10,   11,   21,
   31,   32,   66,   67,  136,   79,  160,  137,  166,   78,
  114,  152,  178,  175,  180,  173,
};
final static short yysindex[] = {                         0,
    0, -197, -256,    0,    0,    0, -254, -247, -251, -197,
 -197,  -53,    0,  -50, -230,    0,    0,    0,    0, -244,
  -86,    0, -214,   12,  -40, -151,  -37,    7,   -1, -193,
  -54, -151,   14,    0,    0,  -48,   22,    0,    0,  -32,
    0, -165,    0,    0,   17,    9,   53,  -17,    0,   74,
   76,   77,    0,    0,    0,    0,   82, -139, -133,   67,
   68,   26,   26,    0,    4,    0,  131,    0,    0,    0,
   26,   26,   26, -127, -126, -129,   26,    9,  106,   26,
    0,    0,    0,    0,    0,  167,  -33,    0,   26,   26,
   26,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,    0,  444,  444,  298,  -45,  101,  109,  444,
 -115,   26,  444,   98,    0,    0,  111,  111,  111,  111,
  458,  451,  286,  111,  111,    8,    8,    0,    0,    0,
  -22,   26,   26,  100,  104,  122,  126,  132,  322,    0,
   26,   26,  -88,  444,  387,    0,    0,   26,    9,   26,
    0,   26,  444,  444,  116,  121,  411,  -84,  422,    9,
  133,   26,   26,  140,    0,    0,  143,    0,    0,  444,
  444,    0,    9,    0,   26,    0,  444,  162,    0,    9,
    0,
};
final static short yyrindex[] = {                         0,
    0,  -58,    0,    0,    0,    0,    0,    0,    0,  -58,
  -58,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   86,    0,    0,    0,    0,
    0,   86,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   88,    0,   35,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  156,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -39,  -28,    0,   42,    0,  172,  176,
    0,    0,  159,    0,    0,    0,  473,  485,  493,  529,
  -36,  -23,    0,  552,  575,  330,  377,    0,    0,    0,
   78,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -26,    0,    0,    0,    0,    0,    0,
    0,  156,   10,   93,  102,  138,    0,  -12,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  129,
  148,    0,    0,    0,  178,    0,  179,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
  -18,   15,    0,    0,    0,  157,    0,    0,    0,    0,
  191,    0,  -74,  784,    0,    0,    0,    0,    0,    0,
   73,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=959;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         62,
   12,   72,   13,  111,   66,   19,   63,   30,   22,   14,
   41,   15,   67,   30,   70,  132,   25,   65,   72,   72,
   45,   66,   66,  143,   17,   18,   66,   45,   74,   67,
   67,   70,   70,   24,   65,   65,   26,   20,  142,   65,
   23,   62,   42,   72,  102,  133,   27,   34,   63,  100,
   77,   28,   29,   72,  101,   33,   66,   35,   62,    3,
    4,    5,    6,   36,   67,   63,   70,   77,   77,   65,
   37,   49,   39,   73,  158,   49,   49,   49,   50,   49,
   43,   49,   50,   50,   50,  168,   50,    7,   50,   64,
   44,  116,   49,   49,   49,   45,   49,   49,  176,   50,
   50,   50,   77,   50,   50,  181,    4,    5,    6,   47,
   45,   68,   45,   75,   51,   76,   77,   81,   51,   51,
   51,   80,   51,   82,   51,   83,   84,   49,   88,  107,
  108,   64,  109,   71,   50,   51,   51,   51,   79,   51,
   51,  134,   79,   79,   79,  112,   79,  102,   79,  135,
   71,   71,  100,   98,  138,   99,  140,  101,  146,   79,
   79,   79,  147,   79,   79,  148,  149,  102,  155,   69,
   51,  150,  100,   98,   78,   99,  162,  101,   78,   78,
   78,  163,   78,  165,   78,   71,   69,   69,   68,  103,
   97,  169,   96,   95,   79,   78,   78,   78,  172,   78,
   78,  174,  179,  102,    8,   68,   68,  115,  100,   98,
   12,   99,    4,  101,   81,   27,   33,   80,   83,   82,
   87,   69,   38,   48,  161,    0,   97,   49,   96,   95,
   78,   50,   51,   52,    0,   53,   54,   55,   56,   57,
   68,   66,   66,    0,   45,    0,   58,   59,   45,   60,
   61,    0,   45,   45,   45,   65,   45,   45,   45,   45,
   45,    0,   69,   70,   71,   48,    0,   45,   45,   49,
   45,   45,    0,   50,   51,   52,    0,   53,   54,   55,
   56,   57,   48,    0,    0,    0,   49,    0,   58,   59,
    0,   60,   61,    0,    0,    0,   55,   56,    0,    0,
    0,    0,    0,    0,    0,   58,   59,    0,   49,   49,
   49,   49,   49,   49,    0,   50,   50,   50,   50,   50,
   50,    0,  102,    0,    0,    0,    0,  100,   98,    0,
   99,    0,  101,    0,  102,    0,    0,    0,    0,  100,
   98,    0,   99,  141,  101,   97,    0,   96,   95,    0,
    0,   51,   51,   51,   51,   51,   51,   97,  102,   96,
   95,    0,  151,  100,   98,    0,   99,    0,  101,    0,
   54,    0,   54,    0,   54,   79,   79,   79,   79,   79,
   79,   97,    0,   96,   95,    0,    0,   54,   54,   54,
  131,   54,   54,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   89,   90,   91,   92,   93,   94,
    0,   78,   78,   78,   78,   78,   78,   55,    0,   55,
    0,   55,   54,  102,    0,    0,    0,    0,  100,   98,
    0,   99,    0,  101,   55,   55,   55,    0,   55,   55,
   89,   90,   91,   92,   93,   94,   97,  102,   96,   95,
    0,  164,  100,   98,    0,   99,    0,  101,  102,    0,
    0,    0,  167,  100,   98,    0,   99,    0,  101,   55,
   97,    0,   96,   95,    0,    0,    0,    0,    0,  156,
  102,   97,    0,   96,   95,  100,   98,  102,   99,    0,
  101,    0,  100,   98,  102,   99,    0,  101,    0,  100,
   98,    0,   99,   97,  101,   96,   95,    0,    0,    0,
   97,    0,   96,   61,    0,    0,    0,   97,    0,   96,
    0,    0,    0,    0,    0,   62,    0,    0,    0,    0,
   61,   61,   61,   63,   61,   61,    0,    0,    0,    0,
    0,    0,   62,   62,   62,    0,   62,   62,    0,    0,
   63,   63,   63,    0,   63,   63,    0,    0,    0,   89,
   90,   91,   92,   93,   94,   61,    0,    0,    0,   64,
    0,   89,   90,   91,   92,   93,   94,   62,    0,    0,
    0,    0,    0,    0,    0,   63,   64,   64,   64,    0,
   64,   64,   59,    0,    0,   89,   90,   91,   92,   93,
   94,    0,    0,   54,   54,   54,   54,   54,   54,   59,
   59,   59,    0,   59,   59,   60,    0,    0,    0,    0,
    0,   64,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   60,   60,   60,    0,   60,   60,    0,    0,
    0,    0,    0,    0,   59,    0,    0,    0,    0,    0,
   55,   55,   55,   55,   55,   55,    0,    0,    0,    0,
   89,   90,   91,   92,   93,   94,    0,   60,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   89,   90,   91,   92,   93,   94,
    0,    0,    0,    0,    0,   89,   90,   91,   92,   93,
   94,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   89,   90,   91,
   92,   93,   94,    0,   89,   90,   91,   92,   93,    0,
    0,   89,   90,   91,   92,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   61,   61,   61,   61,
   61,   61,    0,    0,    0,    0,    0,    0,   62,   62,
   62,   62,   62,   62,    0,    0,   63,   63,   63,   63,
   63,   63,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   64,   64,   64,   64,   64,   64,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   59,   59,   59,   59,   59,
   59,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   85,   86,    0,   60,   60,
   60,   60,   60,   60,  104,  105,  106,    0,    0,    0,
  110,    0,    0,  113,    0,    0,    0,    0,    0,    0,
    0,    0,  117,  118,  119,  120,  121,  122,  123,  124,
  125,  126,  127,  128,  129,  130,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  139,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  144,  145,    0,    0,    0,
    0,    0,    0,    0,  153,  154,    0,    0,    0,    0,
    0,  157,    0,  159,    0,  113,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  170,  171,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  177,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  257,   41,  257,   78,   41,   59,   40,   26,   59,  257,
   59,  263,   41,   32,   41,   61,  261,   41,   58,   59,
   33,   58,   59,   46,   10,   11,   63,   40,   46,   58,
   59,   58,   59,  264,   58,   59,  123,   91,   61,   63,
   91,   33,   91,   61,   37,   91,  261,   41,   40,   42,
   41,   40,   93,   93,   47,   93,   93,   59,   33,  257,
  258,  259,  260,  257,   93,   40,   93,   58,   59,   93,
  125,   37,   59,   91,  149,   41,   42,   43,   37,   45,
   59,   47,   41,   42,   43,  160,   45,  285,   47,  123,
  123,  125,   58,   59,   60,  261,   62,   63,  173,   58,
   59,   60,   93,   62,   63,  180,  258,  259,  260,   93,
  123,   59,  125,   40,   37,   40,   40,  257,   41,   42,
   43,   40,   45,  257,   47,   59,   59,   93,  125,  257,
  257,  123,  262,   41,   93,   58,   59,   60,   37,   62,
   63,   41,   41,   42,   43,   40,   45,   37,   47,   41,
   58,   59,   42,   43,  270,   45,   59,   47,   59,   58,
   59,   60,   59,   62,   63,   44,   41,   37,  257,   41,
   93,   40,   42,   43,   37,   45,   61,   47,   41,   42,
   43,   61,   45,  268,   47,   93,   58,   59,   41,   59,
   60,   59,   62,   63,   93,   58,   59,   60,   59,   62,
   63,   59,   41,   37,  263,   58,   59,   41,   42,   43,
  125,   45,  125,   47,   59,   44,   41,   59,   41,   41,
   64,   93,   32,  257,  152,   -1,   60,  261,   62,   63,
   93,  265,  266,  267,   -1,  269,  270,  271,  272,  273,
   93,  278,  279,   -1,  257,   -1,  280,  281,  261,  283,
  284,   -1,  265,  266,  267,  279,  269,  270,  271,  272,
  273,   -1,  280,  281,  282,  257,   -1,  280,  281,  261,
  283,  284,   -1,  265,  266,  267,   -1,  269,  270,  271,
  272,  273,  257,   -1,   -1,   -1,  261,   -1,  280,  281,
   -1,  283,  284,   -1,   -1,   -1,  271,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  280,  281,   -1,  274,  275,
  276,  277,  278,  279,   -1,  274,  275,  276,  277,  278,
  279,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   -1,   47,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   58,   47,   60,   -1,   62,   63,   -1,
   -1,  274,  275,  276,  277,  278,  279,   60,   37,   62,
   63,   -1,   41,   42,   43,   -1,   45,   -1,   47,   -1,
   41,   -1,   43,   -1,   45,  274,  275,  276,  277,  278,
  279,   60,   -1,   62,   63,   -1,   -1,   58,   59,   60,
   93,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,  274,  275,  276,  277,  278,  279,   41,   -1,   43,
   -1,   45,   93,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   -1,   47,   58,   59,   60,   -1,   62,   63,
  274,  275,  276,  277,  278,  279,   60,   37,   62,   63,
   -1,   41,   42,   43,   -1,   45,   -1,   47,   37,   -1,
   -1,   -1,   41,   42,   43,   -1,   45,   -1,   47,   93,
   60,   -1,   62,   63,   -1,   -1,   -1,   -1,   -1,   93,
   37,   60,   -1,   62,   63,   42,   43,   37,   45,   -1,
   47,   -1,   42,   43,   37,   45,   -1,   47,   -1,   42,
   43,   -1,   45,   60,   47,   62,   63,   -1,   -1,   -1,
   60,   -1,   62,   41,   -1,   -1,   -1,   60,   -1,   62,
   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   -1,   -1,
   58,   59,   60,   41,   62,   63,   -1,   -1,   -1,   -1,
   -1,   -1,   58,   59,   60,   -1,   62,   63,   -1,   -1,
   58,   59,   60,   -1,   62,   63,   -1,   -1,   -1,  274,
  275,  276,  277,  278,  279,   93,   -1,   -1,   -1,   41,
   -1,  274,  275,  276,  277,  278,  279,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   58,   59,   60,   -1,
   62,   63,   41,   -1,   -1,  274,  275,  276,  277,  278,
  279,   -1,   -1,  274,  275,  276,  277,  278,  279,   58,
   59,   60,   -1,   62,   63,   41,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   58,   59,   60,   -1,   62,   63,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,   -1,  274,  275,  276,  277,  278,   -1,
   -1,  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,   -1,   -1,  274,  275,  276,  277,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  274,  275,  276,  277,  278,  279,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   62,   63,   -1,  274,  275,
  276,  277,  278,  279,   71,   72,   73,   -1,   -1,   -1,
   77,   -1,   -1,   80,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   89,   90,   91,   92,   93,   94,   95,   96,
   97,   98,   99,  100,  101,  102,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  112,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  132,  133,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  141,  142,   -1,   -1,   -1,   -1,
   -1,  148,   -1,  150,   -1,  152,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  162,  163,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  175,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=285;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM",
"LIT","VOID","MAIN","READ","WRITE","IF","ELSE","DO","WHILE","TRUE","FALSE",
"FOR","EQ","LEQ","GEQ","NEQ","AND","OR","MAISMAIS","MENOSMENOS","MAISIGUAL",
"BREAK","CONTINUE","STRUCT",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList : declStruct dList",
"dList :",
"$$4 :",
"declStruct : STRUCT ID $$4 '{' listaCampos '}' ';'",
"listaCampos : campo listaCampos",
"listaCampos :",
"campo : type ID ';'",
"campo : type ID '[' NUM ']' ';'",
"decl : type ID '[' NUM ']' ';'",
"decl : type ID ';'",
"decl : ID ID ';'",
"decl : ID ID '[' NUM ']' ';'",
"type : INT",
"type : FLOAT",
"type : BOOL",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : exp ';'",
"cmd : '{' lcmd '}'",
"cmd : WRITE '(' LIT ')' ';'",
"$$5 :",
"cmd : WRITE '(' LIT $$5 ',' exp ')' ';'",
"cmd : READ '(' ID ')' ';'",
"$$6 :",
"$$7 :",
"cmd : WHILE $$6 '(' exp ')' $$7 cmd",
"$$8 :",
"cmd : IF '(' exp $$8 ')' cmd restoIf",
"$$9 :",
"cmd : DO $$9 cmd WHILE '(' exp ')' ';'",
"$$10 :",
"$$11 :",
"$$12 :",
"cmd : FOR '(' forVazio ';' $$10 forVazio ';' $$11 forUpdate ')' $$12 cmd",
"cmd : BREAK ';'",
"cmd : CONTINUE ';'",
"$$13 :",
"restoIf : ELSE $$13 cmd",
"restoIf :",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : ID '.' ID",
"exp : ID '[' exp ']'",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '%' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp EQ exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp NEQ exp",
"exp : exp OR exp",
"exp : exp AND exp",
"exp : ID '=' exp",
"exp : ID '.' ID '[' exp ']' '=' exp",
"exp : ID '[' exp ']' '.' ID '=' exp",
"exp : ID '.' ID '=' exp",
"exp : ID '[' exp ']' '=' exp",
"exp : ID MAISIGUAL exp",
"exp : MAISMAIS ID",
"exp : ID MAISMAIS",
"exp : MENOSMENOS ID",
"exp : ID MENOSMENOS",
"exp : exp '?' exp ':' exp",
"exp : ID '.' ID '[' exp ']'",
"exp : ID '[' exp ']' '.' ID",
"forVazio : exp",
"forVazio :",
"forUpdate : exp",
"forUpdate :",
};

//#line 442 "exemploGC.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRotRep = new Stack<Integer>();
  private Stack<Integer> pRotSel = new Stack<Integer>();
  private int proxRot = 1;

  Map<String, List<TS_entry>> structModels = new HashMap<>();
  List<TS_entry> camposStructAtual;
  String nomeStructAtual;


  public static int ARRAY = 100;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
		void gcExpArit(int oparit) {
 				System.out.println("\tPOPL %EBX");
   			System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EBX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EBX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EBX, %EAX" ); break;

    		case '/': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
   		System.out.println("\tPUSHL %EAX");
		}

	public void gcExpRel(int oprel) {

    System.out.println("\tPOPL %EAX");
    System.out.println("\tPOPL %EDX");
    System.out.println("\tCMPL %EAX, %EDX");
    System.out.println("\tMOVL $0, %EAX");
    
    switch (oprel) {
       case '<':  			System.out.println("\tSETL  %AL"); break;
       case '>':  			System.out.println("\tSETG  %AL"); break;
       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
       }
    
    System.out.println("\tPUSHL %EAX");

	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
       }

    	System.out.println("\tPUSHL %EAX");
	}

	public void gcExpNot(){

  	 System.out.println("\tPOPL %EAX" );
 	   System.out.println("	\tNEGL %EAX" );
  	 System.out.println("	\tPUSHL %EAX");
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t nome COMPLETO e matricula dos componentes do grupo...\n#\n"); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	       System.out.println("\t.zero 30");
	       System.out.println("__fim_msg:");
	       System.out.println("\t.byte 0");
	       System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	           System.out.println("_str_"+i+"Len = . - _str_"+i);  
	      }		
   }
   
//#line 788 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 38 "exemploGC.y"
{ geraInicio(); }
break;
case 2:
//#line 38 "exemploGC.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 3:
//#line 40 "exemploGC.y"
{ System.out.println("_start:"); }
break;
case 4:
//#line 41 "exemploGC.y"
{ geraFinal(); }
break;
case 9:
//#line 51 "exemploGC.y"
{
        nomeStructAtual = val_peek(0).sval;
        camposStructAtual = new ArrayList<>();
      }
break;
case 10:
//#line 56 "exemploGC.y"
{
        structModels.put(nomeStructAtual, camposStructAtual);
      }
break;
case 13:
//#line 68 "exemploGC.y"
{
        camposStructAtual.add(new TS_entry(val_peek(1).sval, val_peek(2).ival));
      }
break;
case 14:
//#line 72 "exemploGC.y"
{
				int tam = Integer.parseInt(val_peek(2).sval);
				camposStructAtual.add(new TS_entry(val_peek(4).sval, Parser.ARRAY, tam, val_peek(5).ival));
			}
break;
case 15:
//#line 78 "exemploGC.y"
{
									TS_entry nodo = ts.pesquisa(val_peek(4).sval);
									if (nodo != null)
										yyerror("variável >" + val_peek(4).sval + "< jah declarada");
									else
										ts.insert(new TS_entry(val_peek(4).sval, val_peek(5).ival, Integer.parseInt(val_peek(2).sval), val_peek(5).ival)); 
								}
break;
case 16:
//#line 86 "exemploGC.y"
{  
									TS_entry nodo = ts.pesquisa(val_peek(1).sval);
									if (nodo != null) 
										yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
									else ts.insert(new TS_entry(val_peek(1).sval, val_peek(2).ival)); 
								}
break;
case 17:
//#line 93 "exemploGC.y"
{
									String tipo = val_peek(2).sval;
									String var = val_peek(1).sval;

									if (!structModels.containsKey(tipo)) {
										yyerror("tipo struct '"+tipo+"' nao declarado");
									} else {
										for (TS_entry campo : structModels.get(tipo)) {
											String nomeReal = var + "_" + campo.getId();
											int tipoBase = (campo.getTipo() == Parser.ARRAY) ? campo.getTipoBase() : campo.getTipo();
											ts.insert(new TS_entry(nomeReal, campo.getTipo(), campo.getNumElem(), tipoBase));
										}
									}
								}
break;
case 18:
//#line 108 "exemploGC.y"
{
									String tipo = val_peek(5).sval;
									String var = val_peek(4).sval;
									int tam = Integer.parseInt(val_peek(2).sval);

									if (!structModels.containsKey(tipo)) {
										yyerror("tipo struct '"+tipo+"' nao declarado");
									} else {
										for (TS_entry campo : structModels.get(tipo)) {
											String nomeReal = var + "_" + campo.getId();
											int tamCampo = (campo.getNumElem() <= 0) ? 1 : campo.getNumElem();
											int tamTotal = tamCampo * tam;
											int tamTotalCorrigido = tamCampo * tam; 
											int tipoBase = (campo.getTipo() == Parser.ARRAY) ? campo.getTipoBase() : campo.getTipo();
											ts.insert(new TS_entry(nomeReal, Parser.ARRAY, tamTotalCorrigido, tipoBase));
										}
									}
								}
break;
case 19:
//#line 128 "exemploGC.y"
{ yyval.ival = INT; }
break;
case 20:
//#line 129 "exemploGC.y"
{ yyval.ival = FLOAT; }
break;
case 21:
//#line 130 "exemploGC.y"
{ yyval.ival = BOOL; }
break;
case 24:
//#line 137 "exemploGC.y"
{ System.out.println("\tPOPL %EDX"); }
break;
case 25:
//#line 138 "exemploGC.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 26:
//#line 141 "exemploGC.y"
{ strTab.add(val_peek(2).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
break;
case 27:
//#line 150 "exemploGC.y"
{ strTab.add(val_peek(0).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}
break;
case 28:
//#line 158 "exemploGC.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 29:
//#line 165 "exemploGC.y"
{
									System.out.println("\tPUSHL $_"+val_peek(2).sval);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
break;
case 30:
//#line 173 "exemploGC.y"
{
					pRotRep.push(proxRot);  proxRot += 2;
					System.out.printf("rot_%02d:\n",pRotRep.peek());
				  }
break;
case 31:
//#line 177 "exemploGC.y"
{
			 							System.out.println("\tPOPL %EAX   # desvia se falso...");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
										}
break;
case 32:
//#line 182 "exemploGC.y"
{
				  		System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());
							System.out.printf("rot_%02d:\n",(int)pRotRep.peek()+1);
							pRotRep.pop();
							}
break;
case 33:
//#line 188 "exemploGC.y"
{	
											pRotSel.push(proxRot);  proxRot += 2;
															
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRotSel.peek());
										}
break;
case 34:
//#line 197 "exemploGC.y"
{
											System.out.printf("rot_%02d:\n",pRotSel.peek()+1);
											pRotSel.pop();
										}
break;
case 35:
//#line 201 "exemploGC.y"
{
          pRotRep.push(proxRot); 
          proxRot += 2;
          System.out.printf("rot_%02d:\n", pRotRep.peek());
		  }
break;
case 36:
//#line 207 "exemploGC.y"
{
			System.out.println("\tPOPL %EAX");
			System.out.println("\tCMPL $1, %EAX");
			System.out.printf("\tJE rot_%02d\n", pRotRep.peek());
			System.out.printf("rot_%02d:\n", pRotRep.peek()+1);
			pRotRep.pop();
		  }
break;
case 37:
//#line 215 "exemploGC.y"
{
				          pRotRep.push(proxRot);
						  proxRot += 4;
						  System.out.printf("rot_%02d:\n", pRotRep.peek()+3);  
			              }
break;
case 38:
//#line 220 "exemploGC.y"
{
				          System.out.println("\tCMPL $0, %EAX");
						  System.out.printf("\tJE rot_%02d\n", pRotRep.peek()+1);  
						  System.out.printf("\tJNE rot_%02d\n", pRotRep.peek()+2);
						  System.out.printf("rot_%02d:\n", pRotRep.peek());
			              }
break;
case 39:
//#line 226 "exemploGC.y"
{
						  System.out.printf("\tJMP rot_%02d\n", pRotRep.peek()+3);
						  System.out.printf("rot_%02d:\n", pRotRep.peek()+2);
			              }
break;
case 40:
//#line 231 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRotRep.peek());
				System.out.printf("rot_%02d:\n", pRotRep.peek()+1);
				pRotRep.pop();
			}
break;
case 41:
//#line 237 "exemploGC.y"
{ System.out.printf("\tJMP rot_%02d\n", pRotRep.peek()+1); }
break;
case 42:
//#line 238 "exemploGC.y"
{ System.out.printf("\tJMP rot_%02d\n", pRotRep.peek()); }
break;
case 43:
//#line 242 "exemploGC.y"
{
											System.out.printf("\tJMP rot_%02d\n", pRotSel.peek()+1);
											System.out.printf("rot_%02d:\n",pRotSel.peek());
								
										}
break;
case 45:
//#line 250 "exemploGC.y"
{
		    System.out.printf("\tJMP rot_%02d\n", pRotSel.peek()+1);
				System.out.printf("rot_%02d:\n",pRotSel.peek());
				}
break;
case 46:
//#line 257 "exemploGC.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 47:
//#line 258 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 48:
//#line 259 "exemploGC.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 49:
//#line 260 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval); }
break;
case 50:
//#line 262 "exemploGC.y"
{
					String nomeReal = val_peek(2).sval + "_" + val_peek(0).sval;

					TS_entry n = ts.pesquisa(nomeReal);
					if (n == null)
						yyerror("campo "+val_peek(0).sval+" inexistente em struct "+val_peek(2).sval);

					System.out.println("\tPUSHL _"+nomeReal);
				}
break;
case 51:
//#line 272 "exemploGC.y"
{
					TS_entry t = ts.pesquisa(val_peek(3).sval);
					if (t == null || t.getNumElem() <= 0)
						yyerror("variável " + val_peek(3).sval + " não é array");
					System.out.println("\tPOPL %EAX");      
					System.out.println("\tIMULL $4, %EAX"); 
					System.out.println("\tPUSHL _"+val_peek(3).sval+"(,%EAX)"); 
				}
break;
case 53:
//#line 281 "exemploGC.y"
{ gcExpNot(); }
break;
case 54:
//#line 283 "exemploGC.y"
{ gcExpArit('+'); }
break;
case 55:
//#line 284 "exemploGC.y"
{ gcExpArit('-'); }
break;
case 56:
//#line 285 "exemploGC.y"
{ gcExpArit('*'); }
break;
case 57:
//#line 286 "exemploGC.y"
{ gcExpArit('/'); }
break;
case 58:
//#line 287 "exemploGC.y"
{ gcExpArit('%'); }
break;
case 59:
//#line 289 "exemploGC.y"
{ gcExpRel('>'); }
break;
case 60:
//#line 290 "exemploGC.y"
{ gcExpRel('<'); }
break;
case 61:
//#line 291 "exemploGC.y"
{ gcExpRel(EQ); }
break;
case 62:
//#line 292 "exemploGC.y"
{ gcExpRel(LEQ); }
break;
case 63:
//#line 293 "exemploGC.y"
{ gcExpRel(GEQ); }
break;
case 64:
//#line 294 "exemploGC.y"
{ gcExpRel(NEQ); }
break;
case 65:
//#line 296 "exemploGC.y"
{ gcExpLog(OR); }
break;
case 66:
//#line 297 "exemploGC.y"
{ gcExpLog(AND); }
break;
case 67:
//#line 302 "exemploGC.y"
{  System.out.println("\tPOPL %EDX");
  						   System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
						   System.out.println("\tPUSHL %EDX");
					     }
break;
case 68:
//#line 307 "exemploGC.y"
{
							String nomeReal = val_peek(7).sval + "_" + val_peek(5).sval;
							System.out.println("\tPOPL %EDX"); 
							System.out.println("\tPOPL %EAX"); 
							System.out.println("\tIMULL $4, %EAX");
							System.out.println("\tMOVL %EDX, _" + nomeReal + "(,%EAX)");
							System.out.println("\tPUSHL %EDX"); 
						}
break;
case 69:
//#line 317 "exemploGC.y"
{
							String nomeReal = val_peek(7).sval + "_" + val_peek(2).sval;
							System.out.println("\tPOPL %EDX"); 
							System.out.println("\tPOPL %EAX"); 
							System.out.println("\tIMULL $4, %EAX");
							System.out.println("\tMOVL %EDX, _" + nomeReal + "(,%EAX)");
							System.out.println("\tPUSHL %EDX");  
						}
break;
case 70:
//#line 326 "exemploGC.y"
{
								String nomeReal = val_peek(4).sval + "_" + val_peek(2).sval;

								TS_entry n = ts.pesquisa(nomeReal);
								if (n == null)
									yyerror("campo "+val_peek(2).sval+" inexistente em struct "+val_peek(4).sval);

								System.out.println("\tPOPL %EDX");
								System.out.println("\tMOVL %EDX, _"+nomeReal);
								System.out.println("\tPUSHL %EDX");
							}
break;
case 71:
//#line 338 "exemploGC.y"
{
								TS_entry t = ts.pesquisa(val_peek(5).sval);
								if (t == null || t.getNumElem() <= 0)
									yyerror("variável " + val_peek(5).sval + " não é array");

								System.out.println("\tPOPL %EDX"); 
								System.out.println("\tPOPL %EAX"); 
								System.out.println("\tIMULL $4, %EAX");
								System.out.println("\tMOVL %EDX, _"+val_peek(5).sval+"(,%EAX)");
								System.out.println("\tPUSHL %EDX");  
							}
break;
case 72:
//#line 349 "exemploGC.y"
{ System.out.println("\tPOPL %EDX");
					   System.out.println("\tPUSHL _"+val_peek(2).sval);
					   System.out.println("\tPUSHL %EDX");
					   gcExpArit('+');
					   System.out.println("\tPOPL %EDX");
					   System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
					   System.out.println("\tPUSHL _"+val_peek(2).sval);
				 }
break;
case 73:
//#line 358 "exemploGC.y"
{System.out.println("\tPUSHL _"+val_peek(0).sval);
		System.out.println("\tPUSHL $1");
		gcExpArit('+');
		System.out.println("\tPOPL %EDX");
		System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);
		System.out.println("\tPUSHL _"+val_peek(0).sval);
		}
break;
case 74:
//#line 365 "exemploGC.y"
{
			System.out.println("\tPUSHL _"+val_peek(1).sval);       
			System.out.println("\tPUSHL _"+val_peek(1).sval);        
			System.out.println("\tPUSHL $1");           
			gcExpArit('+');                          
			System.out.println("\tPOPL %EDX");          
			System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);    
			System.out.println("\tPOPL %EDX");        
			System.out.println("\tPUSHL %EDX");                
		}
break;
case 75:
//#line 375 "exemploGC.y"
{System.out.println("\tPUSHL _"+val_peek(0).sval);
		System.out.println("\tPUSHL $1");
		gcExpArit('-');
		System.out.println("\tPOPL %EDX");
		System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);
		System.out.println("\tPUSHL _"+val_peek(0).sval);
		}
break;
case 76:
//#line 382 "exemploGC.y"
{
			System.out.println("\tPUSHL _"+val_peek(1).sval);       
			System.out.println("\tPUSHL _"+val_peek(1).sval);        
			System.out.println("\tPUSHL $1");         
			gcExpArit('-');                         
			System.out.println("\tPOPL %EDX");        
			System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);  
			System.out.println("\tPOPL %EDX");        
			System.out.println("\tPUSHL %EDX");        
		}
break;
case 77:
//#line 392 "exemploGC.y"
{
			int r = proxRot; proxRot += 2;
			System.out.println("\tPOPL %EAX   # exp3 (falso)");
			System.out.println("\tPOPL %EBX   # exp2 (verdadeiro)");
			System.out.println("\tPOPL %EDX   # cond");
			System.out.println("\tCMPL $0, %EDX");
			System.out.printf("\tJE  rot_%02d\n", r); 
			System.out.println("\tPUSHL %EBX");        
			System.out.printf("\tJMP rot_%02d\n", r+1);
			System.out.printf("rot_%02d:\n", r);
			System.out.println("\tPUSHL %EAX");        
			System.out.printf("rot_%02d:\n", r+1);
		}
break;
case 78:
//#line 406 "exemploGC.y"
{
					String nomeReal = val_peek(5).sval + "_" + val_peek(3).sval;
					TS_entry t = ts.pesquisa(nomeReal);
					if (t == null) yyerror("campo " + val_peek(3).sval + " inexistente em " + val_peek(5).sval);

					System.out.println("\tPOPL %EAX");       
					System.out.println("\tIMULL $4, %EAX");  
					System.out.println("\tPUSHL _" + nomeReal + "(,%EAX)"); 
				}
break;
case 79:
//#line 417 "exemploGC.y"
{
					String nomeReal = val_peek(5).sval + "_" + val_peek(0).sval; 
					TS_entry t = ts.pesquisa(nomeReal);
					if (t == null) yyerror("campo " + val_peek(0).sval + " inexistente na struct " + val_peek(5).sval);
					
					System.out.println("\tPOPL %EAX");       
					System.out.println("\tIMULL $4, %EAX"); 
					System.out.println("\tPUSHL _" + nomeReal + "(,%EAX)"); 
				}
break;
case 80:
//#line 429 "exemploGC.y"
{ 
            System.out.println("\tPOPL %EDX"); 
          }
break;
//#line 1461 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
