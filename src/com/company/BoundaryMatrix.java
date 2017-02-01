package com.company;

import java.util.Collections;
import java.util.Vector;
import java.util.TreeSet;
import java.util.HashMap;


public class BoundaryMatrix {

    boolean[][] boundaryMatrix;

    BoundaryMatrix(Vector<Simplex> F) {
        int n = F.size();
        // Sort the Vector of simplices, according to their function value.
        Collections.sort(F);

        this.boundaryMatrix = new boolean[n][n];

        // We create a HashMap to store and retrieve easily
        // the index of a given simplex in the simplicial complex.
        HashMap<TreeSet<Integer>, Integer> simplexIndices = new HashMap<>();
        int i = 0;
        for (Simplex s: F) {
            simplexIndices.put(s.vert, i);
            i++;
        }

        // We iterate through every simplex
        for (Simplex s: F) {
            // If the simplex is a single vertex, its column will be zero, w edon't need to do anything.
            if (s.dim > 0) {
                i = simplexIndices.get(s.vert);
                // For each vertex in the simplex, we remove it and put a true value
                // in the row of the corresponding subset. This way, we get a true in
                // position (i,j) iff the j-th simplex is a boundary of the i-th simplex.
                for (int k : s.vert) {
                    TreeSet<Integer> subset = new TreeSet<>(s.vert);
                    subset.remove(k);
                    int j = simplexIndices.get(subset);
                    boundaryMatrix[i][j] = true;
                }
            }
        }
    }

}
