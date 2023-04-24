package children;

import santa.ChildVisitors.ChildVisitor;

public final class Kid extends Child {

    public Kid(final Child child) {
        super(child);
    }

    @Override
    public void accept(final ChildVisitor childVisitor) {
        childVisitor.visit(this);
    }
}
