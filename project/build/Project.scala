import sbt._

import reaktor.scct.ScctProject

class MyProject(info: ProjectInfo) extends DefaultWebProject(info) with ScctProject {
    override def compileOptions = super.compileOptions ++ compileOptions("-Xcheckinit")

    def specs2Framework = new TestFramework("org.specs2.runner.SpecsFramework")
    override def testFrameworks = super.testFrameworks ++ Seq(specs2Framework)

    val secVersion = "3.1.0.CI-SNAPSHOT"

    val seccore    = "org.springframework.security" % "spring-security-core" % secVersion % "compile->default" withSources()
    val secweb     = "org.springframework.security" % "spring-security-web" % secVersion % "compile->default" withSources()
    val secopenid  = "org.springframework.security" % "spring-security-openid" % secVersion % "compile->default" withSources()
    val seccfg     = "org.springframework.security" % "spring-security-config" % secVersion % "compile->default" withSources()
    val servletapi = "javax.servlet" % "servlet-api" % "2.5" % "compile->default" withSources()
    val scalatest  = "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test->default" withSources()
    val specs2     = "org.specs2" %% "specs2" % "1.4" % "test"
    val scalaz     = "org.specs2" %% "specs2-scalaz-core" % "6.0.RC2" % "test"

    val mockito    = "org.mockito" % "mockito-all" % "1.8.5" % "test->default"
    val jetty6    = "org.mortbay.jetty" % "jetty" % "6.1.26" % "test->default"

    val logback   = "ch.qos.logback" % "logback-classic" % "0.9.28" % "compile->default"
    val jcl       = "org.slf4j" %  "jcl-over-slf4j" % "1.6.1" % "runtime->default"

    val cglib     = "cglib" % "cglib-nodep" % "2.2.2" % "test->default"


    val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"

//    val scalaToolsReleases  = "releases" at "http://scala-tools.org/repo-releases"
}
