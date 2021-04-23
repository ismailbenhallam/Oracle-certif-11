package nested_classes_AND_lambda_expressions;

public class TestClass {
    static int si = 10;
    int ii = 20;

    public void inner() {
        var ai = 30;    // automatic variable (local variable)
        ai = 31;        // ai is not effectively final anymore. => it cannot be used by a Local Inner Class
        var fai = 40;

        class Inner {
            // Local Inner Classes can access local variables just if they are final or effectively final
            public Inner() {
                System.out.println(si + "  " + ii + "   " + fai + "  ");
            }
        }
        new Inner();
    }

    public static void main(String[] args) {
        new TestClass().inner();
    }
}