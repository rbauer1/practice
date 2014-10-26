import random

def divide(n, div):
	shifts = 1
	if(n > div):
		while(n // shifts != 0):
			shifts = shifts << 1
		tempDiv = div
		while(tempDiv > 0):
			tempDiv = tempDiv >> 1
			shifts = shifts >> 1
		#print(div*shifts, bin(n))
		n = n ^ (div*shifts)
		return divide(n, div)
	else:
		return n 


def encode(gStr,mStr):
	generator = int(gStr,2)
	tempG = generator
	message = int(mStr, 2)
	while(tempG > 0):
		tempG = tempG >> 1
		message = message << 1
	message = message >> 1
	print(bin(message))
	t = message + divide(message, generator)
	return t

def introduceError(message, detectable, generator):
	error = 0
	if(detectable):
		generator = generator >> 1
		while(generator > 0):
			generator = generator >> 1
			error = error + random.randrange(2)
			error = error << 1
		rem = message // error
		count = 0
		while(rem > 0):
			rem = rem >> 1
			count = count + 1
		print(bin(error), count)
		count = random.randrange(count)
		while(count > 0):
			count = count - 1
			error << 1
	else:
		error = random.randrange(message)
	return message ^ error

def checkForError(transmittedMessage, generator):
	if(divide(transmittedMessage, generator) == 0):		
		print("Accept the frame!")
	else:
		print("Reject the frame!")

def test():
	transmitted = encode("10011","1101011011");
	if (random.choice([True, False])):
		received = introduceError(transmitted,True, 19)
		print("Possible Error introduced")
	else:
		received = transmitted
		print("No Error")
	print(transmitted, received)
	print(bin(transmitted), bin(received))
	checkForError(received, 19)

test()
#print(divide(13758, 19))
#print(divide(16384, 12))
