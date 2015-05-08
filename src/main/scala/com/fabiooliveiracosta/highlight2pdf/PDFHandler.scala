package com.fabiooliveiracosta.highlight2pdf

import scala.collection.JavaConversions._

import java.awt.geom.Rectangle2D
import java.util.ArrayList
import java.util.{Map => JMap, List => JList,Iterator=>JIterator}
import java.util.regex.Matcher
import java.util.regex.Pattern

import org.pdfclown.documents.Document
import org.pdfclown.documents.Pages
import org.pdfclown.documents.PageLabels
import org.pdfclown.documents.interaction.navigation.page.PageLabel
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
import org.pdfclown.objects.PdfInteger

object PDFHandler{
	val txtExtractor:TextExtractor= new TextExtractor(true, true)
	def highlightOnFile(filePath:String,highlights:List[HighlightClipping]):Boolean={
		val file:File= new File(filePath)
		val doc:Document=file.getDocument()
		val numericStart:Int=getNumericStart(doc)
		val pages:Pages=doc.getPages()
		for (h <- highlights){
			highlightPages(pages,h,numericStart) match{
				case false=> return false
				case _ =>
			}
		}
		file.save(SerializationModeEnum.Incremental)
		file.close()
		true
	}
	/**
	*Get the first numerical start of a page
	*/
	def getNumericStart(doc:Document):Int={
		val labels:JMap[PdfInteger,PageLabel]=doc.getPageLabels()
		labels.foreach(kv=>{
			if(kv._2.getNumberStyle()==PageLabel.NumberStyleEnum.ArabicNumber && kv._2.getPrefix()==null){
				return kv._1.getIntValue()
			}
		})
		0
	}
	def highlightPages(pages:Pages,clipping:HighlightClipping,numericStart:Int):Boolean={
		var desiredHighlight:String=clipping.content
		var intervalFilter:PDFHighlightIntervalFilter=null
		var page:Page=null
		var pageText:String=null
		var textMap:JMap[Rectangle2D,JList[ITextString]]=null
		var i:Int=0
		for(i<-((clipping.firstPage+numericStart)-1) until (clipping.lastPage+numericStart)){
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