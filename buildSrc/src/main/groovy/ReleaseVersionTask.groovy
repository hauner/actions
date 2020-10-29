import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

class ReleaseVersionTask extends DefaultTask {
    @Option(option="version", description="new version string")
    String version

    @Input
    String source = 'VERSION'

    @TaskAction
    void run() {
        if (!version) {
            loadVersion ()
        }

        stripPrefix ()
        stripSnapshot ()
        saveVersion ()
    }

    private void stripPrefix() {
        version = version.replace ('v', '')
    }

    private void stripSnapshot() {
        version = version.replace ('-SNAPSHOT', '')
    }

    void loadVersion() {
        version = getProject().file(source).readLines ().first ()
    }

    private void saveVersion() {
        getProject().file(source).text = version
    }

}
