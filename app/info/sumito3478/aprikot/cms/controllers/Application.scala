/* Copyright (C) 2013 sumito3478 <sumito3478@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.sumito3478.aprikot.cms.controllers

import play.api._
import play.api.mvc._
//import info.sumito3478.aprikot._
import info.sumito3478.aprikot.cms._
import info.sumito3478.aprikot.classic._
import play.api.data._
import play.api.data.Forms._
import scala.xml._
import play.api.templates._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val latinForm = Form("latin" -> text)

  def analyzeLatin = Action {
    implicit request =>
      val latin = latinForm.bindFromRequest.get
      val analyses = LatinTextAnalysis(latin).toVector
      import info.sumito3478.aprikot.text.StringW
      val words = latin.neutralWordIterator.toVector
      val wordsMap = (for (i <- 0 until words.length) yield (words(i) -> i)).toMap
      val html = <div>
                   <div><h1>{ for (i <- 0 until words.length) yield (<a href={ "#latin-word-" + words(i) + i }>{ words(i) }</a>) }</h1></div>
                   {
                     for (i <- 0 until analyses.length) yield {
                       val a = analyses(i).inflectionAnalyses
                       if (a.isEmpty) {
                         <div></div>
                       } else {
                         <div>
                           <h2 id={ "latin-word-" + analyses(i).inflectionAnalyses.head.inflected.underlined + i }>{ analyses(i).inflectionAnalyses.head.inflected.underlined }</h2>{ analyses(i).toHtml }
                         </div>
                       }
                     }
                   }
                 </div>
      Ok(views.html.latin(latin, Html(new PrettyPrinter(80, 2).format(html))))
  }

  def latin = Action {
    Ok(views.html.latinAnalysis(latinForm))
  }
}