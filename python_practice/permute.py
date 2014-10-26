def permute(string, prefix=None):
    if(prefix is not None):
        length = len(string)
        if(length == 0):
            print(prefix)
        else:
            for i in range(length):
                permute(string[:i] + string[i + 1:], prefix + string[i])
    else:
        permute(string, "")

permute('12321')
