package annotations;

public class DeprecatedMethods {
    /**
     * @deprecated This method,has been deprecated, as it is inherently deadlock-prone.
     * Please use {@link #commit} method instead
     */
    @Deprecated(since = "11", forRemoval = true)
    public void save() {
    }

    public void commit() {
    }
}
