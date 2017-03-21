abstract class Notification

case class Email(emailFrom: String, subject: String) extends Notification
case class SMS(fromNumber: String, message: String) extends Notification

val firstSms = SMS("12345", "Hello!")
val secondSms = SMS("12345", "Hello!")

if (firstSms == secondSms) {
    println("They are equal")
}
