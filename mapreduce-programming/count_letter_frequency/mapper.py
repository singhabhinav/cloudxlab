#!/usr/bin/python

'''
 Count the frequencies of letters
'''

import sys
import re
from collections import defaultdict


def read_input(file):
    pattern = re.compile(r'\s+')
    for line in file:
        line = re.sub(pattern, '', line)
        yield line


def main(separator='\t'):
    data = read_input(sys.stdin)
    for line in data:
        letter_dict = defaultdict(int)
        for letter in line:
            if letter.isalpha():
                letter_dict[letter.lower()] += 1
        for count_tuple in letter_dict.items():
            print '%s%s%d' % (count_tuple[0], separator, count_tuple[1])

if __name__ == "__main__":
    main()
