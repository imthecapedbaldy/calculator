package io.toro.calculator.Model;

public class Expression {

    private String expression;

    private Double x;

    private Double y;

    private Double answer;

    public Expression(){}

    public Expression( String expression, Double x, Double y ) {
        this.expression = expression;
        this.x = x;
        this.y = y;
    }

    public Double getAnswer() {
        return answer;
    }

    public void setAnswer( Double answer ) {
        this.answer = answer;
    }

    public Double getX() {
        return x;
    }

    public void setX( Double x ) {
        if(x==null)
            this.x = 1d;
        else
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY( Double y ) {
        if(y==null)
            this.y = 1d;
        else
            this.y = y;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression( String expression ) {
        this.expression = expression;
    }


}
