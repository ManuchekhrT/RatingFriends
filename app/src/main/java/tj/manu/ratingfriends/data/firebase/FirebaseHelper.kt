package tj.manu.ratingfriends.data.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import tj.manu.ratingfriends.data.firebase.listeners.QuestionDataListener
import tj.manu.ratingfriends.data.firebase.model.Question
import java.util.ArrayList


class FirebaseHelper {
    companion object {
        private const val USERS_TABLE = "Users"
        private const val QUESTIONS_TABLE = "Questions"

        private val instance = FirebaseHelper()
        fun instance(): FirebaseHelper {
            return instance
        }
    }

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val friendsTableReference = firebaseDatabase.getReference(USERS_TABLE)
    private val questionsTableReference = firebaseDatabase.getReference(QUESTIONS_TABLE)


    fun getQuestion(questionIndex: Int, questionDataListener: QuestionDataListener) {
        questionsTableReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val question: Question = if (dataSnapshot.child(questionIndex.toString()).exists()) {
                    getQuestionFromFirebaseChild(dataSnapshot.child(questionIndex.toString()))
                } else {
                    getQuestionFromFirebaseChild(dataSnapshot.child("1"))
                }
                questionDataListener.onQuestionDataLoaded(question)
            }

            private fun getQuestionFromFirebaseChild(dataSnapshot: DataSnapshot): Question {
                val question = Question()
                if (dataSnapshot.children.iterator().hasNext()) {
                    val firebaseQuestion = dataSnapshot.children.iterator().next()
                    question.text = firebaseQuestion.key
                    question.emoji = firebaseQuestion.value!!.toString()
                }
                return question
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun getQuestions(questionIdList: List<String>, questionDataListener: QuestionDataListener) {
        questionsTableReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val questions = ArrayList<Question>()
                for (id in questionIdList) {
                    val question = getQuestionFromFirebaseChild(dataSnapshot.child(id))
                    questions.add(question)
                }
                questionDataListener.onQuestionsDataLoaded(questions)
            }

            private fun getQuestionFromFirebaseChild(dataSnapshot: DataSnapshot): Question {
                val question = Question()
                if (dataSnapshot.children.iterator().hasNext()) {
                    val firebaseQuestion = dataSnapshot.children.iterator().next()
                    question.text = firebaseQuestion.key
                    question.emoji = firebaseQuestion.value!!.toString()
                }
                return question
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

}