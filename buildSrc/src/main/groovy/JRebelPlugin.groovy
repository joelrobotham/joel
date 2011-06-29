import org.gradle.api.Project
import org.gradle.api.Plugin

class JRebelPlugin implements Plugin<Project> {
  def void apply(Project project) {

    project.task('copyJRebelJarXml') << {
       println "creating jrebel jar config"
       def file = """<?xml version="1.0" encoding="UTF-8"?>
<application
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://www.zeroturnaround.com"
  xsi:schemaLocation="http://www.zeroturnaround.com http://www.zeroturnaround.com/alderaan/rebel-2_0.xsd">
  <classpath>
    <dir name="${project.projectDir}/bin"/>
    <dir name="${project.projectDir}/src/main/resources"/>
  </classpath>
</application>"""
       project.file("build/classes/main/rebel.xml").append(file)
    }

    project.copyJRebelJarXml.group = 'build'
    project.copyJRebelJarXml.description = 'Generated a jrebel.xml file and put it in the build/main/classes directory, ready to be jarred up'

    project.task('copyJRebelWarXml') << {
      println "creating jrebel war config"
      def fileContents = """<?xml version="1.0" encoding="UTF-8"?>
<application
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://www.zeroturnaround.com"
  xsi:schemaLocation="http://www.zeroturnaround.com http://www.zeroturnaround.com/alderaan/rebel-2_0.xsd">
  <classpath>
    <dir name="${project.projectDir}/bin"/>
  </classpath>
  <web>
    <link target="/">
      <dir name="${project.projectDir}/src/main/webapp"/>
    </link>
  </web>
</application>"""
     project.file("build/classes/main/rebel.xml").append(fileContents)
    }
   
    project.copyJRebelWarXml.group = 'build'
    project.copyJRebelWarXml.description = 'Generated a jrebel.xml file and put it in the build/main/classes directory, ready to be jarred up'


    project.gradle.taskGraph.whenReady {taskGraph ->
      if (!taskGraph.hasTask(':api:release')) {
          if (project.tasks.findByName('jar') != null) {
            project.jar.doFirst {
              project.copyJRebelJarXml.execute()
            } 
          }
          if (project.tasks.findByName('war') != null) {
            project.war.doFirst {
              project.copyJRebelWarXml.execute()
            } 
          }

      }
  }
  }

}
