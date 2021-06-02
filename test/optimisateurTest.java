import com.polytech.optimisateur;
import com.polytech.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class optimisateurTest {

    private optimisateur optimisateur;

    @BeforeEach
    public void init() {
        optimisateur = new optimisateur();
    }

    @Nested
    class getLowerBound {

        @Test
        void souldReturn0IfListIsEmpty() {
            //Given

            //When
            int result = optimisateur.getLowerBound();

            //Then
            assertEquals(result, 0);
        }

        @Test
        void souldReturn1IfItemsSizeIsLowerThanBinSize() {
            //Given
            int binSize = 2;
            Item item = new Item(1);

            optimisateur.setBinSize(binSize);
            optimisateur.addItem(item);

            //When
            int result = optimisateur.getLowerBound();

            //Then
            assertEquals(result, 1);
        }

        @Test
        void souldReturn1IfItemsSizeEqualsBinSize() {
            //Given
            int binSize = 2;
            Item item = new Item(2);

            optimisateur.setBinSize(binSize);
            optimisateur.addItem(item);

            //When
            int result = optimisateur.getLowerBound();

            //Then
            assertEquals(result, 1);
        }

        @Test
        void souldReturn2IfItemsSizeIsBiggerThanBinSizeAndLowerThan2BinSize() {
            //Given
            int binSize = 2;
            Item item = new Item(3);

            optimisateur.setBinSize(binSize);
            optimisateur.addItem(item);

            //When
            int result = optimisateur.getLowerBound();

            //Then
            assertEquals(result, 2);
        }
    }

}