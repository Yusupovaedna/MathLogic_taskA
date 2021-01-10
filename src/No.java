package src;

public class No implements Expression {
    private final Expression value;

    public No(Expression value) {
        this.value = value;
    }


    @Override
    public String toTree() {
        {
            return "(!" + value.toTree() + ")";
        }
    }
}
