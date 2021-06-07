import com.polytech.Optimisator;
import com.polytech.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptimisatorTest {

    private Optimisator optimisator;

    @BeforeEach
    public void init() {
        optimisator = new Optimisator();
    }

    @Nested
    class getLowerBound {

        @Test
        void souldReturn0IfListIsEmpty() {
            //Given

            //When
            int result = optimisator.getLowerBound();

            //Then
            assertEquals(result, 0);
        }

        @Test
        void souldReturn1IfItemsSizeIsLowerThanBinSize() {
            //Given
            int binSize = 2;
            Item item = new Item(1);

            optimisator.setBinSize(binSize);
            optimisator.addItem(item);

            //When
            int result = optimisator.getLowerBound();

            //Then
            assertEquals(result, 1);
        }

        @Test
        void souldReturn1IfItemsSizeEqualsBinSize() {
            //Given
            int binSize = 2;
            Item item = new Item(2);

            optimisator.setBinSize(binSize);
            optimisator.addItem(item);

            //When
            int result = optimisator.getLowerBound();

            //Then
            assertEquals(result, 1);
        }

        @Test
        void souldReturn2IfItemsSizeIsBiggerThanBinSizeAndLowerThan2BinSize() {
            //Given
            int binSize = 2;
            Item item = new Item(3);

            optimisator.setBinSize(binSize);
            optimisator.addItem(item);

            //When
            int result = optimisator.getLowerBound();

            //Then
            assertEquals(result, 2);
        }
    }

}