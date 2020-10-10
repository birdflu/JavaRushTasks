package studyhall.permutation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {
    List storage = new ArrayList<String>() {{
        add("A");
        add("B");
        add("C");
    }};

    public static void main(String[] args) {
        Permutation solution = new Permutation();
//        System.out.println(solution.permutate(
//                new ArrayList<>() {{
//                    add(new ArrayList<>() {{
//                    }});
//                    add(new ArrayList<>() {{
//                        add(1);
//                        add(2);
//                        add(3);
//                    }});
//                }}));

        System.out.println(solution.permutation(
                new ArrayList<>() {{
                    add(new ArrayList<>() {{
                    }});
                    add(new ArrayList<>() {{
                        add(1);
                        add(2);
                        add(3);
                    }});
                }}));

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
    public List<List> perm (List first, List second) {
//        System.out.print(">" + first);
//        System.out.println(second);
        if (second.isEmpty()) {
            return second;
        } else {
            List head = new ArrayList<>() {{
                addAll(first);
                add(second.get(0));
            }};
            return new ArrayList<List>() {{
                //add(head);
                add(new ArrayList() {{   add(head); }});
                addAll(perm(first, second.subList(1, second.size())) );
            }};
        }
    }
//(define (permutate items)
//  (let ((result (car items))
//        (tail (cadr items)))
//            (map (lambda (x) (cons x (list (remove-sublist x tail))))
//               (perm result tail))))

    public List permutate(List list) {
//        System.out.println("  : " +   list);
        List result = (List) list.get(0);
        List tail = (List) list.get(1);
//        System.out.println("=>" + result + " " + tail + " => "  + perm(result, tail));
//        System.out.println("=>" + result + " " + tail );

        return perm(result, tail).stream()
                .map((x) -> {
                    List t = new ArrayList(tail);
                    t.removeAll((List) x.get(0));
//                    System.out.println("t=" + t);
//                    System.out.println("x.get(0)=" + x.get(0));
//                    System.out.println("x=" + x);
                    //((List)x.get(0)).addAll(new ArrayList(){{ add(t); }});
                    x.addAll(new ArrayList(){{ add(t); }});
                    //return x.get(0);
                    return x;
                } )
                .collect(Collectors.toList());
//        return perm(result, tail);

//        return perm (new ArrayList<>() {{ add(2); }}, new ArrayList<>() {{ add(1); add(3); }});
    }

//    (define (permutation items)
//        (if (null? items)
//            '()
//            (append items (permutation (flatmap permutate items)))))

    public List permutation (List list) {
        if (list.isEmpty()) {
            return list;
        } else {
            System.out.println("list " + list);
            System.out.println("permutate(list " + permutate(list));
            return (List) ((List<List>) permutate(list)).stream()
                    .flatMap((x) -> (permutate(x)).stream())
                    .collect(Collectors.toList());
        }
    }
}