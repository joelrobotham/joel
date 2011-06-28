import org.gradle.api.Project
import org.gradle.api.Plugin

class JRebelPlugin implements Plugin<Project> {
  def void apply(Project project) {

    project.task('copyJrebel') << {
       def file = """<?xml version="1.0" encoding="UTF-8"?>
<application
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://www.zeroturnaround.com"
  xsi:schemaLocation="http://www.zeroturnaround.com http://www.zeroturnaround.com/alderaan/rebel-2_0.xsd">
  <classpath>
    <dir name="${project.rootDir}/bin"/>
    <dir name="${project.rootDir}/src/main/resources"/>
  </classpath>
</application>"""
       project.file("build/classes/main/rebel.xml").append(file)
    }

    project.jar.doFirst {
      project.copyJrebel.execute()
    }
  }

}
