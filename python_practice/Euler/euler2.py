_sqrt5 = 2.2360679775

def fib(n):
	return ((1+sqrt5)**n - (1-sqrt5)**n)//(2**n*sqrt5)

def below_n(n):
	"""Provide next fibonacci number
	
	This generator will provide the next fibonacci number below n
	"""
	i = 1
	x = fib(i)
	while x < n:
		yield x
		i += 1
		x = fib(i)

print sum([x for x in below_n(4000000) if x % 2 == 0])