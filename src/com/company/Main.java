package com.company;

import java.io.FileNotFoundException;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Vector<Simplex> F = new ReadFiltration().readFiltration("filtration_A.txt");
        //System.out.println(F.toString());
        BoundaryMatrix m = new BoundaryMatrix(F);
        m.reduction();
        m.barcodeToFile("test.txt");
    }
}
