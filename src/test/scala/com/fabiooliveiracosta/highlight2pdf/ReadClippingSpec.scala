package com.fabiooliveiracosta.highlight2pdf

import org.scalatest._
import java.io.FileNotFoundException
class ReadClippingSpec extends UnitSpec {
	"All Clippings " should "be obtained from a file and returned on the order they were found" in{
		val path:String=getClass.getResource("/Test1.txt").getPath()
		val clippingList:List[AbstractClipping]=ClippinsReader.readClippingsFromFile(path)
		assert(clippingList.size==3)
		assert(clippingList(0).bookTitle=="Highlight1")
		assert(clippingList(1).bookTitle=="Highlight2")
		assert(clippingList(2).bookTitle=="Bookmark1")
	} 
	"Highlight clippings" should " be returned only when using readHighlightsFomFile" in {
		val path:String=getClass.getResource("/Test1.txt").getPath()
		val clippingList:List[AbstractClipping]=ClippinsReader.readHighlightsFromFile(path)
		assert(clippingList.size==2)
		assert(clippingList(0).bookTitle=="Highlight1")
		assert(clippingList(1).bookTitle=="Highlight2")
		assert(clippingList(0).isInstanceOf[HighlightClipping])
		assert(clippingList(1).isInstanceOf[HighlightClipping])
	}
}