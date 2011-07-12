import org.gradle.api.Project
import org.gradle.api.Plugin

class DemoPlugin implements Plugin<Project> {

    void apply(Project project) {

      project.task('taskWithProperties') << {
        println "This task has a property property1 = ${property1}"
      }

      project.taskWithProperties.property1 = "ORIGINAL"
    }

}

