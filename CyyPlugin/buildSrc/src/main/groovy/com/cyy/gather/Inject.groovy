package com.cyy.gather

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

//字节码注入
public class Inject{

    /**
     * 为自己写的所有点击事件注入代码
     * @param path
     * @param packageName
     */
    public static void injectOnClick(String path){
        File dir = new File(path)
        if (dir.isDirectory()){

            dir.eachFileRecurse {
                //寻找目标类
                String filePath = it.absolutePath
                println("file path == $filePath")
                if (filePath.endsWith(".class")
                        && !filePath.contains('R$')
                        && !filePath.contains('R.class')
                        && !filePath.contains("BuildConfig.class")) {
//                     判断当前目录是否是在我们的应用包里面
//                    int index = filePath.indexOf(packageName)
//                    boolean isMyPackage = index != -1
                    if (true) {
                        println(" －－－－ $it.absolutePath")
                        ClassReader classReader = new ClassReader(it.bytes)
                        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS)
                        ClassVisitor cv = new GatherClassVisitor(Opcodes.ASM5 , classWriter)
                        classReader.accept(cv, ClassReader.EXPAND_FRAMES)

                        byte[] code = classWriter.toByteArray()
                        FileOutputStream fos = new FileOutputStream(
                                it.parentFile.absolutePath + it.separator + it.name)
                        fos.write(code)
                        fos.close()
                    }
                }
            }
        }
    }
}