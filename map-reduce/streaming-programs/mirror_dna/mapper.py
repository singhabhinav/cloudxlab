#!/usr/bin/python

'''
Find all people having same or mirror image of DNAs
'''

import sys


def read_input(file):
    for line in file:
        yield line.split()


def main(separator='\t'):
    data = read_input(sys.stdin)
    for user, dna in data:
        print '%s%s%s' % (min(dna[::-1], dna), separator, user)

if __name__ == "__main__":
    main()
