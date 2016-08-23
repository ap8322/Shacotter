package controllers

import java.sql.Timestamp
import java.util.Date

import com.google.inject.Inject
import jp.t2v.lab.play2.auth.AuthElement
import models.Forms._
import models.Tables._
import models._
import play.api.cache.CacheApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Controller

import scala.concurrent.Future
import scala.reflect.ClassTag

class TweetController @Inject()(val memberDAO: MemberDAO,
                                val tweetDAO: TweetDAO,
                                val cacheApi: CacheApi)
  extends Controller with AuthElement with AuthConfigImpl {

  /**
    * goto mytweet page
    *
    * @return
    */
  def index() = AsyncStack(AuthorityKey -> None) { implicit rs =>
    tweetDAO.selectMyTweet(loggedIn.memberId).map { tweet =>
      Ok(views.html.user.list(loggedIn.name, tweet, tweetForm))
    }
  }

  /**
    * goto profile page
    *
    * @param id
    * @return
    */
  def profile(id: Int) = AsyncStack(AuthorityKey -> None) { implicit rs =>
    tweetDAO.selectFriendTweet(loggedIn.memberId, id).flatMap { tweet =>
      memberDAO.findById(id).map { member =>
        Ok(views.html.user.list(member.get.name, tweet, tweetForm))
      }
    }
  }

  /**
    * tweet
    *
    * @return
    */
  def tweet() = AsyncStack(AuthorityKey -> None) { implicit rs =>
    tweetForm.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(Redirect(routes.TweetController.index))
      },
      form => {
        val tweet = TweetRow(0, Some(loggedIn.memberId), Some(form.tweet), new Timestamp(new Date().getTime))
        tweetDAO.add(tweet)
        Future.successful(Redirect(routes.TweetController.index))
      }
    )
  }

  /**
    * goto timeline page
    *
    * @return
    */
  def timeline() = AsyncStack(AuthorityKey -> None) { implicit rs =>
    tweetDAO.selectFollowerTweet(loggedIn.memberId).map { tweet =>
      Ok(views.html.user.list(loggedIn.name, tweet, tweetForm))
    }
  }
}
