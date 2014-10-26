from euler7 import postponed_sieve

def CheckPandigital(number):
	digit_list = range(10)
	while number > 0:
		digit = number % 10
		number /= 10
		digit_list[digit] -= digit
		if digit_list[digit] < 0:
			return False
	return True
	
print postponed_sieve()