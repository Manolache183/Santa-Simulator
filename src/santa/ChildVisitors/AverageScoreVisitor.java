package santa.ChildVisitors;

import children.Baby;
import children.Kid;
import children.Teen;

import java.util.List;

public final class AverageScoreVisitor implements ChildVisitor {

    /** Visitor Class
     * Used for calculating the Child's average niceScore,
     * based on his age and history **/

    @Override
    public void visit(final Baby baby) {
        baby.setAverageScore(10);
    }

    @Override
    public void visit(final Kid kid) {
        double sum = 0;
        double averageScore;

        List<Double> scores = kid.getNiceScoreHistory();

        int n = scores.size();

        for (double score : scores) {
            sum += score;
        }

        averageScore = sum / n;
        averageScore += averageScore * kid.getNiceScoreBonus() / 100;

        if (Double.compare(averageScore, 10) < 0) {
            kid.setAverageScore(averageScore);
        } else {
            kid.setAverageScore(10);
        }
    }

    @Override
    public void visit(final Teen teen) {
        double sum = 0;
        double averageScore;

        List<Double> scores = teen.getNiceScoreHistory();

        int n = 0;

        for (int i = 0; i < scores.size(); i++) {
            sum += scores.get(i) * (i + 1);
            n += (i + 1);
        }

        averageScore = sum / n;
        averageScore += averageScore * teen.getNiceScoreBonus() / 100;

        if (Double.compare(averageScore, 10) < 0) {
            teen.setAverageScore(averageScore);
        } else {
            teen.setAverageScore(10);
        }
    }
}
