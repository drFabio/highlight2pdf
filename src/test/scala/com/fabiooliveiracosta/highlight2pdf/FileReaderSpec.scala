package com.fabiooliveiracosta.highlight2pdf

import org.scalatest._
import java.io.FileNotFoundException
class FileReaderSpec extends UnitSpec {
  "Files that are not found" should "throw an exception" in{
  	intercept[FileNotFoundException] {
  		FileReader.getFileLines("nontExistent.txt");
  	}
  }
  "Files that are found " should "return the file lines " in {
  	val path:String=getClass.getResource("/ExampleClippings.txt").getPath()
  	val lines:Iterable[String]=FileReader.getFileLines(path)
  	assert(lines.size>0)
  }
 
/*  it should "produce NoSuchElementException when head is invoked" in {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }*/

}