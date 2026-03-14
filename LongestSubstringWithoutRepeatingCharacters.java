import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Finds the length of the longest substring without repeating characters.
     * Uses sliding window approach with HashMap for O(n) time complexity.
     * 
     * @param s Input string
     * @return Length of longest substring without repeating characters
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // Map to store character and its index
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0; // left pointer of sliding window
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If character is already in map and its index is >= left pointer,
            // move left pointer to the position after the previous occurrence
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                left = charIndexMap.get(currentChar) + 1;
            }
            
            // Update the character's index in the map
            charIndexMap.put(currentChar, right);
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Alternative approach using array for ASCII characters (faster for ASCII strings).
     * Time: O(n), Space: O(1) - constant space for ASCII array
     */
    public int lengthOfLongestSubstringASCII(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] charIndex = new int[128]; // ASCII characters
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            if (charIndex[currentChar] > left) {
                left = charIndex[currentChar];
            }
            
            charIndex[currentChar] = right + 1; // Store right + 1 to avoid default 0
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Test method to verify the implementation
     */
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();
        
        // Test cases
        String[] testCases = {
            "abcabcbb",    // Expected: 3 ("abc")
            "bbbbb",       // Expected: 1 ("b")
            "pwwkew",      // Expected: 3 ("wke")
            "",            // Expected: 0
            "au",          // Expected: 2 ("au")
            "dvdf",        // Expected: 3 ("vdf")
            "abba",        // Expected: 2 ("ab" or "ba")
        };
        
        int[] expected = {3, 1, 3, 0, 2, 3, 2};
        
        System.out.println("Testing Longest Substring Without Repeating Characters:");
        System.out.println("=====================================================");
        
        for (int i = 0; i < testCases.length; i++) {
            String test = testCases[i];
            int result1 = solution.lengthOfLongestSubstring(test);
            int result2 = solution.lengthOfLongestSubstringASCII(test);
            
            System.out.printf("Test %d: \"%s\"\n", i + 1, test);
            System.out.printf("  HashMap approach: %d\n", result1);
            System.out.printf("  ASCII array approach: %d\n", result2);
            System.out.printf("  Expected: %d\n", expected[i]);
            System.out.printf("  Result: %s\n\n", result1 == expected[i] ? "PASS" : "FAIL");
        }
    }
}
