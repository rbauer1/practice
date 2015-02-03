from string import ascii_lowercase as alpha


def move2(sIn):
    """
    Solves the second python challenge. translates the string using
    string.translate using str.maketrans on the ascii_lowercase alphabet
    aliased to alpha and the alphabet shifted twice to the right using join on
    a list comprehension that builds the alphabet shifted
    """
    print(sIn.translate(str.maketrans(alpha,
                                      ''.join([alpha[(x + 2) % len(alpha)]
                                               for x in range(len(alpha))]))))
