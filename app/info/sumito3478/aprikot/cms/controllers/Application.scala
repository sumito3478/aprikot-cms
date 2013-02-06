package info.sumito3478.aprikot.cms.controllers

import play.api._
import play.api.mvc._

import info.sumito3478.aprikot._
import info.sumito3478.aprikot.cms._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
}