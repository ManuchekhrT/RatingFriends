package tj.manu.ratingfriends.ui.game

import tj.manu.ratingfriends.data.firebase.FirebaseHelper
import tj.manu.ratingfriends.data.firebase.listeners.QuestionDataListener
import tj.manu.ratingfriends.data.firebase.model.Question
import tj.manu.ratingfriends.ui.game.GameMVP

class GameModel(var presenter: GameMVP.Presenter) : GameMVP.Model, QuestionDataListener {
    override fun onQuestionsDataLoaded(questionList: List<Question>) {

    }

    override fun onQuestionDataLoaded(question: Question) {
    }

    override fun sendAnswer(friendId: String?, currentQuestionIndex: Int) {

    }

    override fun getNextQuestion(currentQuestionIndex: Int) {
        FirebaseHelper.instance().getQuestion(currentQuestionIndex + 1, this)
    }
}