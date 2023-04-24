package santa.GiftAssignStrategy;

import children.Child;

import java.util.List;

public final class NiceScoreGiftAssignStrategy implements GiftAssignStrategy {
    @Override
    public void sortChildren(final List<Child> children) {
        children.sort((a, b) -> Double.compare(b.getAverageScore(), a.getAverageScore()));
    }
}
