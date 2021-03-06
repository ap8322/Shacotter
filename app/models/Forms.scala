package models

import models.Tables.ImageRow
import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by yuki.haneda on 2016/08/10.
  */
object Forms {

  // todo バリデーションを固める｡

  case class Follower(memberId: Long, memberName: String, icon: Option[ImageRow], isFollowed: Boolean)

  case class LoginForm(email: String, password: String)

  //ログインユーザの情報(名前､アイコン画像)
  case class MemberInfo(memberId: Long, memberName: String, icon: Option[ImageRow])

  // ツイートした人の名前、ツイートのユニークID、ツイートの内容、いいねの数、悪いねの数､現在の評価状態
  case class TweetInfo(memberName: String, icon: Option[ImageRow], tweet_id: Long, tweet: String, goodCount: Int, badCount: Int, currentState: String)

  val loginForm = Form(
    mapping(
      "email" -> nonEmptyText(maxLength = 100),
      "password" -> nonEmptyText(maxLength = 100)
    )(LoginForm.apply)(LoginForm.unapply)
  )

  case class Tweet(tweet: String)

  val tweetForm = Form(
    mapping(
      "tweet" -> nonEmptyText(maxLength = 140)
    )(Tweet.apply)(Tweet.unapply)
  )

  case class StatusForm(name: String, email: String, password: String)

  val statusForm = Form(
    mapping(
      "name" -> nonEmptyText(maxLength = 20),
      "email" -> nonEmptyText(maxLength = 200),
      "password" -> nonEmptyText(maxLength = 200)
    )(StatusForm.apply)(StatusForm.unapply)
  )
}
