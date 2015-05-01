package com.fabiooliveiracosta.highlight2pdf
import scala.collection.mutable.ListBuffer
class ReadClippings{
	
}
object ReadClippings{
	protected val defaultSeparator="=========="
	def hello():String={
		return "Eorld"
	}
	def main(args:Array[String]){
		println(hello());
	}
	def readHighlightsFromFile(fileName:String){
		val lines:Iterable[String]=FileReader.getFileLines(fileName);
		var line:String=""
		var clippingList=new ListBuffer[String]()
		for(line<-lines){
			if(line!=""){
				if(line==defaultSeparator){
					println("FIM DA LISTA")
					println(clippingList)
					clippingList=new ListBuffer[String]()

				}
				else{
					clippingList+=line
				}
			}


		}
	}
}