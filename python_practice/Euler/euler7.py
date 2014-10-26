def postponed_sieve():                   # postponed sieve, by Will Ness      
    yield 2; yield 3; yield 5; yield 7;  # original code David Eppstein, 
    D = {}                               #            ActiveState Recipe 2002
    ps = (p for p in postponed_sieve())  # a separate Primes Supply:
    p = ps.next() and ps.next()          # (3) a Prime to add to dict
    q = p*p                              # (9) when its square is 
    c = 9                                # the next Candidate
    while True:
        if c not in D:                # not a multiple of any prime seen so far:
            if c < q: yield c         #   a prime, or
            else:   # (c==q):         #   the next prime's square:
                add(D,c + 2*p,2*p)    #     (9+6,6 : 15,21,27,33,...)
                p=ps.next()           #     (5)
                q=p*p                 #     (25)
        else:                         # 'c' is a composite:
            s = D.pop(c)              #   step of increment
            add(D,c + s,s)            #   next multiple, same step
        c += 2                        # next odd candidate

def add(D,x,s):                          # make no multiple keys in Dict
    while x in D: x += s                 # increment by the given step
    D[x] = s

def nth_prime(n):
	for x in postponed_sieve():
		n -= 1
		if n == 0:
			return x

print(nth_prime(10001))
