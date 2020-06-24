## mc-jacc
Just another compiler with weird syntax (extension .mc). It is still in front end stage.

Things to do:
```text
1. Intermediate Code Generation
2. Backend code generation
```

#### Variable Declaration <br>
Variable declaration must be done inside '<' and '>'. If you want to assign a value, you can put the value inside brackets.
```text
<int i(100)>
```

#### Function Declaration <br>
Function declaration must be done within double **<** and **>** operators. <br>
Function call can be done with '$.' followed by function name.
```text
<<int add(<int a>, <int b>)>> {
  <int c(a+b)>
  return c
}
``` 

#### Start Function <br>
'initiate' is the start function which will not accept any arguments.
```text
<<int initiate()>> {
    ...
}
```
#### Decision and looping statements <br>
All these statements needs to be called with '@.' prefix. <br>
**If:**
```text
<int c>
@.IF(10 < 100) {
  c = $.add(100, 200)
} @.ELSE_IF() {
  c = $.add(200, 100)
} @.ELSE {
  c = -1
}
```
**For:**
```text
@.FOR(<int c(100)>; c < 200; c=c+1) {
    ...
}
```
**Do while:**
```text
@.DO_WHILE(c < 1000) {
    ...
}
```
**While:**
```text
@.WHILE(c < 2000) {
    ...
}
```
