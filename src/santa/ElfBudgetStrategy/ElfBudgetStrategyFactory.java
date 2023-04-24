package santa.ElfBudgetStrategy;


import static common.Constants.BLACK_ELF;
import static common.Constants.PINK_ELF;

public final class ElfBudgetStrategyFactory {

    private ElfBudgetStrategyFactory() { }

    /**
     * Factory method that creates a type of Child based on his age
     * @param elf
     *            the elf's type on which the strategy is based
     * @return
     *            new strategy class
     */
    public static ElfBudgetStrategy createElfBudgetStrategy(final String elf) {
        return switch (elf) {
            case BLACK_ELF -> new BlackElf();
            case PINK_ELF -> new PinkElf();
            default -> null;
        };
    }
}
