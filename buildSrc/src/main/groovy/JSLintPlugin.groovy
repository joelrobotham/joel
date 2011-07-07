import org.gradle.api.Project
import org.gradle.api.Plugin

class JSLintPlugin implements Plugin<Project> {

    void apply(Project project) {

      project.configurations {
          jslint
      }

      project.dependencies {
          jslint 'com.googlecode.jslint4java:jslint4java-ant:1.4.7'
      }

      project.task('jslint') << {
        ant.taskdef(name: 'jslint', classname: 'com.googlecode.jslint4java.ant.JSLintTask', classpath: project.configurations.jslint.asPath)
        
        reportDir = "build/reports/jslint"
	project.file(reportDir).mkdirs()

        ant.jslint() {
          formatter(type: 'xml', destfile: "${reportDir}/jslint.xml")
          fileset(dir: 'src/main/js', includes: '**/*.js')
        }
      }
      
    }

}
