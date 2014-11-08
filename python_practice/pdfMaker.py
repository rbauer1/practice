from re import match
from os import listdir
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Image, PageBreak
from reportlab.lib.units import inch

def create_pdf(pdfName, dir = ".", imgType = "jpg"):
    """
    Original idea from http://www.blog.pythonlibrary.org/2012/01/07/reportlab-converting-hundreds-of-images-into-pdfs/
    Takes all images in given directory (defaults to current) of type imgType
    (defaults to .jpg) and convert combine them into a pdf named pdfName with
    one image per page
    """
    doc = SimpleDocTemplate(pdfName, pagesize=letter, rightMargin=72, leftMargin=72, topMargin=72, bottomMargin=18)
    newPdf = []
    width = 7.5*inch
    height = 9.5*inch
    images = [image for image in listdir(dir) if match(".*\."+imgType,image) != None]
    list.sort(images)
    for image in images:
        img = Image(image, width, height)
        newPdf.append(img)
        newPdf.append(PageBreak())
    doc.build(newPdf)

