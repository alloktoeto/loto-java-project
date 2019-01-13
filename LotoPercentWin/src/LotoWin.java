import java.util.Scanner;

public class LotoWin {

	
	private static double[] res;
	private final static int count = 6;
	private final static int min = 1;
	private final static int max = 37;
	
	
	
	public static void main(String[] args) {
		
		DataManager dataManager = new DataManager();
		
		int[][] data = dataManager.GetData();
		
		int[] userInput = GetUserInput();
		
		int countOfDataItems = GetAllItemsCount(data);
		
		int countOfUserItems;
		
		double[] res = new double[userInput.length];
		
		for(int i = 0; i < userInput.length; i++)
		{
			countOfUserItems = CompareNum(data, userInput[i]);
			if(countOfUserItems == 0)
				countOfUserItems = 1;
			
			res[i] = GetPersentOfItem(countOfDataItems, countOfUserItems);
		}
		
		double win = CalculatePercentOfWin(res.length-1, res);
		
		System.out.println("The persent of win is: " + win + "%");
	}
	
	
	private static int[] GetUserInput()
	{
		Scanner input = new Scanner(System.in);
		
		int [] userInput = new int[count];
		
		int i = 0;
		
		int temp;
		
		while(i < count)
		{
			System.out.println("Enter the " + (i+1) + "-st number");
			try
			{
				temp = input.nextInt();
			}
			catch (Exception e) {
				System.out.println("Error occurred in user input!");
				continue;
			}
			
			if(temp <= max && temp >= min)
			{
				userInput[i] = temp;
				i ++;
			}
			else
			{
				System.out.println("The input is not valid!");
				continue;
			}

		}
		
		input.close();
		
		return userInput;
	}
	
	
	private static int CompareNum(int[][] data, int num)
	{
		int countOfItemInData = 0;
		for (int i=0;i<data.length;i++) {
			for(int j=0;j<data[i].length;j++) {
				if(data[i][j] == num)
					countOfItemInData++;
			}
		}

		return countOfItemInData;
	}
	
	
	private static int GetAllItemsCount(int[][] data)
	{
		int countAll = 0;
		for (int i=0;i<data.length;i++) {
			countAll+=data[i].length;
		}
		return countAll;
	}
	
	
	private static double GetPersentOfItem(int allItems, int numOfItemsInArray)
	{
		return (double) numOfItemsInArray / allItems;
	}
	
	
	private static double CalculatePercentOfWin(int index, double[] resArr)
	{
		if(index == 0)
			return resArr[index];
		else
			return resArr[index] * CalculatePercentOfWin((index-1), resArr);
	}

}
