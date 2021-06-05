import com.polytech.Bin;
import com.polytech.Item;
import com.polytech.Solution;
import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
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
    class putItemInAnotherBin {
        
        @Test
        void shouldReturnSolutionWith2Bin() throws CloneNotSupportedException {
            //Given
            Solution solution = createFakeSolutionWithItemsAndOneSwitchPossible();

            //When
            Solution neighbour = solution.putItemInAnotherBin();

            //Then
            assertEquals(solution.getNbBins(), neighbour.getNbBins());
            //le deuxième item de la bin 1 doit maintenant se trouver dans la bin 2
            assertTrue(neighbour.getListBin().get(1).getListItems().contains(solution.getListBin().get(0).getListItems().get(1)));
        }

        @Test
        void shouldReturnSolutionWith3Bins() throws CloneNotSupportedException {
            //Given
            Solution solution = createFakeSolutionWithItemsAndNoSwitchPossible();

            //When
            Solution neighbour = solution.putItemInAnotherBin();

            //Then
            assertEquals(3, neighbour.getNbBins());
        }
    }

    @Nested
    class switchTwoItems {

        @Test
        void shouldReturnNeighbourOfTheSolution() throws CloneNotSupportedException {
            //Given
            Solution solution = createFakeSolutionWithItems();

            //Item that should be exchange
            Item itemBin2 = solution.getListBin().get(1).getListItems().get(1);

            //When
            Solution neighbour = solution.switchTwoItems();

            //Then
            assertEquals(itemBin2.getSize(), 1);
            assertEquals(true, neighbour.getListBin().get(2).getListItems().contains(itemBin2));
        }


        @Test
        void shouldAddABin() throws CloneNotSupportedException {
            //Given
            Solution solution = createFakeSolutionWithItemsAndNoSwitchPossible();

            //When
            Solution neighbour = solution.switchTwoItems();

            //Then
            assertEquals(solution.getNbBins() + 1, neighbour.getNbBins());
        }

    }

    @Nested
    class calculateFitness {

        @Test
        void shouldReturn68() {

            Bin bin1 = new Bin(10);
            bin1.addItem(new Item(5));
            bin1.addItem(new Item(3));
            Bin bin2 = new Bin(10);
            bin2.addItem(new Item(2));

            Solution solution = new Solution();
            solution.addBin(bin1);
            solution.addBin(bin2);

            Integer fitness = solution.updateFitnessAndGet();

            assertEquals(68, fitness);

        }
    }

    private Solution createFakeSolution(int nbBins) {
        Solution solution = new Solution();
        solution.setNbBins(nbBins);
        return solution;
    }

    private Solution createFakeSolutionWithItems()
    {
        Solution solution = new Solution();
        Bin bin1 = new Bin(3);
        Bin bin2 = new Bin(3);
        Bin bin3 = new Bin(3);

        bin1.addItem(new Item(3));
        bin2.addItem(new Item(2));
        bin2.addItem(new Item(1));
        bin3.addItem(new Item(1));
        bin3.addItem(new Item(1));
        bin3.addItem(new Item(1));

        solution.addBin(bin1);
        solution.addBin(bin2);
        solution.addBin(bin3);

        return solution;
    }

    private Solution createFakeSolutionWithItemsAndOneSwitchPossible()
    {
        Solution solution = new Solution();
        Bin bin1 = new Bin(3);
        Bin bin2 = new Bin(3);

        bin1.addItem(new Item(2));
        bin1.addItem(new Item(1));
        bin2.addItem(new Item(2));

        solution.addBin(bin1);
        solution.addBin(bin2);

        return solution;
    }

    private Solution createFakeSolutionWithItemsAndNoSwitchPossible()
    {
        Solution solution = new Solution();
        Bin bin1 = new Bin(5);
        Bin bin2 = new Bin(5);

        bin1.addItem(new Item(3));
        bin1.addItem(new Item(2));
        bin2.addItem(new Item(4));
        bin2.addItem(new Item(1));

        solution.addBin(bin1);
        solution.addBin(bin2);

        return solution;
    }

}