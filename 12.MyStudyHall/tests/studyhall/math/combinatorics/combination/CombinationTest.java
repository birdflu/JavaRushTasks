package studyhall.math.combinatorics.combination;

import java.util.List;

class CombinationTest {
    Combination p = new Combination();

    @org.junit.jupiter.api.Test
    void getCombinations() {
        assert (p.getCombinations(List.of(1, 2, 3, 4)).toString()
                .equals("[[], [1], [2], [3], [4], " +
                        "[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4], " +
                        "[1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4], " +
                        "[1, 2, 3, 4]]"));
    }
}