package com.cyy.gather

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class GatherPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def android = project.extensions.findByType(AppExtension)
        android.registerTransform(new GatherTransform(project))
    }
}


