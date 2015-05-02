package com.fabiooliveiracosta.highlight2pdf

import java.awt.geom.Rectangle2D
import java.util.ArrayList
import java.util.{Map => JMap, List => JList,Iterator=>JIterator}
import java.util.regex.Matcher
import java.util.regex.Pattern

import org.pdfclown.documents.Pages
import org.pdfclown.documents.Page
import org.pdfclown.documents.contents.ITextString
import org.pdfclown.documents.contents.TextChar
import org.pdfclown.documents.interaction.annotations.TextMarkup
import org.pdfclown.documents.interaction.annotations.TextMarkup.MarkupTypeEnum
import org.pdfclown.files.File
import org.pdfclown.tools.TextExtractor
import org.pdfclown.util.math.Interval
import org.pdfclown.util.math.geom.Quad
import org.pdfclown.files.SerializationModeEnum

object PDFHandler{
	val textExtractor:TextExtractor= new TextExtractor(true, true)
	def highlightOnFile(filePath:String):Boolean={
		val file:File= new File(filePath)
		val pages:Pages=file.getDocument().getPages()
		val ret:Boolean=highlightPage(pages.get(3),"key difference is the approach to and understanding of operational process and delivery and how this is captured in or intersects with contracts")
		val newFilePath:String=filePath+"_tmp"
		file.save(SerializationModeEnum.Incremental)
		file.close()
		ret
	}
	def highlightPage(page:Page,desiredHighlight:String):Boolean={
		//Thi segment is addaptade from pdfClown highlight text sample
		val textMap:JMap[Rectangle2D,JList[ITextString]] = textExtractor.extract(page)
		val pageText:String=TextExtractor.toString(textMap)
		val intervalFiter:TextExtractor.IIntervalFilter=new PDFHighlightIntervalFilter(page,pageText,desiredHighlight)
		textExtractor.filter(textMap,intervalFiter)
		true
	}
}