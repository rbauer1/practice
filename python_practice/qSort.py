from random import randint

def qSort(list):
	if(len(list) < 2):
		return list
	(pivot, *tail) = list
	low = []
	high = []
	eq = [pivot]
	for x in tail:
		if x < pivot:
			low[len(low):] = [x]
		elif x > pivot:
			high[len(high):] = [x]
		else:
			eq[len(eq):] = [x]
	low = qSort(low)
	high = qSort(high)
	return (lambda a, b, c: a)(low, low.extend(eq), low.extend(high))

list = []
while(len(list) < 20):
	list[len(list):] = [randint(0,50)]

print(list)
print(qSort(list))