package com.tsunderebug.spotifyi3

import java.io.{BufferedReader, InputStreamReader}

import net.liftweb.json.JsonAST.JValue
import net.liftweb.json._

import scala.io.Source

object Bar {

  def main(args: Array[String]): Unit = {
    implicit val formats: Formats = DefaultFormats
    val br = new BufferedReader(new InputStreamReader(System.in))
    while(true) {
      while(!br.ready()) {
        Thread.sleep(100)
      }
      val line = br.readLine()
      if(line.startsWith(",") || (line.length > 1 && line.startsWith("["))) {
        val json = if(line.startsWith(",")) line.drop(1) else line
        val bos = parse(json).extract[List[BarOption]]
        val sc = new ProcessBuilder("spotifycli", "--status").start()
        val ss = Source.fromInputStream(sc.getInputStream).getLines().mkString("\n")
        println(s"${if(line.startsWith(",")) "," else ""}${compactRender(JArray((BarOption(
          name = "spotify",
          color = Some("#6AE368"),
          full_text = s"\uD83C\uDFB5 $ss"
        ) :: bos).map(Extraction.decompose)))}")
      } else {
        println(line)
      }
    }
  }

}
