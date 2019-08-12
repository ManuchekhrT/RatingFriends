package tj.manu.ratingfriends.data.firebase.model

data class User(
    var id: String? = null,
    var name: String? = null,
    var photo: String? = null,
    var isOnline: String? = null,
    var hasApp: String? = null
)