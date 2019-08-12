package tj.manu.ratingfriends.data.firebase.listeners

import tj.manu.ratingfriends.data.firebase.model.Question

interface QuestionDataListener {
    fun onQuestionDataLoaded(question: Question)

    fun onQuestionsDataLoaded(questionList: List<Question>)
}