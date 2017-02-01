package com.company;

import java.io.FileNotFoundException;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Vector<Simplex> F = new ReadFiltration().readFiltration("example1.txt");
        System.out.println(F.toString());
        BoundaryMatrix m = new BoundaryMatrix(F);
        m.reduction();
    }
}
