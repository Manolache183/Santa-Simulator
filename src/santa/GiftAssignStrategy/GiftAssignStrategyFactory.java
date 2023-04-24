package santa.GiftAssignStrategy;

import static common.Constants.CITY_NICE_SCORE_STRATEGY;
import static common.Constants.NICE_SCORE_STRATEGY;

public final class GiftAssignStrategyFactory {

    private GiftAssignStrategyFactory() { }

    /**
     * Factory method that creates a type of Child based on his age
     * @param strategy
     *            type of strategy
     * @return
     *            new strategy class
     */
    public static GiftAssignStrategy createGiftAssignStrategy(final String strategy) {
        return switch (strategy) {
            case NICE_SCORE_STRATEGY -> new NiceScoreGiftAssignStrategy();
            case CITY_NICE_SCORE_STRATEGY -> new CityNiceScoreGiftAssignStrategy();
            default -> null;
        };
    }
}
