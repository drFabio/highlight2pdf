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

object PDFHandler{
	val textExtractor:TextExtractor= new TextExtractor(true, true)
	def highlightOnFile(filePath:String):Boolean={
		println(filePath)
		val file:File= new File(filePath)
		val pages:Pages=file.getDocument().getPages()
		highlightPage(pages.get(3),"key difference is the approach to and understanding of operational process and delivery and how this is captured in or intersects with contracts");
		true
	}
	def highlightPage(page:Page,desiredHighlight:String){
		// Extract the page text!
 		val textMap:JMap[Rectangle2D,JList[ITextString]] = textExtractor.extract(page)
 		//Let's highlight the desired text
 		val textString:String=TextExtractor.toString(textMap)
 		val startIndex:Int=textString.indexOf(desiredHighlight)
 		val endIndex:Int=startIndex+textString.length()
 		val interval:Interval[Integer]=new Interval[Integer](startIndex,endIndex)

 		val textStringsIterator:JIterator[JList[ITextString]] = textMap.values().iterator

   		if(!textStringsIterator.hasNext()){
	      return;
   		}
   		val areaTextStringsIterator:JIterator[ITextString]=textStringsIterator.next().iterator
		if(!areaTextStringsIterator.hasNext()){
			return;
		}

	}
}