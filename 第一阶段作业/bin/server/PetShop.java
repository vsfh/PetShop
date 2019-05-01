package server;

import java.io.File;
import java.io.*;
import java.util.ArrayList;

public class PetShop implements Serializable{
	int sum;
	int dogsum;
	int catsum;
	String mainpath;
	String cancel;
	ArrayList<Cat> cats;
	ArrayList<Dog> dogs;
    private static PetShop instance;
    public static PetShop getInstance(){
        if(instance==null){
            instance=new PetShop();
        }
        return instance;
    }
	public PetShop() {
		cats = new ArrayList<>();
		dogs = new ArrayList<>();
		this.cancel = cancel;
		mainpath = "D:\\test1.txt";
		sum = 0;
		dogsum = 0;
		catsum = 0;
		/*try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // ½¨Á¢Ò»¸öÊäÈëÁ÷¶ÔÏóreader
			buf = new BufferedReader(reader);
			int i=0;
			while ((str = buf.readLine()) != null) {
				System.out.println(str);
				String[] arr = str.split("\\s+");
				if(i==0)
					sum=1;
				else
					sum++;
					//sum = Integer.valueOf(arr[0]);
				i++;
				System.out.println(str);
				//i++;
			}

			buf.close();
			reader.close();
			sum++;
			System.out.println("sum:" + sum);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	private void xulie() throws IOException{

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(mainpath));
			oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			oos.close();
		}
        System.out.println("xuliechenggong");

	}

	public void check1(DataOutputStream out) throws IOException {//¹· -1 -1 -1




	    out.writeUTF(Integer.toString(sum));
		for (Dog tdog : dogs) {
			out.writeUTF("¹· " + tdog.getAll());
		}
		for (Cat tcat : cats) {
			out.writeUTF("Ã¨ " + tcat.getAll());
		}


	}


	public void add(String data) {//¹· ¿Ö¾åÄ§Íõ ×Ï 2222
		String[] arr = data.split("\\s+");
		if (arr[0].equals("¹·")) {
			dogs.add(new Dog(arr[1], arr[2], arr[3]));
			dogsum++;
		} else if (arr[0].equals("Ã¨")) {
			cats.add(new Cat(arr[1], arr[2], arr[3]));
			catsum++;
		}

		sum++;
		try {
			xulie();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void check(String data, DataOutputStream out) throws IOException {//¹· -1 -1 -1

		String[] target = data.split("#");
		ArrayList<String> match = new ArrayList<>();
		int i=0;
		if (target[0].equals("¹·")) {
			for (Dog tdog : dogs) {
				if ((target[1].equals("-1") || tdog.getName().contains(target[1])) && (target[2].equals("-1") || tdog.getColor().contains(target[2])) && (target[3].equals("-1") || tdog.getAll().contains(target[3]))) {
					match.add("¹· "+tdog.getAll());
					i++;
				}

			}
		}else if (target[0].equals("Ã¨")) {
			for (Cat tcat : cats) {
				if ((target[1].equals("-1") || tcat.getName().contains(target[1])) && (target[2].equals("-1") || tcat.getColor().contains(target[2])) && (target[3].equals("-1") || tcat.getAll().contains(target[3]))) {
					match.add("Ã¨ "+tcat.getAll());
					i++;
				}

			}
		}
		out.writeUTF(String.valueOf(i));
		for (String tmatch : match) {
			out.writeUTF(tmatch);
		}

	}


	public void modify(String value, String data) throws IOException {//-1 -1 -1
		{
			String[] arr = value.split("\\s+");//Ô­À´
			String[] change = data.split("\\s+");//¸Ä±ä
			if (arr[0].equals("¹·")) {
				for (int i = 0; i < dogsum; i++) {
					if (dogs.get(i).getAll().equals(arr[1] + " " + arr[2] + " " + arr[3])) {
						dogs.get(i).setName(change[1]);
						dogs.get(i).setColor(change[2]);
						dogs.get(i).setAge(change[3]);
					}
				}
			} else if (arr[0].equals("Ã¨")) {
				for (int i = 0; i < catsum; i++) {
					if (cats.get(i).getAll().equals(arr[1] + " " + arr[2] + " " + arr[3])) {
						cats.get(i).setName(change[1]);
						cats.get(i).setColor(change[2]);
						cats.get(i).setAge(change[3]);
					}
				}
			}


		}
		try {
			xulie();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String value, DataOutputStream out) throws IOException {//¹· -1 -1 -1


		String[] arr = value.split("\\s+");

		if (arr[0].equals("¹·")) {
			for (int i = 0; i < dogsum; i++) {
				if (dogs.get(i).getAll().equals(arr[1] + " " + arr[2] + " " + arr[3])) {
					dogs.remove(i);
					dogsum--;
				}
			}
		}

		else if (arr[0].equals("Ã¨")) {
			for (int i = 0; i < catsum; i++) {
				if (cats.get(i).getAll().equals(arr[1] + " " + arr[2] + " " + arr[3])) {
					cats.remove(i);
					catsum--;
				}
			}
		}
		sum--;
		try {
			xulie();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

