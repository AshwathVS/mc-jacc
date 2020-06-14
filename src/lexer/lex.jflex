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

number=[0-9]*(\.[0-9]+)?((e|E)[-+]?[0-9]+)?
text=\"([^\"\\]|\\.)*\"
id=[a-zA-Z_]([a-zA-Z_]|[0-9])*
%%

/** Data types: */
int\s {
    return new Token(Type.INTEGER);
}
double\s {
    return new Token(Type.DOUBLE);
}
char\s {
    return new Token(Type.CHARACTER);
}
bool\s {
    return new Token(Type.BOOLEAN);
}
string\s {
    return new Token(Type.STRING);
}
enum\s {
    return new Token(Type.ENUM);
}


/** Arithmetic Operators: */
\+
{
    return new Token(Type.ADD);
}
\-
{
    return new Token(Type.SUBTRACT);
}
\*
{
    return new Token(Type.MULTIPLY);
}
\/
{
    return new Token(Type.DIVIDE);
}
\%
{
    return new Token(Type.MODULUS);
}
\+\+
{
    return new Token(Type.INCREMENT);
}
\-\-
{
    return new Token(Type.DECREMENT);
}


/** Relational Operators: */
\=\=|EE
{
  return new Token(Type.EQUALS);
}
\!\=|NE
{
    return new Token(Type.NOT_EQUALS);
}
\>|GT
{
    return new Token(Type.GREATER_THAN);
}
\<|LT
{
    return new Token(Type.LESS_THAN);
}
\>=|GE
{
    return new Token(Type.GREATER_THAN_EQUAL);
}
\<=|LE
{
    return new Token(Type.LESS_THAN_EQUAL);
}


/** Bit Wise Operators: */
\&
{
    return new Token(Type.B_AND);
}
\|
{
    return new Token(Type.B_OR);
}
\~
{
    return new Token(Type.B_NOT);
}
\^
{
    return new Token(Type.B_XOR);
}
\<\<
{
    return new Token(Type.B_LEFT_SHIFT);
}
\>\>
{
    return new Token(Type.B_RIGHT_SHIFT);
}
\>\>\>
{
    return new Token(Type.B_RIGHT_SHIFT_ZERO);
}


/** Logical Operators: */
(\&\&|AND)
{
    return new Token(Type.AND);
}
(\|\||OR)
{
    return new Token(Type.OR);
}
(\!|NOT)
{
    return new Token(Type.NOT);
}


/** Assignment Operators */
\=
{
    return new Token(Type.EQUAL_TO);
}
\+\=
{
    return new Token(Type.ADD_EQUALS);
}
\-\=
{
    return new Token(Type.SUBTRACT_EQUALS);
}
\*\=
{
    return new Token(Type.PRODUCT_EQUALS);
}
\/\=
{
    return new Token(Type.DIVIDE_EQUALS);
}
\%\=
{
    return new Token(Type.MODULUS_EQUALS);
}
\<\<\=
{
    return new Token(Type.B_LEFT_SHIFT_EQUALS);
}
\>\>\=
{
    return new Token(Type.B_RIGHT_SHIFT_EQUALS);
}
\&\=
{
    return new Token(Type.B_AND_EQUALS);
}
\^\=
{
    return new Token(Type.B_XOR_EQUALS);
}
\|\=
{
    return new Token(Type.B_OR_EQUALS);
}


/** Decision making Operators: */
if
{
    return new Token(Type.IF);
}
else
{
    return new Token(Type.ELSE);
}
else\sif
{
    return new Token(Type.ELSE_IF);
}
switch\s
{
    return new Token(Type.SWITCH);
}
case\s
{
    return new Token(Type.CASE);
}
continue\s
{
    return new Token(Type.CONTINUE);
}
break\s
{
    return new Token(Type.BREAK);
}
goto\s
{
    return new Token(Type.GOTO);
}



/** Loop Operators:*/
for
{
    return new Token(Type.FOR);
}
while
{
    return new Token(Type.WHILE);
}
do
{
    return new Token(Type.DO);
}

/** Additional Operators: */
\:
{
    return new Token(Type.COLON);
}
\;
{
    return new Token(Type.SEMI_COLON);
}
\(
{
    return new Token(Type.OPEN_BRACKET);
}
\)
{
    return new Token(Type.CLOSE_BRACKET);
}
\[
{
    return new Token(Type.OPEN_SQUARE_BRACKET);
}
\]
{
    return new Token(Type.CLOSE_SQUARE_BRACKET);
}
\{
{
    return new Token(Type.OPEN_CURLY_BRACE);
}
\}
{
    return new Token(Type.CLOSE_CURLY_BRACE);
}
\'
{
    return new Token(Type.SINGLE_QUOTE);
}
\"
{
    return new Token(Type.DOUBLE_QUOTE);
}
\/
{
    return new Token(Type.FORWARD_SLASH);
}
\\
{
    return new Token(Type.BACKWARD_SLASH);
}
\,
{
    return new Token(Type.COMMA);
}
\.
{
    return new Token(Type.DOT);
}
\_
{
    return new Token(Type.UNDER_SCORE);
}
\-
{
    return new Token(Type.HYPHEN);
}
\#
{
    return new Token(Type.HASH_TAG);
}
[\t\r\n\f\s]+
{
    return new Token(Type.WHITE_SPACE);
}
[$]
{
    return new Token(Type.EOF);
}

/*Left value and right value*/
{id} {
    return new Token(Type.ID, yytext());
}

{number} {
    return new Token(Type.NUMBER, yytext());
}

{text} {
    return new Token(Type.TEXT, yytext());
}
