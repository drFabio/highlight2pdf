package com.fabiooliveiracosta.highlight2pdf

abstract class AbstractClipping(val firstPage:Int,val lastPage:Int,val bookTitle:String)
case class HighlightClipping(fstPage:Int,
							lstPage:Int,
							title:String,
							val content:String)
extends AbstractClipping(fstPage,lstPage,title)

case class BookmarkClipping(fstPage:Int,
							lstPage:Int,
							title:String)
extends AbstractClipping(fstPage,lstPage,title)