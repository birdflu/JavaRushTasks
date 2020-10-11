package studyhall.permutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Permutation {
    List storage = Stream.of("A", "B", "C").collect(Collectors.toList());

    public static void main(String[] args) {
        Permutation p = new Permutation();
//        #lang = scheme
//        (perm  '() '(1 2 3))  =>  ((1) (2) (3))
//        (perm  '(2) '(1 3) )  =>  ((2 1) (2 3))
//        (perm '(2 1) '(2 3))  =>  ((2 1 2) (2 1 3))
//        (perm '(2 1) '(2 3 4))=>  ((2 1 2) (2 1 3) (2 1 4))
//        (perm '(2 1 3) '())   =>  ()

//        (permutate  '(( 2 ) ( 1 3 ))) =>    (((2 1) (3)) ((2 3) (1)))
//        (permutate  '(( 2 1 ) ( 3 ))) =>    (((2 1 3) ()))
//        (permutate  '((1 2 3) ( )))   =>    ()

//        (permutation  '(((2) (1 3)))) =>    (((2) (1 3)) ((2 1) (3)) ((2 3) (1)) ((2 1 3) ()) ((2 3 1) ()))

//        (getPermutations '(1 2))      =>    (() (1) (2) (1 2) (2 1))
//        (getPermutations '(1 2 3))    =>    (() (1) (2) (3) (1 2) (1 3) (2 1) (2 3) (3 1) (3 2) (1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1))
        System.out.printf("getPermutations(%s)=%s", p.storage, p.getPermutations(p.storage));
    }

    // (perm  '(2) '(1 3) '(1 3))
    //    (define (perm first second)
    //      (let ((null-first? (null? first))
    //            (null-second? (null? second)))
    //      (cond [null-second? '()]
    //            [else
    //                (append
    //                    (list (append first (list (car second))))
    //                    (perm first (cdr second)))]
    //     )))

    public List<List> perm(List first, List second) {
        if (second.isEmpty()) {
            return second;
        } else {
            List head = new ArrayList<>() {{
                addAll(first);
                add(second.get(0));
            }};
            return new ArrayList<List>() {{
                add(head);
                addAll(perm(first, second.subList(1, second.size())));
            }};
        }
    }
//(define (permutate items)
//  (let ((result (car items))
//        (tail (cadr tems)))
//            (map (lambda (x) (cons x (list (remove-sublist x tail))))
//               (perm result tail))))

    public List permutate(List list) {
        List result = (List) list.get(0);
        List tail = (List) list.get(1);
        return perm(result, tail).stream() // [[2, 1, 2], [2, 1, 3], [2, 1, 4]]
                .map((x) -> {
                    List t = new ArrayList(tail);
                    t.removeAll(x);
                    List r = new ArrayList();
                    r.add(x);
                    r.addAll(new ArrayList() {{
                        add(t);
                    }});
                    return r;
                })
                .collect(Collectors.toList());
    }

//    (define (permutation items)
//        (if (null? items)
//            '()
//            (append items (permutation (flatmap permutate items)))))

    public List permutation(List list) {
        if (list.isEmpty()) {
            return list;
        } else {
            return new ArrayList<List>() {{
                Object collect = list.stream()
                        .flatMap((x) -> (permutate((List) x)).stream())
                        .collect(Collectors.toList());
                addAll(list);
                addAll(permutation(
                        (List) collect));
            }};
        }
    }

    //(define (main items)
    //  (map car (permutation (list (list '() items)))))
    public List getPermutations(List list) {
        List r = new ArrayList();
        r.add(new ArrayList<>() {{
            add(Collections.emptyList());
            add(list);
        }});
        return (List) permutation(r).stream().map(x -> ((List) x).get(0)).collect(Collectors.toList());
    }

}