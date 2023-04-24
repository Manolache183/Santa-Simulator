package output;

import children.Child;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class OutputWriter {

    /** Singleton Class
     * Used for storing the output
     * It has the exact output format needed in the json**/

    public final static class OutputChildren {
        private List<Child> children = new ArrayList<>();

        public OutputChildren() { }

        /**
         * Copy constructor
         * @param outputChildren
         *                  the object that needs to be copied
         */
        public OutputChildren(final OutputChildren outputChildren) {
            for (Child child : outputChildren.getChildren()) {
                this.children.add(new Child(child));
            }

            this.children.sort(Comparator.comparingInt(Child::getId));
        }

        /** Getters and Setters **/

        public void setChildren(final List<Child> children) {
            this.children = children;
        }

        public List<Child> getChildren() {
            return children;
        }
    }

    @JsonIgnore
    private static OutputWriter instance = null;

    private List<OutputChildren> annualChildren = new ArrayList<>();

    /**
     * Gets the instance of this Singleton Class
     */
    public static OutputWriter getInstance() {
        if (instance == null) {
            instance = new OutputWriter();
        }

        return instance;
    }

    /**
     * Populates the cheapestGifts Hashmap
     * @param outputChildren
     *                      the list which elements need to be added
     */
    public void addAnnualChildren(final OutputChildren outputChildren) {
        this.annualChildren.add(new OutputChildren(outputChildren));
    }

    /**
     * Clears the list of children that need to be output
     */
    public void clearAnnualChildren() {
        this.annualChildren.clear();
    }

    /** Getters and Setters **/

    public void setAnnualChildren(final List<OutputChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public List<OutputChildren> getAnnualChildren() {
        return annualChildren;
    }
}
