def collatz(x):
    while x != 1:
        if x % 2 == 0:
            x=x//2
            if x != 1:
                print(x)
        else:
            x=(x*3)+1
            if x != 1:
                print(x)
    return(x)
print("test")
x=input()
x=int(x)
print(collatz(x))