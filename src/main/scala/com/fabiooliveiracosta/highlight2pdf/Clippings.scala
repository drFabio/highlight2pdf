package com.fabiooliveiracosta.highlight2pdf
/*
sealed trait ClippingType
object Highlight extends ClippingType
object Bookmark extends ClippingType
*/

abstract class AbstractClipping{
	def firstPage:Int
	def lastPage:Int
	def bookTitle:String
}
case class HighlightClipping(val firstPage:Int,
							val lastPage:Int,
							val bookTitle:String,
							val content:String) 
extends AbstractClipping

case class BookmarkClipping(val firstPage:Int,
							val lastPage:Int,
							val bookTitle:String) 
extends AbstractClipping

object ClippingFactory{
	val titleRegex="""(?i)\s*-\s*Your (Highlight|Bookmark) (on Page|Location) ((\d*)(-(\d*))?) \|[^,]*, (\d*) (\w*) (\d*) (\d*):(\d*):(\d*)""".r
	def getClippling(data:List[String]):AbstractClipping={
		val title:String=data.head
		val temp=data.tail
		val typeData:String=temp.head
		val groupMatchs=titleRegex findFirstMatchIn typeData
		var firstPage:Int=0
		var lastPage:Int=0
		var isBookmark:Boolean=false
		/**
		*@todo get date if needed
		*/
		groupMatchs  match {
			case Some(value) =>{
				if(value.group(3) contains "-"){
					firstPage=value.group(4).toInt
					lastPage=value.group(6).toInt
				}
				else{
					firstPage=value.group(3).toInt
					lastPage=firstPage
				}
				if(value.group(1).toLowerCase() contains "bookmark"){
					isBookmark=true
				}
				else{
					isBookmark=false
				}
			}
			case None => throw new Exception("Invalid info data")
		}
		if(isBookmark){
			new BookmarkClipping(firstPage,lastPage,title)
		}
		else{
			val content:String=temp.tail.head
			new HighlightClipping(firstPage,lastPage,title,content)
		}
	}
}