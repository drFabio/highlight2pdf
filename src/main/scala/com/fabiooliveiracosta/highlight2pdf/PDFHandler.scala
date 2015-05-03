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
	val txtExtractor:TextExtractor= new TextExtractor(true, true)
	def highlightOnFile(filePath:String,highlights:List[HighlightClipping]):Boolean={
		val file:File= new File(filePath)
		val pages:Pages=file.getDocument().getPages()
		for (h <- highlights){
			highlightPages(pages,h) match{
				case false=> return false
				case _ =>
			}
		}
		file.save(SerializationModeEnum.Incremental)
		file.close()
		true
	}
	def highlightPages(pages:Pages,clipping:HighlightClipping):Boolean={
		var desiredHighlight:String=clipping.content
		var intervalFilter:PDFHighlightIntervalFilter=null
		var page:Page=null
		var pageText:String=null
		var textMap:JMap[Rectangle2D,JList[ITextString]]=null
		var i:Int=0
		for(i<-(clipping.firstPage-1) until (clipping.lastPage)){
			page=pages.get(i)
			textMap= txtExtractor.extract(page)
			pageText=TextExtractor.toString(textMap)
			intervalFilter=new PDFHighlightIntervalFilter(page,pageText,desiredHighlight)
			txtExtractor.filter(textMap,intervalFilter)

			intervalFilter.getRemainingText match{
				case Some(s:String)=>{
					desiredHighlight=s
				}
				case None=>
			}
		}
		true
	}
}