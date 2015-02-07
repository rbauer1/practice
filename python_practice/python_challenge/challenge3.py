from os import chdir
import re
from string import ascii_lowercase as lower


def findSequence():
    """This ended up being far more complicated than it actually needed to be.
    I originally quickly made the regex and used it, but found multiple
    answers. instead of determining that *all* of the matching cases were
    relevant, I assumed that I misunderstood the problem and instead needed to
    check not only horizontally, but vertically as well. That is what the below
    code does, although it is important to note that currently it does not
    check to see that there are EXACTLY 3 caps above and below, just that there
    are AT LEAST 3. This is something I might fix at somepoint as it should
    just be an extra line or so however, it is irrelevant now to this problem
    as the entire thing is as simple as capturing the 10 matches to provide the
    keyword "linkedlist"
    """
    chdir(
        '/home/riley/workspace/Practice/python_practice/python_challenge/data')
    regex = re.compile("[^A-Z][A-Z]{3}[a-z][A-Z]{3}[^A-Z]")
    with open('challenge3_data') as f:
        count = 0
        index = 0
        lineBuffer = ["", "", "", "", "", "", ""]
        for line in f:
            found = True
            if count > 6:
                searchResult = re.search(regex, lineBuffer[(index+3)%7])
                if searchResult is not None:
                    pos = searchResult.start()+4
                    for x in range(2, -1, -1):
                        if (lineBuffer[(index + x)%7][pos] in lower or
                            lineBuffer[(index + 6-x)%7][pos] in lower):
                            found = False
                            break
                    if found:
                        for l in lineBuffer:
                            print(l[pos-3:pos+4])
            lineBuffer[index] = line
            index = (index+1)%7
            count += 1

findSequence()
