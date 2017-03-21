abstract class Notification

case class Email(emailFrom: String, subject: String) extends Notification
case class SMS(fromNumber: String, message: String) extends Notification

def showNotification(notification: Notification): String = {
    notification match {
        case Email(emailFrom, subject) =>
            "You got an email from " + emailFrom + " with subject: " + subject
        case SMS(fromNumber, message) =>
            "You got an SMS from number " + fromNumber + " Message: " + message
    }
}


val someSms = SMS("12345", "Hello")
val notification = showNotification(someSms)
println(notification)
