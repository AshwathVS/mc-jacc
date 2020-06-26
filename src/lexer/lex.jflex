package lexer;

import core.Type;
import core.Token;

%%
%class Lexer
%line
%column
%type core.Token
%function next
%state COMMENT
%public

int_number=[0-9]*((e|E)[-+]?[0-9]+)?
double_number=[0-9]*(\.[0-9]+)+((e|E)[-+]?[0-9]+)?
text=\"([^\"\\]|\\.)*\"
character=\'.\'
boolean=true|false
id=[a-zA-Z_]([a-zA-Z_]|[0-9])*
%%

/** Data types: */
int\s {
    return new Token(Type.INTEGER, yyline, yycolumn);
}
double\s {
    return new Token(Type.DOUBLE, yyline, yycolumn);
}
char\s {
    return new Token(Type.CHARACTER, yyline, yycolumn);
}
bool\s {
    return new Token(Type.BOOLEAN, yyline, yycolumn);
}
void\s {
    return new Token(Type.VOID, yyline, yycolumn);
}

/** Arithmetic Operators: */
\+
{
    return new Token(Type.ADD, yyline, yycolumn);
}
\-
{
    return new Token(Type.SUBTRACT, yyline, yycolumn);
}
\*
{
    return new Token(Type.MULTIPLY, yyline, yycolumn);
}
\/
{
    return new Token(Type.DIVIDE, yyline, yycolumn);
}
\%
{
    return new Token(Type.MODULUS, yyline, yycolumn);
}
\+\+
{
    return new Token(Type.INCREMENT, yyline, yycolumn);
}
\-\-
{
    return new Token(Type.DECREMENT, yyline, yycolumn);
}


/** Bit Wise Operators: */
\&
{
    return new Token(Type.B_AND, yyline, yycolumn);
}
\|
{
    return new Token(Type.B_OR, yyline, yycolumn);
}
\~
{
    return new Token(Type.B_NOT, yyline, yycolumn);
}
\^
{
    return new Token(Type.B_XOR, yyline, yycolumn);
}
\<\<
{
    return new Token(Type.B_LEFT_SHIFT, yyline, yycolumn);
}
\>\>
{
    return new Token(Type.B_RIGHT_SHIFT, yyline, yycolumn);
}
\>\>\>
{
    return new Token(Type.B_RIGHT_SHIFT_ZERO, yyline, yycolumn);
}
*/

/** Relational Operators: */
\=\=|EE
{
  return new Token(Type.EQUALS, yyline, yycolumn);
}
\!\=|NE
{
    return new Token(Type.NOT_EQUALS, yyline, yycolumn);
}
\>|GT
{
    return new Token(Type.GREATER_THAN, yyline, yycolumn);
}
\<|LT
{
    return new Token(Type.LESS_THAN, yyline, yycolumn);
}
\>=|GE
{
    return new Token(Type.GREATER_THAN_EQUAL, yyline, yycolumn);
}
\<=|LE
{
    return new Token(Type.LESS_THAN_EQUAL, yyline, yycolumn);
}



/** Logical Operators: */
(\&\&|AND)
{
    return new Token(Type.AND, yyline, yycolumn);
}
(\|\||OR)
{
    return new Token(Type.OR, yyline, yycolumn);
}
(\!|NOT)
{
    return new Token(Type.NOT, yyline, yycolumn);
}


/** Assignment Operators */
\=
{
    return new Token(Type.EQUAL_TO, yyline, yycolumn);
}
\+\=
{
    return new Token(Type.ADD_EQUALS, yyline, yycolumn);
}
\-\=
{
    return new Token(Type.SUBTRACT_EQUALS, yyline, yycolumn);
}
\*\=
{
    return new Token(Type.PRODUCT_EQUALS, yyline, yycolumn);
}
\/\=
{
    return new Token(Type.DIVIDE_EQUALS, yyline, yycolumn);
}
\%\=
{
    return new Token(Type.MODULUS_EQUALS, yyline, yycolumn);
}
\<\<\=
{
    return new Token(Type.B_LEFT_SHIFT_EQUALS, yyline, yycolumn);
}
\>\>\=
{
    return new Token(Type.B_RIGHT_SHIFT_EQUALS, yyline, yycolumn);
}
\&\=
{
    return new Token(Type.B_AND_EQUALS, yyline, yycolumn);
}
\^\=
{
    return new Token(Type.B_XOR_EQUALS, yyline, yycolumn);
}
\|\=
{
    return new Token(Type.B_OR_EQUALS, yyline, yycolumn);
}


