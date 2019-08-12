package tj.manu.ratingfriends.ui.game

import tj.manu.ratingfriends.ui.friends.Friend
import tj.manu.ratingfriends.data.firebase.model.Question

class GamePresenter : GameMVP.Presenter {

    private var view: GameMVP.View? = null

    private var currentQuestionIndex: Int = 0
    private var currentFriendsSetNumber: Int = 0
    private var currentFriendsSet: MutableList<Friend>? = null

    private var model: GameMVP.Model? = null

    init {
        this.model = GameModel(this)
    }
    override fun bindView(view: GameMVP.View) {
        this.view = view
        currentQuestionIndex = 0
        currentFriendsSetNumber = 1
        currentFriendsSet = mutableListOf()
    }

    override fun loadNewSetOfFriends() {
    }

    override fun loadNewQuestion() {
        model?.getNextQuestion(currentQuestionIndex)
        currentQuestionIndex++
    }

    override fun submitAnswer(friendIndex: Int) {
        val friendId = currentFriendsSet?.get(friendIndex - 1)?.id
        model?.sendAnswer(friendId, currentQuestionIndex)
        view?.onAnswerSubmitted()
    }

    override fun updateCurrentSetOfFriends(friends: List<Friend>) {
        this.currentFriendsSetNumber++
        this.currentFriendsSet?.clear()
        this.currentFriendsSet?.addAll(friends)
        view?.displayNewSetOfFriends(currentFriendsSet)
    }

    override fun updateCurrentQuestion(question: Question) {
        view?.displayNewQuestion(currentQuestionIndex, question.text, question.emoji)
    }
}