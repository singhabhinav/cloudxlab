#!/usr/bin/python

'''
Find anagrams in a text file
'''

import sys
import re


def read_input(file):
    for line in file:
        line = re.split('[^a-z]+', line.lower())
        yield line


def main(separator='\t'):
    for words in read_input(sys.stdin):
        for word in words:
            if len(word) > 0:
                print '%s%s%s' % (''.join(sorted(word)), separator, word)

if __name__ == "__main__":
    main()
