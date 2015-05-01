package com.fabiooliveiracosta.highlight2pdf

import java.nio.file.{Paths, Files}
import scala.io.Source

object FileReader{
	protected val defaultCodecs = List(
		io.Codec("UTF-8"),
		io.Codec("ISO-8859-1")
	)
	def getFileLines(fileName:String, codecs:Iterable[io.Codec] = defaultCodecs): Iterable[String]={
		if(!Files.exists(Paths.get(fileName))){
			throw new java.io.FileNotFoundException("File: "+fileName+" not found ");
		}
		val codec = codecs.head
		val fileHandle=Source.fromFile(fileName)(codec)
		try{
			fileHandle.getLines().toList
		}
		catch{
			case ex: Exception => {
				if (codecs.tail.isEmpty) {
					throw ex
				}
				getFileLines(fileName, codecs.tail)
			}
		}
		finally{
			fileHandle.close()
		}
	}
}