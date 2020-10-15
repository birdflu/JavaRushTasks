package com.javarush.task.task27.task2712.combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Combination {
// c.getCombinations(List.of(1, 2, 3, 4)) =
//      [[], [1], [2], [3], [4],
//      [1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4],
//      [1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4], [1, 2, 3, 4]]

    public static void main(String[] args) {
        Combination c = new Combination();
    }

// (combinate '(() (1 2 3)))    => '(((1) (2 3)) ((2) (3)) ((3) ()))
// (combinate '((2) (2 3 4)))   => '(((2 2) (3 4)) ((2 3) (4)) ((2 4) ()))

// (define (combinate items)
//   (let ((first (car items))
//         (second (cadr items)))
//            (let ((null-first? (null? first))
//                  (null-second? (null? second)))
//              (cond [null-second? '()]
//                    [else
//                      (append
//                          (list
//                            (list (append first (list (car second))) (cdr second) ))
//                          (combinate (list first (cdr second))))]
//            ))))

    public List<List> combinate(List items) {
        List first = (List) items.get(0);
        List second = (List) items.get(1);

        if (second.isEmpty()) {
            return second;
        } else {
            List head = new ArrayList<>() {{
                add(new ArrayList<>() {{
                    addAll(first);
                    add(second.get(0));

                }});
                add(second.subList(1, second.size()));
            }};
            return new ArrayList<List>() {{
                add(head);
                addAll(combinate(
                        new ArrayList<List>() {{
                            add(first);
                            add(second.subList(1, second.size()));
                        }})
                );
            }};
        }
    }


//    (define (combination items)
//            (if (null? items)
//            '()
//            (append items (combination (flatmap combinate items)))))

    public List combination(List list) {
        if (list.isEmpty()) {
            return list;
        } else {
            return new ArrayList<List>() {{
                Object collect = list.stream()
                        .flatMap((x) -> (combinate((List) x)).stream())
                        .collect(Collectors.toList());
                addAll(list);
                addAll(combination(
                        (List) collect));
            }};
        }
    }

    //(define (main items)
    //  (map car (combination (list (list '() items)))))

    public List getCombinations(List list) {
        List r = new ArrayList();
        r.add(new ArrayList() {{
            add(Collections.emptyList());
            add(list);
        }});
        return (List) combination(r).stream().map(x -> ((List) x).get(0)).collect(Collectors.toList());
    }

}