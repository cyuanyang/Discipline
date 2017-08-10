package com.cyy.gather

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class GatherPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def android = project.extensions.findByType(AppExtension)
        if (android!=null){
            android.registerTransform(new GatherTransform(project))
        }else {
            def lib = project.extensions.findByType(LibraryExtension)
            lib.registerTransform(new GatherTransform(project , true))
        }
    }
}


