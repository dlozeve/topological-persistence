package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;



class Simplex implements Comparable<Simplex> {
	float val;
	int dim;
	TreeSet<Integer> vert;

	Simplex(Scanner sc){
		val = sc.nextFloat();
		dim = sc.nextInt();
		vert = new TreeSet<Integer>();
		for (int i=0; i<=dim; i++)
			vert.add(sc.nextInt());
	}

	public String toString(){
		return "{val="+val+"; dim="+dim+"; "+vert+"}\n";
	}

    @Override
    public int compareTo(Simplex s) {
	    // Sort according to val
	    if (this.val < s.val) {
	        return -1;
        }
        else if (this.val > s.val) {
	        return 1;
        }
        else {
	        // If the two values are equal, we sort according to the dimension.
	        if (this.dim < s.dim) {
	            return -1;
            }
            else if (this.dim > s.dim) {
	            return 1;
            }
            else {
	            return 0;
            }
        }
    }
}

public class ReadFiltration {

	static Vector<Simplex> readFiltration (String filename) throws FileNotFoundException {
		Vector<Simplex> F = new Vector<>();
		Scanner sc = new Scanner(new File(filename));
		sc.useLocale(Locale.US);
		while (sc.hasNext())
			F.add(new Simplex(sc));
		sc.close();
		return F;
	}

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Syntax: java ReadFiltration <filename>");
			System.exit(0);
		}
			
		System.out.println(readFiltration(args[0]));
	}
}
