package com.cyy.gather

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class MyAdviceAdapter extends AdviceAdapter implements Opcodes , GatherDescs{

    private String name;
    private String desc;
    private boolean  isFragment
    private boolean customGather = false; //自定义的收集

    protected MyAdviceAdapter(int api, MethodVisitor mv, int access, String name, String desc , boolean  isFragment) {
        super(api, mv, access, name, desc)
        this.name = name;
        this.desc = desc;
        this.isFragment = isFragment
    }

    @Override
    public void visitCode() {
        super.visitCode();

    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if ("Lcom/cyy/gatherlib/annotation/Gather" == desc){
            println("注解需要注入的方法")
            customGather = true;
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc);

        println("visitFieldInsn owner == $owner name = $name desc = $desc");
    }

    @Override
    protected void onMethodEnter() {
        println("onMethodEnter")
        //注入的点击事件
        if (name == "onClick" && desc == "(Landroid/view/View;)V"){
            //注入View.OnClick()
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC , "com/cyy/gatherlib/Inject" , "onClickView" ,
                    "(Landroid/view/View;)V" , false)
        }

        //custom gather method
        if (customGather){

        }
    }

    @Override
    protected void onMethodExit(int i) {
        println("onMethodExit")
        println("name = $name desc = $desc")
        if (isFragment){
            dealFragment()
        }
    }

    /**
     * 处理fragment的方法
     */
    private void dealFragment(){
        if (name == "onViewCreated" && desc == "(Landroid/view/View;Landroid/os/Bundle;)V"){
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onFragmentCreatedView", "(Landroid/support/v4/app/Fragment;Landroid/view/View;)V", false);
        }
        else if (name == "onHiddenChanged" && desc == "(Z)V") {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onHiddenChanged", "(Landroid/support/v4/app/Fragment;Z)V",false)
        }
        else if ((name == "onResume" || name == "onPause") && desc == "()V"){
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(name == "onResume" ? ICONST_1 : ICONST_0);
            mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onFragmentResumeOrPause", "(Landroid/support/v4/app/Fragment;Z)V", false);
        }else if (name == "setUserVisibleHint" && desc == "(Z)V"){
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, INJECT_OWNER, "onFragmentSettUserVisibleHint", "(Landroid/support/v4/app/Fragment;Z)V", false);
        }
    }
}