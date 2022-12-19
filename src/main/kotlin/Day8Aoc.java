import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day8Aoc {
    static List<List<Integer>> input = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        processInput();
        day2();
    }


    public static void processInput() throws Exception{
        File file = new File(
                "/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-8.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            var x = st.toCharArray();
            List<Integer> ls = new ArrayList<>();
            for (char c: x) {
                ls.add(Integer.parseInt(String.valueOf(c)));
            }
            input.add(ls);
        }
    }

    public static void day2() {
        int width = input.get(0).size();
        int height = input.size();
        int[][] scores = new int[width][height];
        // I need to update highest for row i and column j

        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {
                int left = 0;
                int right = 0;
                int top = 0;
                int bottom = 0;
                int current = input.get(row).get(col);
                // check left;
                for (int k = col - 1; k >= 0; k--) {
                    left++;
                    if (input.get(row).get(k) >= current) {
                        break;
                    }
                }

                // check right;
                for (int k = col + 1; k < width; k++) {
                    right++;
                    if (input.get(row).get(k) >= current) {
                        break;
                    }
                }

                // check top;
                for (int k = row -1; k >= 0; k--) {
                    top++;
                    if (input.get(k).get(col) >= current) {
                        break;
                    }
                }
                // check bottom
                for (int k = row + 1; k < height; k++) {
                    bottom++;
                    if (input.get(k).get(col) >= current) {
                        break;
                    }
                }
                scores[row][col] = left * right * bottom * top;
            }
        }
        int currentMax = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                currentMax = Math.max(currentMax, scores[i][j]);
            }
        }
        System.out.println("Answer is: " + currentMax);
    }

    public static void day1() {
        int width = input.get(0).size();
        int height = input.size();
        boolean[][] visible = new boolean[width][height];
        // I need to update highest for row i and column j

        for (int i = 0; i < width; i++) {
            int minHeight = -1;
            for (int j = 0; j < height; j++) {
                if (input.get(i).get(j) > minHeight) {
                    visible[i][j] = true;
                    minHeight = input.get(i).get(j);
                }
            }
            minHeight = -1;
            for (int j = height - 1; j >= 0; j--) {
                if (input.get(i).get(j) > minHeight) {
                    visible[i][j] = true;
                    minHeight = input.get(i).get(j);
                }
            }
        }
        for (int j = 0; j < height; j++) {
            int minHeight = -1;
            for (int i = width - 1; i >= 0; i--) {
                if (input.get(i).get(j) > minHeight) {
                    visible[i][j] = true;
                    minHeight = input.get(i).get(j);
                }
            }
            minHeight = -1;
            for (int i = 0; i < width; i++) {
                if (input.get(i).get(j) > minHeight) {
                    visible[i][j] = true;
                    minHeight = input.get(i).get(j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (visible[i][j]) {
                    ans++;
                }
            }
        }
        System.out.println("Answer is: " + ans);
    }
}
