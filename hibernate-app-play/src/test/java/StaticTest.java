/**
 * 第一执行类中的静态代码，包括静态成员变量的初始化和静态语句块的执行；
 * 第二执行类中的非静态代码，包括非静态成员变量的初始化和非静态语句块的执行，最后执行构造函数。
 * 在继承的情况下，会首先执行父类的静态代码，然后执行子类的静态代码；之后执行父类的非静态代码和构造函数；
 * 最后执行子类的非静态代码和构造函数.
 * （静态变量、静态初始化块）>（变量、初始化块）>构造器
 * 
 * 结果：
 * 	1:j	i=0	n=0
        2:构造块	i=1	n=1
        3:s1	i=2	n=2
        4:j	i=3	n=3
        5:构造块	i=4	n=4
        6:s2	i=5	n=5
        7:i	i=6	n=6
        8:静态块	i=7	n=99
        9:j	i=8	n=100
        10:构造块	i=9	n=101
        11:init	i=10	n=102
        
  将public static int i = print("i");和public static int n = 99;放在s1，s2前面的运行结果如下：
        1:i	i=0	n=0
        2:j	i=1	n=99
        3:构造块	i=2	n=100
        4:s1	i=3	n=101
        5:j	i=4	n=102
        6:构造块	i=5	n=103
        7:s2	i=6	n=104
        8:静态块	i=7	n=105
        9:j	i=8	n=106
        10:构造块	i=9	n=107
        11:init	i=10	n=108
        
   总结： 刚进入该类中，还未执行前，已经加载静态变量和静态代码块，然后按顺序执行静态变量和静态代码块：
   	1.静态变量会按照声明的顺序先依次声明并设置为该类型的默认值，但不赋值为初始化的值；
   		声明完毕后,再按声明的顺序依次设置为初始化的值，如果没有初始化的值就跳过。
   		其中StaticTest s1和StaticTest s2为静态变量，所以赋值的同时去实例化。

	2、当去实例化或调用类对象时，先会为非静态变量赋初值，然后再执行代码块
	
       	其中静态代码块只执行一次。构造代码块在每次创建对象是都会执行。静态代码块，静态变量 > mian方法>构造代码块>构造方法。
 */

public class StaticTest {
	public static int k = 0;
	public static StaticTest s1 = new StaticTest("s1");
	public static StaticTest s2 = new StaticTest("s2");
	public static int i = print("i");
	public static int n = 99;
	public int j = print("j");
	
	{
		print("构造块");
	}
	
	static {
		print("静态块");
	}
	
	public static int print(String s) {
		System.out.println(++k + ":" + s + "\ti=" + i + "\tn=" + n);
		++n;
		return ++i;
	}
	
	public StaticTest(String s) {
		System.out.println(++k + ":" + s + "\ti=" + i + "\tn=" + n);
		++i;
		++n;
	}
	
	public static void main(String[] args) {
		new StaticTest("init");
	}
}
