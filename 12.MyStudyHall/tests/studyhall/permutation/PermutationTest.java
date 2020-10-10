package studyhall.permutation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class PermutationTest {
    Permutation p = new Permutation();
    @Test
    void perm() {
        assert (p.perm(Collections.emptyList(), Arrays.asList(1, 2, 3)).toString().equals("[[1], [2], [3]]"));
        assert (p.perm(Collections.emptyList(), Arrays.asList(1, 2, 3)).toString().equals("[[1], [2], [3]]"));
        assert (p.perm(Arrays.asList(2), Arrays.asList(1, 3)).toString().equals("[[2, 1], [2, 3]]"));
        assert (p.perm(Arrays.asList(2, 1), Arrays.asList(2, 3)).toString().equals("[[2, 1, 2], [2, 1, 3]]"));
        assert (p.perm(Arrays.asList(2, 1), Arrays.asList(2, 3, 4)).toString().equals("[[2, 1, 2], [2, 1, 3], [2, 1, 4]]"));
        assert (p.perm(Arrays.asList(2, 1, 3), Collections.emptyList()).toString().equals("[]"));

    }

    @org.junit.jupiter.api.Test
    void permutate() {
    }

    @org.junit.jupiter.api.Test
    void permutation() {
    }

    @org.junit.jupiter.api.Test
    void getPermutations() {
    }
}