package com.fabiooliveiracosta.highlight2pdf

object ClippingFactory{
	val titleRegex="""(?i)\s*-\s*Your (Highlight|Bookmark) (on Page|Location) ((\d*)(-(\d*))?) \|[^,]*, (\d*) (\w*) (\d*) (\d*):(\d*):(\d*)""".r
	def getHighlight(data:List[String],desiredTitles:String*):Option[HighlightClipping]={	
		val theClipping=this.getClippling(data,desiredTitles:_*)
		theClipping match{
			case Some(h:HighlightClipping)=>Some(h)
			case _=>None
		}
	}
	def getClippling(data:List[String],desiredTitles:String*):Option[AbstractClipping]={
		val title:String=data.head.trim
		if(desiredTitles.length>0 && !(desiredTitles contains title)){
			return None
		}
		val temp=data.tail
		val typeData:String=temp.head.trim
		val groupMatchs=titleRegex findFirstMatchIn typeData
		var firstPage:Int=0
		var lastPage:Int=0
		var isBookmark:Boolean=false
		
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
			Some(new BookmarkClipping(firstPage,lastPage,title))
		}
		else{
			val content:String=temp.tail.head.trim
			Some(new HighlightClipping(firstPage,lastPage,title,content))
		}
	}
}