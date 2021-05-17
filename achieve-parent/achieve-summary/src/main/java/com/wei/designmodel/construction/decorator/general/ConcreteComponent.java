package com.wei.designmodel.construction.decorator.general;

public class ConcreteComponent extends Component {
  
    @Override
    public void operation() {
        //相应的功能处理
        System.out.println("处理业务逻辑");
    }  
  
}  