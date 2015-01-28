def answer(n):
    if n < 4:
        return n
    base = int(n**0.5)
    if base*base == n:
        return 1
    return 1 + answer(n - base*base)

