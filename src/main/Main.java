package main;

import checker.Checker;
import children.Child;
import children.ChildrenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import input.Change;
import input.InputLoader;
import input.YearlyUpdate;
import output.OutputWriter;
import santa.ChildVisitors.AverageScoreVisitor;
import santa.Santa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static common.Constants.FILE_EXTENSION;
import static common.Constants.OUTPUT_FOLDER;
import static common.Constants.OUTPUT_PATH;
import static common.Constants.TESTS_NUMBER;
import static common.Constants.TESTS_PATH;
import static common.Constants.YELLOW_ELF;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        // Constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputLoader inputLoader;
        OutputWriter outputWriter = OutputWriter.getInstance();

        Santa santa = Santa.getInstance();

        List<Child> inputChildren;
        List<Child> children = new ArrayList<>();
        OutputWriter.OutputChildren outputChildren = new OutputWriter.OutputChildren();

        AverageScoreVisitor averageScoreVisitor = new AverageScoreVisitor();
        double avgSum; // used for Santa's budget unit

        // Creation of the Output directory
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // Go through all 25 tests
        for (int i = 1; i <= TESTS_NUMBER; i++) {

            // Reading the target test
            inputLoader = mapper.readValue(new File(TESTS_PATH + i + FILE_EXTENSION), InputLoader.class);

            // Clearing the input data
            inputChildren = inputLoader.getChildren();
            santa.clearCheapestGifts();

            santa.resetMaps();
            avgSum = 0;
            children.clear();

            // Adding the first score to every child's history and
            // categorizing children based on their age
            for (Child child : inputChildren) {
                child.updateScores(child.getNiceScore());
                children.add(ChildrenFactory.createChild(child));
            }

            // Eliminating null elements resulted from Young Adults after factory method
            children.removeIf(Objects::isNull);

            // Using Visitor Design Pattern to calculate avgSum
            for (Child child : children) {
                child.accept(averageScoreVisitor);
                avgSum += child.getAverageScore();
            }

            santa.setGiftList(inputLoader.getSantaGiftList());
            santa.setBudgetUnit(inputLoader.getSantaBudget() / avgSum);
            santa.findCheapestGifts(inputLoader.getSantaGiftList());

            for (Child child : children) {
                santa.givePresents(child);
                if (child.getElf().equals(YELLOW_ELF)) {
                    santa.addToYellowElfList(child);
                }
            }

            santa.yellowElfAssignGifts();

            // Adding to Output
            outputChildren.setChildren(children);
            outputWriter.addAnnualChildren(outputChildren);

            // Getting through the annual changes
            for (int j = 0; j < inputLoader.getNumberOfYears(); j++) {
                avgSum = 0;
                santa.resetMaps();

                Change change = inputLoader.getAnnualChanges().get(j);

                // Updating
                YearlyUpdate.update(change, inputLoader);

                //santa.findCheapestGifts(inputLoader.getSantaGiftList());
                inputChildren = inputLoader.getChildren();

                children.clear();
                for (Child child : inputChildren) {
                    children.add(ChildrenFactory.createChild(child));
                }

                // Eliminating null elements resulted from Young Adults after factory method
                children.removeIf(Objects::isNull);

                // Using Visitor Design Pattern to calculate avgSum
                children.sort(Comparator.comparingInt(Child::getId));
                for (Child child : children) {
                    child.accept(averageScoreVisitor);
                    santa.updateCityScore(child.getCity(), child.getAverageScore());
                    avgSum += child.getAverageScore();
                }

                for (Child child : children) {
                    child.setNiceScoreCity(santa.getCityNiceScore().get(child.getCity())
                                            /
                                           santa.getCityNumberOfChildren().get(child.getCity()));
                }

                // Applying Gift Assign Strategy, children list needs to always be sort by id
                if (santa.getGiftAssignStrategy() != null) {
                    santa.getGiftAssignStrategy().sortChildren(children);
                }

                santa.setBudgetUnit(inputLoader.getSantaBudget() / avgSum);
                santa.findCheapestGifts(inputLoader.getSantaGiftList());

                for (Child child : children) {
                    santa.givePresents(child);
                    if (child.getElf().equals(YELLOW_ELF)) {
                        santa.addToYellowElfList(child);
                    }
                }

                santa.yellowElfAssignGifts();

                // Adding to Output
                outputChildren.setChildren(children);
                outputWriter.addAnnualChildren(outputChildren);
            }

            // Writing the output and clearing it
            mapper.writeValue(new File(OUTPUT_PATH + i + FILE_EXTENSION), outputWriter);
            outputWriter.clearAnnualChildren();
        }

        Checker.calculateScore();
    }
}
