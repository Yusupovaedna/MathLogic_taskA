

public class Implementation implements Expression {
    private final Expression left;
    private final Expression right;

    public Implementation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toTree() {
        return "(->," + left.toTree() + "," + right.toTree() + ")";
    }
}
