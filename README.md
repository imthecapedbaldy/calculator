######@/api/calculator/
##Available routes:  
* */add* -- will add x and y inputs  
* */subtract* will subtract y from x  
* */multiply* will multiply x and y inputs  
* */divide* will divide y from x  
* */expression*  
  
For /add, /subtract, /multiply, /divide, append **x and y** values  
For /expression, using postman, use a **GET** method  
1. add a value for expression through params(variable name = "expression")  
2. expression may need to be URIencoded
3. enclose negative inputs with (parenthesis) example: 10 - (-2)  
4. adding parenthesis does not support changing order of execution
5. you may add x and y variables in the equation.  
- assign their values in params in postman
- expression may need to be URIencoded

**BUGS**
1. parenthesis and variables x and y are not fully "fluent" with each other. may fail to work in some cases