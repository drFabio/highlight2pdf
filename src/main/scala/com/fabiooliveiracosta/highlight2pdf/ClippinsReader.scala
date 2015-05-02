package com.fabiooliveiracosta.highlight2pdf
import scala.collection.mutable.ListBuffer

object ClippinsReader{
	protected val defaultSeparator="=========="
	object ClippingTypes extends Enumeration {
		val ALL,BOOKMARK,CLIPPING = Value
	}
	def readClippingsFromFile(fileName:String,desiredTitles:String*):List[AbstractClipping]={
		filterClippings(fileName,ClippingTypes.ALL,desiredTitles:_*)
	}
	def filterClippings(fileName:String,desiredType:ClippingTypes.Value,desiredTitles:String*):List[AbstractClipping]={
		val lines:Iterable[String]=FileReader.getFileLines(fileName);
		var line:String=""
		var clippingsLinesList=new ListBuffer[String]()
		var clippingBuffer=new ListBuffer[AbstractClipping]()
		var theClipping:Option[AbstractClipping]=None
		var clippingStrList:List[String]=Nil
		for(line<-lines){
			if(line!=""){
				if(line==defaultSeparator){
					clippingStrList=clippingsLinesList.toList
					desiredType match{
						case ClippingTypes.ALL=>{
							theClipping=ClippingFactory.getClippling(clippingStrList,desiredTitles:_*)
						}
						case ClippingTypes.CLIPPING=>{
							theClipping=ClippingFactory.getHighlight(clippingStrList,desiredTitles:_*)
						}
						case ClippingTypes.BOOKMARK=>{
							theClipping=ClippingFactory.getHighlight(clippingStrList,desiredTitles:_*)
						}
					}
					theClipping match{
						case Some(h)=>clippingBuffer+=h
						case None=>
					}
					clippingsLinesList.clear()
				}
				else{
					clippingsLinesList+=line
				}
			}
		}
		clippingBuffer.toList
	}
	def readHighlightsFromFile(fileName:String,desiredTitles:String*):List[HighlightClipping]={
		filterClippings(fileName,ClippingTypes.CLIPPING,desiredTitles:_*).asInstanceOf[List[HighlightClipping]]

		// val lines:Iterable[String]=FileReader.getFileLines(fileName);
		// var line:String=""
		// var clippingsLinesList=new ListBuffer[String]()
		// var clippingBuffer=new ListBuffer[HighlightClipping]()
		// var theClipping:Option[HighlightClipping]=None
		// for(line<-lines){
		// 	if(line!=""){
		// 		if(line==defaultSeparator){
		// 			theClipping=ClippingFactory.getHighlight(clippingsLinesList.toList,CLIPPING,desiredTitles:_*)
		// 			if(!theClipping.isEmpty){
		// 				clippingBuffer+=theClipping.get
		// 			}
		// 			clippingsLinesList.clear()
		// 		}
		// 		else{
		// 			clippingsLinesList+=line
		// 		}
		// 	}
		// }
		// clippingBuffer.toList
	}
}