package children;

import santa.ChildVisitors.ChildVisitor;

public final class Baby extends Child {

    public Baby(final Child child) {
        super(child);
    }

    @Override
    public void accept(final ChildVisitor childVisitor) {
        childVisitor.visit(this);
    }
}
