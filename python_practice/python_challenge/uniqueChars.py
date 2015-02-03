from os import chdir
from string import ascii_letters as ascii


def findUniqueChars():
    chdir(
        '/home/riley/workspace/Practice/python_practice/python_challenge/data')
    f = open('uniqueChars_data')
    for line in f:
        for char in line:
            if char in ascii:
                print(char, end="", flush=True)
    print()
    f.close()
    #below will print all ascii chars in the file, but they are out of order
    #uniqueChars = set(f.read())
    #print(''.join([c for c in uniqueChars if c in ascii]))

findUniqueChars()
