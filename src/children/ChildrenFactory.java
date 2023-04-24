package children;

public final class ChildrenFactory {

    /** Utility Class used for Factory Design Pattern
     * Creates different types of children based on their age **/

    private ChildrenFactory() { }

    /**
     * Factory method that creates a type of Child based on his age
     * @param child
     *            child to parse
     * @return
     *            new class
     */
    public static Child createChild(final Child child) {
        if (child.getAge() < 5) {
            return new Baby(child);
        } else if (child.getAge() < 12) {
            return new Kid(child);
        } else if (child.getAge() < 19) {
            return new Teen(child);
        }

        return null;
    }
}
