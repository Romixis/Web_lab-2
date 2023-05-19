import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class Person(
    @SerializedName("name") val name: String,
    @SerializedName("number") val number: Int,
    @SerializedName("city") val city: Boolean
)

class JsonConverter {

    private val gson = Gson()

    fun toJson(person: Person): String {
        return gson.toJson(person)
    }

    fun fromJson(json: String): Person {
        return gson.fromJson(json, Person::class.java)
    }
}

class PersonTest {
    private val jConv = JsonConverter()

    @Test
    fun serializePerson() {
        val person = Person("Роман", 89995547895, Междуреченск)
        val json = jConv.toJson(person)
        assertEquals("""{"name":"Роман","number":89995547895,"city":Междуреченск}""", json)
        println("Cериализация: \nВходные данные: $person \nВыходные данные: $json")
        println()
    }

    @Test
    fun deserializePerson() {
        val json = """{"name":"Эдик","number":89995547895,"city":Кемерово}"""
        val person = jConv.fromJson(json)
        assertEquals(Person("Эдик", 89995547895, Кемерово), person)
        println("Десериализация: \nВходные данные: $json \nВыходные данные: $person")
        println()
    }
}