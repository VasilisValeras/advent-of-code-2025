package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {
     public static void main(String[] args) {
        ArrayList<String> directions = new ArrayList<>();
        ArrayList<Integer> steps = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                directions.add(line.substring(0, 1));
                steps.add(Integer.valueOf(line.substring(1, line.length())));
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        int part1 = solvePart1(directions, steps);
        int part2 = solvePart2(directions, steps);

        System.out.println(part1);
        System.out.println(part2);
    }

    private static int solvePart1(ArrayList<String> directions, ArrayList<Integer> steps) {
        int dialPointer = 50;
        int numOfZeros = 0;

        int numOfInstructions = directions.size();

        for (int i = 0; i < numOfInstructions; i++) {
            if ((directions.get(i)).equals("L")) {
                dialPointer = Math.floorMod(dialPointer - steps.get(i), 100);
            } else {
                dialPointer = Math.floorMod(dialPointer + steps.get(i), 100);
            }

            if (dialPointer == 0) {
                numOfZeros++;
            }
        }

        return numOfZeros;
    }

    private static int solvePart2(ArrayList<String> directions, ArrayList<Integer> steps) {
        int dialPointer = 50;
        int numOfZeros = 0;

        int numOfInstructions = directions.size();

        int previousDialPointer = 0;
        int repetitions = 0;
        int remaining = 0;

        for (int i = 0; i < numOfInstructions; i++) {
            previousDialPointer = dialPointer;
            if (steps.get(i) < 100) {
                if ((directions.get(i)).equals("L")) {
                    dialPointer = Math.floorMod(dialPointer - steps.get(i), 100);
                    if (dialPointer < previousDialPointer) {
                        if (dialPointer == 0) {
                            numOfZeros++;
                        }
                    } else {
                        if (previousDialPointer != 0) {
                            numOfZeros++;
                        }
                    }
                } else {
                    dialPointer = Math.floorMod(dialPointer + steps.get(i), 100);
                    if (dialPointer <= previousDialPointer) {
                        numOfZeros++;
                    }
                }
            } else {
                repetitions = steps.get(i) / 100;
                numOfZeros += repetitions;
                remaining = steps.get(i) % 100;

                if (remaining != 0) {
                    if ((directions.get(i)).equals("L")) {
                        dialPointer = Math.floorMod(dialPointer - remaining, 100);
                        if (dialPointer < previousDialPointer) {
                            if (dialPointer == 0) {
                                numOfZeros++;
                            }
                        } else {
                            if (previousDialPointer != 0) {
                                numOfZeros++;
                            }
                        }
                    } else {
                        dialPointer = Math.floorMod(dialPointer + remaining, 100);
                        if (dialPointer <= previousDialPointer) {
                            numOfZeros++;
                        }
                    }
                }
            }
        }

        return numOfZeros;
    }
}
