package stream;

public class OddAndEvenCollector extends GroupByPredicateCollector<Integer> {

    public OddAndEvenCollector() {
        super(i -> i % 2 == 0);
    }
}
