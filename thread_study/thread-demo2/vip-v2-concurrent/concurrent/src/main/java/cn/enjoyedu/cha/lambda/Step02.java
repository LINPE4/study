package cn.enjoyedu.cha.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：行为参数化
 */
public class Step02 {

    /*定义挑选圆的行为接口*/
    static interface ChoiceCircle{
        boolean getCircle(Circle circle);
    }


    /*行为参数化，根据条件挑选出圆*/

    public static List<Circle> getCircleByChoice(List<Circle> circles,
                                                 ChoiceCircle choice){
        List<Circle> circleList = new ArrayList<>();
        for(Circle circle :circles){
            if(choice.getCircle(circle)){
                circleList.add(circle);
            }
        }
        return circleList;
    }
    /*挑选圆的行为实现之一，选出红色的圆*/
    static class CircleByRed implements ChoiceCircle{

        @Override
        public boolean getCircle(Circle circle) {
            return "Red".equals(circle.getColor());
        }
    }

    /*挑选圆的行为实现之二，选出半径为2的圆*/
    static class CircleByRadiusTwo implements ChoiceCircle{

        @Override
        public boolean getCircle(Circle circle) {
            return circle.getRadius()==2;
        }
    }

    public static void service(){
        List<Circle> src = new ArrayList<>();/*待处理的圆的集合*/
        List<Circle> result =  getCircleByChoice(src, new CircleByRed());
    }

    /*疑问:每增加一个挑选行为，就需要声明一个接口ChoiceCircle的实现类，
    于是我们可以考虑使用内部匿名类，看step03 */
}
