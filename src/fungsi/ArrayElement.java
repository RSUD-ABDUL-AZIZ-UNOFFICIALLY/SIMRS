/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fungsi;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author hp
 */
public class ArrayElement {
    public static Map<String, Integer> countElements(Stack<String> stack) {
        Map<String, Integer> counts = new HashMap<>();

        // Count the occurrences of each element in the stack
        for (String element : stack) {
            counts.put(element, counts.getOrDefault(element, 0) + 1);
        }

        return counts;
    }
}
