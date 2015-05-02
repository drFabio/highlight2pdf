package com.fabiooliveiracosta.highlight2pdf

import scala.collection.JavaConversions._

import java.awt.geom.Rectangle2D
import java.util.ArrayList
import java.util.{Map => JMap, List => JList,Iterator=>JIterator}

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

class PDFHighlightIntervalFilter(val page:Page,val pageText:String,val desiredHighlight:String) extends TextExtractor.IIntervalFilter{
	
	protected var _alreadyFound=false
	def  hasNext():Boolean={
		if(_alreadyFound){
			false
		}
		else{
			_alreadyFound=true
			true
		}
	}
	def next():Interval[Integer]={
		val startIndex:Int=pageText.indexOf(desiredHighlight)
		val endIndex:Int=startIndex+desiredHighlight.length()
		new Interval[Integer](startIndex,endIndex)
	}
	def process(interval:Interval[Integer],textMatch:ITextString){
		val highlightQuads:JList[Quad] = new ArrayList[Quad]()
		/*
		NOTE: A text pattern match may be split across multiple contiguous lines,
		so we have to define a distinct highlight box for each text chunk.
		*/
		var textBox:Rectangle2D = null
		val txtMatchList=textMatch.getTextChars().toList
		for(textChar:TextChar <- txtMatchList)
		{

			var textCharBox:Rectangle2D = textChar.getBox()
			if(textBox == null)
			{
				textBox=textCharBox.clone().asInstanceOf[Rectangle2D]
			}
			else{
				if(textCharBox.getY() > textBox.getMaxY()){
					highlightQuads.add(Quad.get(textBox));
					textBox=textCharBox.clone().asInstanceOf[Rectangle2D]
				}
				else
				{
					textBox.add(textCharBox);}
				}
		}
		highlightQuads.add(Quad.get(textBox));
        val markup:TextMarkup=new TextMarkup(page, null, MarkupTypeEnum.Highlight, highlightQuads);
        markup.setVisible(true)
        markup.setPrintable(true)
	}
}