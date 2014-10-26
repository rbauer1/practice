def largest_prime_fac(number):
	div = 3
	while number % 2 == 0: 
		number /= 2
	while div * div <= number:
		if number % div == 0:
			number /= div
		else:
			div +=2
	return number

print largest_prime_fac(600851475143)
