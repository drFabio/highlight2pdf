package com.fabiooliveiracosta.highlight2pdf

import java.net.URL
import org.scalatest._
import java.io.FileNotFoundException
class PDFHandlerSpec extends UnitSpec {
	"Highlight" should "be possible" in{
		val url:URL=getClass.getResource("/agile_contracts_primer.pdf")
		val path:String=url.getPath()
		PDFHandler.highlightOnFile(path)
		assert(true)
	}

}