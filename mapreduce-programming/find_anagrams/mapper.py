#!/usr/bin/python

'''
Find anagrams in a text file
'''

import sys


def read_input(file):
    for line in file:
        yield line.split()


def main(separator='\t'):
    data = read_input(sys.stdin)
    for words in data:
        for word in words:
            print '%s%s%s' % (''.join(sorted(word)), separator, word)

if __name__ == "__main__":
    main()
