package com.fabiooliveiracosta.highlight2pdf

import org.scalatest._
import java.io.FileNotFoundException
class ReadClippingSpec extends UnitSpec {
	"All Clippings " should "be obtained from a file and returned on the order they were found" in{
		val path:String=getClass.getResource("/Test1.txt").getPath()
		val clippingList:List[AbstractClipping]=ClippinsReader.readClippingsFromFile(path)
		assert(clippingList.size==4)
		assert(clippingList(0).bookTitle=="Highlight1")
		assert(clippingList(1).bookTitle=="Highlight2")
		assert(clippingList(2).bookTitle=="Highlight3")
		assert(clippingList(3).bookTitle=="Bookmark1")
	} 
	"Highlight clippings" should " be returned only when using readHighlightsFromFile" in {
		val path:String=getClass.getResource("/Test1.txt").getPath()
		val clippingList:List[AbstractClipping]=ClippinsReader.readHighlightsFromFile(path)
		assert(clippingList.size==3)
		assert(clippingList(0).bookTitle=="Highlight1")
		assert(clippingList(1).bookTitle=="Highlight2")
		assert(clippingList(2).bookTitle=="Highlight3")
		assert(clippingList(0).isInstanceOf[HighlightClipping])
		assert(clippingList(1).isInstanceOf[HighlightClipping])
		assert(clippingList(2).isInstanceOf[HighlightClipping])
	}
	"Only the  highlights from the selected title " should " be returned only when using readHighlightsFromFileForBook" in {
		val path:String=getClass.getResource("/Test1.txt").getPath()
		val clippingList:List[AbstractClipping]=ClippinsReader.readHighlightsFromFile(path,"Highlight1","Highlight2")
		assert(clippingList.size==2)
		assert(clippingList(0).bookTitle=="Highlight1")
		assert(clippingList(1).bookTitle=="Highlight2")
	}
}