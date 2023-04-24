package santa.ElfBudgetStrategy;

public final class BlackElf implements ElfBudgetStrategy {
    @Override
    public double changeBudget(final double budget) {
        return budget - budget * 30 / 100;
    }
}
