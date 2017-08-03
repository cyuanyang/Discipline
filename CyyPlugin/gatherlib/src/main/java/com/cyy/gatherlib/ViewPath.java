package com.cyy.gatherlib;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;

/**
 * Created by study on 17/7/19.
 * View Path
 */

public class ViewPath {

    private String path;

    public ViewPath(View view){
        path = getViewPath(view);
    }

    //计算出这个View在ViewTree中的位置
    public static String getViewPath(View view){

        StringBuffer builder = new StringBuffer();
        StringBuffer tempBuilder = new StringBuffer();
        View currentView = view;

        while (currentView.getParent() != null){
            ViewParent parent = currentView.getParent();
            if (parent instanceof View){
                if (parent instanceof ViewGroup){
                    String fragmentName = isFragmentContentView(currentView);
                    if (fragmentName != null){
                        tempBuilder.append(fragmentName).append("/");
                    }else {
                        String itemPath = parentIsList((View) parent, currentView);
                        if (TextUtils.isEmpty(itemPath)){
                            int index = ((ViewGroup) parent).indexOfChild(currentView);
                            tempBuilder.append(currentView.getClass().getSimpleName())
                                    .append("[").append(index).append("]/");
                        }else {
                            tempBuilder.append(itemPath).append("/");
                        }
                    }
                    builder.insert(0 , tempBuilder);
                    if (tempBuilder.length()>0)
                        tempBuilder.delete(0 , tempBuilder.length());
                }
                currentView = (View) parent;
                if (currentView.getId() == android.R.id.content){
                    //加载到了开发者的根布局
                    break;
                }
            }else {
                throw new IllegalStateException("ViewParent 非View或者它的子类");
            }
        }
        return builder.toString();
    }

    public String getPath() {
        return path;
    }

    /**
     * 判断是不是fragment 对包含fragment的ViewPath进行优化
     * @param view 当前View
     * @return 如果是fragment 则返回优化后的路径
     */
    private static String isFragmentContentView(View view){

        Fragment cf = Gather.gather().getCurrentFragment();
        if (cf != null){
            View cfv = cf.getView();
            if (cfv!=null && cfv == view){
                return cf.getClass().getSimpleName();
            }
        }

        return null;
    }

    /**
     * 对 list 类型的View 优化
     */
    private static String parentIsList(View parent , View currentView){
        try {
            if (parent instanceof ListView){
                int position = ((ListView) parent).getPositionForView(currentView);
                return "ListItemView["+position+"]";
            }else if (parent instanceof RecyclerView){

            }
        }catch (NoClassDefFoundError error){

        }
        return "";
    }
}
