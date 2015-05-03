# highlight2pdf
A scala program that get Kingle clipping highlights and highlight them on the PDF 
It uses pdfClown for the pdfHighlight
See the tests for further informations
You can use this as a CLI app using the jar on build like so:
java -jar highlight2pdf -h  clippings.txt -t "BookTitleOnClippings" pdfFileToHighlight.pdf

##Build
You can build using sbt

##Further improvements

- PDF bookmarking

- Make a mapping of clipping title to a pdf file on a folder

###About the pdf on tests
The pdf on tests can be found here  http://www.agilecontracts.org/