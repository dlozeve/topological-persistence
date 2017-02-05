from itertools import combinations

if __name__ == "__main__":
    for d in range(11):
        f = open("ball_"+str(d)+".txt", 'w')
        n = d + 2
        for i in range(1, n+1):
            simplices = set(combinations(range(n), i))
            for x in simplices:
                f.write(str(i-1) + " " + str(i-1) + " ")
                for j in range(len(x)):
                    f.write(str(x[j]) + " ")
                f.write("\n")

