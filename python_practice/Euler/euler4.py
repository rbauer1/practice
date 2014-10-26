candidates = [str(j*i) for i in xrange(1000,100,-1) for j in xrange(1000,100,-1)]
print max([int(x) for x in candidates if x[0:len(x)/2] == x[:((len(x))/2)-1:-1]])