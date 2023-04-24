package children;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Category;
import input.Product;
import santa.ChildVisitors.ChildVisitor;
import santa.ElfBudgetStrategy.ElfBudgetStrategy;

import java.util.ArrayList;
import java.util.List;


public class Child {

    /** Base Class for storing children information
     * Extended by the classes: Baby, Kid, Teen
     * Visitable **/

    private int id;

    private String lastName;
    private String firstName;
    private int age;
    private String city;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double niceScore;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double niceScoreBonus;

    @JsonIgnore
    private Double niceScoreCity;

    private List<Category> giftsPreferences = new ArrayList<>();
    private List<Double> niceScoreHistory  = new ArrayList<>();
    private List<Product> receivedGifts = new ArrayList<>();

    private double assignedBudget;
    private double averageScore;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String elf;

    @JsonIgnore
    private ElfBudgetStrategy elfBudgetStrategy;

    public Child() { }

    /**
     * Copy constructor
     * @param child
     *              the object that needs to be copied
     */
    public Child(final Child child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.age = child.age;
        this.city = child.city;
        this.niceScore = child.niceScore;
        this.assignedBudget = child.getAssignedBudget();
        this.averageScore = child.getAverageScore();
        this.niceScoreBonus = child.getNiceScoreBonus();
        this.niceScoreCity = child.getNiceScoreCity();
        this.elf = child.getElf();
        this.elfBudgetStrategy = child.getElfBudgetStrategy();

        this.giftsPreferences.addAll(child.getGiftsPreferences());
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());

        // Product is the only non-static type
        for (Product product : child.getReceivedGifts()) {
            this.receivedGifts.add(new Product(product));
        }
    }

    /**
     * Method which need to be Overridden
     * Used to implement Visitor Design Pattern
     * Non-abstract because of the problems with Jackson reading from json.
     */
    public void accept(final ChildVisitor childVisitor) { }

    /**
     * Adds a product to the Child's received gift list
     * @param gift
     *           the received product
     */
    public void updateReceivedGifts(final Product gift) {
        this.receivedGifts.add(gift);
    }

    /**
     * Updates the niceScore history list
     * @param score
     *          the score that need to be added to the list
     */
    public void updateScores(final Double score) {
        this.niceScoreHistory.add(score);
    }

    /** Getters and Setters **/

    public final void setAverageScore(final double avgScore) {
        this.averageScore = avgScore;
    }

    public final void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public final void setScores(final List<Double> scores) {
        this.niceScoreHistory = scores;
    }

    public final void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public final void setReceivedGifts(final List<Product> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public final void setAge(final int age) {
        this.age = age;
    }

    public final void setCity(final String city) {
        this.city = city;
    }

    public final void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public final void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public final void setNiceScoreCity(final Double niceScoreCity) {
        this.niceScoreCity = niceScoreCity;
    }

    public final void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public final void setElf(final String elf) {
        this.elf = elf;
    }

    public final void setElfBudgetStrategy(final ElfBudgetStrategy elfBudgetStrategy) {
        this.elfBudgetStrategy = elfBudgetStrategy;
    }

    public final List<Product> getReceivedGifts() {
        return receivedGifts;
    }

    public final double getAssignedBudget() {
        return assignedBudget;
    }

    public final double getAverageScore() {
        return averageScore;
    }

    public final List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final int getId() {
        return id;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final int getAge() {
        return age;
    }

    public final String getCity() {
        return city;
    }

    @JsonIgnore
    public final Double getNiceScoreCity() {
        return niceScoreCity;
    }

    @JsonIgnore
    public final Double getNiceScore() {
        return niceScore;
    }

    @JsonIgnore
    public final Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    @JsonIgnore
    public final String getElf() {
        return elf;
    }

    public final ElfBudgetStrategy getElfBudgetStrategy() {
        return elfBudgetStrategy;
    }

    public final List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }
}
