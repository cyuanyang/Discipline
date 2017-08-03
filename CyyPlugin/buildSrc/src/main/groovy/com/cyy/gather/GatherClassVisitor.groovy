package com.cyy.gather

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class GatherClassVisitor extends ClassVisitor implements Opcodes ,GatherDescs{

    def METHOD_FOR_FRAGMENT = ["onResume",
                               "onPause" ,
                               "onHiddenChanged" ,
                               "onViewCreated", //优化ViewPath
                                "onFragmentSettUserVisibleHint",
    ];

    def isFragment = false //是否是fragment 目前没有想到办法判断是否是一个fragment 所以用了一下注解
    def methodList = METHOD_FOR_FRAGMENT //方法列表

    def superName = "";

    GatherClassVisitor(int i) {
        super(i)
    }

    GatherClassVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        println("visitMethod  name == $name desc = $desc signature = $signature")
        if (METHOD_FOR_FRAGMENT.contains(name)){
            methodList.remove(name);
        }
        def methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        methodVisitor = new MyAdviceAdapter(ASM5 , methodVisitor , access , name , desc , isFragment)
        return methodVisitor
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces)
        this.superName = superName;
        println("visit =$name superName = $superName" )
    }

    @Override
    AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (ANNOTATION_FRAGMENT == desc){
            isFragment = true
        }
        return super.visitAnnotation(desc, visible)
    }

    @Override
    void visitEnd() {
        super.visitEnd()
        this.dealFragment()
    }


    @Override
    FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        println("visitField name == $name  desc = $desc value = $value")
        return super.visitField(access, name, desc, signature, value)
    }

    @Override
    void visitAttribute(Attribute attr) {
        super.visitAttribute(attr)
    }


    @Override
    void visitSource(String source, String debug) {
        super.visitSource(source, debug)

        println("source == $source")
    }

    @Override
    void visitOuterClass(String owner, String name, String desc) {
        super.visitOuterClass(owner, name, desc)
    }

    /**
     * 访问完毕之后 若没有 METHOD_FOR_FRAGMENT 中的方法需要添加
     *
     *
     */
    //todo desc 判断
    private void dealFragment(){
        if (isFragment && !methodList.isEmpty()) {
            //如果是fragment 统计显示与隐藏 需要注入一些必要方法
            methodList.each {
                if (it == "onResume" || it == "onPause"){
                    MethodVisitor mv = cv.visitMethod(ACC_PUBLIC , it, "()V", null, null)
                    mv.visitVarInsn(ALOAD, 0)
                    mv.visitMethodInsn(INVOKESPECIAL, superName, it, "()V", false)
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitInsn(it == "onResume" ? ICONST_1 : ICONST_0);
                    mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onFragmentResumeOrPause", "(Landroid/support/v4/app/Fragment;Z)V", false);
                    mv.visitInsn(RETURN)
                    mv.visitMaxs(1, 1)
                    mv.visitEnd()
                } else if (it == "onHiddenChanged"){
                    MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "onHiddenChanged", "(Z)V", null, null)
                    mv.visitCode()
                    mv.visitVarInsn(ALOAD, 0)
                    mv.visitVarInsn(ILOAD, 1)
                    mv.visitMethodInsn(INVOKESPECIAL, superName, "onHiddenChanged", "(Z)V", false)
                    mv.visitVarInsn(ALOAD, 0)
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onHiddenChanged", "(Landroid/support/v4/app/Fragment;Z)V", false);
                    mv.visitInsn(RETURN)
                    mv.visitMaxs(2, 2)
                    mv.visitEnd()
                }else if (it == "onViewCreated"){
                    MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "onViewCreated", "(Landroid/view/View;Landroid/os/Bundle;)V", null, null);
                    mv.visitCode();
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitVarInsn(ALOAD, 2);
                    mv.visitMethodInsn(INVOKESPECIAL, superName, "onViewCreated", "(Landroid/view/View;Landroid/os/Bundle;)V", false);
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onFragmentCreatedView", "(Landroid/support/v4/app/Fragment;Landroid/view/View;)V", false);
                    mv.visitInsn(RETURN);
                    mv.visitMaxs(3, 3);
                    mv.visitEnd();
                }else if (it == ""){
                    MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "setUserVisibleHint", "(Z)V", null, null);
                    mv.visitCode();
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitMethodInsn(INVOKESPECIAL, superName, "setUserVisibleHint", "(Z)V", false);
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onFragmentSettUserVisibleHint", "(Landroid/support/v4/app/Fragment;Z)V", false);

                    mv.visitInsn(RETURN);
                    mv.visitMaxs(2, 2);
                    mv.visitEnd();
                }
            }
        }
    }
}