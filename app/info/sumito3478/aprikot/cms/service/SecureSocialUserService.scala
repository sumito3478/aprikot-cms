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
package info.sumito3478.aprikot.cms.service

import play.api.{ Logger, Application }
import securesocial.core.{ UserServicePlugin, UserId, SocialUser }
import scala.collection.mutable.HashMap
import securesocial.core.providers.Token

class SecureSocialUserService(
  application: Application) extends UserServicePlugin(application) {
  private val users = HashMap[String, SocialUser]()
  private val tokens = HashMap[String, Token]()

  def find(id: UserId) = {
    if (Logger.isDebugEnabled) {
      Logger.debug("users = %s".format(users))
    }
    users.get(id.id + id.providerId)
  }

  def findByEmailAndProvider(email: String, providerId: String): Option[SocialUser] = {
    if (Logger.isDebugEnabled) {
      Logger.debug("users = %s".format(users))
    }
    users.values.find(u => u.email.map(e => e == email && u.id.providerId == providerId).getOrElse(false))
  }

  def save(user: SocialUser) {
    users += (user.id.id + user.id.providerId -> user)
  }

  def save(token: Token) {
    tokens += (token.uuid -> token)
  }

  def findToken(token: String): Option[Token] = {
    tokens.get(token)
  }

  def deleteToken(uuid: String) {
    tokens -= uuid
  }

  def deleteTokens() {
    tokens.clear
  }

  def deleteExpiredTokens() {
    tokens.filter(!_._2.isExpired)
  }
}