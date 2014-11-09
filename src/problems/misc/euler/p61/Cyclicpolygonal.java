package problems.misc.euler.p61;
 
import java.util.*;
 
public class Cyclicpolygonal {
    final static ArrayList<Integer> triangle = new ArrayList<Integer>();
    final static ArrayList<Integer> square = new ArrayList<Integer>();
    final static ArrayList<Integer> pentagonal = new ArrayList<Integer>();
    final static ArrayList<Integer> hexagonal = new ArrayList<Integer>();
    final static ArrayList<Integer> heptagonal = new ArrayList<Integer>();
    final static ArrayList<Integer> octagonal = new ArrayList<Integer>();
    static ArrayList<ArrayList<Integer>> polygons = new ArrayList<ArrayList<Integer>>();
 
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        for (int i = 1; i < 1000; i++) {
            if ((i * (i + 1) / 2) / 1000 != 0 && (i * (i + 1) / 2) / 10000 == 0) {
                if ((i * (i + 1) / 2) % 100 > 10)
                    triangle.add((i * (i + 1) / 2));
            }
            if (i * (i) > 1000 && i * (i) < 10000) {
                if ((i * i) % 100 > 10)
                    square.add(i * (i));
            }
            if ((i * (3 * i - 1) / 2) / 1000 != 0
                    && (i * (3 * i - 1) / 2) / 10000 == 0) {
                if (((i * (3 * i - 1) / 2)) % 100 > 10)
                    pentagonal.add((i * (3 * i - 1) / 2));
            }
            if ((i * (2 * i - 1)) / 1000 != 0 && (i * (2 * i - 1)) / 10000 == 0) {
                if ((i * (2 * i - 1)) % 100 > 10)
                    hexagonal.add((i * (2 * i - 1)));
            }
            if ((i * (5 * i - 3) / 2) / 1000 != 0
                    && (i * (5 * i - 3) / 2) / 10000 == 0) {
                if ((i * (5 * i - 3) / 2) % 100 > 10)
                    heptagonal.add((i * (5 * i - 3) / 2));
            }
            if ((i * (3 * i - 2)) / 1000 != 0 && (i * (3 * i - 2)) / 10000 == 0) {
                if (((i * (3 * i - 2))) % 100 > 10)
                    octagonal.add(i * (3 * i - 2));
            }
 
        }
        polygons.add(triangle);
        polygons.add(square);
        polygons.add(pentagonal);
        polygons.add(hexagonal);
        polygons.add(heptagonal);
        polygons.add(octagonal);
        int x = 6;
        boolean[] string = new boolean[x];
 
        for (int i = 0; i < triangle.size(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            string[0] = true;
            list.add(triangle.get(i));
            recursive(list, string);
        }
        System.out.println(System.currentTimeMillis()-l);
    }
 
    public static void recursive(ArrayList<Integer> list, boolean[] string) {
        main: for (int i = 1; i < string.length; i++) {
            if (!string[i]) {
                for (int a : polygons.get(i)) {
                    ArrayList<Integer> copylist = new ArrayList<Integer>();
                    copylist.addAll(list);
                    boolean[] copystring = string.clone();
                    if (list.get(list.size() - 1) % 100 == a / 100) {
 
                        if (list.size() == string.length - 1
                                && list.get(0) / 100 == a % 100) {
                            list.add(a);
                            string[i] = true;
                            System.out.println(list);
                            int w = 0;
                            for (int q : list) {
                                w += q;
                            }
                            System.out.println(w);
                            break main;
                        } else {
                            copystring[i] = true;
                            copylist.add(a);
                            recursive(copylist, copystring);
                        }
                    }
 
                }
 
            }
 
        }
    }
 
} 