def answer(heights):
    print(heights)
    if len(heights) < 3:
        return 0
    index = 0
    lastHeight = heights[index]
    while index+1 < len(heights) and heights[index] < heights[index+1]:
        index += 1
    if index >= len(heights):
        return 0
    lastHeight = heights[index]
    total = 0
    numValleys = 0
    sumValleys = 0
    index += 1
    localMax = 0
    while index < len(heights) and lastHeight > heights[index]:
        if heights[index] > localMax:
            localMax = heights[index]
        numValleys += 1
        sumValleys += lastHeight - heights[index]
        index += 1
    if index >= len(heights):
        return 0
    total += sumValleys
    if heights[index] < lastHeight:
        total -= numValleys * (lastHeight - heights[index])
    return total

