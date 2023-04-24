package santa.GiftAssignStrategy;

import children.Child;

import java.util.Comparator;
import java.util.List;

public final class CityNiceScoreGiftAssignStrategy implements GiftAssignStrategy {
    @Override
    public void sortChildren(final List<Child> children) {
        children.sort(Comparator.comparing(Child::getCity));
        children.sort((a, b) -> Double.compare(b.getNiceScoreCity(), a.getNiceScoreCity()));
    }
}
