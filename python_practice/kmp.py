
def findMatch(word, string):
    table = createFailureTable(word)
    stringPosition = 0
    wordPosition = 0
    while stringPosition + wordPosition < len(string):
        if word[wordPosition] == string[wordPosition + stringPosition]:
            if wordPosition == len(word) - 1:
                return stringPosition
            wordPosition = wordPosition + 1
        elif table[wordPosition] > -1:
            stringPosition = stringPosition + \
                wordPosition - table[wordPosition]
            wordPosition = table[wordPosition]
        else:
            wordPosition = 0
            stringPosition = stringPosition + 1
    return len(string)


def createFailureTable(word):
    position = 2
    current = 0
    table = [0 for x in range(len(word))]
    table[0] = -1
    while position < len(word):
        if word[position - 1] == word[current]:
            current = current + 1
            table[position] = current
            position = position + 1
        elif current > 0:
            current = table[current]
        else:
            table[position] = 0
            position = position + 1
    return table

print(findMatch('ABCDABD', 'ABC ABCDAB ABCDABCDABDE'))
