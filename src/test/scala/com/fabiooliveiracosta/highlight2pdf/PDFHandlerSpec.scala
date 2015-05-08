package com.fabiooliveiracosta.highlight2pdf

import java.net.URL
import org.scalatest._
import java.io.FileNotFoundException
class PDFHandlerSpec extends UnitSpec {
	"Highlight" should "be possible" in{
		val clippingPath:String=getClass.getResource("/pdfHighlightTest.txt").getPath()
		val highlightList:List[HighlightClipping]=ClippingsReader.readHighlightsFromFile(clippingPath,"agile_contracts_primer")
		val url:URL=getClass.getResource("/agile_contracts_primer.pdf")
		val path:String=url.getPath()
		val ret:Boolean=PDFHandler.highlightOnFile(path,highlightList)
		assert(ret)

	}

}