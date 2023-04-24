package santa.ChildVisitors;

import children.Baby;
import children.Kid;
import children.Teen;

public interface ChildVisitor {

    /** Visitor Interface
     * Used for implementing Child Visitors,
     * based on their age
     * Implemented only by AverageScoreVisitor for the moment**/

    void visit(Baby baby);
    void visit(Kid kid);
    void visit(Teen teen);

}
