from urllib import request
import shutil
import glob

from os import chdir
from os import listdir
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Image, PageBreak
from reportlab.lib.units import inch

def get_files():
	"""
	Pages won't be in order if there are more than 100
	"""
	chdir(r'/home/riley/Pictures/algebra-solutions/')
	url = "http://research.cyber.ee/~madeline/Herstein/"
	for imgN in range(5,70):
		imgURL = url
		if(imgN > 9):
			file_name = "img0%s.jpg" % imgN
		else:
			file_name = "img00%s.jpg" % imgN
		imgURL = imgURL + file_name
		urllib.request.urlretrieve(imgURL, file_name)
		#with urllib.request.urlopen(url) as response, open(file_name, 'wb') as out_file: shutil.copyfileobj(response, out_file)

def create_pdf():
	"""
	http://www.blog.pythonlibrary.org/2012/01/07/reportlab-converting-hundreds-of-images-into-pdfs/
	"""

	pdf_name = "Algebra Solutions.pdf"
	doc = SimpleDocTemplate(pdf_name, pagesize=letter, rightMargin=72, leftMargin=72, topMargin=72, bottomMargin=18)
	Story = []
	width = 7.5*inch
	height = 9.5*inch

	pictures = listdir()
	list.sort(pictures)

	for pic in pictures:
		img = Image(pic, width, height)
		Story.append(img)
		Story.append(PageBreak())
	doc.build(Story)



#get_files()
create_pdf()