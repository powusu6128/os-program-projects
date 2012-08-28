#!/usr/bin/env python
# vim: set fileencoding=utf-8 :

import requests
import sys
import json

API = "https://api.github.com/markdown"

def get_parser():
    pass
    from optparse import OptionParser

    parser = OptionParser()
    parser.add_option('-i','--input', dest='<filename>',
        help='Input markdown file', metavar="FILE"
    )

if __name__ == '__main__':
    try:
        md = sys.argv[1]
    except Exception:
        sys.exit(1)

    assert md.endswith('.md')

    d = dict()
    d['text'] = open(md).read()
    d['mode'] = 'gfm'

    payload = json.dumps(d)
    
    r = requests.post(API, data=payload)
    result = '<link href="http://kevinburke.bitbucket.org/markdowncss/markdown.css" rel="stylesheet"></link>\n' + r.text
    open("%s.html" % (md[:-3],), 'w').write(result)