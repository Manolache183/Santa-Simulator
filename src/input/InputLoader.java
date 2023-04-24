package input;

import children.Child;

import java.util.ArrayList;
import java.util.List;

public final class InputLoader {

    /** Class used as a database to store de Input
     * Isn't a singleton because of the interaction with jackson reading **/

    private static class InitialData {
        private List<Child> children = new ArrayList<>();
        private List<Product> santaGiftsList = new ArrayList<>();

        /** Getters and Setters **/

        public void setChildren(final List<Child> children) {
            this.children = children;
        }

        public void setSantaGiftsList(final List<Product> santaGiftsList) {
            this.santaGiftsList = santaGiftsList;
        }

        public List<Child> getChildren() {
            return children;
        }

        public List<Product> getSantaGiftsList() {
            return santaGiftsList;
        }

    }

    private double numberOfYears;
    private double santaBudget;
    private InitialData initialData = new InitialData();
    private List<Change> annualChanges = new ArrayList<>();

    /** Getters and Setters **/

    public void setNumberOfYears(final double numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(final double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public void setAnnualChanges(final List<Change> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public List<Product> getSantaGiftList() {
        return initialData.getSantaGiftsList();
    }

    public double getNumberOfYears() {
        return numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public List<Change> getAnnualChanges() {
        return annualChanges;
    }

    public List<Child> getChildren() {
        return initialData.getChildren();
    }
}
