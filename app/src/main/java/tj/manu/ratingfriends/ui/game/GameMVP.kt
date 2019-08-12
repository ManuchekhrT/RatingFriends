package tj.manu.ratingfriends.ui.game

import tj.manu.ratingfriends.ui.friends.Friend
import tj.manu.ratingfriends.data.firebase.model.Question


interface GameMVP {
    interface Model {
        fun getNextQuestion(currentQuestionIndex: Int)
        fun sendAnswer(friendId: String?, currentQuestionIndex: Int)
    }

    interface View {
        fun onAnswerSubmitted()
        fun displayNewSetOfFriends(currentFriendsSet: MutableList<Friend>?)
        fun displayNewQuestion(currentQuestionIndex: Int, text: String?, emoji: String?)
    }

    interface Presenter {
        fun bindView(view: View)
        fun loadNewSetOfFriends()
        fun loadNewQuestion()
        fun submitAnswer(friendIndex: Int)
        fun updateCurrentSetOfFriends(friends: List<Friend>)
        fun updateCurrentQuestion(question: Question)

    }
}