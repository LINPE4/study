package cn.enjoyedu.cha.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：使用内部匿名类和Lambda表达式
 */
public class Step03 {

    /*定义挑选圆的行为接口*/
    interface ChoiceCircle{
        boolean getCircle(Circle circle);
    }

    /*根据条件挑选出圆的方法*/
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

    public static void service(){
        List<Circle> src = new ArrayList<>();/*待处理的圆的集合*/
        List<Circle> radiusTwos =  getCircleByChoice(src, new ChoiceCircle() {
            @Override
            public boolean getCircle(Circle circle) {
                return circle.getRadius()==2;
            }
        });

        List<Circle> reds =  getCircleByChoice(src, new ChoiceCircle() {
            @Override
            public boolean getCircle(Circle circle) {
                return "Red".equals(circle.getColor());
            }
        });

        List<Circle> radiusTwos2 = getCircleByChoice(src,
                (Circle circle) -> circle.getRadius()==2);

        List<Circle> reds2 = getCircleByChoice(src
                ,(Circle circle) -> "Red".equals(circle.getColor()));


        /*所以可以把Lambda表达式看成匿名内部类的一个简洁写法
        * 在语法上，Lambda表达式包含三个部分:
        * 参数列表，箭头，主体，比如：
        *  (parameters) -> expression
        *  或
        *  (parameters) -> ｛statements;｝
        *  */

        /*Lambda表达式用在函数式接口上，
        所谓函数式接口，是只定义了一个抽象方法的接口（Interface），
        接口中是否有默认方法，不影响
        注解@FunctionalInterface可以帮助我们在设计函数式接口时防止出错。
        我们常用的Runnable,Callable都是函数式接口
        JDK8中新增了几个函数式接口
        Predicate<T> :
        包含test方法，接受泛型的T，返回boolean，可以视为断言（检查）接口
        Consumer<T> :
        包含accept方法，接受泛型的T，无返回，可以视为数据消费接口
        Function<T，R> :
        包含apply方法，接受泛型的T，返回R，可以视为映射转换接口
        当然新增的函数式接口不止这些
        * */

    }




}
