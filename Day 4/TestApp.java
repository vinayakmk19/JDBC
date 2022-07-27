class Test{
	static{
		System.out.println("staticblock::TestClass");
	}
	Test(){
		System.out.println("ZeroParam-Constructor::TestClass");
        }
}
class Demo{
	static{
	      System.out.println("staticblock::DemoClass");
	}
	Demo(){
           System.out.println("ZeroParam-Constructor::DemoClass");
        }
}
public class TestApp{
        static{
	      System.out.println("staticblock::TestAppClass");
	}
	public static void main(String ...args)throws ClassNotFoundException{
              Demo d1=new Demo();

 	      
        }
}