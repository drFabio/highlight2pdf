object CLIApp {
	val usage = """Usage: highlight2pdf -h highlightFile -t bookHighlightTitle  pdfFile"""
	def main(args: Array[String]) {
		if (args.length <5){
			 println(usage)
			 return
		}
		type OptionMap = Map[Symbol, String]
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
		val options = nextOption(Map(),arglist)
		println(options)
	}
}