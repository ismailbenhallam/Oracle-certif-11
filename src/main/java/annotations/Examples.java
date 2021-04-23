package annotations;

import annotations.utils.ProductManager;

import java.util.List;
import java.util.stream.Stream;

//@BusinessPolicies({
@BusinessPolicy(value = "Business policy 1", counties = "MA")
//        ,
@BusinessPolicy(value = "Business policy 2", counties = {"MA", "FR"})
//})
public class Examples {

    public static void main(String[] args) {
        getAnnotationsByType();

        deprecated();

        unchecked();

        safeVarargs();
    }

    private static void getAnnotationsByType() {
        Stream.of(Examples.class.getAnnotationsByType(BusinessPolicy.class)).forEach(System.out::println);
    }

    @SuppressWarnings("deprecation")
    private static void deprecated() {
        final var deprecatedMethods = new DeprecatedMethods();
        deprecatedMethods.save();
    }

    @SuppressWarnings("unchecked")
    private static void unchecked() {
        List list = new ProductManager().find();
        list.forEach(System.out::println);
    }

    private static void safeVarargs() {
        new SafeVarargsMethods().some(List.of("Ko"));
    }
}
