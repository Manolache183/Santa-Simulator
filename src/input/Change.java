package input;

import children.Child;
import enums.Category;

import java.util.ArrayList;
import java.util.List;

public final class Change {

    /** Used for implementing the annually changes **/

    public static final class ChildUpdate {
        private int id;

        private Double niceScore;

        private String elf;

        private List<Category> giftsPreferences = new ArrayList<>();

        /** Getters and Setters **/

        public String getElf() {
            return elf;
        }

        public int getId() {
            return id;
        }

        public Double getNiceScore() {
            return niceScore;
        }

        public List<Category> getGiftsPreferences() {
            return giftsPreferences;
        }

        public void setElf(final String elf) {
            this.elf = elf;
        }

        public void setId(final int id) {
            this.id = id;
        }

        public void setNiceScore(final Double niceScore) {
            this.niceScore = niceScore;
        }

        public void setGiftsPreferences(final List<Category> giftsPreferences) {
            this.giftsPreferences = giftsPreferences;
        }

    }

    private double newSantaBudget;
    private String strategy;

    private List<Product> newGifts = new ArrayList<>();
    private List<Child> newChildren = new ArrayList<>();
    private List<ChildUpdate> childrenUpdates = new ArrayList<>();

    /** Getters and Setters **/

    public void setNewSantaBudget(final double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public void setStrategy(final String strategy) {
        this.strategy = strategy;
    }

    public void setNewGifts(final List<Product> newGifts) {
        this.newGifts = newGifts;
    }

    public void setNewChildren(final List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public void setChildrenUpdates(final List<ChildUpdate> childUpdates) {
        this.childrenUpdates = childUpdates;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public String getStrategy() {
        return strategy;
    }

    public List<Product> getNewGifts() {
        return newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }
}
