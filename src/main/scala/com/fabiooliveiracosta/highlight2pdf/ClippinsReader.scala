package com.fabiooliveiracosta.highlight2pdf
import scala.collection.mutable.ListBuffer

object ClippinsReader{
	protected val defaultSeparator="=========="

	def readClippingsFromFile(fileName:String):List[AbstractClipping]={
		val lines:Iterable[String]=FileReader.getFileLines(fileName);
		var line:String=""
		var clippingsLinesList=new ListBuffer[String]()
		var clipplingList=new ListBuffer[AbstractClipping]()
		for(line<-lines){
			if(line!=""){
				if(line==defaultSeparator){
					(ClippingFactory.getClippling(clippingsLinesList.toList): @unchecked) match{
						case Some(h)=>clipplingList+=h
					}
					clippingsLinesList.clear()
				}
				else{
					clippingsLinesList+=line
				}
			}
		}
		clipplingList.toList
	}
	def readHighlightsFromFile(fileName:String,desiredTitles:String*):List[HighlightClipping]={
		val lines:Iterable[String]=FileReader.getFileLines(fileName);
		var line:String=""
		var clippingsLinesList=new ListBuffer[String]()
		var clipplingList=new ListBuffer[HighlightClipping]()
		var theClipping:Option[HighlightClipping]=None
		for(line<-lines){
			if(line!=""){
				if(line==defaultSeparator){
					theClipping=ClippingFactory.getHighlight(clippingsLinesList.toList,desiredTitles:_*)
					if(!theClipping.isEmpty){
						clipplingList+=theClipping.get
					}
					clippingsLinesList.clear()
				}
				else{
					clippingsLinesList+=line
				}
			}
		}
		clipplingList.toList
	}
}