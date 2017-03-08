#!/usr/bin/python

from itertools import groupby
from operator import itemgetter
import sys


def read_mapper_output(file, separator='\t'):
    for line in file:
        yield line.rstrip().split(separator, 1)


def main(separator='\t'):
    data = read_mapper_output(sys.stdin, separator=separator)
    for current_dna, group in groupby(data, itemgetter(0)):
        users = list(set(dna for current_dna, dna in group))
        if len(users) > 1:
            print "%s" % (users)


if __name__ == "__main__":
    main()
