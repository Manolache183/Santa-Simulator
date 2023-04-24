package children;

import santa.ChildVisitors.ChildVisitor;

public final class Teen extends Child {

    public Teen(final Child child) {
        super(child);
    }

    @Override
    public void accept(final ChildVisitor childVisitor) {
        childVisitor.visit(this);
    }
}
