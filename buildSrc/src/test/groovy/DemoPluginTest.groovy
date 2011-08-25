import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test


public class DemoPluginTest {

	@Test
	public void shouldSetProperty1ToORIGINAL() {
		Project project = ProjectBuilder.builder().build()
		
		project.apply plugin: DemoPlugin
		
		assertEquals("ORIGINAL", project.taskWithProperties.property1)
	}
	
	@Test
	public void shouldMakeTaskWithPropertiesDependOnAssembleTaskOnDependencyProject() {
		Project project = ProjectBuilder.builder().build()
		Project other = ProjectBuilder.builder().build()
		Task assembleTask = other.task("assemble")
		Task jarTask = other.task("jar")
		
		project.apply plugin: DemoPlugin
		
		project.convention.plugins.demo.dependencyProject = other
		
	    assertTrue(project.getTasks().getByName('taskWithProperties').dependsOn.contains(assembleTask));
	}
}
