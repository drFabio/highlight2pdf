package com.fabiooliveiracosta.highlight2pdf
object CLIApp {
	val usage = """Usage: highlight2pdf -h highlightFile -t bookHighlightTitle  pdfFile"""
	
	type OptionMap = Map[Symbol, String]

	def highlightFromClippingIntoFile(highlightFile:String,bookTitle:String,pdfFile:String):Boolean={
		val highlightList:List[HighlightClipping]=ClippingsReader.readHighlightsFromFile(highlightFile,bookTitle)
		PDFHandler.highlightOnFile(pdfFile,highlightList)
	}
	def parseCliArguments(args:Array[String]):OptionMap={
		val arglist = args.toList

		def isSwitch(s : String) = (s(0) == '-')
		def nextOption(map : OptionMap, list: List[String]) : OptionMap = {
			list match {
				case Nil => map
				case "-h" :: value :: tail =>{
					nextOption(map ++ Map('highlightFile -> value), tail)
				}
				case "-t" :: value :: tail =>{
					nextOption(map ++ Map('bookTitle -> value), tail)
				}
				case value :: opt2 :: tail if isSwitch(opt2) => {
					nextOption(map ++ Map('pdfFile -> value), list.tail)
				}
				case value :: Nil =>{
					nextOption(map ++ Map('pdfFile -> value), list.tail)
				}
				case _=>{ 
					throw new Exception("Invalid usage")
				}
			}
		}
		nextOption(Map(),arglist)
	}
	def main(args: Array[String]){
		if (args.length <5){
			 println(usage)
			 return
		}
		val opt:OptionMap=parseCliArguments(args)
		println("Trying to highlight the clippings from "+opt.get('highlightFile)+" on the book "+opt.get('bookTitle)+" for the pdf "+opt.get('pdfFile))
	}
	
}