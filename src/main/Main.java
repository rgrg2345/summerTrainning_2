/***********************************************************************
	FileName	[Main.java]
	PackageName	[main]
	JavaProjectName	[summerTrainning_2]
	synopsis	[]
	Author		[Cai Meng Ting]
	Copyright	[ Copyleft(c) 2014 MITLAB, GIEE, NTUST, Taiwan ]
/***********************************************************************/
package main;

public class Main {
	
	public static int input_year=0;
	public static int input_month=0;
	public static int input_day=0;
	private static java.util.Scanner scanner;
	
	public static void main(String[] argv)
	{
		String []string={"請輸入年月，回傳月天數","輸入年月日，回傳星期幾","輸入年月，回傳當月日曆","輸入年，傳回有幾個六日","判斷閏年"};
		
		System.out.println("Please select one function.");
		for(int i=0;i<5;i++)
			System.out.println((i+1)+" : "+string[i]);
		
		//建立掃描物件
		scanner = new java.util.Scanner(System.in);
		//建立日曆物件
		calendar my_cal= new calendar(input_year, input_month,input_day);

		
		while(true){			
			switch(scanner.nextInt()){
			case 1:
				getinput(2);
				System.out.println(input_year+"/"+input_month+" have "+ my_cal.getmonthday(input_year,input_month)+" days");
				break;
			case 2:
				getinput(3);
				my_cal= new calendar(input_year, input_month,input_day);//初始化物件
				if(my_cal.Check_Date()==false)//檢測輸入日期
					break;

				String week[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
				
				System.out.println(input_year+"/"+input_month+"/"+input_day+" : "+week[my_cal.GetDateWeek()]);
				break;
			case 3:
				getinput(2);
				my_cal= new calendar(input_year, input_month,input_day);//初始化物件
				my_cal.Print_Calendar();
				break;
			case 4:
				getinput(1);
				my_cal= new calendar(input_year, 1,input_day);//初始化物件
				System.out.println("There are "+my_cal.GetWeekend()+" weekend days in "+input_year);
				break;
			case 5:
				getinput(1);
				System.out.println(input_year+((my_cal.Isleap(input_year)==true)?" is":" isn't")+" leap year.");
				break;
			default:
				break;
			}
		}
	}
	
	//取得輸入值
	public static void getinput(int type){
		String []stri={"Year(at least 1900)","Month","Day"};		
		int temp=0;
		scanner = new java.util.Scanner(System.in);
		for(int i=0;i<type;){
			System.out.println("Please input "+stri[i]);
			temp=scanner.nextInt();
			if(i==0&&temp>=1900)
				input_year=temp;
			else if(i==1&&temp<13&&temp>0)
				input_month=temp;
			else if(i==2&&temp>0&&temp<32)
				input_day=temp;
			else
				continue;
			i++;
		}		
	}
}




class calendar {
	private int year;
	private int month;
	private int day;
	
	//init
	public calendar(int year,int month,int day){
		this.year=year;
		this.month=month;
		this.day=day;
	}
	
	//檢測日期
	public boolean Check_Date(){
		if( (day>getmonthday(year,month))!=true)
			return true;
		System.out.println("Date is illegal.");
		return false;
	}
	
	//判斷閏年
	public boolean Isleap(int year)
	{
		return ((year%4==0&&year%100!=0)||(year%400==0));
	}
	
	//計算當年前總共有多少天
	public int yearday()
	{
		int Countday=0;
		int baseyear=1900;//以1900為基準
		for(;year>baseyear;baseyear++)
			Countday+=365+((Isleap(baseyear)==true)?1:0);
		return Countday;
	}

	
	//指定月份前的天數總和
	public int monthday()
	{
		int cntday=0;
		for(int i=1;i<month;i++)
			cntday+=getmonthday(year,i);
		return cntday;
	}
	
	//當月月份天數
	public int getmonthday(int year,int month)
	{
		int Month[]={31,28,31,30,31,30,31,31,30,31,30,31};
		return Month[month-1]+((Isleap(year)==true&&month==2)?1:0);
	}
	
	//得到當月1號星期幾
	public int getWeek()
	{
		int allday=yearday()+monthday();
		int week=(allday+1)%7;
		return week;
	}
	
	//指定日期的星期幾
	public int GetDateWeek()
	{
		return (getWeek()+day-1)%7;
	}
	
	//公式計算一年的六日天數
	public int GetWeekend()
	{
		int firstday=getWeek();
		return 104+((firstday>4&&firstday<7)?1:0)+((Isleap(year)==true&&firstday>3&&firstday<6)?1:0); 
	}
	
	//印日曆
	public void Print_Calendar()
	{
		int monthday=getmonthday(year,month)+1;
		int week=getWeek();//1號星期幾
		System.out.println("\n"+year+"/"+month);
		System.out.println("Sun Mon Tue Wed Thu Fri Sat");
		for(int i=0;i<week;i++)
			System.out.printf("%4s"," ");
		for(int i=1;i<monthday;i++)
		{
			System.out.print(" ");
			System.out.print(i);
			if(i<10) System.out.print(" ");
			week=(++week)%7;
			if(week==0)
				System.out.println("");
			else
				System.out.print(" ");
		}
	}
	
}