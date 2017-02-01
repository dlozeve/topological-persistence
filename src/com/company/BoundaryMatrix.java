package com.company;

import java.util.Collections;
import java.util.Vector;
import java.util.TreeSet;
import java.util.HashMap;


public class BoundaryMatrix {

    private int n;
    private Vector<Simplex> G;
    boolean[][] boundaryMatrix;
    int[] lowIndices;
    int[] lowColumn;

    BoundaryMatrix(Vector<Simplex> F) {
        this.n = F.size();
        // Sort the Vector of simplices, according to their function value.
        Collections.sort(F);
        this.G = F;

        // These arrays will be useful for reduction
        // lowIndices stores the row index of the lowest non-zero entry for each column.
        this.lowIndices = new int[this.n];
        // lowColumn[k] stores the index of the column which has its lowest entry at row k.
        this.lowColumn = new int[this.n];
        // We initialize both of these vectors to -1.
        for (int i = 0; i < this.n; i++) {
            lowIndices[i] = -1;
            lowColumn[i] = -1;
        }


        this.boundaryMatrix = new boolean[n][n];

        // We create a HashMap to store and retrieve easily
        // the index of a given simplex in the simplicial complex.
        HashMap<TreeSet<Integer>, Integer> simplexIndices = new HashMap<>();
        int i = 0;
        for (Simplex s : F) {
            simplexIndices.put(s.vert, i);
            i++;
        }

        // We iterate through every simplex
        for (Simplex s : F) {
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
                    boundaryMatrix[j][i] = true;
                }
            }
        }
    }

    // reductColumn takes the indices of two columns i < j and substracts column i from column j.
    // Since we work in Z/2Z, substracting is equivalent to XOR.
    private void reductColumns(int i, int j) {
        for (int k = 0; k < this.n; k++) {
            boundaryMatrix[k][j] = boundaryMatrix[k][i] ^ boundaryMatrix[k][j];
        }
    }

    // Returns the row number of the lowest non-sero entry in column j, or -1 if the column is empty.
    private int getLow(int j) {
        int res = -1;
        for (int k = 0; k < this.n; k++) {
            if (boundaryMatrix[k][j]) {
                res = k;
            }
        }
        return res;
    }

    void reduction() {
        // Reminder:
        // lowIndices stores the row index of the lowest non-zero entry for each column.
        // lowColumn[k] stores the index of the column which has its lowest entry at row k.

        // We loop through all columns
        for (int j = 0; j < this.n; j++) {
            int low = getLow(j);
            // While the column is not empty and there exists a previous column with the same pivot:
            while(low != -1 && lowColumn[low] != -1) {
                // We update the j-th column
                int i = lowColumn[low];
                reductColumns(i, j);
                // We compute the new pivot
                low = getLow(j);
            }
            // Final pivot
            lowIndices[j] = low;
            // If the column is non-empty, we complet lowColumn
            if (low != -1) {
                lowColumn[low] = j;
            }
        }
    }

    String barcode() {
        String res = "";
        // We loop through all columns
        for (int j = 0; j < this.n; j++) {
            // We retrieve the index of the pivot
            int i = lowIndices[j];
            // If the column has a pivot, i.e. it is not empty:
            if (i > -1) {
                // We get the dimension and the bounds of the interval
                float valInf = this.G.get(i).val;
                float valSup = this.G.get(j).val;
                int dim = this.G.get(i).dim;
                res += dim + " " + valInf + " " + valSup + "\n";
            }
            else {
                // If the column is empty and the corresponding row has no pivot:
                if (lowColumn[j] == -1) {
                    // We add the interval [j, inf)
                    int dim = this.G.get(j).dim;
                    float valInf = this.G.get(j).val;
                    res += dim + " " + valInf + " inf\n";
                }
            }
        }
        return res;
    }

}
