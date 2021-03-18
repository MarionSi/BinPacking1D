package algorithm;

import com.polytech.Item;
import com.polytech.Solution;
import com.polytech.algorithm.FirstFitDecreasing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class FirstFitDecreasingTest {

    private FirstFitDecreasing algorithm;

    @BeforeEach
    public void init() {
        algorithm = new FirstFitDecreasing();
    }
    
    @Nested
    class generateSolution {

        @Test
        void souldReturnNullIfBinSizeIsSmallerThan0() {
            //Given
            LinkedList<Item> listItems = new LinkedList<Item>();
            int binSize = -3;

            //When
            Solution solution = algorithm.generateSolution(listItems, binSize);

            //Then
            assertNull(solution);
        }

        @Test
        void souldReturnNullIfBinSizeIs0() {
            //Given
            LinkedList<Item> listItems = new LinkedList<Item>();
            int binSize = 0;

            //When
            Solution solution = algorithm.generateSolution(listItems, binSize);

            //Then
            assertNull(solution);
        }

        @Test
        void souldReturnNullIfListItemsIsNull() {
            //Given
            LinkedList<Item> listItems = null;
            int binSize = 4;

            //When
            Solution solution = algorithm.generateSolution(listItems, binSize);

            //Then
            assertNull(solution);
        }

        @Test
        void souldReturnSolutionWith2Bin() {
            //Given
            LinkedList<Item> listItems = new LinkedList<>();
            listItems.add(new Item(1));
            listItems.add(new Item(3));
            listItems.add(new Item(2));

            int binSize = 4;

            //When
            Solution solution = algorithm.generateSolution(listItems, binSize);

            //Then
            assertNotNull(solution);
            assertNotNull(solution.getListBin());
            assertEquals(solution.getNbBins(), 2);
            assertEquals(solution.getListBin().get(0).getListItems().size(), 2);
            assertEquals(solution.getListBin().get(0).getEmptySize(), 0);
            assertEquals(solution.getListBin().get(1).getListItems().size(), 1);
            assertEquals(solution.getListBin().get(1).getEmptySize(), 2);

        }
    }

}