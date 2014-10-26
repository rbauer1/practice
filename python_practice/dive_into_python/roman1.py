import random
import re


roman_numeral_map = (('M',  1000),
                     ('CM', 900),
                     ('D',  500),
                     ('CD', 400),
                     ('C',  100),
                     ('XC', 90),
                     ('L',  50),
                     ('XL', 40),
                     ('X',  10),
                     ('IX', 9),
                     ('V',  5),
                     ('IV', 4),
                     ('I',  1))


class OutOfRangeError(ValueError):
    pass


class NotIntegerError(ValueError):
    pass


class InvalidRomanNumeralError(ValueError):
    pass


def genNums(amount):
    ''' Create and print a list of numbers 'amount' long
    '''
    nums = [random.randrange(4001)+1 for x in range(amount)]
    print(nums)
    return nums


def to_roman(n):
    '''convert integer to Roman numeral'''
    if not isinstance(n, int):
        raise NotIntegerError('n must be an integer')
    if not (0 < n < 4000):
        raise OutOfRangeError('Number out of range: must be 0 < n < 4000')
    result = ''
    for numeral, integer in roman_numeral_map:
        while n >= integer:
            result += numeral
            n -= integer
    return result


def to_arabic(s):
    ''' Returns the arabic numeral representaton of 'n'
    '''
    if not s:
        raise InvalidRomanNumeralError('Input cannot be blank')
    if not is_valid_numerals(s):
        raise InvalidRomanNumeralError('Invalid Roman numeral: {0}'.format(s))
    result = 0
    index = 0
    for numeral, integer in roman_numeral_map:
        while s[index:index+len(numeral)] == numeral:
            result += integer
            index += len(numeral)
    return result


def is_valid_numerals(numeral):
    ''' Returns the boolean value representing whether or not numeral is a
    valid roman numeral. This uses a verbose regex, hence the re.VERBOSE in the
    re.match() call
    '''
    pattern = '''
    ^                   # starting at beginning of the string
    M{0,3}              # thousands place
    (C[MD]|D?C{0,3})    # hundreds place
    (X[CL]|L?X{0,3})    # tens place
    (I[XV]|V?I{0,3})    # ones place
    $                   # ending at the end of the string
    '''
    return bool(re.match(pattern, numeral, re.VERBOSE))

# print(to_roman(1999))
#l = [to_roman(x) for x in genNums(30)]
#print(l)
#print([is_valid_numerals(x) for x in l])
#print(is_valid_numerals('MMX'))
