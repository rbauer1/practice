def showLists():
    print(
        """List syntax is:\nlistName[beginningIndex:endingIndex:increment]
        \nWhere beginningIndex defaults to 0, endingIndex defaults to
        len(listName) and increment defaults to 1""")
    l = ("0123456789")
    print("List given", l)
    print("l", l)
    print("l[:]", l[:])
    print("l[::]", l[::])
    print("l[0]", l[0])
    print("l[-1]", l[-1])
    print("l[4:]", l[4:])
    print("l[:4]", l[:4])
    print("l[4:8]", l[4:8])
    print("l[4:8:2]", l[4:8:2])
    print("l[8:4]", l[8:4])
    print("l[8:4:]", l[8:4:])
    print("l[8:4:-1]", l[8:4:-1])
    print("l[8:4:-2]", l[8:4:-2])
    print("l[:4:-1]", l[:4:-1])
    print("l[4:-1]", l[4:-1])
    print("l[:-1]", l[:-1])
    print("l[::-1]", l[::-1])

showLists()
