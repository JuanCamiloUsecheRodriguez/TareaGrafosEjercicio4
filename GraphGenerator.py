from random import randint

ORDER = 6

with open("Problema4.in", "w+") as f:
    n = randint(2, ORDER)
    nEdges = randint(1, n * (n - 1) // 2)
    print(nEdges)
    f.write(f"{n}\n")
    for i in range(1, nEdges):
        v1 = randint(0, n-1)
        v2 = randint(0, n-1)
        while(v1 == v2):
            v1 = randint(0, n-1)
            v2 = randint(0, n-1)
        cost = randint(1, 10) #los vuelos pueden costar entre 1 y 10 Millones
        f.write(f"{v1} {v2} {cost}\n")
        f.write(f"{v2} {v1} {cost}\n")        
    f.write("0")