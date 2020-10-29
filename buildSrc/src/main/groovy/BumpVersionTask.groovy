import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class BumpVersionTask extends DefaultTask {

    @Input
    String source = 'VERSION'

    @Input
    Boolean snapshot = false

    @TaskAction
    void run () {
        def version = loadVersion ()

        def parts = version.split ('-')
        def numbers = parts.first ().split ('\\.')
        def lastIndex = numbers.size () - 1
        numbers [lastIndex] = (numbers [lastIndex] as Integer) + 1

        def nextVersion = numbers.join ('.')
        if (parts.contains ('-SNAPSHOT') || snapshot) {
            nextVersion += '-SNAPSHOT'
        }

        saveVersion (nextVersion)
    }

    String loadVersion() {
        return getProject().file(source).readLines ().first ()
    }

    private void saveVersion(String version) {
        getProject().file(source).text = version
    }

}
