package algorithm;

import com.polytech.Solution;
import com.polytech.algorithm.ProgrammationLineaire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammationLineaireTest {

    @Test
    void test() {

//        System.loadLibrary("jniortools");


        ProgrammationLineaire programmationLineaire = new ProgrammationLineaire();
        Solution solution = programmationLineaire.generateSolution(null, -1);
    }


}