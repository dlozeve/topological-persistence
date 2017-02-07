# TD5: Topological Persistence

Authors: Joseph Budin and Dimitri Lozeve

## Where to find the source code

All source code (in Java) is in the `src/` directory. Input
filtrations are at the repository root, along with some output
barcodes. Some additional programs (for generating filtrations and
visualizing barcodes), in Python and R, are in the `python/` folder.

All the source code is available at
https://github.com/dlozeve/topological-persistence.

## How to run the code

You can launch it from your favorite IDE. The `Main` class is in the
`src/` folder, along with the rest of the program. You can also change
the input filtration by modifying the `filtrationName` variable in the
`main` method.

Note that the filtrations A, B, C, and D are too heavy for git and are
not included in the repository. You can add them to the root, along
with other filtrations such as `torus.txt`, `klein.txt`,
`python/sphere_7.txt`, etc.

## Files included

`*_barcode.txt` files are barcodes, generated as outputs by the
program.

All other `*.txt` files are filtrations. The name describes the
topological space represented, along with its dimension for spheres
and balls.

`ballfiltrations.py` and `spherefiltrations.py` are Python 3 scripts
that can be used to generate the filtrations for the spheres and the
balls, from dimension 1 to 10. You can run them with `python
spherefiltrations.py` for example.

Java files in `src/com/company/` are the main
program. `BoundaryMatrix.java` and `ReadFiltration.java` are classes
used by the main program in `Main.java`.

Finally, `report.pdf` is the report containing the answers to the
theoretical questions.




