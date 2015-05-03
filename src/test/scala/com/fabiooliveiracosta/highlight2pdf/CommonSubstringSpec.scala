package com.fabiooliveiracosta.highlight2pdf

import org.scalatest._
class CommonSubstringSpec extends UnitSpec {
	val haystack:String="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse porttitor luctus massa, quis pretium sapien tristique vitae. Cras non magna scelerisque, pretium ex quis, blandit lacus. Aenean lacinia aliquet tempus. Aenean suscipit eget quam in pharetra. Donec id velit sit amet velit pretium pretium. Donec et enim ac velit congue hendrerit. Cras tempus sodales dui quis gravida. Cras pellentesque ut sem eu ullamcorper. Aenean dictum, purus eget pulvinar semper, nulla magna suscipit erat, non vestibulum mi ante eget elit. Aliquam ut leo nisl. Fusce tristique vitae nisi a gravida. Vestibulum semper tristique turpis quis ultricies. Sed sit amet euismod quam. In hac habitasse platea dictumst. Nulla dapibus sagittis orci vitae cursus. Aliquam libero nisi, volutpat sit amet rhoncus eu, dapibus a lorem"
	"The longest common substring" should "be found if fully matched" in{
		val needle:String="Lorem ipsum dolor sit amet"
		val comparison=CommonSubstring.findSegment(needle,haystack)
		assert(needle==comparison)
  	}
  	"The longest common substring" should "be found if partialy matched" in{
  		val baseNeedle:String="volutpat sit amet rhoncus eu, dapibus a lorem"
  		val comparison=CommonSubstring.findSegment(baseNeedle+"Some stuff that should never be found",haystack)
		assert(baseNeedle==comparison)
	 }
}