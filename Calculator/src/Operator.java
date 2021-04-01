public class Operator {

    /**
     * the enumeration for operators
     */
    enum Operators{
        SUM,MINUS,DIVISION,DOT,MOD
    }

    Operators op;
    Float num[] = new Float[2];

    /**
     * constructor of class
     */
    public Operator(){
        num[0] = 0f;
        num[1] = 0f;
    }

    /**
     * @param op operator of the work we want to do
     */
    public void setOp(Operators op){
        this.op = op;
    }

    /**
     * @param i witch number
     * @param content content of number
     */
    public void setContent(int i, float content) {
        num[i] = content;
    }

    /**
     * @return the result of work
     */
    public float getResult(){
        switch (op){
            case DOT: return num[0] * num[1];
            case SUM: return num[0] + num[1];
            case MINUS: return  num[0] - num[1];
            case DIVISION: return num[0]/num[1];
            case MOD: return num[0]%num[1];
        }
        return 0;
    }
}
