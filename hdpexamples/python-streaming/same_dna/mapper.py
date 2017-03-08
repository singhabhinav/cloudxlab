#!/usr/bin/python

'''
Find people having same sequence of DNA
'''

import sys


def read_input(file):
    for line in file:
        yield line.split()


def main(separator='\t'):
    data = read_input(sys.stdin)
    for user, dna in data:
        print '%s%s%s' % (dna, separator, user)

if __name__ == "__main__":
    main()
