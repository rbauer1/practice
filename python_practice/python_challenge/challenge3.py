from os import chdir
import re


def findSequence():
    chdir(
        '/home/riley/workspace/Practice/python_practice/python_challenge/data')
    regex = re.compile("[^A-Z][A-Z]{3}[a-z][A-Z]{3}[^A-Z]")
    with open('challenge3_data') as f:
        count = 0
        last3Lines = ["", "", ""]
        lineToLookAt = ""
        next3Lines = ["", "", ""]
        for line in f:
            #there is a problem here currently. the way it is set up, it will not check the very first valid line. right?
            #should change the line buffers to be FIFO, meaning we look at the three values, push in a new one, and the oldest is pushed out, thus preserving order.
            #this should be able be done easier with a single buffer instead of the two and the current line, just have a FIFO thing with 7 spaces. search on the middle string and then check the other 6 if necessary
            if count > 6:
                searchResult = re.search(regex, line)
                if searchResult is not None:
                    print()
            elif count < 3:
                last3Lines[count] = line
            elif count == 3:
                lineToLookAt = line
            elif count < 7:
                next3Lines[count-4] = line
            count += 1

findSequence()
