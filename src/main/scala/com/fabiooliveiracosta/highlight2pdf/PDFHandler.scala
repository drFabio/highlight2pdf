package com.fabiooliveiracosta.highlight2pdf

import java.awt.geom.Rectangle2D
import java.util.ArrayList
import java.util.List
import java.util.Map
import java.util.regex.Matcher
import java.util.regex.Pattern
 
import org.pdfclown.documents.Page
import org.pdfclown.documents.contents.ITextString
import org.pdfclown.documents.contents.TextChar
import org.pdfclown.documents.interaction.annotations.TextMarkup
import org.pdfclown.documents.interaction.annotations.TextMarkup.MarkupTypeEnum
import org.pdfclown.files.File
import org.pdfclown.tools.TextExtractor
import org.pdfclown.util.math.Interval
import org.pdfclown.util.math.geom.Quad

object PDFHandler{
}