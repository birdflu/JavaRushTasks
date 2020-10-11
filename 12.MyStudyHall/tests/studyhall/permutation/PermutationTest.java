package studyhall.permutation;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class PermutationTest {
    Permutation p = new Permutation();
    @Test
    void perm() {
        assert (p.perm(Collections.emptyList(), List.of(1, 2, 3)).toString().equals("[[1], [2], [3]]"));
        assert (p.perm(Collections.emptyList(), List.of(1, 2, 3)).toString().equals("[[1], [2], [3]]"));
        assert (p.perm(List.of(2), List.of(1, 3)).toString().equals("[[2, 1], [2, 3]]"));
        assert (p.perm(List.of(2, 1), List.of(2, 3)).toString().equals("[[2, 1, 2], [2, 1, 3]]"));
        assert (p.perm(List.of(2, 1), List.of(2, 3, 4)).toString()
                .equals("[[2, 1, 2], [2, 1, 3], [2, 1, 4]]"));
        assert (p.perm(List.of(2, 1, 3), Collections.emptyList()).toString().equals("[]"));
    }

    @Test
    void permutate() {
        assert (p.permutate(List.of(List.of(2), List.of(1, 3))).toString()
                .equals("[[[2, 1], [3]], [[2, 3], [1]]]"));
        assert (p.permutate(List.of(List.of(2, 1), List.of(3))).toString()
                .equals("[[[2, 1, 3], []]]"));
        assert (p.permutate(List.of(List.of(1, 2, 3), Collections.emptyList())).toString().equals("[]"));
    }

    @org.junit.jupiter.api.Test
    void permutation() {
        assert (p.permutation(List.of(List.of(List.of(2), List.of(1, 3)))).toString()
                .equals("[[[2], [1, 3]], [[2, 1], [3]], [[2, 3], [1]], [[2, 1, 3], []], [[2, 3, 1], []]]"));

    }

    @org.junit.jupiter.api.Test
    void getPermutations() {
        assert (p.getPermutations(List.of(1, 2)).toString().equals("[[], [1], [2], [1, 2], [2, 1]]"));
        assert (p.getPermutations(List.of(1, 2, 3)).toString()
                .equals("[[], [1], [2], [3], [1, 2], [1, 3], [2, 1], [2, 3], [3, 1], [3, 2], " +
                        "[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]"));
    }
}