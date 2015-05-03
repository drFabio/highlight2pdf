object CLIApp {
	val usage = """Usage: highlight2pdf -h highlightTitle -p pdfFile"""
	def main(args: Array[String]) {
		if (args.length == 0) println(usage)
			val arglist = args.toList
			println(arglist)
	}
}