import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.Task
import org.gradle.api.tasks.bundling.Jar

class DemoPlugin implements Plugin<Project> {

    void apply(Project project) {

      def demoConvention = new DemoPluginConvention(project);
      project.convention.plugins.demo = demoConvention

      project.task('taskWithProperties') << {
        println "This task has a property property1 = ${property1}"
        println "should be using the jar with ${demoConvention.dependencyTask.archivePath}"
      }

      project.taskWithProperties.property1 = "ORIGINAL"
    }

}

class DemoPluginConvention {
    private Project project
    private Task dependencyTask

    public DemoPluginConvention(Project project)  {
        this.project = project
    }

    void setDependencyProject(Project otherProject) {
        project.taskWithProperties.dependsOn otherProject.assemble 
        dependencyTask = otherProject.jar
    }

    def demo(Closure closure) {
        closure.delegate = this
        closure()
    }
}

