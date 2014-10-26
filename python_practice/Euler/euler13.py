from shlex import quote
from os import chdir

def solve(num_digits):
	numbytes = num_digits+2
	chdir(r'/home/riley/python practice/Euler/')
	with open(quote('data13.txt'), 'rb') as data:

		def readskip():
			d = data.read(numbytes)
			data.read(51-numbytes)
			return int(d)

		sm = 0
		for x in range(100):
			sm += readskip()
	div = 10**num_digits
	mag = num_digits
	while(sm//div > 0):
		div *= 10
		mag += 1
	print(sm//(10**(mag - num_digits)))
solve(10)