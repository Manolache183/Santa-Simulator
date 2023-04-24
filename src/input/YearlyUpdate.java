package input;

import children.Child;
import enums.Category;
import input.Change.ChildUpdate;
import santa.GiftAssignStrategy.GiftAssignStrategyFactory;
import santa.Santa;

import java.util.List;

public final class YearlyUpdate {

    /** Utility Class used for applying a yearly change
     * Updates:
     * Santa's budget
     * Santa's gift assign strategy
     * Children's nice scores
     * Children's elves
     * Children's gift preferences
     * Children's age
     * The Children List
     * Santa's Gift List **/

    private YearlyUpdate() { }

    /**
     * Updates the input based on the parameters
     * @param change
     *          the Change for the current year
     * @param inputLoader
     *          the current Input
     */
    public static void update(final Change change, final InputLoader inputLoader) {

        inputLoader.setSantaBudget(change.getNewSantaBudget());
        Santa.getInstance().setGiftAssignStrategy(GiftAssignStrategyFactory.createGiftAssignStrategy(change.getStrategy()));

        // Children's updates based on id
        for (ChildUpdate childUpdate : change.getChildrenUpdates()) {
            for (Child child : inputLoader.getChildren()) {
                if (childUpdate.getId() == child.getId()) {
                    if (childUpdate.getNiceScore() != null) {
                        child.updateScores(childUpdate.getNiceScore());
                    }

                    if (childUpdate.getElf() != null) {
                        child.setElf(childUpdate.getElf());
                    }

                    List<Category> childGiftPreferences = child.getGiftsPreferences();

                    // Removing existing category's in Child's gift preferences list
                    // and adding them at the start of the list
                    for (int i = childUpdate.getGiftsPreferences().size() - 1; i >= 0; i--) {
                        for (int j = 0; j < childGiftPreferences.size(); j++) {
                            if (childGiftPreferences.get(j).equals(childUpdate.getGiftsPreferences().get(i))) {
                                childGiftPreferences.remove(childUpdate.getGiftsPreferences().get(i));
                                break;
                            }
                        }

                        childGiftPreferences.add(0, childUpdate.getGiftsPreferences().get(i));
                    }

                }
            }
        }

        // Children getting a year older
        for (Child child : inputLoader.getChildren()) {
            child.setAge(child.getAge() + 1);
        }

        // Adding the first niceScore to the new Child's history
        for (Child child : change.getNewChildren()) {
            child.updateScores(child.getNiceScore());
        }

        inputLoader.getChildren().addAll(change.getNewChildren());
        inputLoader.getSantaGiftList().addAll(change.getNewGifts());

    }
}
