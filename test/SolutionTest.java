import com.polytech.Solution;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Nested
    class getBestSolutionFromList {

        @Test
        void shouldReturnNullIfListIsNull() {
            //Given
            ArrayList<Solution> listSolution = null;

            //When
            Solution result = Solution.getBestSolutionFromList(listSolution);

            //Then
            assertNull(result);
        }

        @Test
        void shouldReturnNullIfListIsEmpty() {
            //Given
            ArrayList<Solution> listSolution = new ArrayList<>();

            //When
            Solution result = Solution.getBestSolutionFromList(listSolution);

            //Then
            assertNull(result);
        }

        @Test
        void shouldReturnSolution3() {
            //Given
            ArrayList<Solution> listSolution = new ArrayList<>();
            Solution solution1 = createFakeSolution(2);
            Solution solution2 = createFakeSolution(6);
            Solution solution3 = createFakeSolution(1);
            listSolution.add(solution1);
            listSolution.add(solution2);
            listSolution.add(solution3);

            //When
            Solution result = Solution.getBestSolutionFromList(listSolution);

            //Then
            assertNotNull(result);
            assertEquals(solution3, result);
        }
    }


    private Solution createFakeSolution(int nbBins) {
        Solution solution = new Solution();
        solution.setNbBins(nbBins);
        return solution;
    }

}