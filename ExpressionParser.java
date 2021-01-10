
public class ExpressionParser {
    private String expression;
    private int index = 0;

    private boolean isNotFinished() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
        return index < expression.length();
    }

    private char current() {
        return expression.charAt(index);
    }

    private void skipCurrent() {
        index++;
    }


    public Expression parse(String expression) {
        this.expression = expression;
        index = 0;

        return startParsing();
    }

    private Expression startParsing() {
        return parseTo();
    }

    private Expression parseTo() {
        Expression left = parseOr();
        Expression right;
        while (isNotFinished()) {
            if (current() == '>') {
                skipCurrent();
                right = parseOr();
                left = new Implementation(left, right);
            } else {
                return left;
            }
        }
        return left;
    }

    private Expression parseOr() {
        Expression left = parseAnd();
        Expression right;
        while (isNotFinished()) {
            if (current() == '|') {
                skipCurrent();
                right = parseAnd();
                left = new Or(left, right);
            } else {
                return left;
            }
        }
        return left;
    }


    private Expression parseAnd() {
        Expression left = parseHighestPriority();
        Expression right;
        while (isNotFinished()) {
            if (current() == '&') {
                skipCurrent();
                right = parseHighestPriority();
                left = new And(left, right);
            } else {
                return left;
            }
        }
        return left;
    }


    private Expression parseHighestPriority() {
        if (!isNotFinished()) {
            throw new IllegalArgumentException("Expression is not finished correctly!");
        }

        switch (current()) {
            case '!':

                skipCurrent();
                return new No(parseHighestPriority());


            case '(':
                skipCurrent();
                Expression inside = startParsing();
                if (!isNotFinished() || current() != ')') {
                    throw new IllegalArgumentException("Bracket is not closed!");
                }
                skipCurrent();
                return inside;


            default:
                if (Character.isLetter(current()) | current() == '\'') {
                    String add;

                    add = String.valueOf(current());

                    add = getString(add);
                    skipCurrent();
                    return new Variable(add);
                }

                throw new IllegalArgumentException("Unexpected token: " + current());
        }
    }

    private String getString(String add) {

        StringBuilder addBuilder = new StringBuilder(add);
        while (expression.charAt(index + 1) == '\'') {
            if (index + 2 > expression.length()) break;
            else {
                addBuilder.append(expression.charAt(index + 1));
                if (index + 2 < expression.length()) index++;
                else break;
            }

            add = addBuilder.toString();
        }
        return add;
    }
}
