import java.io.*; // import for BufferedReader and IOException
import java.util.ArrayList;
import java.util.HashMap; // hashMap to store the addresses
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		// use buffered reader because scanner is slow
		String line;
		BufferedReader input = new BufferedReader(new FileReader("input.txt"));
		line = input.readLine();
		String[] array = new String[2];

		int velocity_aux;
		Map<String, List<Integer>> entries = new HashMap<String, List<Integer>>();
		List<String> unsortedArray = new ArrayList<String>();

		// -------- READ THE INPUT ---------------------------------------------------------------
		int i = 0;
		int size = 0;
		while (line != null) {
			array = line.split(" ");
			//System.out.println("1st arg : " + array[0] + " 2nd arg : " + array[1]);
			velocity_aux = Integer.parseInt(array[1]);

			if (entries.containsKey(array[0])) {
				i = 0;
				List<Integer> list_velocity = entries.get(array[0]);
				// i did this to add the values on decreasing order later will be easier to print
				while (i != list_velocity.size() && list_velocity.get(i) > velocity_aux)
					i++;
				list_velocity.add(i, velocity_aux);
			} else {
				List<Integer> newList = new ArrayList<Integer>();
				newList.add(velocity_aux);
				entries.put(array[0], newList);
				unsortedArray.add(array[0]);
			}
			//System.out.println(line); // DEBUG
			line = input.readLine();
			size++;
		}
		
		//----------FOR DEBUG ------------------------------------
		//		i = 0;
		//		size = unsortedArray.size();
		//		while (i < size)
		//		{
		//			System.out.print(unsortedArray.get(i));
		//			for (Integer velocity3 : entries.get(unsortedArray.get(i)))
		//				System.out.print(" " + velocity3);
		//			System.out.println();
		//			i++;
		//		}
		//--------------------------------------------------------
		
		input.close();
		//-------------------- SORTING THE CAR REGISTRATIONS BY VELOCITY ---------------------------
		int position = 0;
		i = 0; 				// i used the same aux index reset its value
		String[] sorted = new String[size];

		List<Integer> velocities_1;
		List<Integer> velocities_2;
		int aux1;
		int aux2;
		int size1;
		int size2;
		boolean end = false;

		for (String car : unsortedArray) {
			velocities_1 = entries.get(car);
			size1 = velocities_1.size(); //size of the number of velocities that this car has
			for (String car2 : unsortedArray) {
				if (!car.equals(car2)) {
					velocities_2 = entries.get(car2);
					size2 = velocities_2.size();  //size of the number of velocities that this car has
					aux1 = velocities_1.get(i);	  //velocity car 1
					aux2 = velocities_2.get(i);   //velocity car 2
					//System.out.println("carro 1 : " + car + " carro 2 :" + car2);
					while (aux1 == aux2) {
						i++;
						if (i == size1 || i == size2) // see if it reaches the end of the velocities
						{
							end = true;
							if (i != size2)
								position++;
							break;
						}else {
							aux1 = velocities_1.get(i);
							aux2 = velocities_2.get(i);
						}
					}
					if (!end && aux1 < aux2) { //decreasing order
						position++;
					}
					//System.out.println("position :" + position);
					i = 0;
					end = false;
				}
			}
			sorted[position] = car;
			position = 0;
		}
		//-------------------------------PRINTING RESULTS-------------------------------------------------
		i = 0;
		size = sorted.length;
		while (i < size)
		{
			System.out.print(sorted[i]);
			for (Integer velocity : entries.get(sorted[i]))
				System.out.print(" " + velocity);
			System.out.println();
			i++;
		}
	}
}
