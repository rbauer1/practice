def partition(the_list, low, high):
    original_low = low
    pivot = the_list[low]
    low += 1
    while(low <= high):
        while(low <= high and the_list[low] <= pivot):
            low += 1
        while(the_list[high] >= pivot and high >= low):
            high -= 1
        if(low <= high):
            swap(the_list, low, high)
    swap(the_list, original_low, high)
    return high


def qSort(the_list):
    qS(the_list, 0, len(the_list) - 1)
    print(the_list)


def qS(the_list, low, high):
    if(low < high):
        pivot = partition(the_list, low, high)
        qS(the_list, low, pivot - 1)
        qS(the_list, pivot + 1, high)


def swap(the_list, a, b):
    if(a == b):
        return
    temp = the_list[a]
    the_list[a] = the_list[b]
    the_list[b] = temp

#the_list = [22, 37, 32, 8, 18, 8, 48, 6]
the_list = [1,2,3,8,4,5,6,7]

print(the_list)
qSort(the_list)
