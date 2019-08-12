package tj.manu.ratingfriends.ui.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tj.manu.ratingfriends.R
import tj.manu.ratingfriends.ui.friends.Friend

class GameFragment : Fragment(), GameMVP.View {

    private lateinit var presenter: GameMVP.Presenter

    override fun onAnswerSubmitted() {


    }

    override fun displayNewSetOfFriends(currentFriendsSet: MutableList<Friend>?) {
    }

    override fun displayNewQuestion(currentQuestionIndex: Int, text: String?, emoji: String?) {
        Log.d("DisplayNewQuestion", " $text$emoji")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = GamePresenter()
        presenter.bindView(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.loadNewQuestion()
    }
}