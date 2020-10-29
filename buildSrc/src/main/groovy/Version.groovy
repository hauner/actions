import org.gradle.api.Project

class Version {

    static String loadVersion(Project project) {
       return project.file('VERSION').readLines ().first ()
    }

}

