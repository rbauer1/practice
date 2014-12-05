#!/usr/bin/python

import urllib
import shutil
import getopt
import sys

from os import chdir
from os import listdir
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Image, PageBreak
from reportlab.lib.units import inch

def main(argv):
    """
    Pages won't be in order if there are more than 100
    example usage: ./fileGrabber.py -u "http://weather.unisys.com/archive/eta_init/1411/"
    -f "1411,.gif" -d "/home/riley/test" -s 1412 -n 11 -i 88,12
    """
    url = ''
    out_dir = ''
    file = ''
    start = 0
    num = 0
    incr = [0]
    try:
        opts, args = getopt.getopt(argv,
                "hu:f:d:s:n:i:",["url=","file=","dir=","start=","num=","increment="])
    except getopt.GetoptError:
        print('-u <baseurl>\n-f <fileprefix,filesuffix>\n-d \
<path_to_out_dir>\n-s <starting_file_num>\n-n <num_files>\n-i \
<incr1, incr2, ...>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print('-u <baseurl>\n-f <fileprefix,filesuffix>\n-d \
<path_to_out_dir>\n-s <starting_file_num>\n-n <num_files>\n-i \
<incr1, incr2, ...>')
            sys.exit()
        elif opt in ("-u","--url"):
            url = arg
        elif opt in ("-f","--file"):
            temp = arg.split(",")
            if(len(temp) != 2):
                print('file must be of the form <prefix,suffix> where suffix \
where suffix includes the file extension')
                sys.exit(2)
            file = temp[0] + "%s" + temp[1]
        elif opt in ("-d","--dir"):
            out_dir = arg
        elif opt in ("-s","--start"):
            start = int(arg)
        elif opt in ("-n","--num"):
            num = int(arg)
            if(num < 0):
                print('num must be >= 0')
        elif opt in ("-i","--increment"):
            incr = [int(i) for i in arg.split(",")]
    chdir(out_dir)
    for imgN in range(0,num):
        file_name = file % start
        imgURL = url + file_name
        print(imgURL)
        urllib.request.urlretrieve(imgURL, file_name)
        start += incr[imgN%len(incr)]

#def create_pdf():
#    """
#    http://www.blog.pythonlibrary.org/2012/01/07/reportlab-converting-hundreds-of-images-into-pdfs/
#    """
#    pdf_name = "Algebra Solutions.pdf"
#    doc = SimpleDocTemplate(pdf_name, pagesize=letter, rightMargin=72, leftMargin=72, topMargin=72, bottomMargin=18)
#    Story = []
#    width = 7.5*inch
#    height = 9.5*inch
#    pictures = listdir()
#    list.sort(pictures)
#    for pic in pictures:
#        img = Image(pic, width, height)
#        Story.append(img)
#        Story.append(PageBreak())
#    doc.build(Story)

if __name__ == "__main__":
    main(sys.argv[1:])

#create_pdf()