/** Decision making Operators: */
IF
{
    return new Token(Type.IF, yyline, yycolumn);
}
ELSE
{
    return new Token(Type.ELSE, yyline, yycolumn);
}
ELSE_IF
{
    return new Token(Type.ELSE_IF, yyline, yycolumn);
}
CONTINUE\s
{
    return new Token(Type.CONTINUE, yyline, yycolumn);
}
BREAK\s
{
    return new Token(Type.BREAK, yyline, yycolumn);
}



/** Loop Operators:*/
FOR
{
    return new Token(Type.FOR, yyline, yycolumn);
}
WHILE
{
    return new Token(Type.WHILE, yyline, yycolumn);
}
DO_WHILE
{
    return new Token(Type.DO_WHILE, yyline, yycolumn);
}

/** Additional Operators: */
\$\.
{
    return new Token(Type.FUNCTION_CALL_PREFIX, yyline, yycolumn);
}
\@\.
{
    return new Token(Type.INTERNAL_CALL_PREFIX, yyline, yycolumn);
}
\@
{
    return new Token(Type.AT, yyline, yycolumn);
}
\$ {
    return new Token(Type.DOLLAR, yyline, yycolumn);
}
return\s {
    return new Token(Type.RETURN, yyline, yycolumn);
}
\:
{
    return new Token(Type.COLON, yyline, yycolumn);
}
\;
{
    return new Token(Type.SEMI_COLON, yyline, yycolumn);
}
\(
{
    return new Token(Type.OPEN_BRACKET, yyline, yycolumn);
}
\)
{
    return new Token(Type.CLOSE_BRACKET, yyline, yycolumn);
}
\[
{
    return new Token(Type.OPEN_SQUARE_BRACKET, yyline, yycolumn);
}
\]
{
    return new Token(Type.CLOSE_SQUARE_BRACKET, yyline, yycolumn);
}
\{
{
    return new Token(Type.OPEN_CURLY_BRACE, yyline, yycolumn);
}
\}
{
    return new Token(Type.CLOSE_CURLY_BRACE, yyline, yycolumn);
}
\'
{
    return new Token(Type.SINGLE_QUOTE, yyline, yycolumn);
}
\"
{
    return new Token(Type.DOUBLE_QUOTE, yyline, yycolumn);
}
\/
{
    return new Token(Type.FORWARD_SLASH, yyline, yycolumn);
}
\\
{
    return new Token(Type.BACKWARD_SLASH, yyline, yycolumn);
}
\,
{
    return new Token(Type.COMMA, yyline, yycolumn);
}
\.
{
    return new Token(Type.DOT, yyline, yycolumn);
}
\_
{
    return new Token(Type.UNDER_SCORE, yyline, yycolumn);
}
\-
{
    return new Token(Type.HYPHEN, yyline, yycolumn);
}
\#
{
    return new Token(Type.HASH_TAG, yyline, yycolumn);
}
[\t\r\n\f\s]+
{
    return new Token(Type.WHITE_SPACE, yyline, yycolumn);
}


/*Left value and right value*/

{boolean} {
    return new Token(Type.BOOLEAN_LITERAL, yyline, yycolumn, yytext());
}

{id} {
    return new Token(Type.ID, yyline, yycolumn, yytext());
}

{double_number} {
    return new Token(Type.DOUBLE_LITERAL, yyline, yycolumn, yytext());
}

{int_number} {
    return new Token(Type.INTEGER_LITERAL, yyline, yycolumn, yytext());
}

{character} {
    return new Token(Type.CHARACTER_LITERAL, yyline, yycolumn, yytext());
}

