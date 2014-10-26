from operator import mul

_map = {2:0,3:0,5:0,7:0,11:0,13:0,17:0,19:0}

def prime_fac(number):
	"""Provides factors, omits 1"""
	facs = []
	div = 3
	while number % 2 == 0: 
		number /= 2
		facs.append(2)
	while div * div <= number:
		if number % div == 0:
			number /= div
			facs.append(div)
		else:
			div +=2
	if number != 1:
		facs.append(number)
	return facs

for i in xrange(1, 21):
	local_map = {i:0 for i in _map}
	for f in prime_fac(i):
		local_map[f] += 1
	for x in _map:
		_map[x] = max(local_map[x],_map[x])

print reduce(mul, [a**b for a,b in _map.iteritems()], 1)


