import play.api.GlobalSettings
import com.google.inject.{Guice, AbstractModule}
import com.events.dependencyinjection._
//import services.{WelcomeTextGenerator, TextGenerator}

object Global extends GlobalSettings {
  lazy val injector = Guice.createInjector(new ConfigModule)

  override def getControllerInstance[A](controllerClass: Class[A]): A = injector.getInstance(controllerClass)
}
