package com.fabiooliveiracosta.highlight2pdf
import scala.util.control.Breaks

object CommonSubstring{
	/**
	*Finds the segment of a needle on a haystack assuming that needle is started on haystack always
	*/
	def findSegmentPosition(needle:String,haystack:String):String={
		var start:Int=0
		var max:Int=0
		val needleLength:Int=needle.length()
		val haystackLength:Int=haystack.length()
		var j:Int=0
		var x:Int=0
		for(j<-0 until haystackLength){
			x=0
			def loopNeedle{
				if(x>=needleLength || (j+x)>=haystackLength){
					return
				}
				if(needle.charAt(x)==haystack.charAt(j+x)){
					x+=1
				}
				else{
					return
				}
				loopNeedle
			}
			loopNeedle

			if(x>max){
				max=x
				start=j
			}
			//Already found match
			if(max==needleLength){
				return haystack.substring(start,(start+max))
			}
		}
		
		haystack.substring(start,(start+max))
	}
}