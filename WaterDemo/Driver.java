import java.util.*;

public class Driver {
    public static void main(String[] args) {
        int[] heights = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Max Area of " + Arrays.toString(heights) + " is " + maxArea(heights));
        System.out.println(maxAreaSolution(heights));
    }

    public static long maxArea(int[] heights) {
        Map<Integer, List<Integer>> heightMap = new HashMap<>();
        for (int i = 0; i < heights.length; i++) {
            var indexList = heightMap.getOrDefault(heights[i], new ArrayList<>());
            indexList.add(i);
            heightMap.put(heights[i], indexList);
        }
        var entryList = heightMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).toList();

        entryList.forEach(System.out::println);

        int entryIndex = 0;
        long maxArea = 0;
        for (var entry : entryList) {
            System.out.println("Current Entry: " + entry);
            var indexes = entry.getValue();
            int height = entry.getKey();
            if (indexes.size() > 1) {
                System.out.println("Duplicate Entry!!");
                final int area = height * Math.abs(indexes.get(indexes.size() - 1) - indexes.get(0));

                if (area > maxArea) {
                    maxArea = area;
                }
            }

            for (int i = entryIndex + 1; i < entryList.size(); i++) {
                var indexList = entryList.get(i).getValue();

                for (int index : entry.getValue()) {
                    final int area = height * Math.abs(indexList.get(indexList.size() - 1) - index);
                    if (area > maxArea)
                        maxArea = area;
                }
            }
            entryIndex++;
        }
        return maxArea;
    }

    public static int maxAreaSolution(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        int area;
        int conHeight;
        int width;
        while (left != right){
            conHeight = Math.min(height[left],height[right]);
            width = right - left;

            area = conHeight * width;

            if (max < area){
                max = area;
            }
            if (height[left] < height[right]){
                left++;
            }else {
                right --;
            }
        }
        return max;
    }

}
