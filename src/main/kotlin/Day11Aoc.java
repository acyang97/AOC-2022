import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Day11Aoc {
    static List<Monkey> listOfMonkeys = new ArrayList<>();

    public static void main(String[] args) {
        setInput();
        // part 1
        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey: listOfMonkeys) {
                while (!monkey.items.isEmpty()) {
                    monkey.throwItem();
                }
            }
            System.out.println("After round " + i);
            for (int j = 0; j < listOfMonkeys.size(); j++) {
                System.out.println("Monkey " + j + ": " + listOfMonkeys.get(j).toString());
            }
        }
        for (Monkey monkey: listOfMonkeys) {
            System.out.println(monkey.timesInspected);
        }
    }

    static class Monkey {
        Queue<Long> items;
        String operation;
        Long intOp;
        Long testDivisibleBy;
        int monkeyToThrowToIfTrue;
        int monkeyToThrowToIfFalse;
        int timesInspected;

        public Monkey(
                String itemString,
                String operation,
                Long intOp,
                Long testDivisibleBy,
                int monkeyToThrowToIfTrue,
                int monkeyToThrowToIfFalse
        ) {
            this.items = new ArrayDeque<>();
            for (String item: itemString.split(", ")) {
                items.add(Long.parseLong(item));
            }
            this.operation = operation;
            this.intOp = intOp;
            this.testDivisibleBy = testDivisibleBy;
            this.monkeyToThrowToIfTrue = monkeyToThrowToIfTrue;
            this.monkeyToThrowToIfFalse = monkeyToThrowToIfFalse;
            this.timesInspected = 0;
        }

        public void throwItem() {
            Long itemToThrow = this.items.poll();
            Long newItem;
            if (operation == "+") {
                Long x = Long.valueOf(intOp);
                newItem = itemToThrow + x;
            } else if (operation == "-") {
                Long x = Long.valueOf(intOp);
                newItem = itemToThrow - x;
            } else if (operation == "*") {
                if (intOp == -1) {
                    newItem = itemToThrow * itemToThrow;
                } else {
                    Long x = Long.valueOf(intOp);
                    newItem = itemToThrow * x;
                }
            } else {
                Long x = Long.valueOf(intOp);
                newItem = itemToThrow / x;
            }
//          newItem = newItem/3; // part 1 divide 3, part 2 no need
            newItem = newItem % (7L*3L*2L*11L*17L*5L*13L*19L);
            if (newItem % testDivisibleBy == 0) {
                listOfMonkeys.get(monkeyToThrowToIfTrue).items.add(newItem);
            } else {
                listOfMonkeys.get(monkeyToThrowToIfFalse).items.add(newItem);
            }
            setTimesInspected();
        }

        public void setTimesInspected() {
            this.timesInspected = timesInspected + 1;
        }

        @Override
        public String toString() {
            return this.items.toString();
        }
    }

    static void setInput() {
        listOfMonkeys = new ArrayList<>();
        // Monkey 0
        listOfMonkeys.add(
                new Monkey(
                        "91, 58, 52, 69, 95, 54",
                        "*",
                        13L,
                        7L,
                        1,
                        5
                )
        );
        // Monkey 1
        listOfMonkeys.add(
                new Monkey(
                        "80, 80, 97, 84",
                        "*",
                        -1L,
                        3L,
                        3,
                        5
                )
        );
        // Monkey 2
        listOfMonkeys.add(
                new Monkey(
                        "86, 92, 71",
                        "+",
                        7L,
                        2L,
                        0,
                        4
                )
        );
        // Monkey 3
        listOfMonkeys.add(
                new Monkey(
                        "96, 90, 99, 76, 79, 85, 98, 61",
                        "+",
                        4L,
                        11L,
                        7,
                        6
                )
        );
        // Monkey 4
        listOfMonkeys.add(
                new Monkey(
                        "60, 83, 68, 64, 73",
                        "*",
                        19L,
                        17L,
                        1,
                        0
                )
        );
        // Monkey 5
        listOfMonkeys.add(
                new Monkey(
                        "96, 52, 52, 94, 76, 51, 57",
                        "+",
                        3L,
                        5L,
                        7,
                        3
                )
        );
        // Monkey 6
        listOfMonkeys.add(
                new Monkey(
                        "75",
                        "+",
                        5L,
                        13L,
                        4,
                        2
                )
        );
        // Monkey 7
        listOfMonkeys.add(
                new Monkey(
                        "83, 75",
                        "+",
                        1L,
                        19L,
                        2,
                        6
                )
        );

        // sample input
//        listOfMonkeys.add(
//                new Monkey(
//                        "79, 98",
//                        "*",
//                        19L,
//                        23L,
//                        2,
//                        3
//                )
//        );
//        listOfMonkeys.add(
//                new Monkey(
//                        "54, 65, 75, 74",
//                        "+",
//                        6L,
//                        19L,
//                        2,
//                        0
//                )
//        );
//        listOfMonkeys.add(
//                new Monkey(
//                        "79, 60, 97",
//                        "*",
//                        -1L,
//                        13L,
//                        1,
//                        3
//                )
//        );
//        listOfMonkeys.add(
//                new Monkey(
//                        "74",
//                        "+",
//                        3L,
//                        17L,
//                        0,
//                        1
//                )
//        );
    }
}