package santa.ElfBudgetStrategy;

public interface ElfBudgetStrategy {

    /**
     * Changes budget base on the child's elf
     * @param budget
     *          target budget
     * @return
     *          new budget
     */
    double changeBudget(double budget);
}
