import org.gradle.api.Project
import org.gradle.api.Plugin

class ReleasePlugin implements Plugin<Project> {
  def void apply(Project project) {
    project.task('release') << {
	println "performing release"
    }
  }
}
