import com.polytech.Bin;
import com.polytech.Item;
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

    @Nested
    class getNeighbour {

        @Test
        void shouldReturnNeighbourOfTheSolution() {
            //Given
            Solution solution = createFakeSolutionWithItems();

            //Item that should be exchange
            Item itemBin2 = solution.getListBin().get(1).getListItems().get(1);

            //When
            Solution neighbour = solution.getNeighbour();

            //Then
            assertEquals(itemBin2.getSize(), 1);
//            assertNotEquals(solution.getListBin(), neighbour.getListBin());
            assertEquals(true, neighbour.getListBin().get(2).getListItems().contains(itemBin2));
        }

    }

    private Solution createFakeSolution(int nbBins) {
        Solution solution = new Solution();
        solution.setNbBins(nbBins);
        return solution;
    }

    private Solution createFakeSolutionWithItems()
    {
        Solution solution = createFakeSolution(3);
        Bin bin1 = new Bin(3);
        Bin bin2 = new Bin(3);
        Bin bin3 = new Bin(3);

        bin1.addItem(new Item(3));
        bin2.addItem(new Item(2));
        bin2.addItem(new Item(1));
        bin3.addItem(new Item(1));
        bin3.addItem(new Item(1));
        bin3.addItem(new Item(1));

        solution.getListBin().add(bin1);
        solution.getListBin().add(bin2);
        solution.getListBin().add(bin3);

        return solution;
    }

}