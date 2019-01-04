package cn.wx.Enum;

public enum MyEnum {
	state_one,
	state_two,
	state_three;
	
	public static void main(String[] args) {
		System.out.println(MyEnum.state_one);
		
		System.out.println(MyEnum.state_one.ordinal());
		System.out.println(MyEnum.state_three.ordinal());
		
		System.out.println(MyEnum.state_two.compareTo(MyEnum.state_one));
		System.out.println(MyEnum.state_two.compareTo(MyEnum.state_three));
	}
}
