package tj.manu.ratingfriends.data.firebase.listeners

interface QuestionIdListener {
    fun onQuestionIdsLoaded(idList: List<String>)
}