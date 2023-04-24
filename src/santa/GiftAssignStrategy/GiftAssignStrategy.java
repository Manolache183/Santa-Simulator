package santa.GiftAssignStrategy;

import children.Child;

import java.util.List;

public interface GiftAssignStrategy {

    /**
     * Sorts children list based on the given strategy
     * @param children
     *              target list
     */
    void sortChildren(List<Child> children);
}
