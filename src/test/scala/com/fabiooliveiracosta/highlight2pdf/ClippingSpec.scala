package com.fabiooliveiracosta.highlight2pdf

import org.scalatest._
import java.io.FileNotFoundException
class ClippingSpec extends UnitSpec {
	"Clipping with highlight " should "be returned as highlighted clippings " in{
		val clippingList=List("Some title",
								"- Your Highlight on page 24-30 | Added on Sunday, 12 April 2015 14:15:50 ",
								"Lorem ipsum dolor sit amet");

		val clipping:AbstractClipping=ClippingFactory.getClippling(clippingList)
		clipping match{
				case h:HighlightClipping=>{
					println(h.lastPage)
					assert(h.lastPage==30)
					assert(h.firstPage==24)
					assert(h.bookTitle=="Some title")
				}
		 		case _=>{
					fail("Type of clipping should be highlight")
				}
		}
	}
	"The clippings" should "be indexed by book title" in {
		//assert(false)

	}
/*  it should "produce NoSuchElementException when head is invoked" in {
	intercept[NoSuchElementException] {
	  Set.empty.head
	}
  }*/

}