package santa;

import children.Child;
import enums.Category;
import input.Product;
import santa.ElfBudgetStrategy.ElfBudgetStrategyFactory;
import santa.GiftAssignStrategy.GiftAssignStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Santa {

    /** Singleton Class
     * Implements a Santa-like behaviour
     * Used to handout gifts to a given Child **/

    private static Santa instance = null;

    private double budgetUnit;

    private GiftAssignStrategy giftAssignStrategy;

    private List<Product> giftList;

    // Hashmaps containing the cities with their niceScores
    private final HashMap<String, Double> cityNiceScore = new HashMap<>();

    // Hashmaps containing the number of kids in every city
    private final HashMap<String, Integer> cityNumberOfChildren = new HashMap<>();

    // Hashmap containing the current cheapest Product (gift) from every Category
    private final HashMap<Category, Product> cheapestGifts = new HashMap<>();

    // asta e sa primeasca cel mai ieftin doar daca nu e in map
    private final HashSet<Category> cheapestOutOfStockGifts = new HashSet<>();

    private final List<Child> yellowElfChildren = new ArrayList<>();

    /**
     * Calculates a Child's assigned budget
     * As long as Santa still has money for him
     * updates his receivedGifts list
     * @param child
     *              the Child which receives the presents
     */
    public void givePresents(final Child child) {
        double childBudget = budgetUnit * child.getAverageScore();

        child.setElfBudgetStrategy(ElfBudgetStrategyFactory.createElfBudgetStrategy(child.getElf()));

        if (child.getElfBudgetStrategy() != null) {
            childBudget = child.getElfBudgetStrategy().changeBudget(childBudget);
        }

        child.setAssignedBudget(childBudget);

        // Removing Duplicates
        List<Category> giftPreferences = child.getGiftsPreferences().stream().distinct()
                                        .collect(Collectors.toList());

        child.getGiftsPreferences().clear();
        child.getGiftsPreferences().addAll(giftPreferences);


        // Handing out gifts
        for (Category category : child.getGiftsPreferences()) {
            if (cheapestGifts.containsKey(category)
                    && Double.compare(childBudget, cheapestGifts.get(category).getPrice()) >= 0) {

                Product gift = cheapestGifts.get(category);
                child.updateReceivedGifts(gift);
                childBudget -= gift.getPrice();

                updateGiftStock(gift);
            }
        }
    }

    /**
     * Gets the instance of this Singleton Class
     */
    public static Santa getInstance() {
        if (instance == null) {
            instance = new Santa();
        }

        return instance;
    }

    /**
     * Populates the cheapestGifts Hashmap
     * @param santaGiftList
     *                      the list to be searched
     */
    public void findCheapestGifts(final List<Product> santaGiftList) {
        for (Product product : santaGiftList) {
            if (product.getQuantity() > 0) {
                if (!cheapestGifts.containsKey(product.getCategory())
                        || Double.compare(product.getPrice(),
                           cheapestGifts.get(product.getCategory()).getPrice()) < 0) {

                    cheapestGifts.put(product.getCategory(), product);
                }
            }
        }
    }

    /**
     * Clears the cheapestGifts list
     */
    public void clearCheapestGifts() {
        this.getCheapestGifts().clear();
        this.cheapestOutOfStockGifts.clear();
    }

    /**
     * Parses the list with children having the assigned elf yellow
     * and check if they can get a gift, if so, assigns gift
     */
    public void yellowElfAssignGifts() {
        for (Child child : yellowElfChildren) {
            Category category = child.getGiftsPreferences().get(0);

            if (cheapestGifts.containsKey(category)
                    && !cheapestOutOfStockGifts.contains(category)
                    && child.getReceivedGifts().isEmpty()) {

                child.getReceivedGifts().add(cheapestGifts.get(category));
                updateGiftStock(cheapestGifts.get(category));
            }
        }
    }

    /**
     * Adds a child to the yellow elf's list
     * @param child
     *          target child
     */
    public void addToYellowElfList(final Child child) {
        yellowElfChildren.add(child);
    }

    /**
     * Updates city nice scores and the number of kids in that city
     * @param city
     *          target city
     * @param score
     *          the averageScore of a kid in that city
     */
    public void updateCityScore(final String city, final Double score) {
            if (cityNiceScore.containsKey(city)) {
                cityNiceScore.put(city, Double.sum(cityNiceScore.get(city), score));
                cityNumberOfChildren.put(city, cityNumberOfChildren.get(city) + 1);
            } else {
                cityNiceScore.put(city, score);
                cityNumberOfChildren.put(city, 1);
            }
    }

    /**
     * Clears some data
     */
    public void resetMaps() {
        cityNiceScore.clear();
        cityNumberOfChildren.clear();
        yellowElfChildren.clear();
    }

    /**
     * Updates the lists regarding gifts
     * @param gift
     *          most recent assigned gift
     */
    private void updateGiftStock(final Product gift) {
        gift.setQuantity(gift.getQuantity() - 1);

        if (gift.getQuantity() == 0) {
            cheapestOutOfStockGifts.add(gift.getCategory());
            cheapestGifts.remove(gift.getCategory());
            findCheapestGifts(giftList);
        }
    }

    /** Getters and Setters **/

    public void setBudgetUnit(final double budgetUnit) {
        this.budgetUnit = budgetUnit;
    }

    public void setGiftList(final List<Product> giftList) {
        this.giftList = giftList;
    }

    public void setGiftAssignStrategy(final GiftAssignStrategy giftAssignStrategy) {
        this.giftAssignStrategy = giftAssignStrategy;
    }

    public GiftAssignStrategy getGiftAssignStrategy() {
        return giftAssignStrategy;
    }

    public HashMap<Category, Product> getCheapestGifts() {
        return cheapestGifts;
    }

    public HashMap<String, Double> getCityNiceScore() {
        return cityNiceScore;
    }

    public HashMap<String, Integer> getCityNumberOfChildren() {
        return cityNumberOfChildren;
    }
}
