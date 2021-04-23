package nested_classes_AND_lambda_expressions;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class LambdaExpressions {
    private Math takeSystem1(System s) {
        return null;
    }

    private static Math takeSystem2(LambdaExpressions l, System s) {
        return null;
    }

    private Math takeSystem3(LambdaExpressions l, System system) {
        return null;
    }

    private static BigDecimal takeIt(BiFunction<LambdaExpressions, System, Math> f) {
        return null;
    }

    public static void main(String[] args) {
        takeIt(LambdaExpressions::takeSystem1);
        takeIt(LambdaExpressions::takeSystem2);
        var LambdaExpressions = new LambdaExpressions();
        takeIt(LambdaExpressions::takeSystem3);
    }
}
