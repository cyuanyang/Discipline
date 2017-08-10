package com.cyy.gather

import com.android.build.api.transform.Context
import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Project
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils

public class GatherTransform extends Transform{

    Project project
    boolean isLibrary = false

    // 构造函数，我们将Project保存下来备用
    public GatherTransform(Project project) {
        this.project = project
    }
    public GatherTransform(Project project , boolean isLibrary) {
        this.project = project
        this.isLibrary = isLibrary
    }

    // 设置我们自定义的Transform对应的Task名称
    @Override
    String getName() {
        return "GatherTransform"
    }

    // 指定输入的类型，通过这里的设定，可以指定我们要处理的文件类型
    //这样确保其他类型的文件不会传入
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    // 指定Transform的作用范围
    @Override
    Set<QualifiedContent.Scope> getScopes() {
        if (isLibrary){
            return TransformManager.SCOPE_FULL_LIBRARY
        }else {
            return TransformManager.SCOPE_FULL_PROJECT
        }
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        println(" transform transform ")
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs,
                   Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider, boolean isIncremental)
            throws IOException, TransformException, InterruptedException {

        /**
         * Transform的inputs有两种类型，
         *  一种是目录， DirectoryInput
         *  一种是jar包，JarInput
         *  要分开遍历
         */
        inputs.each { TransformInput input ->
            /**
             * 对类型为“文件夹”的input进行遍历
             */
            input.directoryInputs.each {
                /**
                 * 文件夹里面包含的是
                 *  我们手写的类
                 *  R.class、
                 *  BuildConfig.class
                 *  R$XXX.class
                 *  等
                 *  根据自己的需要对应处理
                 */
                println("it == ${it}")
                //注入代码
                Inject.injectOnClick(it.file.absolutePath)
                // 获取output目录
                def dest = outputProvider.getContentLocation(it.name,
                        it.contentTypes, it.scopes,
                        Format.DIRECTORY)

                // 将input的目录复制到output指定目录
                FileUtils.copyDirectory(it.file, dest)
            }
            //对类型为jar文件的input进行遍历
            input.jarInputs.each { JarInput jarInput ->

                //jar文件一般是第三方依赖库jar文件

                // 重命名输出文件（同目录copyFile会冲突）
                def jarName = jarInput.name
                def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                //生成输出路径
                def dest = outputProvider.getContentLocation(jarName + md5Name,
                        jarInput.contentTypes, jarInput.scopes, Format.JAR)
                //将输入内容复制到输出
                FileUtils.copyFile(jarInput.file, dest)
            }
        }

    }
}