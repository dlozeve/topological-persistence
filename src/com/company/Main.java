package com.company;

import java.io.FileNotFoundException;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String filtrationName = "filtration_A";
        Vector<Simplex> F = new ReadFiltration().readFiltration(filtrationName + ".txt");
        //System.out.println(F.toString());
        long startTime = System.nanoTime();
        BoundaryMatrix m = new BoundaryMatrix(F);
        long creationTime = System.nanoTime() - startTime;
        m.reduction();
        long reductionTime = System.nanoTime() - creationTime - startTime;
        //System.out.println(m.barcode());
        m.barcodeToFile(filtrationName + "_barcode.txt");
        System.out.println("Boundary matrix creation time: " + creationTime / 1000000. + " ms");
        System.out.println("Reduction time: " + reductionTime / 1000000. + " ms");
    }
}
